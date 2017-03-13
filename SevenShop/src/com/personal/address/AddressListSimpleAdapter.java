package com.personal.address;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.Seven.Shop.R;
import com.cart.ShoppingCanst;
import com.cart.bean.AddressBean;
import com.cart.bean.AddressList;
import com.google.gson.Gson;
import com.http.util.HttpUtil1;
import com.shop.entity.Response;
import com.squareup.okhttp.Request;

import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.Preference;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;  

public class AddressListSimpleAdapter extends BaseAdapter {

	private LayoutInflater layoutinflater;
	private List<Map<String,Object>> list;
	private int layoutId;
	private String flag[];
	private int ItemId[];
	private Context context;

	public AddressListSimpleAdapter(Context context,List<Map<String,Object>> list,
			int layoutId,String flag[],int ItemId[]){
		
        this.context = context;
		this.layoutinflater = LayoutInflater.from(context);
		this.list = list;
		this.layoutId = layoutId;
		this.flag = flag;
		this.ItemId = ItemId;

	}

	//���ò���ʾview������
	@Override
	public int getCount(){

		return list.size();
	}

	@Override
	public Object getItem(int arg0){

		return arg0;
	}

	@Override
	public long getItemId(int arg0){

		return 0;
	}

	@Override
	public View getView(final int position,View convertView,ViewGroup parent){

		convertView = layoutinflater.inflate(layoutId, null);
        final int address_id = Integer.valueOf(list.get(position).get("addressid")+"");
		//������ݵ���Ӧ�Ŀؼ��У�ע��˴���textviewӦд������棬��������ʵ�����ؼ�
		for(int i=0;i<flag.length;i++){

			if(convertView.findViewById(ItemId[i]) instanceof ImageView){
				ImageView imageView = (ImageView) convertView.findViewById(ItemId[i]);
				imageView.setBackgroundResource((Integer) list.get(position).get(flag[i]));
			}
			else if(ItemId[i]==R.id.address_delete){

				Button button = (Button) convertView.findViewById(ItemId[i]);	
				if(flag[i].equals(""))  //�ж��Ƿ�Ϊ����д���ı�				
					button.setText((String) list.get(position).get(flag[i]));

				button.setOnClickListener(
						new Button.OnClickListener() {
							@Override
							public void onClick(View v) {
								SharedPreferences preferences;
								preferences = context.getSharedPreferences("loginState", Context.MODE_PRIVATE);
								ShoppingCanst.addressList = new ArrayList<AddressBean>();
								AddressBean address = new AddressBean();
								System.out.println("aaaaaaaaaaaaaaaaaaaaaaa"+address_id);
								address.setAddress_id(address_id);
						        HttpUtil1.postAsync("deletedaddress.php", address, new HttpUtil1.DataCallBack() {
									
									@Override
									public void requestSuccess(String result) throws Exception {
										// TODO Auto-generated method stub
										
										System.out.println(result);
										Gson gson = new Gson();
										Response response = new Response();
										response = gson.fromJson(result, Response.class);
										
										if (response.getSuccess() == 0){
											list.remove(position);
											/*new AlertDialog.Builder(MyaddressActivity.addTestActivity)
											.setTitle("Button")
											.setMessage("��ɾ����"+position+"��")
											.show();*/
											AddressListSimpleAdapter.this.notifyDataSetChanged();  //ˢ���б�
										}else{
											Toast.makeText(context, "ɾ��ʧ��", Toast.LENGTH_LONG).show();
										}
										
										
									}
									
									@Override
									public void requestFailure(Request request, IOException e) {
										// TODO Auto-generated method stub
									
									}
								});

							}
						});

			}else if(ItemId[i]==R.id.address_edit){

				Button edit = (Button) convertView.findViewById(ItemId[i]);				
				edit.setText((String) list.get(position).get(flag[i]));
				edit.setVisibility(View.GONE);
				edit.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						/*new AlertDialog.Builder(MyaddressActivity.addTestActivity)
						.setTitle("Button")
						.setMessage("��Ҫ�༭��"+position+"��")
						.show();*/
					}
				});
				

				//************Ԥ�������ؼ�����������д��TextView��ǰ��***************
			}else if(convertView.findViewById(ItemId[i]) instanceof TextView){ //д�ջ���

				TextView textView = (TextView) convertView.findViewById(ItemId[i]);

				textView.setText((String) list.get(position).get(flag[i])); //�ı����

			}
		}
		return convertView;
	}

}