package com.eromanga.robot;



import java.util.ArrayList;
import java.util.List;

import com.Seven.Shop.R;
import com.eromanga.bean.Answer;
import com.eromanga.bean.TimeMessage;
import com.eromanga.bean.UserRequest;
import com.eromanga.utils.PostMsg;
import com.shop.entity.ActivityCollect;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;



public class RobotActivity extends Activity {
	private static ListView ChatListView;

	private static ChatListAdapter adapter;

	private EditText chat_text;

	private Button post;

	private static List<TimeMessage> ChatList = new ArrayList<TimeMessage>();
	
	TextView titleName;
	View bac;   //个人中心背景
	
	private static Context context;
	
	
	//适配器

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);  
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.activity_robot); 	

		ActivityCollect.addActivity(this);

		//设置标题为某个layout
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.drawable.titleback);
		
		ActivityCollect.addActivity(this);
		
		init();
		context = getApplicationContext();

	}
	private static Handler mHandler = new Handler()
	{
		public void handleMessage(android.os.Message msg)
		{
			switch (msg.what) {
			case 0:
				ChatList.add((TimeMessage) msg.obj);
				adapter.notifyDataSetChanged();
				ChatListView.smoothScrollToPosition(ChatList.size()-1);
				break;
			case 1:
				Toast.makeText(context, "无法连接到服务器",
					     Toast.LENGTH_SHORT).show();
				break;
			case 2:
				Toast.makeText(context,"无网络可用", 
						Toast.LENGTH_LONG).show();
					   
				
				break;
			default:
				break;
			}
			
			//			ChatMessage from = (ChatMessage) msg.obj;
			//			mDatas.add(from);
			//			mAdapter.notifyDataSetChanged();
			//			mChatView.setSelection(mDatas.size() - 1);
		};
	};
	private void init(){
		ChatListView = (ListView)findViewById(R.id.ChatListView);
		//ChatListView.setEnabled(false);
		titleName=(TextView) findViewById(R.id.titleName2);
		titleName.setText("客服机器人");
		bac=findViewById(R.id.backCenter);
		bac.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		
		chat_text = (EditText)findViewById(R.id.chat_text);
		post = (Button)findViewById(R.id.post);
		post.setOnClickListener(new OnClickListener() {			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				final String msg = chat_text.getText().toString();
				final UserRequest ur = new UserRequest(msg);
				if(!msg.isEmpty())
				{
					Message message = Message.obtain();
					message.what = 0;
					message.obj = ur;
					mHandler.sendMessage(message);
					chat_text.setText("");
					new Thread()
					{
						TimeMessage answer;
						Message message = Message.obtain();
						public void run()
						{
							if(isNetworkAvailable())
							{
								try
								{
									answer = PostMsg.dopost(ur);
								} catch (Exception e)
								{
									Log.d("MainActivity","false thread");
								}
								if(answer != null)
								{
									message.what = 0;
									message.obj = answer;
									mHandler.sendMessage(message);
								}
								else
								{
									message.what = 1;
									mHandler.sendMessage(message);
									
								}
							}
							else
							{
								message.what = 2;
								mHandler.sendMessage(message);
							}
								
						};
					}.start();

				}
			}
		});
		adapter = new ChatListAdapter(this, ChatList);
		ChatListView.setAdapter(adapter);
	}
	private boolean isNetworkAvailable() {
		// 得到网络连接信息
		ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		// 去进行判断网络是否连接
		if (manager.getActiveNetworkInfo() != null) {
			return manager.getActiveNetworkInfo().isAvailable();
		}
		return false;
	}
	public static void MsgofWait(){
		Message message = Message.obtain();
		Answer answer = new Answer();
		answer.setText("等下哦，正在下载信息...");
		message.what = 0;
		message.obj = answer;
		mHandler.sendMessage(message);
	}

}
