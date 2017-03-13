package com.commodity.fragment;

import android.support.v4.app.Fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.Seven.Shop.R;
import com.commodity.CommodityActivity;
import com.commodity.adapter.EvaluateAdapter;
import com.shop.entity.Evaluate;
import com.shop.entity.Goods;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class CommoTab1 extends Fragment{
	private ListView tab1;
	private List<Evaluate> evaluates;
	ImageView head;
	private EvaluateAdapter evaluateAdapter;
	

	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		
		View view = inflater.inflate(R.layout.pro_tab1, container, false);
		tab1 = (ListView) view.findViewById(R.id.pro_tab1);
		
		
		return view;
	}
	public void upData(){
		evaluates = ((CommodityActivity)getActivity()).getEvaluates();
		
		System.out.println("This is evaluate's id:"+evaluates.get(0).getEvaluate_id());
		
		evaluateAdapter = new EvaluateAdapter(getActivity(), evaluates);
		tab1.setAdapter(evaluateAdapter);
	}
	
}
