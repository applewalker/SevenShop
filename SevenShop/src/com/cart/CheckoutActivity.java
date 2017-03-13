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
	
	private Button sureCheckOut;		//确认订单
	private TextView addresseeName;		//收货人姓名
	private TextView smearedAddress;	//收货人区地址
	private TextView detailAddress;		//收货人详细地址
	private TextView checkOutAllPrice;	//结算的总金额
	private RelativeLayout addressRelative;	  //显示收货人信息的布局
	private NoScrollListView checkOutListView;//商品列表
	
	private CheckOutAdapter adapter;
	private List<carBean> list;			  //结算商品数据集合
	private List<AddressBean> addressList;	  //收货人地址数据集合
	
	private static int REQUESTCODE = 1;		  //跳转请求码
	private float allPrice = 0;				  //购买商品需要的总金额
	
	private SharedPreferences preferences;
	
	private int tempIndex;
	
	
	TextView titleName;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);	
		setContentView(R.layout.activity_checkout);
			
		//设置标题为某个layout
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.drawable.titleback);
		ActivityCollect.addActivity(this);
		
		initView();
		init();
	}
	
	//初始化数据
	private void init(){
		
		titleName=(TextView) findViewById(R.id.titleName2);
		titleName.setText("订单");
		View backCenter= findViewById(R.id.backCenter);   //返回键
		
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
		checkOutAllPrice.setText("总共有"+list.size()+"个商品       总价￥"+allPrice);
		
		
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
	
	
	
	//初始化UI界面
	private void initView(){
		sureCheckOut = (Button) findViewById(R.id.sureCheckOut);
		addresseeName = (TextView) findViewById(R.id.addresseeName);
		smearedAddress = (TextView) findViewById(R.id.smearedAddress);
		detailAddress = (TextView) findViewById(R.id.detailAddress);
		checkOutAllPrice = (TextView) findViewById(R.id.checkOutAllPrice);
		
		checkOutListView = (NoScrollListView) findViewById(R.id.checkOutListView);
		addressRelative = (RelativeLayout) findViewById(R.id.addressRelative);
		addresseeName.setText("还没有地址，请添加");
		ClickListener cl = new ClickListener();
		sureCheckOut.setOnClickListener(cl);
		addressRelative.setOnClickListener(cl);
	}
	
	//显示收货人姓名地址等信息
	private void showInfo(int index){
		AddressBean bean = addressList.get(index);
		addresseeName.setText(bean.getName());
		smearedAddress.setText(bean.getSmearedAddress());
		detailAddress.setText(bean.getDetailAddress());
	}
	
	//获取收货人地址数据集合
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
				    showInfo(0);	//默认显示第一条地址信息
				}
				
				
			}
			
			@Override
			public void requestFailure(Request request, IOException e) {
				// TODO Auto-generated method stub
			
			}
		});
		 
		
		/*AddressBean bean = new AddressBean();
		bean.setName("余彦霄");
		bean.setSmearedAddress("湖北省武汉市洪山区");
		bean.setDetailAddress("狮子山街一号华中农业大学  13037157995");
		ShoppingCanst.addressList.add(bean);
		AddressBean bean2 = new AddressBean();
		bean2.setName("王晟");
		bean2.setSmearedAddress("湖北省武汉市洪山区");
		bean2.setDetailAddress("狮子山街一号 18140549110");
		ShoppingCanst.addressList.add(bean2);*/
	}
	
	//修改地址
	private void updateAddress(){
		Intent intent = new Intent(CheckoutActivity.this,UpdateAddressActivity.class);
		startActivityForResult(intent, REQUESTCODE);
	}
	
	//事件点击监听器
	private final class ClickListener implements OnClickListener{
		@Override
		public void onClick(View v) {
			if (ShoppingCanst.addressList.size() != 0 ){
				int rid = v.getId();
				if(rid == R.id.sureCheckOut){	//确认点击按钮
					
					
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
							//调到结算界面
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
					
					
				}else if(rid == R.id.addressRelative){	//修改地址
					updateAddress();
				}
			}else {
				Toast.makeText(getApplication(), "请前往个人中心添加地址", Toast.LENGTH_SHORT).show();
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
			 * 支付成功刷新购物车
			 */ 
			finish();
		}
	}
	
	@SuppressLint("HandlerLeak")
	private Handler handler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			if(msg.what == 1){		//异步更改地址	
				tempIndex = (Integer)msg.obj;
				showInfo(tempIndex);
			}
		}
	};
}
