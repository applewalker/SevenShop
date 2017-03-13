package com.view.UI;

import java.io.IOException;
import java.util.ArrayList;

import com.cart.ShoppingCanst;
import com.cart.tab2;
import com.cart.bean.CarList;
import com.cart.bean.carBean;
import com.google.gson.Gson;
import com.http.util.HttpUtil1;
import com.login.LoginActivity;
import com.shop.entity.ActivityCollect;
import com.shop.entity.Goods;
import com.squareup.okhttp.Request;
import com.Seven.Shop.R;
import com.view.RotateDownPageTransformer;
import com.view.homepage.tab1;
import com.view.personal.User;
import com.view.personal.tab3;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class FisrtActivity extends FragmentActivity{

	private ViewPager vp;   // 用来放置界面切换
	private LinearLayout tab1;
	private LinearLayout tab2;
	private LinearLayout tab3;
	private tab1 view1 = new tab1(); 
	private tab2 view2 = new tab2();
	private tab3 view3 = new tab3();
	private ImageView iv1;
	private ImageView iv2;
	private ImageView iv3;
	private TextView tv1;
	private TextView tv2;
	private TextView tv3;
	//private CarList carList;
	public SharedPreferences preferences;
	
	
	
	public void onConfigurationChanged(Configuration newConfig) {
		// TODO Auto-generated method stub
		super.onConfigurationChanged(newConfig);
		if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
			Toast.makeText(this, "landscape", Toast.LENGTH_SHORT).show();
		} else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
			Toast.makeText(this, "portrait", Toast.LENGTH_SHORT).show();
		}
	}

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE); //去掉顶部标题
		setContentView(R.layout.activity_first);
		
		ActivityCollect.finishAll();
		ActivityCollect.addActivity(this);
		
		initView(); //初始化设置

		

		final ArrayList<Fragment> views = new ArrayList<Fragment>();
		views.add(view1);
		views.add(view2);
		views.add(view3);

		tab1 = (LinearLayout) findViewById(R.id.tab1);
		tab2 = (LinearLayout) findViewById(R.id.tab2);
		tab3 = (LinearLayout) findViewById(R.id.tab3);

		tab1.setOnClickListener(click);
		tab2.setOnClickListener(click);
		tab3.setOnClickListener(click);

		FragmentPagerAdapter adapter=new FragmentPagerAdapter(getSupportFragmentManager()) {
			
			@Override
			public int getCount() {
				// TODO Auto-generated method stub
				return views.size();
			}
			
			@Override
			public Fragment getItem(int arg0) {
				// TODO Auto-generated method stub
				return views.get(arg0);
			}
		};
		
		vp.setPageTransformer(true, new RotateDownPageTransformer()); //动画切换
		//vp.setPageTransformer(true, new DepthPageTransformer());
		//vp.setPageTransformer(true, new ZoomOutPageTransformer());
		
		vp.setAdapter(adapter);
		vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

			@Override
			public void onPageSelected(int arg0) { //滑动的时候
				int currentItem = vp.getCurrentItem();
				resetImg();
				switch (currentItem) {
				case 0:
					iv1.setImageResource(R.drawable.home_press);
					tv1.setTextColor(0xFFFF9900);  //FF9900橙色
					break;
				case 1:
					preferences = getApplication().getSharedPreferences("loginState", Context.MODE_PRIVATE);
					if (preferences.getBoolean("loginState", false) == false) {
						Intent intent=new Intent(getApplication(), LoginActivity.class);
						startActivity(intent);
					}else{
							iv2.setImageResource(R.drawable.cart_press);
							tv2.setTextColor(0xFFFF9900);
							User user = new User();
							user.setUser_id(preferences.getInt("userid", 0));
							HttpUtil1.postAsync("showcar.php", user, new HttpUtil1.DataCallBack() {
								
								@Override
								public void requestSuccess(String result) throws Exception {
									// TODO Auto-generated method stub
									
									CarList carList = new CarList();
									Gson gson = new Gson();
									carList = gson.fromJson(result, CarList.class);
									view2.upData(carList.getCarBeans());
									
								}
								
								@Override
								public void requestFailure(Request request, IOException e) {
									// TODO Auto-generated method stub
									
								}
							});
					}
					
					break;
				case 2:
					preferences = getApplication().getSharedPreferences("loginState", Context.MODE_PRIVATE);
					if (preferences.getBoolean("loginState", false) == false) {
						Intent intent=new Intent(getApplication(), LoginActivity.class);
						startActivity(intent);
					}else{
						iv3.setImageResource(R.drawable.person_press);
						tv3.setTextColor(0xFFFF9900);
					}
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
		});
	}
		
	/**
	* 初始化设置
	*/
	private void initView() {
		vp = (ViewPager) findViewById(R.id.viewpage);
		// 初始化3个LinearLayout
		tab1 = (LinearLayout) findViewById(R.id.tab1);
		tab2 = (LinearLayout) findViewById(R.id.tab2);
		tab3 = (LinearLayout) findViewById(R.id.tab3);
		
		iv1 = (ImageView)findViewById(R.id.home_img);
		iv2 = (ImageView)findViewById(R.id.cart_img);
		iv3 = (ImageView)findViewById(R.id.person_img);
		
		tv1 = (TextView)findViewById(R.id.home_text);
		tv2 = (TextView)findViewById(R.id.cart_text);
		tv3 = (TextView)findViewById(R.id.person_text);
	}
		
	/**
	 * 把所有图片变暗
	 */
	private void resetImg() {
		iv1.setImageResource(R.drawable.home);
		iv2.setImageResource(R.drawable.cart);
		iv3.setImageResource(R.drawable.person);
		tv1.setTextColor(this.getResources().getColor(R.color.black));
		tv2.setTextColor(this.getResources().getColor(R.color.black));
		tv3.setTextColor(this.getResources().getColor(R.color.black));
	}
	
	OnClickListener click = new OnClickListener() {
		@Override
		public void onClick(View v){
			switch(v.getId()){
			case R.id.tab1:
				vp.setCurrentItem(0);
				iv1.setImageResource(R.drawable.home_press);
				tv1.setTextColor(0xFFFF9900);
				break;
			case R.id.tab2:

				vp.setCurrentItem(1);
				iv2.setImageResource(R.drawable.cart_press);
				tv2.setTextColor(0xFFFF9900); 
				
				/*User user = new User();
				user.setUser_id(preferences.getInt("userid", 0));
				HttpUtil1.postAsync("http://192.168.1.100:80/test/showcar.php", user, new HttpUtil1.DataCallBack() {
					
					@Override
					public void requestSuccess(String result) throws Exception {
						// TODO Auto-generated method stub
						
						System.out.println(result);
						Gson gson = new Gson();
						carList = gson.fromJson(result, CarList.class);
						view2.upData(carList.getCarBeans());
					  
						
						
					}
					
					@Override
					public void requestFailure(Request request, IOException e) {
						// TODO Auto-generated method stub
						
					}
				});*/
				break;
			case R.id.tab3:
				vp.setCurrentItem(2);
				iv3.setImageResource(R.drawable.person_press);
				tv3.setTextColor(0xFFFF9900);
				break;
			default:
				break;
			}
		}
	};

}
