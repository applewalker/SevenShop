package com.fenlei;

import java.util.ArrayList;
import java.util.Arrays;

import com.fenlei.Model;
import com.Seven.Shop.R;
import com.search.show.*;
import com.fenlei.GridViewAdapter;
import com.fenlei.Type;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

public class ProTypeFragment extends Fragment {

	private ArrayList<Type> list;
	private GridView gridView;
	private GridViewAdapter adapter;
	private Type type;
	private String typename1, typename_search;
	private String[] typename = new String[20];

	private int[] typeicon = new int[20];
	private TextView typesearch;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_pro_type, null);
		gridView = (GridView) view.findViewById(R.id.listView);
		int index = getArguments().getInt("index");

		typename1 = Model.toolsList[index]; //大类名
		
		switch(index){
		case 0:
			typename = Model.typename;
			typeicon = Model.typeicon;
			break;
		case 4:
			typename = Model.typename1;
			typeicon = Model.typeicon1;
			break;
			
		default:
			Arrays.fill(typeicon, Model.iconList[index]);
			Arrays.fill(typename, Model.toolsList[index]);
			break;
		}
		
		((TextView) view.findViewById(R.id.toptype)).setText(typename1);
		
		GetTypeList();
		adapter = new GridViewAdapter(getActivity(), list);
		gridView.setAdapter(adapter);
		
		gridView.setOnItemClickListener(new OnItemClickListener() {  //点击小分类，进入结果界面
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				typesearch = (TextView) arg1.findViewById(R.id.typename);
				typename_search = typesearch.getText().toString();
				Toast.makeText(getActivity(), typename_search, Toast.LENGTH_LONG).show();
				Intent intent = new Intent();
				intent.putExtra("search_name", typename_search); // typename 为类
				intent.setClass(getActivity(), Search_showActivity.class);
				getActivity().startActivity(intent);
			}
		});

		return view;
	}

	private void GetTypeList() {
		list = new ArrayList<Type>();
		for (int i = 0; i < typename.length; i++) {
			type = new Type(i, typename[i], typeicon[i]);
			list.add(type);
		}
	}
}
