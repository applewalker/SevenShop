package com.commodity.adapter;

import java.util.ArrayList;
import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import android.view.View;

public class CommoPagerAdapter extends FragmentPagerAdapter{
	private List<Fragment> fragmentList=new ArrayList<Fragment>();

	public CommoPagerAdapter(FragmentManager fm)
	{
		super(fm);	
	}
	public CommoPagerAdapter(FragmentManager fragmentManager,List<Fragment> arrayList) {
		super(fragmentManager);
		this.fragmentList=arrayList;
	}
	public Fragment getItem(int arg0) {
		return fragmentList.get(arg0);
	}
	public int getCount() {
		return fragmentList.size();
	}
	

	
	
}
