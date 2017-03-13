package com.view.UI;  

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import com.Seven.Shop.R;
import com.shop.entity.ActivityCollect;
import com.view.ViewPagerAdapter;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;  
import android.support.v4.view.ViewPager;  
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;  
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;  


public class MainActivity extends Activity implements OnClickListener,OnPageChangeListener {  
	//定义ViewPager对象  
	private ViewPager viewPager;  

	private Button button;

	//定义ViewPager适配器  
	private ViewPagerAdapter vpAdapter;  

	//定义一个ArrayList来存放View  
	private ArrayList<View> views;  

	//引导图片资源  
	private static final int[] pics = {R.drawable.p1,R.drawable.p2,R.drawable.p3,R.drawable.p4};  

	//底部小点的图片  
	private ImageView[] points;  

	//记录当前选中位置  
	private int currentIndex;
 
	private RelativeLayout welcomeImg;

	@Override  
	protected void onCreate(Bundle savedInstanceState) {  
		super.onCreate(savedInstanceState);  
		//requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		
		ActivityCollect.activities = new ArrayList<Activity>();
		ActivityCollect.addActivity(this);
		
		SharedPreferences sharedPreferences = this.getSharedPreferences("share", MODE_PRIVATE); 
		boolean isFirstRun = sharedPreferences.getBoolean("isFirstRun", true); 
		
		if (isFirstRun) 
		{ 
			setContentView(R.layout.activity_main); 			
			//设置标题为某个layout
			initView();  

			initData(); 
			
			sharedPreferences = this.getSharedPreferences("share", MODE_PRIVATE);
			//此处表示该应用程序专用
			isFirstRun = sharedPreferences.getBoolean("isFirstRun", true);
			//此处表示如果key "isFirstRun"对应的value没有值则默认为true，
			//否则就把对应的value取出赋值给变量isFirstRun
			Editor editor = sharedPreferences.edit();
			editor.putBoolean("isFirstRun", false);
			//此处表示putBoolean(key, value)，
			//将value写入对应的key，而且是一一对应的
			editor.commit();
			//将isFirstRun写入editor中保存


		} else{ 
			setContentView(R.layout.welcome);  
			
			welcomeImg = (RelativeLayout)findViewById(R.id.welcomeImg);
			welcomeImg.startAnimation(AnimationUtils.loadAnimation(MainActivity.this,R.anim.welcome_bg_in));
			final Intent it = new Intent(this,FisrtActivity.class);
			Timer timer=new Timer();
			TimerTask task=new TimerTask(){

				@Override
				public void run() {
					// TODO Auto-generated method stub
					startActivity(it);
					finish();
				}
				
			};
			timer.schedule(task, 4000);
			//welcomeImg = (ImageView) this.findViewById(R.id.welcome_img);  
//	        AlphaAnimation anima = new AlphaAnimation(0.3f, 1.0f);  
//	        anima.setDuration(3000);// 设置动画显示时间  
////	        welcomeImg.startAnimation(anima);  
//	        welcomeImg.startAnimation(anima);
//	        anima.setAnimationListener(new AnimationImpl());
//	        
			//startActivity(new Intent(MainActivity.this,OtherActivity.class));
		}

	}  
//欢迎动画
	private class AnimationImpl implements AnimationListener {  
		  
        @Override  
        public void onAnimationStart(Animation animation) {  
            //welcomeImg.setBackgroundResource(R.drawable.welcome);  
        }  
  
        @Override  
        public void onAnimationEnd(Animation animation) {  
            skip(); // 动画结束后跳转到别的页面  
        }  
  
        @Override  
        public void onAnimationRepeat(Animation animation) {  
  
        }  
  
    }  
  
    private void skip() {  
        startActivity(new Intent(this, FisrtActivity.class));  
        finish();  
    }  
  
  //欢迎动画   
  
	/** 
	 * 初始化组件 
	 */  
	private void initView(){  
		//实例化ArrayList对象  
		views = new ArrayList<View>();  

		LayoutInflater mli = LayoutInflater.from(this);
		View view1 = mli.inflate(R.layout.animation1, null);
		View view2 = mli.inflate(R.layout.animation2, null);
		View view3 = mli.inflate(R.layout.animation3, null);
		View view4 = mli.inflate(R.layout.animation, null);

		views.add(view1);
		views.add(view2);
		views.add(view3);
		views.add(view4);


		button=(Button) view4.findViewById(R.id.button1);
		//实例化ViewPager  
		viewPager = (ViewPager) findViewById(R.id.viewpager);  

		//实例化ViewPager适配器  
		vpAdapter = new ViewPagerAdapter(views);  
	}  

	/** 
	 * 初始化数据 
	 */  
	private void initData(){  
		//定义一个布局并设置参数  


		//设置数据  
		viewPager.setAdapter(vpAdapter);  
		//设置监听  
		viewPager.setOnPageChangeListener(this);  

		//初始化底部小点  
		initPoint(); 

		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(MainActivity.this,FisrtActivity.class);
				startActivity(intent);
				finish();
			}
		});

	}  

	private void initPoint(){  
		LinearLayout linearLayout = (LinearLayout) findViewById(R.id.ll);         

		points = new ImageView[views.size()];  

		//循环取得小点图片  
		for (int i = 0; i < views.size(); i++) {  
			//得到一个LinearLayout下面的每一个子元素  
			points[i] = (ImageView) linearLayout.getChildAt(i);  
			//默认都设为灰色  
			points[i].setEnabled(true);  
			//给每个小点设置监听  
			points[i].setOnClickListener(this);  
			//设置位置tag，方便取出与当前位置对应  
			points[i].setTag(i);  
		}  

		//设置当面默认的位置  
		currentIndex = 0;  
		//设置为白色，即选中状态  
		points[currentIndex].setEnabled(false);  
	}  

	/** 
	 * 当滑动状态改变时调用 
	 */  
	@Override  
	public void onPageScrollStateChanged(int arg0) {  

	}  

	/** 
	 * 当当前页面被滑动时调用 
	 */  

	@Override  
	public void onPageScrolled(int arg0, float arg1, int arg2) {  

	}  

	/** 
	 * 当新的页面被选中时调用 
	 */  

	@Override  
	public void onPageSelected(int position) {  
		//设置底部小点选中状态  
		setCurDot(position);  
	}  

	/** 
	 * 通过点击事件来切换当前的页面 
	 */  
	@Override  
	public void onClick(View v) {  
		int position = (Integer)v.getTag();  
		setCurView(position);  
		setCurDot(position);         
	}  

	/** 
	 * 设置当前页面的位置 
	 */  
	private void setCurView(int position){  
		if (position < 0 || position >= pics.length) {  
			return;  
		}  
		viewPager.setCurrentItem(position);  
	}  

	/** 
	 * 设置当前的小点的位置 
	 */  
	private void setCurDot(int positon){  
		if (positon < 0 || positon > pics.length - 1 || currentIndex == positon) {  
			return;  
		}  
		points[positon].setEnabled(false);  
		points[currentIndex].setEnabled(true);  

		currentIndex = positon; 
	}    

}  