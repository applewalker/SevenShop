package com.commodity.adapter;

import java.util.List;

import com.Seven.Shop.R;
import com.shop.entity.Evaluate;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class EvaluateAdapter extends BaseAdapter {

	private Context context;
	private List<Evaluate> ar;
	private LayoutInflater inflater;

	public EvaluateAdapter(Context context, List<Evaluate> ar) {
		this.context = context;
		this.ar = ar;
		inflater = LayoutInflater.from(context);
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return ar.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return ar.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		// TODO Auto-generated method stub
		viewHolder holder;
		if (arg1 == null) {
			holder = new viewHolder();
			arg1 = inflater.inflate(R.layout.comment, null);
			holder.comment_user = (TextView) arg1.findViewById(R.id.comment_username);
			holder.comment_time = (TextView) arg1.findViewById(R.id.comment_time);
			holder.comment_content = (TextView)arg1.findViewById(R.id.comment_content);
            
			arg1.setTag(holder);
		} else {
			holder = (viewHolder) arg1.getTag();
		}
		
		holder.comment_user.setText("用户名："+ar.get(arg0).getUser_name());
		holder.comment_time.setText("日期："+ar.get(arg0).getDate());
		holder.comment_content.setText("内容："+ar.get(arg0).getEvaluate());

		return arg1;
	}

	private class viewHolder {
		private TextView comment_user;
		private TextView comment_time;
		private TextView comment_content;
	}
}
