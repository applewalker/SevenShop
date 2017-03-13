package com.fenlei;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.Seven.Shop.R;
import com.fenlei.Model;
import com.fenlei.ProTypeFragment;
import com.shop.entity.ActivityCollect;
import com.view.homeSearch.SearchActivity;

public class ScrollGridActivity extends FragmentActivity {

	private String[] list;
	private TextView[] tvList;
	private ImageView search_fenlei;
	private ImageView back_fenlei;
	private View[] views;
	private LayoutInflater inflater;
	private ScrollView scrollView;
	private ViewPager viewpager;
	private int currentItem = 0;
	private ShopAdapter shopAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);  //去掉顶部标题
		setContentView(R.layout.activity_scrollgrid);
		
		ActivityCollect.addActivity(this);
		
		search_fenlei = (ImageView) findViewById(R.id.search_fenlei);
		back_fenlei = (ImageView) findViewById(R.id.back_fenlei);
		
		back_fenlei.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		
		search_fenlei.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(ScrollGridActivity.this,SearchActivity.class);
				startActivity(intent);
				
			}
		});    
		
		scrollView = (ScrollView) findViewById(R.id.tools_scrlllview);
		shopAdapter = new ShopAdapter(getSupportFragmentManager());
		inflater = LayoutInflater.from(this);
		showToolsView();
		initPager();
	}

	private void showToolsView() {
		list = Model.toolsList;
		LinearLayout toolsLayout = (LinearLayout) findViewById(R.id.tools);
		tvList = new TextView[list.length];
		views = new View[list.length];

		for (int i = 0; i < list.length; i++) {
			View view = inflater.inflate(R.layout.item_list_layout, null);
			view.setId(i);
			view.setOnClickListener(toolsItemListener);
			TextView textView = (TextView) view.findViewById(R.id.text);
			textView.setText(list[i]);
			toolsLayout.addView(view);
			tvList[i] = textView;
			views[i] = view;
		}
		changeTextColor(0);
	}

	private View.OnClickListener toolsItemListener = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			viewpager.setCurrentItem(v.getId());
		}
	};

	/**
	 * initPager<br/>
	 * 鍒濆鍖朧iewPager鎺т欢鐩稿叧鍐呭
	 */
	private void initPager() {
		viewpager = (ViewPager) findViewById(R.id.goods_pager);
		viewpager.setAdapter(shopAdapter);
		viewpager.setOnPageChangeListener(onPageChangeListener);
	}

	private OnPageChangeListener onPageChangeListener = new OnPageChangeListener() {
		@Override
		public void onPageSelected(int arg0) {
			if (viewpager.getCurrentItem() != arg0)
				viewpager.setCurrentItem(arg0);
			if (currentItem != arg0) {
				changeTextColor(arg0);
				changeTextLocation(arg0);
			}
			currentItem = arg0;
		}

		@Override
		public void onPageScrollStateChanged(int arg0) {
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
		}
	};

	/**
	 * ViewPager 
	 */
	private class ShopAdapter extends FragmentPagerAdapter {
		public ShopAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int index) {
			Fragment fragment = new ProTypeFragment();
			Bundle bundle = new Bundle();
			bundle.putInt("index", index);
			fragment.setArguments(bundle);
			return fragment;
		}

		@Override
		public int getCount() {
			return list.length;
		}
	}

	/**
	 * textView
	 */
	private void changeTextColor(int id) {
		for (int i = 0; i < tvList.length; i++) {
			if (i != id) {
				tvList[i].setBackgroundColor(0x00000000);
				tvList[i].setTextColor(0xFF000000);
			}
		}
		tvList[id].setBackgroundColor(0xFFFFFFFF);
		tvList[id].setTextColor(0xFFFF5D5E);
	}

	private void changeTextLocation(int clickPosition) {
		int x = (views[clickPosition].getTop());
		scrollView.smoothScrollTo(0, x);
	}

}