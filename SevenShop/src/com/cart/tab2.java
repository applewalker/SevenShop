package com.cart;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.Seven.Shop.R;
import com.cart.adapter.ShopAdapter;
import com.cart.bean.CarList;
import com.cart.bean.carBean;
import com.google.gson.Gson;
import com.http.util.HttpUtil1;
import com.shop.entity.Response;
import com.squareup.okhttp.Request;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class tab2 extends Fragment{

	private CheckBox checkBox;
	private ListView listView;
	private TextView popTotalPrice;		//结算的价格
	private TextView popDelete;			//删除
	private TextView popCheckOut;		//结算
	private LinearLayout layout;		//结算布局
	private ShopAdapter adapter;		//自定义适配器
	private List<carBean> list;		//购物车数据集合
	int []delIndex;
	String shopIndex;
	private boolean flag = true;		//全选或全取消
	private SharedPreferences preferences;
	
	public static tab2 TAB2;
	
	public tab2() {
		// TODO Auto-generated constructor stub
		TAB2 = this;
	}
	
	@SuppressLint("InflateParams")
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view=inflater.inflate(R.layout.tab02,container,false);

		initViews(view);

		return view;
	}

	//初始化UI界面
	private void initViews(View view){
		checkBox = (CheckBox) view.findViewById(R.id.all_check);
		listView = (ListView)  view.findViewById(R.id.main_listView);
		popTotalPrice = (TextView)  view.findViewById(R.id.shopTotalPrice);
		popDelete = (TextView)  view.findViewById(R.id.delete);
		popCheckOut = (TextView)  view.findViewById(R.id.checkOut);
		layout = (LinearLayout)  view.findViewById(R.id.price_relative);

		ClickListener cl = new ClickListener();

		checkBox.setOnClickListener(cl);
		popDelete.setOnClickListener(cl);
		popCheckOut.setOnClickListener(cl);

		list = new ArrayList<carBean>();
		adapter = new ShopAdapter(getActivity(),this.list,handler,R.layout.cart_item);
		listView.setAdapter(adapter);
	}

	//事件点击监听器
	private final class ClickListener implements OnClickListener{
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.all_check:		//全选
				selectedAll();
				break;
			case R.id.delete:			//删除
				String shopIndex = deleteOrCheckOutShop();
				Toast.makeText(getActivity(), shopIndex, 1).show();
				showDialogDelete(shopIndex);
				break;
			case R.id.checkOut:			//结算
				goCheckOut();
				break;
			}
		}
	}

	public void upData(List<carBean> list1){
		//ShoppingCanst shoppingCanst = new ShoppingCanst();
		if (list == null){
			list = new ArrayList<carBean>();
		}
		if(adapter == null){
			adapter = new ShopAdapter(getActivity(),this.list,handler,R.layout.cart_item);
			listView.setAdapter(adapter);
		}
		if (list.size() != list1.size())
	      	handler.sendMessage(handler.obtainMessage(13, list1));


	}

	//结算
	private void goCheckOut(){
		shopIndex = deleteOrCheckOutShop();
		Intent checkOutIntent = new Intent(getActivity(),CheckoutActivity.class);
		checkOutIntent.putExtra("shopIndex", shopIndex);
		startActivity(checkOutIntent);
	}

	//全选或全取消
	private void selectedAll(){
		for(int i=0;i<list.size();i++){
			ShopAdapter.getIsSelected().put(i, flag);
		}
		adapter.notifyDataSetChanged();
	}

	//删除或结算商品
	private String deleteOrCheckOutShop(){
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<list.size();i++){
			if(ShopAdapter.getIsSelected().get(i)){
				sb.append(i);
				sb.append(",");
			}
		}
		sb.deleteCharAt(sb.length()-1);
		return sb.toString();
	}

	//弹出对话框询问用户是否删除被选中的商品
	private void showDialogDelete(final String str){

		final String[] delShopIndex = str.split(",");    //将删除的商品下标逆序排列  避免商品删除越界
		delIndex=new int[delShopIndex.length];
		int r=delShopIndex.length-1;
		for(int i=0;i<delShopIndex.length;i++){
			delIndex[i]=Integer.valueOf(delShopIndex[r]);
			r--;
		}

		new AlertDialog.Builder(getActivity())
		.setMessage("您确定删除"+str+"商品吗？")
		.setPositiveButton("确定", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface arg0, int arg1) {

				CarList carList = new CarList();
				List<carBean>  listcarbean = new ArrayList<carBean>();
				for(int i=0;i<delShopIndex.length;i++){
					//list.remove(index);
					int size=list.size();
					carBean carBean = new carBean();
					carBean.setGoodscar_id(list.get(delIndex[i]).getGoodscar_id());
					listcarbean.add(carBean);
				}
				carList.setCarBeans(listcarbean);
				HttpUtil1.postAsync("deletecar.php", carList, new HttpUtil1.DataCallBack() {

					@Override
					public void requestSuccess(String result) throws Exception {
						// TODO Auto-generated method stub

						System.out.println(result);
						Gson gson = new Gson();
						Response r = gson.fromJson(result, Response.class);

						for(int i=0;i<delShopIndex.length;i++){
							//list.remove(index);
							int size=list.size();									
							list.remove(delIndex[i]);	
							ShoppingCanst.list.remove(delIndex[i]);
							//连接服务器之后，获取数据库中商品对应的ID，删除商品
							//									list.get(index).getShopId();

						}
						flag = false;
						selectedAll();	//删除商品后，取消所有的选择
						flag = true;	//刷新页面后，设置Flag为true，恢复全选功能
						adapter.notifyDataSetChanged();
						layout.setVisibility(View.GONE);
					}

					@Override
					public void requestFailure(Request request, IOException e) {
						// TODO Auto-generated method stub

					}
				});
			}
		}).setNegativeButton("取消", null)
		.create().show();
	}

	@SuppressLint("HandlerLeak")
	private Handler handler = new Handler(){
		@SuppressWarnings("unchecked")
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			if(msg.what == 10){	//更改选中商品的总价格
				float price = (Float)msg.obj;
				if(price > 0){
					popTotalPrice.setText("￥"+price);
					layout.setVisibility(View.VISIBLE);
				}else{
					layout.setVisibility(View.GONE);
				}
			}else if(msg.what == 11){
				//所有列表中的商品全部被选中，让全选按钮也被选中
				//flag记录是否全被选中
				flag = !(Boolean)msg.obj;
				checkBox.setChecked((Boolean)msg.obj);
			}else if(msg.what == 13){
				if (ShoppingCanst.list == null){
					ShoppingCanst.list = new ArrayList<carBean>();
				}else {
					ShoppingCanst.list.clear();
				}
					list.clear();
				if ((List<carBean>)msg.obj != null){
					list.addAll((List<carBean>)msg.obj);
					ShoppingCanst.list.addAll((List<carBean>)msg.obj);
					//	ShoppingCanst.list.addAll(list);
					for (int i = 0; i < list.size(); i++) {
						adapter.getIsSelected().put(i, false);
					}
					adapter.notifyDataSetChanged();
				}

			}
		}
	};

	public void flashcart() {
		/*String[] shopIndexs = shopIndex.split(",");
		delIndex=new int[shopIndexs.length];
		int r=shopIndexs.length-1;
		for(int i=0;i<shopIndexs.length;i++){
			int position = Integer.valueOf(shopIndexs[i]);
			delIndex[r]=position;
			r--;
		}

		for(int i=0;i<shopIndexs.length;i++){
			list.remove(delIndex[i]);	
			ShoppingCanst.list.remove(delIndex[i]);	
		}

		layout.setVisibility(View.GONE);
		adapter.notifyDataSetChanged();*/
	}
}


