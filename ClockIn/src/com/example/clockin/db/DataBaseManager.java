package com.example.clockin.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class DataBaseManager {

	private Context mContext;

	private static SQLiteDatabase dbRead;
	
	private static SQLiteDatabase dbWrite;

	public DataBaseManager(Context context) {
		this.mContext = context;
	}
	
	//使用多SQLiteDatabase实例模式操纵数据库
	public SQLiteDatabase getReadableDB() {
		return DatabaseHelper.getInstance(mContext).getWritableDatabase();
	}
	
	public SQLiteDatabase getWritableDB() {
		return DatabaseHelper.getInstance(mContext).getWritableDatabase();
	}

	//使用单SQLiteDatabase实例模式获取数据库操作权限
	public static SQLiteDatabase getWritableDatabase(Context context) {
		if(null==dbWrite){
			dbWrite =DatabaseHelper.getInstance(context).getWritableDatabase();
		}
		return dbWrite;
	}

	public static SQLiteDatabase getReadableDatabase(Context context) {
		if(null==dbRead){
			dbRead =DatabaseHelper.getInstance(context).getReadableDatabase();
		}
		return dbRead;
	}

}
