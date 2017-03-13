//package com.personal.order;
//
//
//import android.os.Bundle;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import com.Seven.Shop.R;
//import com.cart.bean.CarList;
//import com.google.gson.Gson;
//import com.http.util.HttpUtil1;
//import com.login.LoginActivity;
//import com.squareup.okhttp.Request;
//import com.view.personal.User;
//
//import android.app.Activity;
//import android.app.AlertDialog;
//import android.content.Context;
//import android.content.Intent;
//import android.content.SharedPreferences;
//import android.view.Menu;
//import android.view.View;
//import android.view.Window;
//import android.view.View.OnClickListener;
//import android.widget.Adapter;
//import android.widget.AdapterView;
//import android.widget.AdapterView.OnItemClickListener;
//import android.widget.BaseAdapter;
//import android.widget.ListView;
//import android.widget.TextView;
//
//public class Test extends Activity {
//
//	private OrderListSimpleAdapter listAdapter;
//	public static ListView listView;                 // �����б���ͼ����
//	private List<Map<String, Object>> list;    // �����б�����
//	public static MyorderActivity orderTestActivity;
//
//	private int position_tmp;    //position����ʱ�洢����
//	TextView titleName;
//	private SharedPreferences preferences;
//	@Override
//	protected void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);  
//		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
//		setContentView(R.layout.myorder); 			
//		//���ñ���Ϊĳ��layout
//		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.drawable.titleback);
//
//		orderTestActivity = this;
//
//		Init(); 
//
//	}
//
//	private void Init() {
//		// TODO Auto-generated method stub
//		titleName=(TextView) findViewById(R.id.titleName2);
//		titleName.setText("�ҵĶ���");
//		View backCenter= findViewById(R.id.backCenter);   //���ؼ�
//
//
//		//ʵ�����б���ͼ
//				listView = new ListView(this);        //�½�һ��list
//
//				//ʵ�����б�����
//				list = new ArrayList<Map<String, Object>>();  
//				preferences = getApplication().getSharedPreferences("loginState", Context.MODE_PRIVATE);
//				 Order order = new Order();
//				 order.setUser_id(preferences.getInt("userid", 0));
//					HttpUtil1.postAsync("showorder.php", order, new HttpUtil1.DataCallBack() {
//						
//						@Override
//						public void requestSuccess(String result) throws Exception {
//							// TODO Auto-generated method stub
//							
//							System.out.println(result);
//		                    OrderList orderList = new OrderList();
//		                    Gson gson = new Gson();
//		                    orderList = gson.fromJson(result, OrderList.class);
//		                    try{
//
//		    					for(int i=0;i<orderList.getOrders().size();i++){
//		    						//ʵ��һ���б������������������ݿ�������ӵ��б�������ÿһ��map�����ݶ�ӦListView��������һ��
//		    						Map<String, Object> map = new HashMap<String, Object>();
//		    						map.put("orderid", orderList.getOrders().get(i).getOrder_id());
//		    						map.put("pic",orderList.getImagepath());
//		    						map.put("title",orderList.getOrders().get(i).getTotal_price());
//		    						map.put("content",orderList.getOrders().get(i).getTitle());
//		    						map.put("button","");
//		    						list.add(map);
//		    						/*Map<String, Object> map = new HashMap<String, Object>();
//		    						map.put("pic",R.drawable.demo);
//		    						map.put("title","��ã����Ǳ���"+Integer.toString(i));
//		    						map.put("content","�������ݣ�������������");
//		    						map.put("button","");
//		    						list.add(map);*/
//		    					}
//		    					listAdapter.notifyDataSetChanged();
//		    				}
//		    				catch(Exception e){
//		    					e.printStackTrace();}
//		                  //��ʾ�б���ͼ
//		    				MyorderActivity.this.setContentView(listView);
//						}
//						
//						@Override
//						public void requestFailure(Request request, IOException e) {
//							// TODO Auto-generated method stub
//							
//						}
//					});
//
//				
//
//				//ʵ����������������ɲμ�SimpleAdapter��ʹ��
//				listAdapter = new OrderListSimpleAdapter(MyorderActivity.this, list, R.layout.orderlistview_item, new String[] {
//						"pic", "title", "content","button"}, new int[] {R.id.order_pic, R.id.title, R.id.content,R.id.order_delete});
//				
//				listView.setAdapter(listAdapter);  
//
//				//���ListViewÿһ�еĵ�������¼����˴������ü����������
//				listView.setOnItemClickListener(new OnItemClickListener() {
//
//					public void onItemClick(AdapterView<?> arg0, View arg1,
//							int position, long id) {
//
//						position_tmp = position;
//
//						new AlertDialog.Builder(orderTestActivity)
//						.setTitle("ListView")
//						.setMessage("�����˵�"+Integer.toString(position_tmp)+"�У�")
//						.show();
//					}
//				}); 
//				
//				
//
//				
//		
//
//		backCenter.setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View arg0) {
//				// TODO Auto-generated method stub
//				finish();
//
//			}
//		});
//	}
//
//
//
//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.myorder, menu);
//		return true;
//	}
//
//}