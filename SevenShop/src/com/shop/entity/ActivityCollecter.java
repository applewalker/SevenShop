package com.shop.entity;

import java.util.List;

import android.app.Activity;

public class ActivityCollecter {

	public static List<Activity> activities;
	

	
	public void addActivity(Activity object){
		
		activities.add(object);
	}
	
	public void finishAll(){
		for (Activity activity : activities) {
			if (!activity.isFinishing()) {
				activity.finish();
			}
		}
	}	
	
	
}
