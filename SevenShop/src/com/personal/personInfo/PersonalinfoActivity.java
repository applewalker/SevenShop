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


	private ImageView imageView; // ͷ��ͼƬ
	TextView titleName;
	View bac;   //�������ı���
	Bitmap myBitmap;
	TextView username;
	TextView sex;
	SharedPreferences preferences;
	Editor editor;
	String[] sexArry = new String[] { "Ů", "��" };
	boolean loginState;
	private BaseAnimatorSet bas_in;
	private BaseAnimatorSet bas_out;
	private static String path = "//sdcard//shop//";// sd·��
	@Override

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);  
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.personalinfo); 	

		ActivityCollect.addActivity(this);

		//���ñ���Ϊĳ��layout
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.drawable.titleback);
		init();
	}

	private void init() {
		// TODO Auto-generated method stub
		titleName=(TextView) findViewById(R.id.titleName2);  // ������
		titleName.setText("������Ϣ");

		preferences=this.getSharedPreferences("loginState", Context.MODE_PRIVATE);//��ʼ��preferences

		View backCenter= findViewById(R.id.backCenter);   //���ؼ�
		backCenter.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				finish();

			}
		});

		View editusername=findViewById(R.id.personal_editusername); //�û��ǳ��޸İ�ť	
		username=(TextView) findViewById(R.id.personal_username);//��ȡ��ǰ����

		Intent intent=getIntent();
		username.setText(intent.getStringExtra("name"));  //��ȡ���ĵ�����




		editusername.setOnClickListener(new OnClickListener() {  //�����û���

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(preferences.getBoolean("loginState", false) == true){
					Intent intent=new Intent(PersonalinfoActivity.this,EditusernameActivity.class);						   
					intent.putExtra("name",  username.getText().toString());
					startActivityForResult(intent, 0);
				}else{
					Toast.makeText(PersonalinfoActivity.this, "���ȵ�¼", 1).show();
				}
			}
		});



		String IMAGES_PATH= path+username.getText();

		imageView=(ImageView) findViewById(R.id.personal_headImg); 


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




		View headswap=findViewById(R.id.personal_headswap);
		headswap.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if(preferences.getBoolean("loginState", false) == true){



					final NormalDialog dialog = new NormalDialog(PersonalinfoActivity.this);

					dialog.content("ѡ��ͼƬ��Դ")//
					.style(NormalDialog.STYLE_TWO)//
					.titleTextSize(23)//
					.showAnim(bas_in)//
					.dismissAnim(bas_out)//
					.show();

					dialog.btnText("����","���");
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
									startActivityForResult(intent1, 2);// ����ForResult��
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
					Toast.makeText(PersonalinfoActivity.this, "���ȵ�¼", 1).show();
				}


			}

		});

		View sexswap=findViewById(R.id.sexswap);// �Ա��޸�
		sex=(TextView) findViewById(R.id.personal_sexText);

		sexswap.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				showSexChooseDialog();      //�޸��Ա�
			}
		});


	}



	private void showSexChooseDialog() {  

		AlertDialog.Builder builder = new AlertDialog.Builder(this);// �Զ���Ի���  
		builder.setSingleChoiceItems(sexArry, 0, new DialogInterface.OnClickListener() {// 2Ĭ�ϵ�ѡ��  

			@Override  
			public void onClick(DialogInterface dialog, int which) {// which�Ǳ�ѡ�е�λ��  
				// showToast(which+"");  
				sex.setText(sexArry[which]);  
				dialog.dismiss();// �����һ��item��ʧ�Ի��򣬲��õ��ȷ��ȡ��  
			}  
		});  
		builder.show();// �õ�������ʾ  
	}  

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
					setPicToView(myBitmap);// ������SD����

					imageView.setImageBitmap(myBitmap);// ��ImageView��ʾ����

					tab3.imageView.setImageBitmap(myBitmap);

				}
			}
			break;

		default:
			break;

		}

		if(resultCode == RESULT_FIRST_USER){          //�޸�����

			Bundle bundle = data.getExtras();
			String Name = bundle.getString("name");
			username.setText(Name);
			tab3.username.setText(Name);
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

		File file = new File(path);

		if (!file.exists()) {
			file.mkdir();
		}

		String userpath=path+username.getText().toString()+"//";	

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
		getMenuInflater().inflate(R.menu.personalinfo, menu);
		return true;
	}

}
