package com.cart;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.Seven.Shop.R;
import com.cart.adapter.CheckOutAdapter;
import com.cart.bean.AddressBean;
import com.cart.bean.AddressList;
import com.cart.bean.carBean;
import com.google.gson.Gson;
import com.http.util.HttpUtil1;
import com.personal.order.Order;
import com.shop.entity.ActivityCollect;
import com.shop.entity.Response;
import com.squareup.okhttp.Request;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class CheckoutActivity extends Activity {
	
	private Button sureCheckOut;		//ȷ�϶���
	private TextView addresseeName;		//�ջ�������
	private TextView smearedAddress;	//�ջ�������ַ
	private TextView detailAddress;		//�ջ�����ϸ��ַ
	private TextView checkOutAllPrice;	//������ܽ��
	private RelativeLayout addressRelative;	  //��ʾ�ջ�����Ϣ�Ĳ���
	private NoScrollListView checkOutListView;//��Ʒ�б�
	
	private CheckOutAdapter adapter;
	private List<carBean> list;			  //������Ʒ���ݼ���
	private List<AddressBean> addressList;	  //�ջ��˵�ַ���ݼ���
	
	private static int REQUESTCODE = 1;		  //��ת������
	private float allPrice = 0;				  //������Ʒ��Ҫ���ܽ��
	
	private SharedPreferences preferences;
	
	private int tempIndex;
	
	
	TextView titleName;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);	
		setContentView(R.layout.activity_checkout);
			
		//���ñ���Ϊĳ��layout
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.drawable.titleback);
		ActivityCollect.addActivity(this);
		
		initView();
		init();
	}
	
	//��ʼ������
	private void init(){
		
		titleName=(TextView) findViewById(R.id.titleName2);
		titleName.setText("����");
		View backCenter= findViewById(R.id.backCenter);   //���ؼ�
		
		list = new ArrayList<carBean>();
		String shopIndex = getIntent().getStringExtra("shopIndex");
		
		if (shopIndex == null){
			carBean bean = ShoppingCanst.list.get(0);
			allPrice += bean.getNumber()*bean.getPrice();
			list.add(bean);
		}else{
		
			String[] shopIndexs = shopIndex.split(",");
			
			for(String s : shopIndexs){
				int position = Integer.valueOf(s);
				carBean bean = ShoppingCanst.list.get(position);
				allPrice += bean.getNumber()*bean.getPrice();
				list.add(bean);
			}
		}
		
		getAddressData();
		addressList = ShoppingCanst.addressList;
		checkOutAllPrice.setText("�ܹ���"+list.size()+"����Ʒ       �ܼۣ�"+allPrice);
		
		
		adapter = new CheckOutAdapter(this, list, R.layout.checkout_item);
		checkOutListView.setAdapter(adapter);
		
		backCenter.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				finish();

			}
		});
	}
	
	
	
	//��ʼ��UI����
	private void initView(){
		sureCheckOut = (Button) findViewById(R.id.sureCheckOut);
		addresseeName = (TextView) findViewById(R.id.addresseeName);
		smearedAddress = (TextView) findViewById(R.id.smearedAddress);
		detailAddress = (TextView) findViewById(R.id.detailAddress);
		checkOutAllPrice = (TextView) findViewById(R.id.checkOutAllPrice);
		
		checkOutListView = (NoScrollListView) findViewById(R.id.checkOutListView);
		addressRelative = (RelativeLayout) findViewById(R.id.addressRelative);
		addresseeName.setText("��û�е�ַ�������");
		ClickListener cl = new ClickListener();
		sureCheckOut.setOnClickListener(cl);
		addressRelative.setOnClickListener(cl);
	}
	
	//��ʾ�ջ���������ַ����Ϣ
	private void showInfo(int index){
		AddressBean bean = addressList.get(index);
		addresseeName.setText(bean.getName());
		smearedAddress.setText(bean.getSmearedAddress());
		detailAddress.setText(bean.getDetailAddress());
	}
	
	//��ȡ�ջ��˵�ַ���ݼ���
	private void getAddressData(){
		preferences = getApplication().getSharedPreferences("loginState", Context.MODE_PRIVATE);
		ShoppingCanst.addressList = new ArrayList<AddressBean>();
		AddressBean address = new AddressBean();
		address.setUser_id(preferences.getInt("userid", 0));
        HttpUtil1.postAsync("showaddress.php", address, new HttpUtil1.DataCallBack() {
			
			@Override
			public void requestSuccess(String result) throws Exception {
				// TODO Auto-generated method stub
				
				System.out.println(result);
				Gson gson = new Gson();
				AddressList a = new AddressList();
				a = gson.fromJson(result, AddressList.class);
				if (a.getAddressBeans() != null){
				    ShoppingCanst.addressList.addAll(a.getAddressBeans());
				    showInfo(0);	//Ĭ����ʾ��һ����ַ��Ϣ
				}
				
				
			}
			
			@Override
			public void requestFailure(Request request, IOException e) {
				// TODO Auto-generated method stub
			
			}
		});
		 
		
		/*AddressBean bean = new AddressBean();
		bean.setName("������");
		bean.setSmearedAddress("����ʡ�人�к�ɽ��");
		bean.setDetailAddress("ʨ��ɽ��һ�Ż���ũҵ��ѧ  13037157995");
		ShoppingCanst.addressList.add(bean);
		AddressBean bean2 = new AddressBean();
		bean2.setName("����");
		bean2.setSmearedAddress("����ʡ�人�к�ɽ��");
		bean2.setDetailAddress("ʨ��ɽ��һ�� 18140549110");
		ShoppingCanst.addressList.add(bean2);*/
	}
	
	//�޸ĵ�ַ
	private void updateAddress(){
		Intent intent = new Intent(CheckoutActivity.this,UpdateAddressActivity.class);
		startActivityForResult(intent, REQUESTCODE);
	}
	
	//�¼����������
	private final class ClickListener implements OnClickListener{
		@Override
		public void onClick(View v) {
			if (ShoppingCanst.addressList.size() != 0 ){
				int rid = v.getId();
				if(rid == R.id.sureCheckOut){	//ȷ�ϵ����ť
					
					
					Order order = new Order();
				    order.setAddress_id(ShoppingCanst.addressList.get(tempIndex).getAddress_id());
				    order.setOrder_state(0);
				    String string = new String();
				    for (int i = 0; i < list.size(); i++) {
				    	string = string + list.get(i).getGoods_name()+list.get(i).getGoodsdescribe();
				    }
				    order.setTotal_price(allPrice);
				    order.setTitle(string);
				    order.setGoods_id(list.get(0).getGoods_id());
				    order.setUser_id(list.get(0).getUser_id());
				    Intent intent = getIntent();
					List<carBean> l = new ArrayList<carBean>();
					final String shopIndex = intent.getStringExtra("shopIndex");
					if (shopIndex != null){
						String[] shopIndexs = shopIndex.split(",");					
						for(String s : shopIndexs){
							int position = Integer.valueOf(s);
							carBean bean = ShoppingCanst.list.get(position);
							l.add(bean);
						}
					}else
						l.add(ShoppingCanst.list.get(0));
					order.setCarBeans(l);
					HttpUtil1.postAsync("submitorder.php", order, new HttpUtil1.DataCallBack() {
						
						@Override
						public void requestSuccess(String result) throws Exception {
							// TODO Auto-generated method stub
							//�����������
							System.out.println(result);
							Response response = new Response();
							Gson gson = new Gson();
							response = gson.fromJson(result, Response.class);
							int order_id = response.getSuccess();
							Intent intent=new Intent(CheckoutActivity.this,SureCheckOutActivity.class);
							String shopIndex = getIntent().getStringExtra("shopIndex");
							intent.putExtra("shopIndex", shopIndex);
							intent.putExtra("check_price", String.valueOf(allPrice));
							intent.putExtra("order_id", order_id);
							startActivityForResult(intent, 0);
							tab2.TAB2.flashcart();
							
						}
						
						@Override
						public void requestFailure(Request request, IOException e) {
							// TODO Auto-generated method stub
						
						}
					});
					
					
				}else if(rid == R.id.addressRelative){	//�޸ĵ�ַ
					updateAddress();
				}
			}else {
				Toast.makeText(getApplication(), "��ǰ������������ӵ�ַ", Toast.LENGTH_SHORT).show();
			}
		}
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if(requestCode == REQUESTCODE && resultCode == RESULT_OK){
			Bundle bundle = data.getExtras();
			handler.sendMessage(handler.obtainMessage(1, bundle.getInt("addressIndex")));
		}
		
		if(resultCode == 0000){    
			
			/**
			 * ֧���ɹ�ˢ�¹��ﳵ
			 */ 
			finish();
		}
	}
	
	@SuppressLint("HandlerLeak")
	private Handler handler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			if(msg.what == 1){		//�첽���ĵ�ַ	
				tempIndex = (Integer)msg.obj;
				showInfo(tempIndex);
			}
		}
	};
}
