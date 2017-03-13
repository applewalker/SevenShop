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
	private TextView popTotalPrice;		//����ļ۸�
	private TextView popDelete;			//ɾ��
	private TextView popCheckOut;		//����
	private LinearLayout layout;		//���㲼��
	private ShopAdapter adapter;		//�Զ���������
	private List<carBean> list;		//���ﳵ���ݼ���
	int []delIndex;
	String shopIndex;
	private boolean flag = true;		//ȫѡ��ȫȡ��
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

	//��ʼ��UI����
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

	//�¼����������
	private final class ClickListener implements OnClickListener{
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.all_check:		//ȫѡ
				selectedAll();
				break;
			case R.id.delete:			//ɾ��
				String shopIndex = deleteOrCheckOutShop();
				Toast.makeText(getActivity(), shopIndex, 1).show();
				showDialogDelete(shopIndex);
				break;
			case R.id.checkOut:			//����
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

	//����
	private void goCheckOut(){
		shopIndex = deleteOrCheckOutShop();
		Intent checkOutIntent = new Intent(getActivity(),CheckoutActivity.class);
		checkOutIntent.putExtra("shopIndex", shopIndex);
		startActivity(checkOutIntent);
	}

	//ȫѡ��ȫȡ��
	private void selectedAll(){
		for(int i=0;i<list.size();i++){
			ShopAdapter.getIsSelected().put(i, flag);
		}
		adapter.notifyDataSetChanged();
	}

	//ɾ���������Ʒ
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

	//�����Ի���ѯ���û��Ƿ�ɾ����ѡ�е���Ʒ
	private void showDialogDelete(final String str){

		final String[] delShopIndex = str.split(",");    //��ɾ������Ʒ�±���������  ������Ʒɾ��Խ��
		delIndex=new int[delShopIndex.length];
		int r=delShopIndex.length-1;
		for(int i=0;i<delShopIndex.length;i++){
			delIndex[i]=Integer.valueOf(delShopIndex[r]);
			r--;
		}

		new AlertDialog.Builder(getActivity())
		.setMessage("��ȷ��ɾ��"+str+"��Ʒ��")
		.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {
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
							//���ӷ�����֮�󣬻�ȡ���ݿ�����Ʒ��Ӧ��ID��ɾ����Ʒ
							//									list.get(index).getShopId();

						}
						flag = false;
						selectedAll();	//ɾ����Ʒ��ȡ�����е�ѡ��
						flag = true;	//ˢ��ҳ�������FlagΪtrue���ָ�ȫѡ����
						adapter.notifyDataSetChanged();
						layout.setVisibility(View.GONE);
					}

					@Override
					public void requestFailure(Request request, IOException e) {
						// TODO Auto-generated method stub

					}
				});
			}
		}).setNegativeButton("ȡ��", null)
		.create().show();
	}

	@SuppressLint("HandlerLeak")
	private Handler handler = new Handler(){
		@SuppressWarnings("unchecked")
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			if(msg.what == 10){	//����ѡ����Ʒ���ܼ۸�
				float price = (Float)msg.obj;
				if(price > 0){
					popTotalPrice.setText("��"+price);
					layout.setVisibility(View.VISIBLE);
				}else{
					layout.setVisibility(View.GONE);
				}
			}else if(msg.what == 11){
				//�����б��е���Ʒȫ����ѡ�У���ȫѡ��ťҲ��ѡ��
				//flag��¼�Ƿ�ȫ��ѡ��
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


