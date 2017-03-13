package com.shop.entity;


import java.util.List;
import android.app.Activity;

public class ActivityCollect {

public static List<Activity> activities;
	

	
	public static void addActivity(Activity object){
		
		activities.add(object);
	}
	
	public static void finishAll(){
		for (Activity activity : activities) {
			if (!activity.isFinishing()) {
				activity.finish();
			}
		}
	}	
	
}
