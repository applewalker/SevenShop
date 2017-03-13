package com.login;

import android.os.Bundle;
import android.os.Environment;

import java.io.File;
import java.io.IOException;

import com.Seven.Shop.R;
import com.cart.tab2;
import com.google.gson.Gson;
import com.http.util.HttpUtil1;
import com.http.util.IsSuccess;
import com.shop.entity.ActivityCollect;
import com.squareup.okhttp.Request;
import com.view.personal.User;
import com.view.personal.tab3;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends Activity {

	Button login;
	EditText username;
	EditText pass;
	TextView register;
	String name;
	Editor editor;
	String password;
	ImageView loginImg;
	SharedPreferences preferences;
	private static String path = "//sdcard//shop//";// sd路径
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);  //无titleBar
		setContentView(R.layout.login);

		//ActivityCollect.finishAll();
		ActivityCollect.addActivity(this);
		

		init();

	}

	private void init() {
		// TODO Auto-generated method stub
		login=(Button) findViewById(R.id.login_loginbth);
		username=(EditText) findViewById(R.id.login_nameText);
		pass=(EditText) findViewById(R.id.login_passText);
		register=(TextView) findViewById(R.id.login_register);
		loginImg=(ImageView) findViewById(R.id.loginImg);

		preferences=this.getSharedPreferences("loginState", Context.MODE_PRIVATE);

		login.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub

				if(username.getText().toString().isEmpty()){
					Toast.makeText(LoginActivity.this, "请输入用户名!", 1).show();
				}else if(pass.getText().toString().isEmpty()){
					Toast.makeText(LoginActivity.this, "请输入密码!", 1).show();
				}else{

					name=username.getText().toString();					
					password=pass.getText().toString();
					User user = new User();
					user.setUser_name(name);
					user.setPassword(password);
					HttpUtil1.postAsync("login.php", user, callBack);					

				}
			}
		});

		register.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(LoginActivity.this,RegisterActivity.class);
				startActivityForResult(intent, 0);
			}
		});

	}
	HttpUtil1.DataCallBack callBack = new HttpUtil1.DataCallBack() {

		@Override
		public void requestSuccess(String result) throws Exception {
			// TODO Auto-generated method stub
			System.out.println(result);
			Gson gson = new Gson();
			IsSuccess isSuccess = gson.fromJson(result, IsSuccess.class);


			if (isSuccess.getSuccess() == -1){

				Toast.makeText(LoginActivity.this, "密码或用户名有错", 1).show();


			}else {
				Toast.makeText(LoginActivity.this, "登录成功", 1).show();
				editor = preferences.edit();  
				editor.putBoolean("loginState", true); 
				editor.putString("username", name);
				editor.putInt("userid", isSuccess.getSuccess());
				editor.commit();
				
				name=username.getText().toString();
				
				

				String IMAGES_PATH= path+username.getText();

				Bitmap myBitmap = BitmapFactory.decodeFile(IMAGES_PATH + "//head.jpg");// 从SD卡中找头像，转换成Bitmap
				if (myBitmap != null) {
					@SuppressWarnings("deprecation")
					Drawable drawable = new BitmapDrawable(myBitmap);// 转换成drawable
					loginImg.setImageDrawable(drawable);
					tab3.imageView.setImageDrawable(drawable);
				} else {
					/**
					 * 如果SD里面没有则需要从服务器取头像，取回来的头像再保存在SD中
					 * 
					 */
				}
				//tab2.TAB2.upData(null);
			    finish();
			}
		}

		

		@Override
		public void requestFailure(Request request, IOException e) {
			// TODO Auto-generated method stub

		}
	};
	
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(resultCode == 123){          //修改名字

			Bundle bundle = data.getExtras();
			name = bundle.getString("name");
			username.setText(name);

			
			String IMAGES_PATH= path+username.getText();

			Bitmap myBitmap = BitmapFactory.decodeFile(IMAGES_PATH + "//head.jpg");// 从SD卡中找头像，转换成Bitmap
			if (myBitmap != null) {
				@SuppressWarnings("deprecation")
				Drawable drawable = new BitmapDrawable(myBitmap);// 转换成drawable
				loginImg.setImageDrawable(drawable);
				tab3.imageView.setImageDrawable(drawable);
			} else {
				/**
				 * 如果SD里面没有则需要从服务器取头像，取回来的头像再保存在SD中
				 * 
				 */
			}
		}

		super.onActivityResult(requestCode, resultCode, data);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

}
