package com.eromanga.robot;


import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import com.Seven.Shop.R;
import com.eromanga.bean.Answer;
import com.eromanga.bean.News;
import com.eromanga.bean.News.Article;
import com.eromanga.bean.Recipes.Course;
import com.eromanga.bean.Recipes;
import com.eromanga.bean.TimeMessage;
import com.eromanga.bean.Url;
import com.eromanga.bean.UserRequest;
import com.eromanga.utils.Prefs;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.text.Html;
import android.text.Html.ImageGetter;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ChatListAdapter extends BaseAdapter{
	private static final int Typecount = 2;
	private LayoutInflater mInflater;
	private List<TimeMessage> mChatList;

	public ChatListAdapter(Context context ,List<TimeMessage> ChatList) {
		// TODO Auto-generated constructor stub
		mInflater = LayoutInflater.from(context);
		mChatList = ChatList;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mChatList.size();
	}

	@Override
	public TimeMessage getItem(int position) {
		// TODO Auto-generated method stub
		return mChatList.get(position);
	}
	public int getItemType(int position) {
		//view type必须从0开始，而且必须连续。
		if (mChatList.get(position) instanceof UserRequest) 
			return Prefs.TYPE_USERREQUEST;  
		else if (mChatList.get(position) instanceof Answer)
		{ 			 
			if (mChatList.get(position) instanceof News) 
				return Prefs.TYPE_NEWS;        
			else if (mChatList.get(position) instanceof Recipes) 
				return Prefs.TYPE_RECIPES;       
			else if (mChatList.get(position) instanceof Url) 
				return Prefs.TYPE_URL;   
			return Prefs.TYPE_ANSWER;
		}
		return 1;
	}
	public int getItemViewType(int position) {
		//view type必须从0开始，而且必须连续。
		if (mChatList.get(position) instanceof UserRequest) 
			return 0;  
		else if (mChatList.get(position) instanceof Answer)
		{ 	   
			return 1;
		}
		return 1;
	}
	public int getViewTypeCount(){
		return Typecount;
	}
	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		TimeMessage temp = mChatList.get(position);
		Holder mHolder = null;
		int viewtype = getItemViewType(position);
		int itemtype = getItemType(position);
		if(convertView == null)
		{
			mHolder = new Holder();
			switch (viewtype) {
			case 0:
				convertView = mInflater.inflate(R.layout.chat_right_msg, null);
				mHolder.content = (TextView) convertView.findViewById(R.id.user_text);
				mHolder.date = (TextView) convertView.findViewById(R.id.right_time);				
				break;
			case 1:		
				convertView = mInflater.inflate(R.layout.chat_left_answer,null);
				mHolder.content = (TextView) convertView.findViewById(R.id.answer_text);
				mHolder.date = (TextView) convertView.findViewById(R.id.answer_time);			
				break;
			default:
				break;
			}
			convertView.setTag(mHolder);
		}							
		else
		{
			mHolder = (Holder) convertView.getTag();
		}
		switch (itemtype) {
		case Prefs.TYPE_USERREQUEST:
			UserRequest u = (UserRequest)temp;
			mHolder.content.setText(u.getInfo());	
			break;
		case Prefs.TYPE_ANSWER:
			Answer a = (Answer)temp;
			if(a != null)
			{
				mHolder.content.setText(a.getText());
			}
			break;
		case Prefs.TYPE_NEWS:
			News n = (News)temp;
			mHolder.content.setText(n.getChs());
			break;
		case Prefs.TYPE_RECIPES:
			Recipes r = (Recipes)temp;
			mHolder.content.setText(r.getChs());
			break;
		case Prefs.TYPE_URL:
			Url ur = (Url)temp;
			String html = "<body>"+ur.getText()+
					"<br><a href=\""+ur.getUrl()+"\">点击此处</a></body>";
			mHolder.content.setText(Html.fromHtml(html));
			break;
		default:
			break;
		}
		mHolder.content.setMovementMethod(LinkMovementMethod.getInstance());
		mHolder.date.setText(temp.getDate());
		return convertView;
	}
	private class Holder
	{
		public TextView date;
		public TextView content;
	}
//	private class MsgObj{
//		private CharSequence chs;
//		private TextView tv; 
//	}
//	Handler handler = new Handler() {
//		@Override
//		public void handleMessage(Message msg) {
//			// TODO Auto-generated method stub
//			if (msg.what == 1) {   
//				MsgObj m = (MsgObj)msg.obj;
//				m.tv.setText(m.chs);
//				m.tv.setMovementMethod(LinkMovementMethod.getInstance());
//
//			}
//			super.handleMessage(msg);
//
//		}
//	};
//
//	private void ThreadforMsg(final TextView tv,final String html) {   
//
//		Thread t = new Thread(new Runnable() {
//			Message msg = Message.obtain();
//
//			public void run() {
//				// TODO Auto-generated method stub			
//				ImageGetter imageGetter = new Html.ImageGetter() {  
//
//
//					public Drawable getDrawable(String source) {
//						// TODO Auto-generated method stub
//						URL url;
//						Drawable drawable = null;
//						try {
//							url = new URL(source);
//							drawable = Drawable.createFromStream(
//									url.openStream(), null);
//							drawable.setBounds(0, 0,
//									drawable.getIntrinsicWidth(),
//									drawable.getIntrinsicHeight());
//						} catch (MalformedURLException e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						} catch (IOException e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						}
//						return drawable;
//					}
//				};  
//				MsgObj mObj = new MsgObj();
//				CharSequence chs = Html.fromHtml(html, imageGetter, null);
//				mObj.chs = chs;
//				mObj.tv = tv;
//				msg.what = 1;			  
//				msg.obj = mObj;		
//				handler.sendMessage(msg);
//			}
//		});
//		t.start();
//	}

}


