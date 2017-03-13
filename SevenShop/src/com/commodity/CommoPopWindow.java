package com.commodity;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import com.Seven.Shop.R;
import com.cart.CheckoutActivity;
import com.cart.ShoppingCanst;
import com.cart.bean.carBean;
import com.google.gson.Gson;
import com.http.util.HttpUtil1;
import com.login.LoginActivity;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.shop.entity.Goods;
import com.shop.entity.Response;
import com.squareup.okhttp.Request;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.TextView;

/**
 * 宝贝详情界面的弹窗
 * @author http://yecaoly.taobao.com
 *
 */
@SuppressLint("CommitPrefEdits")
public class CommoPopWindow implements 
OnDismissListener, OnClickListener,OnCheckedChangeListener {
	public HashMap<String, Object> allHashMap=new HashMap<String,Object>();
	
	private int flag = 0; //属性是否选全的标志
	private float wholeprice;
	private int wholenumber;
	private int buynumber = 1;
	private ListView listView;
	private PropertyAdapter propertyAdapter;
	private ArrayList<HashMap<String, Object>> list;
	
	private ImageView iv_adapter_grid_pic;
	private TextView goodsdeitalnameandnumber;
	private Goods goods;
	private TextView pop_add,pop_reduce,pop_num,pop_ok;
	private ImageView pop_close;
	private RelativeLayout pop_layout;
	private PopupWindow popupWindow;//popupWindow2;
	//private OnItemClickListener listener;
	private final int ADDORREDUCE=1;
	private Context context;
	/**保存选择的颜色的数据*/
	private String str_color="";
	/**保存选择的类型的数据*/
	private String str_type="";
	
	private HashMap<String, Object> resultlist = new HashMap<String, Object>();
	public SharedPreferences preferences;



	@SuppressWarnings("deprecation")
	public CommoPopWindow(Context context) {
		this.context=context;
		View view=LayoutInflater.from(context).inflate(R.layout.popwindow, null);
		//View background=LayoutInflater.from(context).inflate(R.layout.pop_bg, null);
		pop_layout = (RelativeLayout) view.findViewById(R.id.pop_layout);
		pop_add=(TextView) view.findViewById(R.id.pop_add);
		pop_reduce=(TextView) view.findViewById(R.id.pop_reduce);
		pop_num=(TextView) view.findViewById(R.id.pop_num);
		pop_ok=(TextView) view.findViewById(R.id.pop_ok);
		pop_close=(ImageView) view.findViewById(R.id.pop_close);
		iv_adapter_grid_pic = (ImageView) view.findViewById(R.id.iv_adapter_grid_pic);
		
		goodsdeitalnameandnumber = (TextView) view.findViewById(R.id.goodsdeitalnameandnumber);
		
		listView = (ListView) view.findViewById(R.id.lv_property);
		list = new ArrayList<HashMap<String,Object>>();
		
	
		
		
//		group2.setOnCheckedChangeListener(this);
//		group3.setOnCheckedChangeListener(this);
//		pop_choice_black.setOnClickListener(this);
//		pop_choice_white.setOnClickListener(this);
		pop_add.setOnClickListener(this);
		pop_reduce.setOnClickListener(this);
		pop_ok.setOnClickListener(this);
		pop_close.setOnClickListener(this);

		popupWindow=new PopupWindow(view, LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT,true);
		//popupWindow2=new PopupWindow(background, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT,true);
		//设置popwindow的动画效果


		popupWindow.setAnimationStyle(R.style.popWindow_anim_style);
		//popupWindow2.setAnimationStyle(R.style.bg_anim_style);
		popupWindow.setBackgroundDrawable(new BitmapDrawable());
		popupWindow.setTouchable(true);
		popupWindow.setOutsideTouchable(true);	
		popupWindow.getContentView().setFocusableInTouchMode(true);
		popupWindow.getContentView().setFocusable(true);
		//popupWindow.setFocusable(true);

		popupWindow.setOnDismissListener(this);// 当popWindow消失时的监听
		//		popupWindow.getContentView().setOnKeyListener(new OnKeyListener()
		//		{
		//		    public boolean onKey(View v, int keyCode, KeyEvent event)
		//		    {
		//		    	if (keyCode == KeyEvent.KEYCODE_MENU && event.getRepeatCount() == 0
		//                        && event.getAction() == KeyEvent.ACTION_DOWN) {
		//                    if (popupWindow != null && popupWindow.isShowing()) {
		//                    	popupWindow.dismiss();
		//                    }
		//                    return true;
		//                }
		//                return false;
		//		    }
		//		});
	}	
	//更新数据 
	
	public void upData(Goods goods){
		this.goods = goods;
		goodsdeitalnameandnumber.setText("还没有选择");
		
		handler.sendMessage(handler.obtainMessage(1, goods.getImage_path()));
		for (int i = 0; i < goods.getType().size(); i++) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			List<String> mlist = new ArrayList<String>();
			map.put("type", goods.getType().get(i).getName());
			//System.out.println(goods.getType().get(i).getLable().size());
			for (int j = 0; j < goods.getType().get(i).getLable().size(); j++) {
				//System.out.println(goods.getType().get(i).getLable().get(j).getDetial());
				mlist.add(goods.getType().get(i).getLable().get(j).getDetial());
			}
			map.put("lable", mlist);
			list.add(map);
		}
		propertyAdapter = new PropertyAdapter(context, list,message);
		listView.setAdapter(propertyAdapter);
		
	}
	
	
	public void setvi(){
		pop_layout.setVisibility(View.VISIBLE);
	}
	public void setinvi(){
		pop_layout.setVisibility(View.INVISIBLE);
	}
	// 当popWindow消失时响应
	@Override
	public void onDismiss() {
		popupWindow.update();
		//popupWindow2.dismiss();
		CommodityActivity.setallblack(1,context);

	}
	/**弹窗显示的位置*/  
	public void showAsDropDown(View parent){
		//setvi();
		//popupWindow2.showAtLocation(parent, Gravity.BOTTOM, 0, 0);
		//popupWindow2.update();
		setvi();
		popupWindow.showAtLocation(parent, Gravity.BOTTOM, 0, 0);
		popupWindow.update();


	}
	/**消除弹窗*/
	public void dissmiss(){

		popupWindow.dismiss();



	}
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		switch(checkedId){

		}
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.pop_add:
			if (!pop_num.getText().toString().equals(String.valueOf(wholenumber))) {

				String num_add=Integer.valueOf(pop_num.getText().toString())+ADDORREDUCE+"";
				buynumber = Integer.valueOf(num_add);
				pop_num.setText(num_add);
				goodsdeitalnameandnumber.setText("￥"+wholeprice*buynumber+"     库存"+wholenumber);
			}else {
				Toast.makeText(context, "不能超过最大产品数量", Toast.LENGTH_SHORT).show();
			}

			break;
		case R.id.pop_reduce:
			if (!pop_num.getText().toString().equals("1")) {
				String num_reduce=Integer.valueOf(pop_num.getText().toString())-ADDORREDUCE+"";
				buynumber = Integer.valueOf(num_reduce);
				pop_num.setText(num_reduce);
				goodsdeitalnameandnumber.setText("￥"+wholeprice*buynumber+"     库存"+wholenumber);
			}else {			
				Toast.makeText(context, "购买数量不能低于1件", Toast.LENGTH_SHORT).show();
			}
			break;
		case R.id.pop_close:
			dissmiss();
			break;
		case R.id.pop_ok:
			//listener.onClickOKPop();
			/*if (str_color.equals("")) {
				Toast.makeText(context, "亲，你还没有选择颜色哟~", Toast.LENGTH_SHORT).show();
			}else if (str_type.equals("")) {
				Toast.makeText(context, "亲，你还没有选择类型哟~",Toast.LENGTH_SHORT).show();*/
			if (flag == 0){
				Toast.makeText(context, "亲，你没有选全~",Toast.LENGTH_SHORT).show();
			}else {
//				allHashMap.put("color",str_color);
//				allHashMap.put("type",str_type);
//				allHashMap.put("num",pop_num.getText().toString());
				//allHashMap.put("id",Data.arrayList_cart_id+=1);

				//Data.arrayList_cart.add(allHashMap);
				if(CommodityActivity.CartorBuy == 0)
				{
					preferences = context.getSharedPreferences("loginState", Context.MODE_PRIVATE);
					if (preferences.getBoolean("loginState", false) == false) {
						Intent intent=new Intent(context, LoginActivity.class);
						context.startActivity(intent);
					}else{
						carBean carBean = new carBean();
						carBean.setGoods_id(goods.getGoods_id());
						carBean.setGoods_name(goods.getGoods_name());
						carBean.setImagepath(goods.getImage_path());
						carBean.setState(0);
						String string = new String();
						for (int i = 0; i < resultlist.size(); i++) {
							string = string + goods.getType().get(i).getName()+":"+resultlist.get(goods.getType().get(i).getName());
						}
						carBean.setGoodsdescribe(string);
						carBean.setNumber(buynumber);
						carBean.setPrice(wholeprice);
						carBean.setUser_id(preferences.getInt("userid", 0));
						HttpUtil1.postAsync("updatecar.php", carBean, new HttpUtil1.DataCallBack() {
							
							@Override
							public void requestSuccess(String result) throws Exception {
								// TODO Auto-generated method stub
								
								
								Gson gson = new Gson();
								Response  r = gson.fromJson(result, Response.class);
							   // System.out.println("asfdasfsafffffffffffffffff"+good.getType().get(1).getName());
								if (r.getSuccess() == 0){
									Toast.makeText(context, "添加成功", Toast.LENGTH_LONG).show();
								}else{
									Toast.makeText(context, "添加失败 ", Toast.LENGTH_LONG).show();
								}
								
								
								
							}
							
							@Override
							public void requestFailure(Request request, IOException e) {
								// TODO Auto-generated method stub
							
							}
						});
					}
				}
				else if(CommodityActivity.CartorBuy == 1)
				{
					preferences = context.getSharedPreferences("loginState", Context.MODE_PRIVATE);
					if (preferences.getBoolean("loginState", false) == false) {
						Intent intent=new Intent(context, LoginActivity.class);
						context.startActivity(intent);
					}else {
					
						carBean carBean = new carBean();
						carBean.setGoods_id(goods.getGoods_id());
						carBean.setGoods_name(goods.getGoods_name());
						carBean.setImagepath(goods.getImage_path());
						carBean.setState(0);
						String string = new String();
						for (int i = 0; i < resultlist.size(); i++) {
							string = string + goods.getType().get(i).getName()+":"+resultlist.get(goods.getType().get(i).getName());
						}
						carBean.setGoodsdescribe(string);
						carBean.setNumber(buynumber);
						carBean.setPrice(wholeprice);
						preferences = context.getSharedPreferences("loginState", Context.MODE_PRIVATE);
						carBean.setUser_id(preferences.getInt("userid", 0));
						if (ShoppingCanst.list == null){
							ShoppingCanst.list = new ArrayList<carBean>();
						}else {
							ShoppingCanst.list.clear();
						}
						ShoppingCanst.list.add(carBean);
						Intent intent = new Intent(context,CheckoutActivity.class);
						context.startActivity(intent);
						//Toast.makeText(context, "跳到订单",Toast.LENGTH_SHORT).show();
					}
				}
				setSaveData();
				dissmiss();
			}
			break;

		default:
			break;
		}
	}
	/**保存购物车的数据*/
	private void setSaveData(){
		//		SharedPreferences sp=context.getSharedPreferences("SAVE_CART", Context.MODE_PRIVATE);
		//		Editor editor=sp.edit();
		//		editor.putInt("ArrayCart_size", Data.arrayList_cart.size());
		//		for (int i = 0; i < Data.arrayList_cart.size(); i++) {
		//			editor.remove("ArrayCart_type_"+i);
		//			editor.remove("ArrayCart_color_"+i);
		//			editor.remove("ArrayCart_num_"+i);
		//			editor.putString("ArrayCart_type_"+i, Data.arrayList_cart.get(i).get("type").toString());
		//			editor.putString("ArrayCart_color_"+i, Data.arrayList_cart.get(i).get("color").toString());
		//			editor.putString("ArrayCart_num_"+i, Data.arrayList_cart.get(i).get("num").toString());
		//			
		//		}
	}
	MyMessage message = new MyMessage(){

		public void upView(HashMap<String, Object> result){
			flag = 1;
			List<Float> floats = new ArrayList<Float>();
			List<String> num = new ArrayList<String>();
			Set set = result.keySet();
			Iterator iter = set.iterator();
			while (iter.hasNext()) {
			String key = (String) iter.next();
			    for (int i = 0; i < goods.getType().size(); i++) {
					if (key.equals(goods.getType().get(i).getName())){
						for (int j = 0; j < goods.getType().get(i).getLable().size(); j++) {
							if (result.get(key).equals(goods.getType().get(i).getLable().get(j).getDetial())){
								resultlist.put(key, goods.getType().get(i).getLable().get(j).getDetial());
								floats.add(goods.getType().get(i).getLable().get(j).getPrice());
								num.add(String.valueOf(goods.getType().get(i).getLable().get(j).getNumber()));
							}
						}
					}
				}
			}
			float price = 0;
			int number = 0;
			for (int i = 0; i < floats.size(); i++) {
				price +=  floats.get(i);
				number += Integer.valueOf(num.get(i));
			}
			wholeprice = price;
			wholenumber = number;
			goodsdeitalnameandnumber.setText("￥"+price*buynumber+"     库存"+number);
			
		}
		
	};
	
	private Handler handler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			if(msg.what == 1){		//异步更改地址	
				String imagepath = (String)msg.obj;
				ImageLoader.getInstance().displayImage(imagepath, iv_adapter_grid_pic);
			}
		}
	};
	

}
