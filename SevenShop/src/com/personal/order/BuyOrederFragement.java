package com.personal.order;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.Seven.Shop.R;
import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class BuyOrederFragement extends Fragment {

	private View view;

	private OrderListSimpleAdapter listAdapter;
	private ListView listView; // 声明列表视图对象
	private List<Map<String, Object>> list; // 声明列表容器

	private int position_tmp; // position的临时存储变量
	TextView titleName;

	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.myorder, null);
		listView = (ListView) view.findViewById(R.id.order_Listview);
		//实例化列表容器
	     list = new ArrayList<Map<String, Object>>();  
		//实例化适配器，具体可参见SimpleAdapter的使用
		listAdapter = new OrderListSimpleAdapter(getActivity(), list, R.layout.orderlistview_item, new String[] {
				"pic", "title", "content","button"}, new int[] {R.id.order_pic, R.id.title, R.id.content,R.id.order_delete});
		
		listView.setAdapter(listAdapter);  

		//添加ListView每一行的点击监听事件，此处可设置监听处理操作
		listView.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long id) {

				position_tmp = position;

				/*new AlertDialog.Builder(MyorderActivity.orderTestActivity)
				.setTitle("ListView")
				.setMessage("你点击了第"+Integer.toString(position_tmp)+"行！")
				.show();*/
			}
		}); 

		return view;
	}

	public void Init() {
       

		 
				

		for(int i=0;i<OrderConst.buyorder.size();i++){
			//实例一个列表数据容器，并将数据库数据添加到列表容器，每一个map的数据对应ListView中完整的一项
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("orderid", OrderConst.buyorder.get(i).getOrder_id());
			map.put("pic",OrderConst.buyorder.get(i).getImagepath());
			map.put("title",OrderConst.buyorder.get(i).getTotal_price());
			map.put("content",OrderConst.buyorder.get(i).getTitle());
			map.put("button","");
			list.add(map);
			/*Map<String, Object> map = new HashMap<String, Object>();
			map.put("pic",R.drawable.demo);
			map.put("title","你好，我是标题"+Integer.toString(i));
			map.put("content","测试内容，内容内容内容");
			map.put("button","");
			list.add(map);*/
		 }
		 listAdapter.notifyDataSetChanged();
				

				

				
	}

}
