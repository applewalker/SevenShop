package com.personal.address;

import android.os.Bundle;
import android.os.Environment;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.Seven.Shop.R;
import com.cart.ShoppingCanst;
import com.cart.bean.AddressBean;
import com.cart.bean.AddressList;
import com.google.gson.Gson;
import com.http.util.HttpUtil1;
import com.squareup.okhttp.Request;
import com.view.personal.tab3;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.text.TextUtils.StringSplitter;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;

public class MyaddressActivity extends Activity {

	private AddressListSimpleAdapter listAdapter;
	public static ListView listView;                 // �����б���ͼ����
	static List<Map<String, Object>> list;    // �����б�����
	public static MyaddressActivity addTestActivity;

	private List<AddressBean> addressList;	  //�ջ��˵�ַ���ݼ���

	private int position_tmp;    //position����ʱ�洢����
	TextView titleName;
	ImageView add_address;
	private SharedPreferences preferences;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);  
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.myaddress); 			
		//���ñ���Ϊĳ��layout
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.drawable.title_back_address);

		addTestActivity = this;
		Init();
	}

	private void Init() {
		// TODO Auto-generated method stub
		titleName=(TextView) findViewById(R.id.titleName2);
		titleName.setText("�ҵĵ�ַ");
		add_address=(ImageView) findViewById(R.id.title_addressAdd);
		View backCenter= findViewById(R.id.backCenter);   //���ؼ�


		//ʵ�����б���ͼ
		listView = new ListView(this);        //�½�һ��list

		//ʵ�����б�����
		list = new ArrayList<Map<String, Object>>();     

		addressList = ShoppingCanst.addressList;  
		
		
		

		ShoppingCanst.addressList = new ArrayList<AddressBean>();
		AddressBean address = new AddressBean();
		preferences = getApplication().getSharedPreferences("loginState", Context.MODE_PRIVATE);
		address.setUser_id(preferences.getInt("userid", 0));
		
        HttpUtil1.postAsync("showaddress.php", address, new HttpUtil1.DataCallBack() {
			
			@Override
			public void requestSuccess(String result) throws Exception {
				// TODO Auto-generated method stub
				
				//System.out.println(result);
				Gson gson = new Gson();
				AddressList a = new AddressList();
				a = gson.fromJson(result, AddressList.class);
				if (true){
					//ʵ��һ���б������������������ݿ�������ӵ��б�������ÿһ��map�����ݶ�ӦListView��������һ��
					for (int i = 0; i < a.getAddressBeans().size(); i++) {
						Map<String, Object> map = new HashMap<String, Object>();
						map.put("addressid",a.getAddressBeans().get(i).getAddress_id()+"" );
						map.put("receivername",a.getAddressBeans().get(i).getName());
						map.put("address",a.getAddressBeans().get(i).getSmearedAddress()+
								a.getAddressBeans().get(i).getDetailAddress());
						map.put("delete","");
						map.put("edit", "edit");
						list.add(map);
					}
					
				    ShoppingCanst.addressList.addAll(a.getAddressBeans());
				    listAdapter.notifyDataSetChanged();
				}
				
				
			}
			
			@Override
			public void requestFailure(Request request, IOException e) {
				// TODO Auto-generated method stub
			
			}
		});
/*		AddressBean bean = new AddressBean();
		bean.setName("������");	
		bean.setDetailAddress("ʨ��ɽ��һ�Ż���ũҵ��ѧ  13037157995");
		ShoppingCanst.addressList.add(bean);*/


		


		//ʵ����������������ɲμ�SimpleAdapter��ʹ��
		listAdapter = new AddressListSimpleAdapter(MyaddressActivity.this, 
				list, 
				R.layout.addresslistview_item, 
				new String[] {"receivername", "address","edit","delete"}, 
				new int[] {R.id.address_receiver, R.id.address_address,R.id.address_edit ,R.id.address_delete});

		listView.setAdapter(listAdapter);  

		//���ListViewÿһ�еĵ�������¼����˴������ü����������
//		listView.setOnItemClickListener(new OnItemClickListener() {
//
//			public void onItemClick(AdapterView<?> arg0, View arg1,
//					int position, long id) {
//
//				position_tmp = position;
//
//				new AlertDialog.Builder(addTestActivity)
//				.setTitle("ListView")
//				.setMessage("�����˵�"+Integer.toString(position_tmp)+"�У�")
//				.show();
//			}
//		}); 



		//��ʾ�б���ͼ
		this.setContentView(listView);



		backCenter.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				finish();

			}
		});

		add_address.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent  intent=new Intent(MyaddressActivity.this,AddaddressActivity.class);
				startActivityForResult(intent, 0);
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.myaddress, menu);
		return true;
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		
		if(resultCode == 111){          //�޸�����

			Intent intent=data;
			
			ShoppingCanst.addressList = new ArrayList<AddressBean>();
			AddressBean bean = new AddressBean();
			bean.setName(data.getStringExtra("receiver_name"));	
			bean.setDetailAddress(data.getStringExtra("receiver_address"));
			ShoppingCanst.addressList.add(bean);

			AddressBean address = new AddressBean();
			preferences = getApplication().getSharedPreferences("loginState", Context.MODE_PRIVATE);
			address.setUser_id(preferences.getInt("userid", 0));
			
	        HttpUtil1.postAsync("showaddress.php", address, new HttpUtil1.DataCallBack() {
				
				@Override
				public void requestSuccess(String result) throws Exception {
					// TODO Auto-generated method stub
					
					//System.out.println(result);
					Gson gson = new Gson();
					AddressList a = new AddressList();
					a = gson.fromJson(result, AddressList.class);
					if (true){
						list.clear();
						//ʵ��һ���б������������������ݿ�������ӵ��б�������ÿһ��map�����ݶ�ӦListView��������һ��
						for (int i = 0; i < a.getAddressBeans().size(); i++) {
							Map<String, Object> map = new HashMap<String, Object>();
							map.put("addressid",a.getAddressBeans().get(i).getAddress_id()+"" );
							map.put("receivername",a.getAddressBeans().get(i).getName());
							map.put("address",a.getAddressBeans().get(i).getSmearedAddress()+
									a.getAddressBeans().get(i).getDetailAddress());
							map.put("delete","");
							map.put("edit", "edit");
							list.add(map);
						}
						
					    ShoppingCanst.addressList.addAll(a.getAddressBeans());
					    listAdapter.notifyDataSetChanged();
					}
					
					
				}
				
				@Override
				public void requestFailure(Request request, IOException e) {
					// TODO Auto-generated method stub
				
				}
			});
			
			/*//ʵ��һ���б������������������ݿ�������ӵ��б�������ÿһ��map�����ݶ�ӦListView��������һ��
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("receivername",bean.getName());
			map.put("address",bean.getDetailAddress());
			map.put("delete","");
			map.put("edit", "edit");
			list.add(map);*/
			
			
		}

		super.onActivityResult(requestCode, resultCode, data);
	}

}
