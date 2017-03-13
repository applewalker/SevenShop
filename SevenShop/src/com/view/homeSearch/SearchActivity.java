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
	    requestWindowFeature(Window.FEATURE_NO_TITLE);  //ȥ����������
	    setContentView(R.layout.search);
	    
	    ActivityCollect.addActivity(this);
	    
	    // ��ʼ���ؼ�
	    initView();
	 
	    //���ؼ�
	    back_ib.setOnClickListener(clickListener); 
	    
	    //������Ʒ
	    //search_ib.setOnClickListener(clickListener); 
	    
	    // ���������ʷ
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
					Toast.makeText(SearchActivity.this, "��������Ʒ����", 0).show();
				}
				else{
					// �����������󽫵�ǰ��ѯ�Ĺؼ��ֱ�������,����ùؼ����Ѿ����ھͲ�ִ�б���
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
	 
	    // ������ļ�������������ص�
	    et_search.setOnKeyListener(new View.OnKeyListener() {// ������󰴼����ϵ�������
	 
	      @Override
		public boolean onKey(View v, int keyCode, KeyEvent event) {
	        if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {// �޸Ļس�������         
	        	// �����ؼ���
	          ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(
	              getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
	          
	          String search_name = et_search.getText().toString();
				if (search_name.isEmpty()){
					Toast.makeText(SearchActivity.this, "��������Ʒ����", 0).show();
				}
				else{
					// �����������󽫵�ǰ��ѯ�Ĺؼ��ֱ�������,����ùؼ����Ѿ����ھͲ�ִ�б���
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
	 
	    // ��������ı��仯ʵʱ����
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
	          tv_tip.setText("������ʷ");
	        } else {
	          tv_tip.setText("�������");
	        }
	        String tempName = et_search.getText().toString();
	        // ����tempNameȥģ����ѯ���ݿ�����û������
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
	        // TODO ��ȡ��item��������֣����ݸùؼ�����ת����һ��ҳ���ѯ�������Լ�ȥʵ��
	      }
	    });
	 
	    // �������ݣ����ڲ��ԣ������һ�ν���û��������ô����ѽ��
	/*    Date date = new Date();
	    long time = date.getTime();
	    insertData("Text" + time); */
	 
	    // ��һ�ν����ѯ���е���ʷ��¼
	    queryData("");
	  }

	/**
	   * ��������
	   */
	  private void insertData(String tempName) {
	    db = helper.getWritableDatabase();
	    db.execSQL("insert into records(name) values('" + tempName + "')");
	    db.close();
	  }
	 
	  /**
	   * ģ����ѯ����
	   */
	  private void queryData(String tempName) {
	    Cursor cursor = helper.getReadableDatabase().rawQuery(
	        "select id as _id,name from records where name like '%" + tempName + "%' order by id desc ", null);
	    // ����adapter����������
	    adapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_1, cursor, new String[] { "name" },
	        new int[] { android.R.id.text1 }, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
	    // ����������
	    listView.setAdapter(adapter);
	    adapter.notifyDataSetChanged();
	  }
	  /**
	   * ������ݿ����Ƿ��Ѿ��и�����¼
	   */
	  private boolean hasData(String tempName) {
	    Cursor cursor = helper.getReadableDatabase().rawQuery(
	        "select id as _id,name from records where name =?", new String[]{tempName});
	    //�ж��Ƿ�����һ��
	    return cursor.moveToNext();
	  }
	 
	  /**
	   * �������
	   */
	  private void deleteData() {
	    db = helper.getWritableDatabase();
	    db.execSQL("delete from records");
	    db.close();
	  }
	 
	 //��ʼ��
	  private void initView() {
	    et_search = (EditText) findViewById(R.id.search_ib);
	    tv_tip = (TextView) findViewById(R.id.tv_tip);
	    listView = (MyListView) findViewById(R.id.listView);
	    tv_clear = (TextView) findViewById(R.id.tv_clear);
	    back_ib = (ImageView)findViewById(R.id.back_ib);
	    searchbtn=(ImageView) findViewById(R.id.searchImg_ib);
	   // search_ib = (ImageButton)findViewById(R.id.search_ib);
	  }
	  
	  //ImageButton�������
	  OnClickListener clickListener = new OnClickListener(){
		  @Override
		  public void onClick(View v) {
			  if (v.getId() == R.id.back_ib){//��ť1
				  finish();
			  }
			/*  else if(v.getId()==R.id.search_ib){//��ť2
				  Intent intent = new Intent();
				  Toast.makeText(SearchActivity.this, "������Ʒ", 0).show();
			  }*/
		  }
	 };
 }
