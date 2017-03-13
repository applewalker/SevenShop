package com.cart.adapter;

import java.util.List;

import com.Seven.Shop.R;
import com.cart.bean.carBean;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

public class CheckOutAdapter extends BaseAdapter {

	private int resourceId;				//��ͼ��ԴID
	private List<carBean> list;		//ѡ����Ʒ����
	private LayoutInflater inflater;	//���������
	public CheckOutAdapter(Context context,List<carBean> list
			,int resourceId){
		this.list = list;
		this.resourceId = resourceId;
		inflater = LayoutInflater.from(context);
	}
	
	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		carBean bean = list.get(position);
		ViewHolder holder = null;
		if(convertView == null){
			holder = new ViewHolder();
			convertView = inflater.inflate(resourceId, null);
			holder.storeName = (TextView) convertView.findViewById(R.id.storeName);
			holder.checkOut_price = (TextView) convertView.findViewById(R.id.checkOut_price);
			holder.checkOut_description = 
					(TextView) convertView.findViewById(R.id.checkOut_description);
			holder.checkOut_number = (TextView) convertView.findViewById(R.id.checkOut_number);
			holder.checkOut_singlePrice = 
					(TextView) convertView.findViewById(R.id.checkOut_singlePrice);
			holder.checkout_size = (TextView) convertView.findViewById(R.id.checkout_size);
			holder.leaveWorld = (EditText) convertView.findViewById(R.id.leaveWorld);
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
		
		float allPrice = bean.getNumber()*bean.getPrice();
		holder.storeName.setText(bean.getGoods_name());
		holder.checkOut_price.setText("��"+allPrice);
		holder.checkOut_description.setText(bean.getGoodsdescribe());
		holder.checkOut_number.setText(bean.getNumber()+"��");
		holder.checkOut_singlePrice.setText("��"+bean.getPrice());
		
		return convertView;
	}
	
	@SuppressWarnings("unused")
	private final class ViewHolder{
		public TextView storeName;				//�������
		public TextView checkOut_price;			//����Ʒ�����ܽ��
		public TextView checkOut_description;	//��Ʒ����
		public TextView checkOut_number;		//��Ʒ����
		public TextView checkout_size;			//��Ʒ��ɫ�ͳ���
		public TextView checkOut_singlePrice;	//������Ʒ�ļ۸�
		public EditText leaveWorld;				//���̼�����
	}

}
