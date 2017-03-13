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
	//����ViewPager����  
	private ViewPager viewPager;  

	private Button button;

	//����ViewPager������  
	private ViewPagerAdapter vpAdapter;  

	//����һ��ArrayList�����View  
	private ArrayList<View> views;  

	//����ͼƬ��Դ  
	private static final int[] pics = {R.drawable.p1,R.drawable.p2,R.drawable.p3,R.drawable.p4};  

	//�ײ�С���ͼƬ  
	private ImageView[] points;  

	//��¼��ǰѡ��λ��  
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
			//���ñ���Ϊĳ��layout
			initView();  

			initData(); 
			
			sharedPreferences = this.getSharedPreferences("share", MODE_PRIVATE);
			//�˴���ʾ��Ӧ�ó���ר��
			isFirstRun = sharedPreferences.getBoolean("isFirstRun", true);
			//�˴���ʾ���key "isFirstRun"��Ӧ��valueû��ֵ��Ĭ��Ϊtrue��
			//����ͰѶ�Ӧ��valueȡ����ֵ������isFirstRun
			Editor editor = sharedPreferences.edit();
			editor.putBoolean("isFirstRun", false);
			//�˴���ʾputBoolean(key, value)��
			//��valueд���Ӧ��key��������һһ��Ӧ��
			editor.commit();
			//��isFirstRunд��editor�б���


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
//	        anima.setDuration(3000);// ���ö�����ʾʱ��  
////	        welcomeImg.startAnimation(anima);  
//	        welcomeImg.startAnimation(anima);
//	        anima.setAnimationListener(new AnimationImpl());
//	        
			//startActivity(new Intent(MainActivity.this,OtherActivity.class));
		}

	}  
//��ӭ����
	private class AnimationImpl implements AnimationListener {  
		  
        @Override  
        public void onAnimationStart(Animation animation) {  
            //welcomeImg.setBackgroundResource(R.drawable.welcome);  
        }  
  
        @Override  
        public void onAnimationEnd(Animation animation) {  
            skip(); // ������������ת�����ҳ��  
        }  
  
        @Override  
        public void onAnimationRepeat(Animation animation) {  
  
        }  
  
    }  
  
    private void skip() {  
        startActivity(new Intent(this, FisrtActivity.class));  
        finish();  
    }  
  
  //��ӭ����   
  
	/** 
	 * ��ʼ����� 
	 */  
	private void initView(){  
		//ʵ����ArrayList����  
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
		//ʵ����ViewPager  
		viewPager = (ViewPager) findViewById(R.id.viewpager);  

		//ʵ����ViewPager������  
		vpAdapter = new ViewPagerAdapter(views);  
	}  

	/** 
	 * ��ʼ������ 
	 */  
	private void initData(){  
		//����һ�����ֲ����ò���  


		//��������  
		viewPager.setAdapter(vpAdapter);  
		//���ü���  
		viewPager.setOnPageChangeListener(this);  

		//��ʼ���ײ�С��  
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

		//ѭ��ȡ��С��ͼƬ  
		for (int i = 0; i < views.size(); i++) {  
			//�õ�һ��LinearLayout�����ÿһ����Ԫ��  
			points[i] = (ImageView) linearLayout.getChildAt(i);  
			//Ĭ�϶���Ϊ��ɫ  
			points[i].setEnabled(true);  
			//��ÿ��С�����ü���  
			points[i].setOnClickListener(this);  
			//����λ��tag������ȡ���뵱ǰλ�ö�Ӧ  
			points[i].setTag(i);  
		}  

		//���õ���Ĭ�ϵ�λ��  
		currentIndex = 0;  
		//����Ϊ��ɫ����ѡ��״̬  
		points[currentIndex].setEnabled(false);  
	}  

	/** 
	 * ������״̬�ı�ʱ���� 
	 */  
	@Override  
	public void onPageScrollStateChanged(int arg0) {  

	}  

	/** 
	 * ����ǰҳ�汻����ʱ���� 
	 */  

	@Override  
	public void onPageScrolled(int arg0, float arg1, int arg2) {  

	}  

	/** 
	 * ���µ�ҳ�汻ѡ��ʱ���� 
	 */  

	@Override  
	public void onPageSelected(int position) {  
		//���õײ�С��ѡ��״̬  
		setCurDot(position);  
	}  

	/** 
	 * ͨ������¼����л���ǰ��ҳ�� 
	 */  
	@Override  
	public void onClick(View v) {  
		int position = (Integer)v.getTag();  
		setCurView(position);  
		setCurDot(position);         
	}  

	/** 
	 * ���õ�ǰҳ���λ�� 
	 */  
	private void setCurView(int position){  
		if (position < 0 || position >= pics.length) {  
			return;  
		}  
		viewPager.setCurrentItem(position);  
	}  

	/** 
	 * ���õ�ǰ��С���λ�� 
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