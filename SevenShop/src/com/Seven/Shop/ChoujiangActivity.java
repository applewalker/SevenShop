package com.Seven.Shop;

import java.util.Timer;
import java.util.TimerTask;

import com.shop.entity.ActivityCollect;
import com.view.UI.FisrtActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ChoujiangActivity extends Activity {
	//设置一个时间常量，此常量有两个作用，1.圆灯视图显示与隐藏中间的切换时间；2.指针转一圈所需要的时间，现设置为500毫秒
		private static final long ONE_WHEEL_TIME = 500;
		//记录圆灯视图是否显示的布尔常量
		private boolean lightsOn = true;
		//开始转动时候的角度，初始值为0
		private int startDegree = 0;

		private ImageView lightIv;
		private ImageView pointIv;
		private ImageView wheelIv;
		
		TextView titleName;
		
		//指针转圈圈数数据源
		private int[] laps = { 5, 7, 10, 15 };
		//指针所指向的角度数据源，因为有6个选项，所有此处是6个值
		private int[] angles = { 0, 60, 120, 180, 240, 300 };
		//转盘内容数组
		private String[] lotteryStr = { "索尼PSP", "10元红包", "谢谢参与", "DNF钱包",
				"OPPO MP3", "5元红包", };
		
		//子线程与UI线程通信的handler对象
		private Handler mHandler = new Handler() {

			public void handleMessage(android.os.Message msg) {
				switch (msg.what) {
				case 0:
					if (lightsOn) {
						// 设置lightIv不可见
						lightIv.setVisibility(View.INVISIBLE);
						lightsOn = false;
					} else {
						// 设置lightIv可见
						lightIv.setVisibility(View.VISIBLE);
						lightsOn = true;
					}
					break;

				default:
					break;
				}
			};

		};
		
		//监听动画状态的监听器
		private AnimationListener al = new AnimationListener() {

			@Override
			public void onAnimationStart(Animation animation) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationEnd(Animation animation) {
				String jangpin = lotteryStr[startDegree % 360 / 60];
				Toast.makeText(ChoujiangActivity.this, "恭喜你，得到了"+jangpin, Toast.LENGTH_LONG).show();
				
				//抽完条道另一个界面
				//Intent intent = new Intent(MainActivity.this, SecondActivity.class);
				//intent.putExtra("jiangpin", jangpin);
				//startActivity(intent);
                Intent intent = new Intent(getApplication(),FisrtActivity.class);
                startActivity(intent);
			}
		};


		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);  
			requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
			setContentView(R.layout.choujiang);
			getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.drawable.title);
			
			ActivityCollect.addActivity(this);
			
			setupViews();
			flashLights();

			pointIv.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					int lap = laps[(int) (Math.random() * 4)];
					int angle = angles[(int) (Math.random() * 6)];
					//每次转圈角度增量
					int increaseDegree = lap * 360 + angle;
					//初始化旋转动画，后面的四个参数是用来设置以自己的中心点为圆心转圈
					RotateAnimation rotateAnimation = new RotateAnimation(
							startDegree, startDegree + increaseDegree,
							RotateAnimation.RELATIVE_TO_SELF, 0.5f,
							RotateAnimation.RELATIVE_TO_SELF, 0.5f);
					//将最后的角度赋值给startDegree作为下次转圈的初始角度
					startDegree += increaseDegree;
					//计算动画播放总时间
					long time = (lap + angle / 360) * ONE_WHEEL_TIME;
					//设置动画播放时间
					rotateAnimation.setDuration(time);
					//设置动画播放完后，停留在最后一帧画面上
					rotateAnimation.setFillAfter(true);
					//设置动画的加速行为，是先加速后减速
					rotateAnimation.setInterpolator(ChoujiangActivity.this,
							android.R.anim.accelerate_decelerate_interpolator);
					//设置动画的监听器
					rotateAnimation.setAnimationListener(al);
					//开始播放动画
					pointIv.startAnimation(rotateAnimation);
				}
			});

		}
		
		private void setupViews(){
			titleName=(TextView) findViewById(R.id.titleName1);
			titleName.setText("抽奖");
			lightIv = (ImageView) findViewById(R.id.light);
			pointIv = (ImageView) findViewById(R.id.chou_point);
			wheelIv = (ImageView) findViewById(R.id.main_wheel);	
		}
		
		//控制灯圈动画的方法
		private void flashLights() {

			Timer timer = new Timer();
			TimerTask tt = new TimerTask() {

				@Override
				public void run() {
					// 向UI线程发送消息
					mHandler.sendEmptyMessage(0);

				}
			};

			// 每隔ONE_WHEEL_TIME毫秒运行tt对象的run方法
			timer.schedule(tt, 0, ONE_WHEEL_TIME);
		}

}
