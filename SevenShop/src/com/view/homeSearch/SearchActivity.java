package com.view.homeSearch;

import com.Seven.Shop.R;
import com.search.show.Search_showActivity;
import com.shop.entity.ActivityCollect;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class SearchActivity extends Activity{
	  private EditText et_search;
	  private TextView tv_tip;
	  private MyListView listView;
	  private TextView tv_clear;
	  private RecordSQLiteOpenHelper helper = new RecordSQLiteOpenHelper(this);;
	  private SQLiteDatabase db;
	  private BaseAdapter adapter;
	  private ImageView back_ib/*, search_ib*/;
	  private ImageView searchbtn;
	 
	  @Override
	  protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    requestWindowFeature(Window.FEATURE_NO_TITLE);  //去掉顶部标题
	    setContentView(R.layout.search);
	    
	    ActivityCollect.addActivity(this);
	    
	    // 初始化控件
	    initView();
	 
	    //返回键
	    back_ib.setOnClickListener(clickListener); 
	    
	    //查找商品
	    //search_ib.setOnClickListener(clickListener); 
	    
	    // 清空搜索历史
	    tv_clear.setOnClickListener(new View.OnClickListener() {
	      @Override
	      public void onClick(View v) {
	        deleteData();
	        queryData("");
	      }
	    });
	    
	    
	    searchbtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				String search_name = et_search.getText().toString().trim();
				if (search_name.isEmpty()){
					Toast.makeText(SearchActivity.this, "请输入商品名！", 0).show();
				}
				else{
					// 按完搜索键后将当前查询的关键字保存起来,如果该关键字已经存在就不执行保存
			          boolean hasData = hasData(search_name);
			          if (!hasData) {
			            insertData(et_search.getText().toString().trim());
			            queryData("");			        
			          }
			          Intent intent = new Intent(SearchActivity.this, Search_showActivity.class);
			          intent.putExtra("search_name", search_name);
			          startActivity(intent);
				}
			}
		});
	 
	    // 搜索框的键盘搜索键点击回调
	    et_search.setOnKeyListener(new View.OnKeyListener() {// 输入完后按键盘上的搜索键
	 
	      @Override
		public boolean onKey(View v, int keyCode, KeyEvent event) {
	        if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {// 修改回车键功能         
	        	// 先隐藏键盘
	          ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(
	              getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
	          
	          String search_name = et_search.getText().toString();
				if (search_name.isEmpty()){
					Toast.makeText(SearchActivity.this, "请输入商品名！", 0).show();
				}
				else{
					// 按完搜索键后将当前查询的关键字保存起来,如果该关键字已经存在就不执行保存
			          boolean hasData = hasData(search_name);
			          if (!hasData) {
			            insertData(et_search.getText().toString().trim());
			            queryData("");			        
			          }
			          Intent intent = new Intent(SearchActivity.this, Search_showActivity.class);
			          intent.putExtra("search_name", search_name);
			          startActivity(intent);
				}
	 
	        }
	        return false;
	      }
	    });
	 
	    // 搜索框的文本变化实时监听
	    et_search.addTextChangedListener(new TextWatcher() {
	      @Override
	      public void beforeTextChanged(CharSequence s, int start, int count, int after) {
	 
	      }
	 
	      @Override
	      public void onTextChanged(CharSequence s, int start, int before, int count) {
	 
	      }
	 
	      @Override
	      public void afterTextChanged(Editable s) {
	        if (s.toString().trim().length() == 0) {
	          tv_tip.setText("搜索历史");
	        } else {
	          tv_tip.setText("搜索结果");
	        }
	        String tempName = et_search.getText().toString();
	        // 根据tempName去模糊查询数据库中有没有数据
	        queryData(tempName);
	 
	      }
	    });
	 
	    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
	      @Override
	      public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
	        TextView textView = (TextView) view.findViewById(android.R.id.text1);
	        String name = textView.getText().toString();
	        et_search.setText(name);
	        Toast.makeText(SearchActivity.this, name, Toast.LENGTH_SHORT).show();
	        // TODO 获取到item上面的文字，根据该关键字跳转到另一个页面查询，由你自己去实现
	      }
	    });
	 
	    // 插入数据，便于测试，否则第一次进入没有数据怎么测试呀？
	/*    Date date = new Date();
	    long time = date.getTime();
	    insertData("Text" + time); */
	 
	    // 第一次进入查询所有的历史记录
	    queryData("");
	  }

	/**
	   * 插入数据
	   */
	  private void insertData(String tempName) {
	    db = helper.getWritableDatabase();
	    db.execSQL("insert into records(name) values('" + tempName + "')");
	    db.close();
	  }
	 
	  /**
	   * 模糊查询数据
	   */
	  private void queryData(String tempName) {
	    Cursor cursor = helper.getReadableDatabase().rawQuery(
	        "select id as _id,name from records where name like '%" + tempName + "%' order by id desc ", null);
	    // 创建adapter适配器对象
	    adapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_1, cursor, new String[] { "name" },
	        new int[] { android.R.id.text1 }, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
	    // 设置适配器
	    listView.setAdapter(adapter);
	    adapter.notifyDataSetChanged();
	  }
	  /**
	   * 检查数据库中是否已经有该条记录
	   */
	  private boolean hasData(String tempName) {
	    Cursor cursor = helper.getReadableDatabase().rawQuery(
	        "select id as _id,name from records where name =?", new String[]{tempName});
	    //判断是否有下一个
	    return cursor.moveToNext();
	  }
	 
	  /**
	   * 清空数据
	   */
	  private void deleteData() {
	    db = helper.getWritableDatabase();
	    db.execSQL("delete from records");
	    db.close();
	  }
	 
	 //初始化
	  private void initView() {
	    et_search = (EditText) findViewById(R.id.search_ib);
	    tv_tip = (TextView) findViewById(R.id.tv_tip);
	    listView = (MyListView) findViewById(R.id.listView);
	    tv_clear = (TextView) findViewById(R.id.tv_clear);
	    back_ib = (ImageView)findViewById(R.id.back_ib);
	    searchbtn=(ImageView) findViewById(R.id.searchImg_ib);
	   // search_ib = (ImageButton)findViewById(R.id.search_ib);
	  }
	  
	  //ImageButton点击监听
	  OnClickListener clickListener = new OnClickListener(){
		  @Override
		  public void onClick(View v) {
			  if (v.getId() == R.id.back_ib){//按钮1
				  finish();
			  }
			/*  else if(v.getId()==R.id.search_ib){//按钮2
				  Intent intent = new Intent();
				  Toast.makeText(SearchActivity.this, "查找商品", 0).show();
			  }*/
		  }
	 };
 }
