package com.personal.address;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.Selectarea.Selectarea;
import com.Seven.Shop.R;
import com.cart.ShoppingCanst;
import com.cart.bean.AddressBean;
import com.cart.bean.AddressList;
import com.google.gson.Gson;
import com.http.util.HttpUtil1;
import com.shop.entity.Response;
import com.squareup.okhttp.Request;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AddaddressActivity extends Activity {
	private TextView titleName;
	private EditText receiver_name;
	private TextView smearedAddress;
	private EditText detialAddress;
	private Button addAddressbtn;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);	
		setContentView(R.layout.addaddress);		
		//设置标题为某个layout
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.drawable.titleback);
		
		init();
	}

	private void init() {
		// TODO Auto-generated method stub
		titleName=(TextView) findViewById(R.id.titleName2);
		titleName.setText("订单");
		View backCenter= findViewById(R.id.backCenter);   //返回键
		receiver_name=(EditText) findViewById(R.id.add_receiver_Text);
		smearedAddress=(TextView) findViewById(R.id.smearedAddress);
		detialAddress=(EditText) findViewById(R.id.detailAddress);
		addAddressbtn=(Button) findViewById(R.id.add_addressbtn);
		
		
		smearedAddress.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getApplication(),Selectarea.class);
				startActivityForResult(intent, 0);				
				
			}
		});
		
		addAddressbtn.setOnClickListener(new OnClickListener() {
			
			

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				SharedPreferences preferences;
				AddressBean address = new AddressBean();
				preferences = getApplication().getSharedPreferences("loginState", Context.MODE_PRIVATE);
				address.setName(receiver_name.getText().toString());
				address.setSmearedAddress(smearedAddress.getText().toString());
				address.setDetailAddress(detialAddress.getText().toString());
				address.setUser_id(preferences.getInt("userid", 0));
		        HttpUtil1.postAsync("addaddress.php", address, new HttpUtil1.DataCallBack() {
					
					@Override
					public void requestSuccess(String result) throws Exception {
						// TODO Auto-generated method stub
						
						//System.out.println(result);
						Gson gson = new Gson();
						Response response = new Response();
						response = gson.fromJson(result, Response.class);
						if (response.getSuccess() == 0){
							Intent intent=getIntent();
							
							intent.putExtra("receiver_name", receiver_name.getText().toString());
							intent.putExtra("receiver_address", smearedAddress.getText().toString()+" "+detialAddress.getText().toString());
							setResult(111, intent);
							
							finish();
						}else{
							Toast.makeText(getApplicationContext(), "添加失败", Toast.LENGTH_LONG).show();
						}
						
						
						
					}
					
					@Override
					public void requestFailure(Request request, IOException e) {
						// TODO Auto-generated method stub
					
					}
				});
				
				
			}
		});
		
		
		
		backCenter.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				finish();

			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.addaddress, menu);
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
	
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {  
        // TODO Auto-generated method stub  
        super.onActivityResult(requestCode, resultCode, data);  
          
        if(resultCode == 110){  
            if(requestCode == 0){  
                String request = data.getStringExtra("smearedAddress");//接收返回数据  
                smearedAddress.setText(request);  
            }  
        }  
          
    }  
}
