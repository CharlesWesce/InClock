package com.example.clockin.utils;

import android.annotation.SuppressLint;
import java.text.SimpleDateFormat;
import java.util.Date;

@SuppressLint("SimpleDateFormat")
public class TimeUtils {

	public static final SimpleDateFormat DEFAULT_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static final SimpleDateFormat DATE_FORMAT_DATE = new SimpleDateFormat("yyyy-MM-dd");

	private TimeUtils() {
		throw new AssertionError();
	}

	/**
	 * long time to string
	 * 
	 * @param timeInMillis
	 * @param dateFormat
	 * @return
	 */
	public static String getTime(long timeInMillis, SimpleDateFormat dateFormat) {
		return dateFormat.format(new Date(timeInMillis));
	}

	/**
	 * long time to string, format is {@link #DEFAULT_DATE_FORMAT}
	 * 
	 * @param timeInMillis
	 * @return
	 */
	public static String getTime(long timeInMillis) {
		return getTime(timeInMillis, DEFAULT_DATE_FORMAT);
	}

	/**
	 * get current time in milliseconds
	 * 
	 * @return
	 */
	public static long getCurrentTimeInLong() {
		return System.currentTimeMillis();
	}

	/**
	 * get current time in milliseconds, format is {@link #DEFAULT_DATE_FORMAT}
	 * 
	 * @return
	 */
	public static String getCurrentTimeInString() {
		return getTime(getCurrentTimeInLong());
	}

	/**
	 * get current time in milliseconds
	 * 
	 * @return
	 */
	public static String getCurrentTimeInString(SimpleDateFormat dateFormat) {
		return getTime(getCurrentTimeInLong(), dateFormat);
	}

	public static String getCurTime() {

		SimpleDateFormat sDateFormat = new SimpleDateFormat("HH:mm:ss");
		return sDateFormat.format(new java.util.Date());
	}

	public static String getCurDate() {

		SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return sDateFormat.format(new java.util.Date());
	}

	public static boolean isNeedToClearImgCache(String strLastClearDate) {

		return dateCompare(strLastClearDate, getCurDate(), 7);
	}

	/**
	 * 比较两个时间相差的天数
	 * 
	 * @param pStrDate1
	 *            日期-"yyyy-MM-dd HH:mm:ss"
	 * @param pStrDate2
	 *            日期-"yyyy-MM-dd HH:mm:ss"
	 * @param expectDay
	 *            期望天数差
	 * @return if(>=天数差)返回true
	 * */
	public static boolean dateCompare(String strDate1, String strDate2,
			int expectDay) {
		try {
			// 设定时间的模板
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			// 得到指定模范的时间
			Date d1 = sdf.parse(strDate1);
			Date d2 = sdf.parse(strDate2);
			// 比较
			if (Math.abs(((d1.getTime() - d2.getTime()) / (24 * 3600 * 1000))) >= 7) {
				// System.out.println("大于7天");
				return true;
			} else {
				// System.out.println("小于7天");
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}

	/*
	 * 计算星期几
	 */
	public static String getWeekDayString(int year, int monthOfYear,
			int dayOfMonth) {
		int y = year - 1;
		int m = monthOfYear;
		int c = 20;
		int d = dayOfMonth + 12;
		int w = (y + (y / 4) + (c / 4) - 2 * c + (26 * (m + 1) / 10) + d - 1) % 7;
		String weekString = null;
		switch (w) {
		case 0:
			weekString = "日";
			break;
		case 1:
			weekString = "一";
			break;
		case 2:
			weekString = "二";
			break;
		case 3:
			weekString = "三";
			break;
		case 4:
			weekString = "四";
			break;
		case 5:
			weekString = "五";
			break;
		case 6:
			weekString = "六";
			break;
		default:
			break;
		}
		return weekString;
	}

	public static int date2Compare(String strDate1, String strDate2) {
		try {
			// 设定时间的模板
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
			// 得到指定模范的时间
			Date d1 = sdf.parse(strDate1);
			Date d2 = sdf.parse(strDate2);
			// 比较
			int result = (int) (((d1.getTime() - d2.getTime()) / (60 * 1000)));
			if (result > 0)
				return result;
			else
				return 0;
		} catch (Exception e) {
			return 0;
		}
	}

}
