package com.personal.order;


import java.util.List;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class OrderAdapter extends FragmentPagerAdapter {
	
	private List<Fragment> ar;

	public OrderAdapter(FragmentManager fm, List<Fragment> ar) {
		super(fm);
		this.ar = ar;
	}

	@Override
	public Fragment getItem(int arg0) {
		// TODO Auto-generated method stub
		return (Fragment) ar.get(arg0);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return ar.size();
	}

}
