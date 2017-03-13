package com.personal.order;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.Seven.Shop.R;
import com.google.gson.Gson;
import com.http.util.HttpUtil1;
import com.shop.entity.ActivityCollect;
import com.squareup.okhttp.Request;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MyorderActivity extends FragmentActivity{
	
	private BuyOrederFragement buyOrederFragement; 
	private WantBuyOrderFragement wantBuyOrderFragement;

	private ViewPager vpOrder;
	private Button btnBuy;
	private Button btnWantBuy;

	private FragmentManager fragmentManager;
	private OrderAdapter orderAdapter;
	
	private SharedPreferences preferences;
	
	public static MyorderActivity orderTestActivity;
 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.order);
		//���ñ���Ϊĳ��layout
	    getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.drawable.titleback);

		fragmentManager = getSupportFragmentManager();
		
		ActivityCollect.addActivity(this);
		
		orderTestActivity = this;
		// ��ʼ���ؼ�
		initView();
		// ���¼�
		initEvent();
		//�����ϳ�ʼ������
		InitData();
	}

	/**
	 * ���¼�
	 */
	private void initEvent() {
		// ��ť����¼�
		btnBuy.setOnClickListener(listener);
		btnWantBuy.setOnClickListener(listener);
		
		
		// ViewPager�����¼�
		vpOrder.setOnPageChangeListener(vp_listener);
	}

	/**
	 * ��ʼ���ؼ�
	 */
	private void initView() {
		vpOrder = (ViewPager) this.findViewById(R.id.vp_order);
		btnBuy = (Button) this.findViewById(R.id.btn_buy);
		btnWantBuy = (Button) this.findViewById(R.id.btn_wantbuy);

		// ʵ����FragMent
		buyOrederFragement = new BuyOrederFragement();
		wantBuyOrderFragement = new WantBuyOrderFragement();

		// ��FragMent��ӵ�������
		List<Fragment> ar = new ArrayList<Fragment>();
		ar.add(buyOrederFragement);
		ar.add(wantBuyOrderFragement);

		orderAdapter = new OrderAdapter(fragmentManager, ar);
		vpOrder.setAdapter(orderAdapter);

		// Ĭ��ѡ���һ��
		vpOrder.setCurrentItem(0);

	}

	/**
	 * ��ť����¼�
	 */
	OnClickListener listener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			if (v.getId() == R.id.btn_buy) {
				vpOrder.setCurrentItem(0);
			}
			if (v.getId() == R.id.btn_wantbuy) {
				vpOrder.setCurrentItem(1);
			}
		}
	};

	/**
	 * ViewPager�����¼�
	 */
	OnPageChangeListener vp_listener = new OnPageChangeListener() {

		@Override
		public void onPageSelected(int arg0) {
			// �����ʽ
			clearStyle();
			switch (arg0) {
			case 0:
				btnBuy.setBackgroundColor(Color.parseColor("#f9923e"));
                btnBuy.setTextColor(Color.parseColor("#ffffff"));
                btnWantBuy.setTextColor(Color.parseColor("#f9923e"));
				break;
			case 1:
				btnWantBuy.setBackgroundColor(Color.parseColor("#f9923e"));
				btnBuy.setTextColor(Color.parseColor("#f9923e"));
                btnWantBuy.setTextColor(Color.parseColor("#ffffff"));
				break;
			default:
				break;
			}
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onPageScrollStateChanged(int arg0) {
			// TODO Auto-generated method stub

		}
	};
	

	/**
	 * �����ʽ
	 */
	private void clearStyle() {
		btnBuy.setBackgroundColor(Color.parseColor("#eeeeee"));
		btnWantBuy.setBackgroundColor(Color.parseColor("#eeeeee"));
	}
	
	private void InitData(){
		OrderConst.buyorder = new ArrayList<Order>();
		OrderConst.wantbuyorder = new ArrayList<Order>();

		
		preferences = getApplication().getSharedPreferences("loginState", Context.MODE_PRIVATE);
		 Order order = new Order();
		 order.setUser_id(preferences.getInt("userid", 0));
			HttpUtil1.postAsync("showorder.php", order, new HttpUtil1.DataCallBack() {
				
				@Override
				public void requestSuccess(String result) throws Exception {
					// TODO Auto-generated method stub
					
					
				   System.out.println(result);	
                   OrderList orderList = new OrderList();
                   Gson gson = new Gson();
                   orderList = gson.fromJson(result, OrderList.class);
   					for(int i=0;i<orderList.getOrders().size();i++){  						
   						if (orderList.getOrders().get(i).getOrder_state() == 1){
   							OrderConst.buyorder.add(orderList.getOrders().get(i));
   
   						}else if(orderList.getOrders().get(i).getOrder_state() == 0){
   							OrderConst.wantbuyorder.add(orderList.getOrders().get(i));
   							
   						}
   					}
   					
   					buyOrederFragement.Init();
   					wantBuyOrderFragement.Init();
   			
				}
				
				@Override
				public void requestFailure(Request request, IOException e) {
					// TODO Auto-generated method stub
					
				}
			});

		
	}
	
}

