package com.commodity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.Seven.Shop.R;
import com.commodity.adapter.CommoPagerAdapter;
import com.commodity.adapter.DepthPageTransformer;
import com.commodity.bean.Commodity;
import com.commodity.fragment.CommoTab0;
import com.commodity.fragment.CommoTab1;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.zxing.WriterException;
import com.http.util.HttpUtil1;
import com.loopj.android.image.SmartImageView;
import com.shop.entity.ActivityCollect;
import com.shop.entity.Evaluate;
import com.shop.entity.Goods;
import com.shop.entity.Recommend;
import com.shop.entity.Scharacteristdetial;
import com.squareup.okhttp.Request;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.animation.AnimationUtils;
import android.widget.AbsListView.MultiChoiceModeListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.SimpleAdapter;
import android.widget.TextView;

@SuppressLint("InflateParams")
public class CommodityActivity extends FragmentActivity implements
OnPageChangeListener,OnCheckedChangeListener,OnClickListener{

	private ViewPager pro_pager;
	private CommoTab0 commoTab0 = new CommoTab0();//商品详情页
	private CommoTab1 commoTab1 = new CommoTab1();//商品评论页
	private Goods goods = new Goods();
	private Goods good = new Goods();
	private List<Evaluate> evaluate;	
	private List <Scharacteristdetial> scharacteristdetials;
	private RadioGroup group;
	private RadioButton button0, button1;
	private CommoPagerAdapter adapter;  
	private List<Fragment> lists = new ArrayList<Fragment>();
	private ListView tab1;
	private static LinearLayout pro_all_layout;
	public static int CartorBuy;//标志，加入购物车 0,立即购买 1
	private CommoPopWindow popwindow;
	private TextView actitle,/*pro_title,*/pro_name;
	private Button collect,add2cart,buy,share;
	private View back;
	private int id = 123;
	private SharedPreferences preferences;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);  
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.activity_commodity); 
		//设置标题为某个layout
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.drawable.titleback);
		
		ActivityCollect.addActivity(this);
		
		Intent intent =getIntent();
		id = intent.getIntExtra("id",id);
		
		
		//网络操作
		goods.setGoods_id(id);	
		preferences = getApplication().getSharedPreferences("loginState", Context.MODE_PRIVATE);
		if (preferences.getBoolean("loginState", false) == true){
			int user_id = preferences.getInt("userid", 0);
			goods.setUser_id(user_id);
		}else 
			goods.setUser_id(0);
		HttpUtil1.postAsync("showgoodsdetail.php", goods, new HttpUtil1.DataCallBack() {
			
			@Override
			public void requestSuccess(String result) throws Exception {
				// TODO Auto-generated method stub
				
				
				Gson gson = new Gson();
				good = gson.fromJson(result, Goods.class);
			   // System.out.println("asfdasfsafffffffffffffffff"+good.getType().get(1).getName());
				commoTab0.upData();
				popwindow.upData(good);
				
				
				
			}
			
			@Override
			public void requestFailure(Request request, IOException e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		HttpUtil1.postAsync("showevaluate.php", goods, new HttpUtil1.DataCallBack() {
			
			@Override
			public void requestSuccess(String result) throws Exception {
				// TODO Auto-generated method stub
				
				Gson gson = new Gson();
				evaluate = new ArrayList<Evaluate>();
				 JsonParser parser = new JsonParser();
		    	    JsonArray Jarray = parser.parse(result).getAsJsonArray();
		    	    for (JsonElement obj : Jarray){
		    	          Evaluate e =  gson.fromJson(obj, Evaluate.class);
		    	          evaluate.add(e);
		    	    }
		    	    
			}
			
			@Override
			public void requestFailure(Request request, IOException e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
        HttpUtil1.postAsync("showevaluate.php", goods, new HttpUtil1.DataCallBack() {
			
			@Override
			public void requestSuccess(String result) throws Exception {
				// TODO Auto-generated method stub
				
				Gson gson = new Gson();
				scharacteristdetials = new ArrayList<Scharacteristdetial>();
				JsonParser parser = new JsonParser();
		    	    JsonArray Jarray = parser.parse(result).getAsJsonArray();
		    	    for (JsonElement obj : Jarray){
		    	    	Scharacteristdetial s =  gson.fromJson(obj, Scharacteristdetial.class);
		    	    	scharacteristdetials.add(s);
		    	    }
		    	    commoTab1.upData();
		    	   
			}
			
			@Override
			public void requestFailure(Request request, IOException e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		//连接完毕
		
		
		actitle = (TextView) findViewById(R.id.titleName2);
		actitle.setText("商品");
//		pro_title= (TextView) findViewById(R.id.pro_title);
//		pro_title.setText("评论");
		
		group = (RadioGroup) findViewById(R.id.pro_radioGroup1);
		button0 = (RadioButton) findViewById(R.id.pro_radio0);
		button1 = (RadioButton) findViewById(R.id.pro_radio1);
		group.setOnCheckedChangeListener(this);

		pro_all_layout = (LinearLayout) findViewById(R.id.pro_all_layout);    
		pro_pager = (ViewPager) findViewById(R.id.pro_pager);

		
		lists.add(commoTab0);  
		lists.add(commoTab1);
		adapter = new CommoPagerAdapter(getSupportFragmentManager(), lists);        
		pro_pager.setPageTransformer(true, new DepthPageTransformer());
		pro_pager.setAdapter(adapter);
		pro_pager.setOffscreenPageLimit(lists.size()-1);// 缓存页面,显示第一个缓存最后一个
		pro_pager.setOnPageChangeListener(this);

		popwindow = new CommoPopWindow(this);
		//popwindow.setOnItemClickListener(this);

		//按钮
		//collect = (Button)findViewById(R.id.btn_collect);
		add2cart = (Button)findViewById(R.id.btn_add2cart);
		buy = (Button)findViewById(R.id.btn_buy);
		back = findViewById(R.id.backCenter);
		
		//按钮监听事件
	
		add2cart.setOnClickListener(this);
		buy.setOnClickListener(this);
		back.setOnClickListener(this);
		
	}

	public void onClick(View view) {
		switch(view.getId()){
		/*case R.id.btn_collect:
			//添加到收藏
			break;*/
		case R.id.btn_add2cart:
			CartorBuy = 0;
			setallblack(0,CommodityActivity.this);
			//弹出选择界面
			popwindow.showAsDropDown(view);
			//添加到购物车
			break;
		case R.id.btn_buy:
			CartorBuy = 1;
			setallblack(0,CommodityActivity.this);
			//弹出选择界面
			popwindow.showAsDropDown(view);
			//跳到订单
			break;
		case R.id.backCenter:
			//返回上一个界面
			finish();
			break;
//		case R.id.btn_share:
//			//分享		
//			ShareMsg s = null;
//			try {
//				s = new ShareMsg(pro_title.getText().toString(),
//						pro_name.getText().toString(), view_tab0.findViewById(R.id.pro_pic));
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}			
//			startActivity(Intent.createChooser(s.getIntent(), "分享"));
//			break;
		}
	}
	public Goods getGoods(){
		return good;
	}
	public List<Evaluate> getEvaluates(){
		return evaluate;
		
	}
	public static void setallblack( int flag,final Context context) {
		switch (flag) {
		case 0:
			pro_all_layout.setVisibility(View.VISIBLE);
			pro_all_layout.startAnimation(AnimationUtils.loadAnimation(context,
					R.anim.bg_in));
			break;
		case 1:
			pro_all_layout.startAnimation(AnimationUtils.loadAnimation(context,
					R.anim.bg_out));
			pro_all_layout.setVisibility(View.GONE);
			break;
		}
	}
	private void getTabState(int index) {
		button0.setChecked(false);
		button1.setChecked(false);		
		switch (index) {
		case 0:
			button0.setChecked(true);
			break;
		case 1:
			button1.setChecked(true);
			break;
		default:
			break;
		}
	}
	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		switch (checkedId) {
		case R.id.pro_radio0:
			pro_pager.setCurrentItem(0);
			break;
		case R.id.pro_radio1:
			pro_pager.setCurrentItem(1);
            
			break;
		default:
			break;
		}
	}	
	public void onPageScrollStateChanged(int arg0) {
	}
	public void onPageScrolled(int arg0, float arg1, int arg2) {
	}
	public void onPageSelected(int arg0) {
		getTabState(arg0);
	}
	protected void onResume(){
		popwindow.setvi();
		super.onResume(); 				
	}
	protected void onUserLeaveHint() {  
		popwindow.setinvi(); 
		super.onUserLeaveHint();  
	}  
}
