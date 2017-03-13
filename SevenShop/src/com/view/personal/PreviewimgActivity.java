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
	private static String path = "//sdcard//shop//";// sd·��
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);  
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.previewimg); 
		
		ActivityCollect.addActivity(this);

		//���ñ���Ϊĳ��layout
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.drawable.titleback);

		init();


	}

	private void init() {
		// TODO Auto-generated method stub
		titleName=(TextView) findViewById(R.id.titleName2);  // ������
		titleName.setText("ͷ��Ԥ��");
		View backCenter= findViewById(R.id.backCenter);   //���ؼ�
		backCenter.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				finish();

			}
		});

		String IMAGES_PATH= path+tab3.username.getText();
		
		ImageView imageView=(ImageView) findViewById(R.id.previewHead);
		Bitmap myBitmap = BitmapFactory.decodeFile(IMAGES_PATH + "//head.jpg");// ��SD������ͷ��ת����Bitmap
		
		if (myBitmap != null) {
			@SuppressWarnings("deprecation")
			Drawable drawable = new BitmapDrawable(myBitmap);// ת����drawable
			imageView.setImageDrawable(drawable);
		} else {
			/**
			 * ���SD����û������Ҫ�ӷ�����ȡͷ��ȡ������ͷ���ٱ�����SD��
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
	public boolean onTouch(View arg0, MotionEvent arg1) {      //����Ļ����
		// TODO Auto-generated method stub
		return false;
	}

}
