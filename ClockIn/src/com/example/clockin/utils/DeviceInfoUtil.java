package com.example.clockin.utils;

import android.app.Activity;
import android.content.Context;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;

/**
 * 设备信息工具类
 * 
 * @author 萧楚
 * */
public class DeviceInfoUtil {

	/**
	 * 获取IMEI码
	 * */
	public static String getIMEI(Activity activity) {

		// 获取设备IMEI码
		TelephonyManager tm = (TelephonyManager) activity
				.getSystemService(Context.TELEPHONY_SERVICE);

		return tm.getDeviceId();
	}
	
	/**
	 * 获取屏幕宽度
	 * */
	public static int getDeviceWidth(Activity activity){
		
		//获取屏幕像素
        DisplayMetrics metric = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(metric);
		
        int width = metric.widthPixels; // 屏幕宽度（像素）
        
        return width;
	}
	
	/**
	 * 获取屏幕高度
	 * */
	public static int getDeviceHeight(Activity activity){
		
		//获取屏幕像素
        DisplayMetrics metric = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(metric);

		int height = metric.heightPixels; // 屏幕高度（像素）
        
        return height;
	}
}
