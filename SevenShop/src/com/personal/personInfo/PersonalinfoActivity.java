package com.personal.personInfo;

import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import com.Seven.Shop.R;
import com.flyco.animation.BaseAnimatorSet;
import com.flyco.dialog.listener.OnBtnClickL;
import com.flyco.dialog.widget.NormalDialog;
import com.shop.entity.ActivityCollect;
import com.view.personal.tab3;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class PersonalinfoActivity extends Activity {


	private ImageView imageView; // 头像图片
	TextView titleName;
	View bac;   //个人中心背景
	Bitmap myBitmap;
	TextView username;
	TextView sex;
	SharedPreferences preferences;
	Editor editor;
	String[] sexArry = new String[] { "女", "男" };
	boolean loginState;
	private BaseAnimatorSet bas_in;
	private BaseAnimatorSet bas_out;
	private static String path = "//sdcard//shop//";// sd路径
	@Override

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);  
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.personalinfo); 	

		ActivityCollect.addActivity(this);

		//设置标题为某个layout
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.drawable.titleback);
		init();
	}

	private void init() {
		// TODO Auto-generated method stub
		titleName=(TextView) findViewById(R.id.titleName2);  // 标题栏
		titleName.setText("个人信息");

		preferences=this.getSharedPreferences("loginState", Context.MODE_PRIVATE);//初始化preferences

		View backCenter= findViewById(R.id.backCenter);   //返回键
		backCenter.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				finish();

			}
		});

		View editusername=findViewById(R.id.personal_editusername); //用户昵称修改按钮	
		username=(TextView) findViewById(R.id.personal_username);//获取当前名字

		Intent intent=getIntent();
		username.setText(intent.getStringExtra("name"));  //获取中心的名字




		editusername.setOnClickListener(new OnClickListener() {  //更改用户名

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(preferences.getBoolean("loginState", false) == true){
					Intent intent=new Intent(PersonalinfoActivity.this,EditusernameActivity.class);						   
					intent.putExtra("name",  username.getText().toString());
					startActivityForResult(intent, 0);
				}else{
					Toast.makeText(PersonalinfoActivity.this, "请先登录", 1).show();
				}
			}
		});



		String IMAGES_PATH= path+username.getText();

		imageView=(ImageView) findViewById(R.id.personal_headImg); 


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




		View headswap=findViewById(R.id.personal_headswap);
		headswap.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if(preferences.getBoolean("loginState", false) == true){



					final NormalDialog dialog = new NormalDialog(PersonalinfoActivity.this);

					dialog.content("选择图片来源")//
					.style(NormalDialog.STYLE_TWO)//
					.titleTextSize(23)//
					.showAnim(bas_in)//
					.dismissAnim(bas_out)//
					.show();

					dialog.btnText("照相","相册");
					dialog.cornerRadius(15);
					dialog.titleTextColor(0x99f9923e);
					dialog.btnPressColor(0x50f9923e);
					dialog.setOnBtnClickL(
							new OnBtnClickL() {
								@Override
								public void onBtnClick() {
									Intent intent1 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
									intent1.putExtra(MediaStore.EXTRA_OUTPUT,
											Uri.fromFile(new File(Environment.getExternalStorageDirectory(), "head.jpg")));
									startActivityForResult(intent1, 2);// 采用ForResult打开
									dialog.dismiss();
								}
							},
							new OnBtnClickL() {
								@Override
								public void onBtnClick() {
									Intent intent = new Intent(
											Intent.ACTION_PICK,
											android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
									startActivityForResult(intent, 1);
									dialog.dismiss();
								}
							});
					
				}else{
					Toast.makeText(PersonalinfoActivity.this, "请先登录", 1).show();
				}


			}

		});

		View sexswap=findViewById(R.id.sexswap);// 性别修改
		sex=(TextView) findViewById(R.id.personal_sexText);

		sexswap.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				showSexChooseDialog();      //修改性别
			}
		});


	}



	private void showSexChooseDialog() {  

		AlertDialog.Builder builder = new AlertDialog.Builder(this);// 自定义对话框  
		builder.setSingleChoiceItems(sexArry, 0, new DialogInterface.OnClickListener() {// 2默认的选中  

			@Override  
			public void onClick(DialogInterface dialog, int which) {// which是被选中的位置  
				// showToast(which+"");  
				sex.setText(sexArry[which]);  
				dialog.dismiss();// 随便点击一个item消失对话框，不用点击确认取消  
			}  
		});  
		builder.show();// 让弹出框显示  
	}  

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (requestCode) {
		case 1:
			if (resultCode == RESULT_OK) {
				cropPhoto(data.getData());// 裁剪图片
			}

			break;
		case 2:
			if (resultCode == RESULT_OK) {  //储存图片
				File temp = new File(Environment.getExternalStorageDirectory() + "/head.jpg");
				cropPhoto(Uri.fromFile(temp));// 裁剪图片
			}

			break;
		case 3:
			if (data != null) {
				Bundle extras = data.getExtras();
				myBitmap = extras.getParcelable("data");
				if (myBitmap != null) {
					/**
					 * 上传服务器代码
					 */
					setPicToView(myBitmap);// 保存在SD卡中

					imageView.setImageBitmap(myBitmap);// 用ImageView显示出来

					tab3.imageView.setImageBitmap(myBitmap);

				}
			}
			break;

		default:
			break;

		}

		if(resultCode == RESULT_FIRST_USER){          //修改名字

			Bundle bundle = data.getExtras();
			String Name = bundle.getString("name");
			username.setText(Name);
			tab3.username.setText(Name);
		}

		super.onActivityResult(requestCode, resultCode, data);
	}

	/**
	 * 调用系统的裁剪功能
	 * 
	 * @param uri
	 */
	public void cropPhoto(Uri uri) {
		Intent intent = new Intent("com.android.camera.action.CROP");
		intent.setDataAndType(uri, "image/*");
		intent.putExtra("crop", "true");
		// aspectX aspectY 是宽高的比例
		intent.putExtra("aspectX", 1);
		intent.putExtra("aspectY", 1);
		// outputX outputY 是裁剪图片宽高
		intent.putExtra("outputX", 150);
		intent.putExtra("outputY", 150);
		intent.putExtra("return-data", true);
		startActivityForResult(intent, 3);
	}

	private void setPicToView(Bitmap mBitmap) {
		String sdStatus = Environment.getExternalStorageState();
		if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) { // 检测sd是否可用
			return;
		}
		FileOutputStream b = null;

		File file = new File(path);

		if (!file.exists()) {
			file.mkdir();
		}

		String userpath=path+username.getText().toString()+"//";	

		String fileName = userpath + "head.jpg";// 图片名字

		try {
			b = new FileOutputStream(fileName);
			mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, b);// 把数据写入文件
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				// 关闭流
				b.flush();
				b.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.personalinfo, menu);
		return true;
	}

}
