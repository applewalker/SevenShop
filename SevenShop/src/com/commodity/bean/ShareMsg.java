package com.commodity.bean;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.view.View;


public class ShareMsg {
	private static final String EXT = ".PNG";
	private String title,name;
	private View view,view2;
	public Uri uri,uri2;
	public String msg;
	private Intent intent;
	public ShareMsg(String title,String name,View view,View view2) throws IOException{
		this.title=title;
		this.name=name;
		this.view=view;
		this.view2=view2;
		uri = sharepic(this.view,0);
		uri2 = sharepic(this.view2, 1);
		sharemsg();
	}
	private Uri sharepic(View view,int type) throws IOException{
		String dirpath = Environment.getExternalStorageDirectory().toString()+"/shareswip";		  
		File ss = new File(dirpath);
		  if (!ss.exists()) {
		   ss.mkdirs();
		  }
		File imageFile = null;
		  view.setDrawingCacheEnabled(true);
		  //view.measure(MeasureSpec.makeMeasureSpec(500, MeasureSpec.AT_MOST), MeasureSpec.makeMeasureSpec(500, MeasureSpec.AT_MOST));  
		  //Log.d("wh",);
		  //view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());  
		  //view.buildDrawingCache(); 
		  
		  view.setDrawingCacheQuality(100);
		  Bitmap bitmap = Bitmap.createBitmap(view.getDrawingCache());	
			//Bitmap bitmap = view.getDrawingCache().createBitmap(view.getMeasuredWidth(), view.getMeasuredHeight(), config);
		  Uri uri = null;
		  boolean hasSDCard = Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);  
		  if(hasSDCard){
			  switch (type){
			  case 0:
				  imageFile = new File(dirpath,"sharepic"+EXT);
		  	break;
			  case 1:
			  	  imageFile = new File(dirpath,"shareqrcode"+EXT);
			break;	  
			  }
		  if (imageFile.exists())
		  {
			  imageFile.delete();
			  Log.d("aaaaaa", "abc");
		  }
		  
		  FileOutputStream fos = new FileOutputStream(imageFile);
		  bitmap.compress(CompressFormat.PNG, 100, fos); 
		  uri=Uri.fromFile(imageFile);
		  fos.flush();  
		  fos.close();	 
		  view.setDrawingCacheEnabled(false);
		  }
		  return uri;
	  } 
	private void sharemsg(){
		msg="share this cool stuff! "+name;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public View getView() {
		
		return view;
	}
	public void setView(View view) {
		this.view = view;
	}
	public Intent getIntent() {
		Intent intent = new Intent(Intent.ACTION_SEND_MULTIPLE);
		intent.setType("image/*");
		intent.putExtra(Intent.EXTRA_SUBJECT, "Share");
		intent.putExtra(Intent.EXTRA_TEXT,
				msg);
		
//		intent.putExtra(Intent.EXTRA_STREAM, uri);
		ArrayList<Uri> imageUris = new ArrayList<Uri>();  
		imageUris.add(uri);
		imageUris.add(uri2);
		intent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, imageUris);
		//intent.putParcelableArrayListExtra(Intent.EXTRA_STREAM,uri);
		
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		this.intent=intent;
		return this.intent;
	}
	public void setIntent(Intent intent) {
		this.intent = intent;
	}
}
