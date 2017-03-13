package com.login;

import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.Seven.Shop.R;
import com.google.gson.Gson;
import com.http.util.HttpUtil1;
import com.http.util.IsSuccess;
import com.shop.entity.ActivityCollect;
import com.squareup.okhttp.Request;
import com.view.personal.User;
import com.view.personal.tab3;

import android.R.string;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class RegisterActivity extends Activity {

	Button registBtn;
	EditText username;
	EditText password;
	EditText sex;
	int headflag=0;
	View headswap;
	ImageView imageView;  //ͷ��
	SharedPreferences preferences;
	Bitmap myBitmap;
	String path = "//sdcard//shop//";// sd·��
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);  //��titleBar
		setContentView(R.layout.register);
		
		ActivityCollect.addActivity(this);

		inti();
	}

	private void inti() {
		// TODO Auto-generated method stub
		registBtn=(Button) findViewById(R.id.regist_registbth);
		username=(EditText) findViewById(R.id.regist_nameText);
		password=(EditText) findViewById(R.id.regist_passText);
		sex=(EditText) findViewById(R.id.regist_sexText);
		headswap=findViewById(R.id.register_headswap);
		imageView=(ImageView) findViewById(R.id.registImg);


		headswap.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				
				// TODO Auto-generated method stub
				if(username.getText().toString().isEmpty()){
					Toast.makeText(RegisterActivity.this, "�������û���!", 1).show();
				}else{

					final AlertDialog.Builder builder = new AlertDialog.Builder(
							RegisterActivity.this);
					builder.setTitle("ѡ�����");
					builder.setNegativeButton("����",
							new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							Intent intent1 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
							intent1.putExtra(MediaStore.EXTRA_OUTPUT,
									Uri.fromFile(new File(Environment.getExternalStorageDirectory(), "head.jpg")));
							startActivityForResult(intent1, 2);// ����ForResult��
							dialog.dismiss();
						}
					});
					builder.setPositiveButton("���",
							new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							Intent intent = new Intent(
									Intent.ACTION_PICK,
									android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
							startActivityForResult(intent, 1);

						}
					});
					AlertDialog alert = builder.create();
					alert.show();		


				}
			}
		});


		preferences=this.getSharedPreferences("loginState", Context.MODE_PRIVATE);

		registBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if(username.getText().toString().isEmpty()){
					Toast.makeText(RegisterActivity.this, "�������û���!", 1).show();
				}else if(password.getText().toString().isEmpty()){
					Toast.makeText(RegisterActivity.this, "����������!", 1).show();
				}else if (sex.getText().toString().isEmpty()) {
					Toast.makeText(RegisterActivity.this, "�������Ա�!", 1).show();
				}else{
					
					User user = new User();
					user.setUser_name(username.getText().toString());
					user.setPassword(password.getText().toString());
					user.setSex(sex.getText().toString());
					
					
					HttpUtil1.postAsync("register.php", user, callBack);
					
					
						
					
				}
				
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
            	
            	
            	if (isSuccess.getSuccess() == 0){
					System.out.println("asdfffffffasfasfsafasfadsfasfasf");
					Toast.makeText(RegisterActivity.this, "ע��ɹ�!", 1).show();
					
					Editor editor = preferences.edit();  
					editor.putBoolean("loginState", true);  
					editor.commit();
					
					if(headflag==1){
						setPicToView(myBitmap);// ������SD����
						headflag=0;
					}else {
						
					}
					
					Intent intent=getIntent();
					Bundle bundle=new Bundle();
					
					bundle.putString("name", username.getText().toString());//���Ҫ���ظ�ҳ��1������
					intent.putExtras(bundle);
					
					setResult(123, intent);
					
					
					finish();
            	}else if (isSuccess.getSuccess() == 1){
            		Toast.makeText(RegisterActivity.this, "�������������ԭ����ע��ʧ��", 1).show();
            	}else {
            		Toast.makeText(RegisterActivity.this, "�û��Ѵ���", 1).show();
            	}
			}
			
			@Override
			public void requestFailure(Request request, IOException e) {
				// TODO Auto-generated method stub
				
			}
		};

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (requestCode) {
		case 1:
			if (resultCode == RESULT_OK) {
				cropPhoto(data.getData());// �ü�ͼƬ
			}

			break;
		case 2:
			if (resultCode == RESULT_OK) {  //����ͼƬ
				File temp = new File(Environment.getExternalStorageDirectory() + "/head.jpg");
				cropPhoto(Uri.fromFile(temp));// �ü�ͼƬ
			}

			break;
		case 3:
			if (data != null) {
				Bundle extras = data.getExtras();
				myBitmap = extras.getParcelable("data");
				if (myBitmap != null) {
					/**
					 * �ϴ�����������
					 */
//					setPicToView(myBitmap);// ������SD����
					imageView.setImageBitmap(myBitmap);// ��ImageView��ʾ����
					headflag=1;
				}
			}
			break;
		default:
			break;

		}
		super.onActivityResult(requestCode, resultCode, data);
	}

	/**
	 * ����ϵͳ�Ĳü�����
	 * 
	 * @param uri
	 */
	public void cropPhoto(Uri uri) {
		Intent intent = new Intent("com.android.camera.action.CROP");
		intent.setDataAndType(uri, "image/*");
		intent.putExtra("crop", "true");
		// aspectX aspectY �ǿ�ߵı���
		intent.putExtra("aspectX", 1);
		intent.putExtra("aspectY", 1);
		// outputX outputY �ǲü�ͼƬ���
		intent.putExtra("outputX", 150);
		intent.putExtra("outputY", 150);
		intent.putExtra("return-data", true);
		startActivityForResult(intent, 3);
	}

	private void setPicToView(Bitmap mBitmap) {
		String sdStatus = Environment.getExternalStorageState();
		if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) { // ���sd�Ƿ����
			return;
		}
		FileOutputStream b = null;
		
		String userpath=path+username.getText().toString()+"//";	
		
		Toast.makeText(RegisterActivity.this, userpath, 1).show();
		
		File file = new File(userpath);
		
		if(!file.exists())
		file.mkdirs();// �����ļ���
		
		
		
		String fileName = userpath + "head.jpg";// ͼƬ����
		try {
			b = new FileOutputStream(fileName);
			mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, b);// ������д���ļ�
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				// �ر���
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
		getMenuInflater().inflate(R.menu.register, menu);
		return true;
	}

}
