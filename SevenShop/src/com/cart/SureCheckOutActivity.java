package com.cart;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.Seven.Shop.R;
import com.cart.bean.carBean;
import com.flyco.animation.BaseAnimatorSet;
import com.flyco.dialog.listener.OnBtnClickL;
import com.flyco.dialog.widget.NormalDialog;
import com.http.util.HttpUtil1;
import com.personal.order.Order;
import com.shop.entity.ActivityCollect;
import com.squareup.okhttp.Request;
import com.view.UI.FisrtActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

public class SureCheckOutActivity extends Activity {
	TextView titleName;
	TextView will_pay;
	View backCenter;
	Button cancel_check;
	Button sure_check;
	String pay_price=null;
	int order_id;

	private BaseAnimatorSet bas_in;
	private BaseAnimatorSet bas_out;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);  
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.surecheckout); 			
		//设置标题为某个layout		
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.drawable.titleback);
		
		ActivityCollect.addActivity(this);
		
//		bas_in = new BounceTopEnter();
//        bas_out = new SlideBottomExit();

		init();
	}

	private void init() {
		// TODO Auto-generated method stub
		titleName=(TextView) findViewById(R.id.titleName2);
		titleName.setText("付款");
		sure_check=(Button) findViewById(R.id.check_surecheck);
		cancel_check=(Button) findViewById(R.id.check_cancelcehck);
		will_pay=(TextView) findViewById(R.id.willpay_price); //先是将要支付多少元

		Intent intent=getIntent();
		pay_price=intent.getStringExtra("check_price");
		order_id=intent.getIntExtra("order_id", 0);
		
		will_pay.setText("即将付款"+pay_price+"元");
		backCenter=findViewById(R.id.backCenter);
		backCenter.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				final NormalDialog dialog = new NormalDialog(SureCheckOutActivity.this);

				dialog.content("确认要取消付款吗？")//
				.style(NormalDialog.STYLE_TWO)//
				.titleTextSize(23)//
				.showAnim(bas_in)//
				.dismissAnim(bas_out)//
				.show();
				
				dialog.btnText("否","是");
				dialog.cornerRadius(15);
				dialog.titleTextColor(0x99f9923e);
				dialog.btnPressColor(0x50f9923e);
				dialog.setOnBtnClickL(
						new OnBtnClickL() {
							@Override
							public void onBtnClick() {
								dialog.dismiss();
							}
						},
						new OnBtnClickL() {
							@Override
							public void onBtnClick() {
								Intent intent = new Intent(getApplication(),FisrtActivity.class);
								startActivity(intent);
								dialog.dismiss();
							}
						});

			}
		});

		cancel_check.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				final NormalDialog dialog = new NormalDialog(SureCheckOutActivity.this);

				dialog.content("确认要取消付款吗？")//
				.style(NormalDialog.STYLE_TWO)//
				.titleTextSize(23)//
				.showAnim(bas_in)//
				.dismissAnim(bas_out)//
				.show();
				
				dialog.btnText("否","是");
				dialog.cornerRadius(15);
				dialog.titleTextColor(0x99f9923e);
				dialog.btnPressColor(0x50f9923e);
				dialog.setOnBtnClickL(
						new OnBtnClickL() {
							@Override
							public void onBtnClick() {
								dialog.dismiss();
							}
						},
						new OnBtnClickL() {
							@Override
							public void onBtnClick() {
								Intent intent = new Intent(getApplication(),FisrtActivity.class);
								startActivity(intent);

								dialog.dismiss();
							}
						});
			}
		});

		sure_check.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				/**
				 * 确认支付
				 */
				Order order = new Order();
				order.setOrder_id(order_id);
				Intent intent = getIntent();
				List<carBean> l = new ArrayList<carBean>();
				final String shopIndex = intent.getStringExtra("shopIndex");
				HttpUtil1.postAsync("buy.php", order, new HttpUtil1.DataCallBack() {
					
					@Override
					public void requestSuccess(String result) throws Exception {
						// TODO Auto-generated method stub
						Intent intent=new Intent(SureCheckOutActivity.this,ChecksuccessActivity.class);
						intent.putExtra("shopIndex", shopIndex);
						intent.putExtra("check_price", pay_price);
						startActivityForResult(intent, 0);
					}
					
					@Override
					public void requestFailure(Request request, IOException e) {
						// TODO Auto-generated method stub
						
					}
				});
				
			}
		});
	}

	public boolean onKeyDown(int keyCode, KeyEvent event) {

		if (keyCode == KeyEvent.KEYCODE_BACK
				&& event.getRepeatCount() == 0) {
			final NormalDialog dialog = new NormalDialog(SureCheckOutActivity.this);

			dialog.content("确认要取消付款吗？")//
			.style(NormalDialog.STYLE_TWO)//
			.titleTextSize(23)//
			.showAnim(bas_in)//
			.dismissAnim(bas_out)//
			.show();
			
			dialog.btnText("否","是");
			dialog.cornerRadius(15);
			dialog.titleTextColor(0x99f9923e);
			dialog.btnPressColor(0x50f9923e);
			dialog.setOnBtnClickL(
					new OnBtnClickL() {
						@Override
						public void onBtnClick() {
							dialog.dismiss();
						}
					},
					new OnBtnClickL() {
						@Override
						public void onBtnClick() {
							finish();

							dialog.dismiss();
						}
					});
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.sure_check_out, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		if(resultCode == 0000){          //修改名字
			finish();
			Intent intent=getIntent();
			setResult(0000, intent);
		}

		super.onActivityResult(requestCode, resultCode, data);
	}
}
