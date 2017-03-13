package com.view.personal;

import android.os.Bundle;

import com.Seven.Shop.R;
import com.shop.entity.ActivityCollect;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.TextView;

public class PreviewimgActivity extends Activity implements OnTouchListener {

	TextView titleName;
	private static String path = "//sdcard//shop//";// sd路径
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);  
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.previewimg); 
		
		ActivityCollect.addActivity(this);

		//设置标题为某个layout
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.drawable.titleback);

		init();


	}

	private void init() {
		// TODO Auto-generated method stub
		titleName=(TextView) findViewById(R.id.titleName2);  // 标题栏
		titleName.setText("头像预览");
		View backCenter= findViewById(R.id.backCenter);   //返回键
		backCenter.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				finish();

			}
		});

		String IMAGES_PATH= path+tab3.username.getText();
		
		ImageView imageView=(ImageView) findViewById(R.id.previewHead);
		Bitmap myBitmap = BitmapFactory.decodeFile(IMAGES_PATH + "//head.jpg");// 从SD卡中找头像，转换成Bitmap
		
		if (myBitmap != null) {
			@SuppressWarnings("deprecation")
			Drawable drawable = new BitmapDrawable(myBitmap);// 转换成drawable
			imageView.setImageDrawable(drawable);
		} else {
			/**
			 * 如果SD里面没有则需要从服务器取头像，取回来的头像再保存在SD中
			 * 
			 */
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.previewimg, menu);
		return true;
	}

	@Override 
	public boolean onTouchEvent(MotionEvent event) { 
		if (event.getAction() == MotionEvent.ACTION_DOWN) { 
			finish();
		}
		return false;
	}

	@Override
	public boolean onTouch(View arg0, MotionEvent arg1) {      //按屏幕返回
		// TODO Auto-generated method stub
		return false;
	}

}
