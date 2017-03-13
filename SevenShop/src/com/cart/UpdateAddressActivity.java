package com.cart;

import com.Seven.Shop.R;
import com.cart.adapter.UpdateAddressAdapter;
import com.shop.entity.ActivityCollect;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class UpdateAddressActivity extends Activity {
	
	private ListView updateAddressListView;	//�ϼ�������ʾ�б�
	private UpdateAddressAdapter adapter;	//�Զ���������
	TextView titleName;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);	
		setContentView(R.layout.activity_updateaddress);
			
		//���ñ���Ϊĳ��layout
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.drawable.titleback);
		
		ActivityCollect.addActivity(this);
		
		initView();
		init();
	}
	
	//��ʼ��UI��ͼ
	private void initView(){
		titleName=(TextView) findViewById(R.id.titleName2);
		titleName.setText("ѡ���ַ");
		View backCenter= findViewById(R.id.backCenter);   //���ؼ�
		
		backCenter.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				finish();

			}
		});
		
		updateAddressListView = (ListView) findViewById(R.id.updateAddressListView);
		
		updateAddressListView.setOnItemClickListener(new ListViewItemClickListener());
	}
	
	//��ʼ������
	private void init(){
		if(ShoppingCanst.addressList != null){
			adapter = new UpdateAddressAdapter(this,ShoppingCanst.addressList
					,R.layout.updateaddress_item);
			updateAddressListView.setAdapter(adapter);
		}
	}
	
	
	//ListView��Ŀ���������
	private final class ListViewItemClickListener implements OnItemClickListener{
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long arg3) {
			Intent intent = new Intent();
			intent.putExtra("addressIndex", position);
			setResult(RESULT_OK,intent);
			finish();
		}
	}
}
