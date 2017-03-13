package com.cart;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.impl.client.EntityEnclosingRequestWrapper;

import com.Seven.Shop.ChoujiangActivity;
import com.Seven.Shop.R;
import com.cart.bean.CarList;
import com.cart.bean.carBean;
import com.google.gson.Gson;
import com.http.util.HttpUtil1;
import com.shop.entity.ActivityCollect;
import com.shop.entity.Evaluate;
import com.shop.entity.EvaluateList;
import com.squareup.okhttp.Request;
import com.view.UI.FisrtActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ChecksuccessActivity extends Activity {

	TextView titleName;
	Button go_center;
	TextView success_text;
	EditText add_content;
	private SharedPreferences preferences;
	private String shopIndex;
	private int flag;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);  
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.checksuccess); 			
		//设置标题为某个layout	
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.drawable.title);

		ActivityCollect.addActivity(this);
		
		titleName=(TextView) findViewById(R.id.titleName1);
		titleName.setText("结算成功");
		
		add_content = (EditText) findViewById(R.id.add_content);
		
		success_text=(TextView) findViewById(R.id.success_price);//显示结算成功和金额
		Intent intent=getIntent();
		shopIndex = intent.getStringExtra("shopIndex");
		success_text.setText("结算成功，共消费："+intent.getStringExtra("check_price")+"元");

		go_center=(Button) findViewById(R.id.success_gabackcenter);
		go_center.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				if (add_content.getText().toString().equals("")){
					Toast.makeText(getApplication(), "评论为空", Toast.LENGTH_LONG).show();
				}else{
					
					

					preferences = getApplication().getSharedPreferences("loginState", Context.MODE_PRIVATE);
					String username = preferences.getString("username", "");

					List<Evaluate> l = new ArrayList<Evaluate>();
					if (shopIndex != null){
						flag = 1;
						String[] shopIndexs = shopIndex.split(",");
						for(String s : shopIndexs){
							int position = Integer.valueOf(s);
							carBean bean = ShoppingCanst.list.get(position);
							Evaluate evaluate = new Evaluate();
							evaluate.setEvaluate(add_content.getText().toString());
							evaluate.setUser_name(username);
							evaluate.setGoods_id(bean.getGoods_id());
							l.add(evaluate);
						}
					}else{
						flag = 2;
						Evaluate evaluate = new Evaluate();
						evaluate.setEvaluate(add_content.getText().toString());
						evaluate.setUser_name(username);
						evaluate.setGoods_id(ShoppingCanst.list.get(0).getGoods_id());
						l.add(evaluate);
					}
					EvaluateList evaluateList = new EvaluateList();
					evaluateList.setEvaluates(l);
					HttpUtil1.postAsync("addevaluate.php", evaluateList, new HttpUtil1.DataCallBack() {
						
						@Override
						public void requestSuccess(String result) throws Exception {
							// TODO Auto-generated method stub
							System.out.println(result);
							Toast.makeText(getApplication(), "评论成功", Toast.LENGTH_LONG).show();
							
							Intent intent = new Intent(getApplication(),ChoujiangActivity.class);
							startActivity(intent);
							
//							if (flag == 1){
//								tab2.TAB2.flashcart();
//								Intent intent=getIntent();
//								setResult(0000, intent);
//								finish();
//							}else if (flag ==2){
//								finish();
//								Intent intent = new Intent(ChecksuccessActivity.this,FisrtActivity.class);
//								startActivity(intent);
//							}
							
						}
						
						@Override
						public void requestFailure(Request request, IOException e) {
							// TODO Auto-generated method stub
							
						}
					});
					
				}
				
				
			}
		});
		
		//Intent intent1 = new Intent(ChecksuccessActivity.this, ChoujiangActivity.class);
		//startActivity(intent1);
	}


	public boolean onKeyDown(int keyCode, KeyEvent event) {  //监听返回案件

		if (keyCode == KeyEvent.KEYCODE_BACK
				&& event.getRepeatCount() == 0) {
			//tab2.TAB2.flashcart();
			Intent intent = new Intent(getApplication(),FisrtActivity.class);
			startActivity(intent);
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.checksuccess, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
}
