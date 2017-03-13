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
	private ListView listView; // �����б���ͼ����
	private List<Map<String, Object>> list; // �����б�����

	private int position_tmp; // position����ʱ�洢����
	TextView titleName;

	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.myorder, null);
		listView = (ListView) view.findViewById(R.id.order_Listview);
		//ʵ�����б�����
	     list = new ArrayList<Map<String, Object>>();  
		//ʵ����������������ɲμ�SimpleAdapter��ʹ��
		listAdapter = new OrderListSimpleAdapter(getActivity(), list, R.layout.orderlistview_item, new String[] {
				"pic", "title", "content","button"}, new int[] {R.id.order_pic, R.id.title, R.id.content,R.id.order_delete});
		
		listView.setAdapter(listAdapter);  

		//���ListViewÿһ�еĵ�������¼����˴������ü����������
		listView.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long id) {

				position_tmp = position;

				/*new AlertDialog.Builder(MyorderActivity.orderTestActivity)
				.setTitle("ListView")
				.setMessage("�����˵�"+Integer.toString(position_tmp)+"�У�")
				.show();*/
			}
		}); 

		return view;
	}

	public void Init() {
       

		 
				

		for(int i=0;i<OrderConst.buyorder.size();i++){
			//ʵ��һ���б������������������ݿ�������ӵ��б�������ÿһ��map�����ݶ�ӦListView��������һ��
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("orderid", OrderConst.buyorder.get(i).getOrder_id());
			map.put("pic",OrderConst.buyorder.get(i).getImagepath());
			map.put("title",OrderConst.buyorder.get(i).getTotal_price());
			map.put("content",OrderConst.buyorder.get(i).getTitle());
			map.put("button","");
			list.add(map);
			/*Map<String, Object> map = new HashMap<String, Object>();
			map.put("pic",R.drawable.demo);
			map.put("title","��ã����Ǳ���"+Integer.toString(i));
			map.put("content","�������ݣ�������������");
			map.put("button","");
			list.add(map);*/
		 }
		 listAdapter.notifyDataSetChanged();
				

				

				
	}

}
