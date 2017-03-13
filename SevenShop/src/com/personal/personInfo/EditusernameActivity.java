package com.personal.personInfo;

import com.Seven.Shop.R;
import com.shop.entity.ActivityCollect;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class EditusernameActivity extends Activity {

	TextView titleName;
	EditText editname;
	Button edit_confirm;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);  
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.editusername); 			

		//设置标题为某个layout
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.drawable.titleback);
		
		ActivityCollect.addActivity(this);
		
		
		init();
	}

	private void init() {
		// TODO Auto-generated method stub
		titleName=(TextView) findViewById(R.id.titleName2);  // 标题栏
		titleName.setText("淘宝昵称");
		
		
		View backCenter= findViewById(R.id.backCenter);   //返回键
		backCenter.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				finish();

			}
		});
		
		editname=(EditText) findViewById(R.id.edit_nameText);
		Intent intent = this.getIntent();
		editname.setText(intent.getStringExtra("name"));
		
		editname.setSelection(editname.getText().length());  //设置光标位置
		
		edit_confirm=(Button) findViewById(R.id.edit_confirm);
		edit_confirm.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				Intent intent = getIntent();
				Bundle bundle = intent.getExtras();
				bundle.putString("name", editname.getText().toString());//添加要返回给页面1的数据
				intent.putExtras(bundle);
				setResult(RESULT_FIRST_USER, intent);
				finish();
				
			}
		});
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.editusername, menu);
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
