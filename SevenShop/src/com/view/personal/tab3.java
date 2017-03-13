package com.view.personal;

import java.io.File;
import com.Seven.Shop.R;
import com.eromanga.robot.RobotActivity;
import com.flyco.animation.BaseAnimatorSet;
import com.flyco.dialog.listener.OnBtnClickL;
import com.flyco.dialog.widget.NormalDialog;
import com.login.LoginActivity;
import com.personal.address.MyaddressActivity;
import com.personal.order.MyorderActivity;
import com.personal.personInfo.PersonalinfoActivity;
import com.view.UI.FisrtActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class tab3 extends Fragment{

	public Boolean loginState;
	private String name;
	private String password;
	public SharedPreferences preferences;
	public Editor editor;
	public static ImageView imageView; // ͷ��ͼƬ
	Bitmap myBitmap;	
	TextView titleName;
	public static TextView username;
	public static View view;
	public static int loginOK=0;
	private static String path = "//sdcard//shop//";// sd·��
	private BaseAnimatorSet bas_in;
	private BaseAnimatorSet bas_out;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		view=inflater.inflate(R.layout.tab03, container,false);
//		preferences = getActivity().getSharedPreferences("loginState", Context.MODE_PRIVATE);
//		if (preferences.getBoolean("loginState", false) == true) {
//
//			Init(view);
//			
//		}else{
//
//			Intent intent=new Intent(getActivity(), LoginActivity.class);
//			startActivityForResult(intent,0);
//			Init(view);
//		}
		
		Init(view);

		//ȡ����Ӧ��ֵ�����û�и�ֵ��˵����δд�룬��true��ΪĬ��ֵ  

		return view;
	}


	private void Init(View view) {
		// TODO Auto-generated method stub
		View myaddress=view.findViewById(R.id.address);  //�ҵĵ�ַ
		View personalinfo=view.findViewById(R.id.personalinfo);// ������Ϣ
		View myorder=view.findViewById(R.id.order);// �ҵĶ���
		View robot = view.findViewById(R.id.robot);
		View exit_layout=view.findViewById(R.id.exit);
		imageView=(ImageView) view.findViewById(R.id.headImg); // ͷ��imageview
		username=(TextView) view.findViewById(R.id.personalcenter_name);
 
		preferences = getContext().getSharedPreferences("loginState", Context.MODE_PRIVATE);
		username.setText(preferences.getString("username", "���½��ע��"));

		String IMAGES_PATH= path+username.getText();

		File imgFile = new File(IMAGES_PATH);
		if (!imgFile.exists()) {
			imgFile.mkdir();
		}

		//ͷ������
		Bitmap myBitmap = BitmapFactory.decodeFile(IMAGES_PATH+ "//head.jpg");// ��SD������ͷ��ת����Bitmap
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

		personalinfo.setOnClickListener(new OnClickListener() {   //������Ϣ

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if(preferences.getBoolean("loginState", false) == true){
					Intent intent=new Intent(getActivity(), PersonalinfoActivity.class);
					intent.putExtra("name", username.getText().toString());
					startActivityForResult(intent,0);
				}else{
					Toast.makeText(getActivity(), "�㻹û��¼!", 1).show();
				}
			}
		});

		myaddress.setOnClickListener(new OnClickListener() {   //�ҵĵ�ַ

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if(preferences.getBoolean("loginState", false) == true){
					Intent intent=new Intent(getActivity(), MyaddressActivity.class);
					startActivityForResult(intent,0);
				}else{
					Toast.makeText(getActivity(), "�㻹û��¼!", 1).show();
				}
			}
		});

		myorder.setOnClickListener(new OnClickListener() {   //�ҵĶ���

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if(preferences.getBoolean("loginState", false) == true){
					Intent intent=new Intent(getActivity(), MyorderActivity.class);
					startActivityForResult(intent,0);
				}else{
					Toast.makeText(getActivity(), "�㻹û��¼!", 1).show();
				}
			}
		});
		
		robot.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(preferences.getBoolean("loginState", false) == true){
					Intent intent=new Intent(getActivity(), RobotActivity.class);
					startActivityForResult(intent,0);
				}else{
					Toast.makeText(getActivity(), "�㻹û��¼!", 1).show();
				}
			}
		});


		imageView.setOnClickListener(new OnClickListener() {    //��ͷ��

			@Override
			public void onClick(View v) {           
				// TODO Auto-generated method stub

				Intent intent=new Intent(getActivity(),PreviewimgActivity.class);
				startActivityForResult(intent, 0);
			}
		});

		exit_layout.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(preferences.getBoolean("loginState", false) == true){
					
					final NormalDialog dialog = new NormalDialog(getContext());

					dialog.content("ȷ��Ҫע����")//
					.style(NormalDialog.STYLE_TWO)//
					.titleTextSize(23)//
					.showAnim(bas_in)//
					.dismissAnim(bas_out)//
					.show();
					
					dialog.btnText("��","��");
					dialog.cornerRadius(15);
					dialog.titleTextColor(0x99f9923e);
					dialog.btnPressColor(0x50f9923e);
					dialog.setOnBtnClickL(
							new OnBtnClickL() {
								@Override
								public void onBtnClick() {
									dialog.dismiss();
								}
							},
							new OnBtnClickL() {
								@Override
								public void onBtnClick() {
									editor = preferences.edit();  
									editor.putBoolean("loginState", false); 
									editor.putString("username", "���½��ע��");
									editor.putInt("userid", 0);
									editor.commit();  
									imageView.setImageResource(R.drawable.demo);
									username.setText("���¼��ע��");
//									Intent intent = new Intent(getActivity(),FisrtActivity.class);
//									startActivity(intent);
									Toast.makeText(getActivity(), "ע���ɹ�!", 1).show();

									dialog.dismiss();
								}
							});
					
				}else{
					Toast.makeText(getActivity(), "�㻹û��¼!", 1).show();
				}
			}
		});

	}
}


