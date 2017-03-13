package com.tejia_like;

import java.io.IOException;

import com.Seven.Shop.R;
import com.commodity.CommodityActivity;
import com.example.android.bitmapfun.util.ImageFetcher;
import com.google.gson.Gson;
import com.http.util.HttpUtil1;

import com.origamilabs.library.views.StaggeredGridView;
import com.origamilabs.library.views.StaggeredGridView.OnItemClickListener;
import com.pinstame.view.SwipeRefreshAndLoadLayout;
import com.search.show.StaggeredAdapter;
import com.shop.entity.ActivityCollect;
import com.shop.entity.Goods;
import com.shop.entity.GoodsList;
import com.squareup.okhttp.Request;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

public class likeActivity extends Activity implements SwipeRefreshAndLoadLayout.OnRefreshListener {
	
	private ImageFetcher mImageFetcher;
	private StaggeredAdapter mAdapter;
	private SwipeRefreshAndLoadLayout swipeLayout;
	private SharedPreferences preferences;
 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);  //去掉顶部标题
		setContentView(R.layout.search_show);
		
		ActivityCollect.addActivity(this);
		
		mImageFetcher = new ImageFetcher(this, 240);
		mImageFetcher.setLoadingImage(R.drawable.empty_photo);
		StaggeredGridView gridView = (StaggeredGridView) this.findViewById(R.id.staggeredGridView1);

		//get
		Goods goods = new Goods();
		preferences = getApplication().getSharedPreferences("loginState", Context.MODE_PRIVATE);
		if (preferences.getBoolean("loginState", false) == true){
			int user_id = preferences.getInt("userid", 0);
			goods.setUser_id(user_id);
		}else 
			goods.setUser_id(0);
		HttpUtil1.postAsync("like.php", goods, new HttpUtil1.DataCallBack() {
			Gson gson = new Gson();
			@Override
			public void requestSuccess(String result) throws Exception {
				// TODO Auto-generated method stub				

				GoodsList goodsList = new GoodsList();
				goodsList = gson.fromJson(result, GoodsList.class);	
				//System.out.println(goodsList.getGoodslist().get(0).getGoods_name());
				
				
				mAdapter.addItemTop(goodsList.getGoodslist());
				mAdapter.notifyDataSetChanged();
			}
			
			@Override
			public void requestFailure(Request request, IOException e) {
				// TODO Auto-generated method stub			
			}
		});
		
		//
		
		int margin = getResources().getDimensionPixelSize(R.dimen.margin);

		gridView.setFastScrollEnabled(true);

		mAdapter = new StaggeredAdapter(likeActivity.this, mImageFetcher);
		gridView.setAdapter(mAdapter);
		
		//-----跳转到商品详情页
		gridView.setOnItemClickListener(new OnItemClickListener() {
			
			int goodid;
			@Override
			public void onItemClick(StaggeredGridView parent, View view, int position, long id) {
				// TODO Auto-generated method stub	
				goodid = Integer.parseInt(((TextView) view.findViewById(R.id.albid)).getText().toString());
				
				Toast.makeText(likeActivity.this, goodid+"", 0).show();
				Intent intent = new Intent(likeActivity.this, CommodityActivity.class);
				intent.putExtra("id", goodid);
				startActivity(intent);				
			}
		});
		
		mAdapter.notifyDataSetChanged();
		
		AddItemToContainer(1);
		
		swipeLayout = (SwipeRefreshAndLoadLayout) this.findViewById(R.id.swipe_refresh);
		swipeLayout.setOnRefreshListener(this);
		swipeLayout.setmMode(SwipeRefreshAndLoadLayout.Mode.BOTH);
		
		// 顶部刷新的样式
		swipeLayout.setColorScheme(android.R.color.holo_red_light, android.R.color.holo_green_light, android.R.color.holo_blue_bright, android.R.color.holo_orange_light);
	
	}

	@Override
    public void onRefresh() {
	
		
		Toast.makeText(this, "refresh", 2000).show();
		
		new Handler().postDelayed(new Runnable() {
			public void run() {
				swipeLayout.setRefreshing(false);
				
			}
		}, 5000);
		
    }

    @Override
    public void onLoadMore() {
    	
    	Toast.makeText(this, "refresh", 2000).show();
		
		new Handler().postDelayed(new Runnable() {
			public void run() {
				swipeLayout.setRefreshing(false);
				
			}
		}, 5000);
    }
  
    private void AddItemToContainer(int type){
    /*	if (task.getStatus() != Status.RUNNING) {
			String url = result_json;
			ContentTask task = new ContentTask(this, type);
			task.execute(url);

		}*/
    }
}
