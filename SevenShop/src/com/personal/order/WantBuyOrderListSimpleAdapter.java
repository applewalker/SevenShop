package com.personal.order;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.http.util.HttpUtil1;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.squareup.okhttp.Request;

import android.app.AlertDialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;  

public class WantBuyOrderListSimpleAdapter extends BaseAdapter {

	private LayoutInflater layoutinflater;
	private List<Map<String,Object>> list;
	private int layoutId;
	private String flag[];
	private int ItemId[];

	public WantBuyOrderListSimpleAdapter(Context context,List<Map<String,Object>> list,
			int layoutId,String flag[],int ItemId[]){

		this.layoutinflater = LayoutInflater.from(context);
		this.list = list;
		this.layoutId = layoutId;
		this.flag = flag;
		this.ItemId = ItemId;

	}

	//调用并显示view的条数
	@Override
	public int getCount(){

		return list.size();
	}

	@Override
	public Object getItem(int arg0){

		return 0;
	}

	@Override
	public long getItemId(int arg0){

		return 0;
	}

	@Override
	public View getView(final int position,View convertView,ViewGroup parent){

		convertView = layoutinflater.inflate(layoutId, null);

		//填充数据到对应的控件中，注意此处的textview应写在最后面，才能正常实例化控件
		for(int i=0;i<flag.length;i++){

			if(convertView.findViewById(ItemId[i]) instanceof ImageView){
				ImageView imageView = (ImageView) convertView.findViewById(ItemId[i]);
				ImageLoader.getInstance().displayImage((String) list.get(position).get(flag[i]), imageView);
			//	imageView.setBackgroundResource((Integer) list.get(position).get(flag[i]));
				Log.e("type","imageview");
			}
			else if(convertView.findViewById(ItemId[i]) instanceof Button){
				
				Button button = (Button) convertView.findViewById(ItemId[i]);
				
				if(flag[i].equals(""))
				button.setText((String) list.get(position).get(flag[i]));
				
				button.setOnClickListener(
						new Button.OnClickListener() {
							@Override
							public void onClick(View v) {
								/*new AlertDialog.Builder(MyorderActivity.orderTestActivity)
								.setTitle("Button")
								.setMessage("按钮触发监听事件！"+"按钮"+position)
								.show();*/
							   Order order = new Order();
							   order.setOrder_id(Integer.valueOf(list.get(position).get("orderid")+""));
							   HttpUtil1.postAsync("deleteorder.php", order, new HttpUtil1.DataCallBack() {
								
								@Override
								public void requestSuccess(String result) throws Exception {
									// TODO Auto-generated method stub
									 list.remove(position);
									   
									 WantBuyOrderListSimpleAdapter.this.notifyDataSetChanged();
									   
								}
								
								@Override
								public void requestFailure(Request request, IOException e) {
									// TODO Auto-generated method stub
									
								}
							});
				
							  
								//layoutinflater.inflate(R.id.order_Listview, root)
								
							}
						});
			}
			else if(convertView.findViewById(ItemId[i]) instanceof TextView){
				
				TextView textView = (TextView) convertView.findViewById(ItemId[i]);
				
				textView.setText(String.valueOf(list.get(position).get(flag[i])) ); //文本添加
				Log.e("type","textview");
			}
			else{
				//预留其他控件处理，但必需写在TextView的前面
			}
		}
		return convertView;
	}

}