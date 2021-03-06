package com.example.clockin.db;

import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.clockin.common.Config.Config;

public class DatabaseHelper extends SQLiteOpenHelper {
	
	private static DatabaseHelper dbHelper ;

	public DatabaseHelper(Context context) {
		super(context, Config.DBName, null,Config.DBVersion);
		// TODO Auto-generated constructor stub
	}
	
	public static DatabaseHelper getInstance (Context context){
		if(null==dbHelper){
			dbHelper =new DatabaseHelper(context);
		}
		return dbHelper;
	} 

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		List<String> dbInitScript=DBInitScript.DBScript;
        for (int i = 0; i < dbInitScript.size(); i++) {
			db.execSQL(dbInitScript.get(i));
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		if(oldVersion>=newVersion){
		}else{
			DBUpgradeSql dbUpdateSQL = new DBUpgradeSql();
			HashMap<Integer, String>updateScripts = dbUpdateSQL.getUpdateDBScript();
			for (int i = oldVersion+1; i <=newVersion; i++) {
				String updateScript= updateScripts.get(i);
				if(null!=updateScript&&!"".equals(updateScript)){
					String[] sql=updateScript.split("\\|");
					for (int j = 0; j < sql.length; j++) {
						if(!"".equals(sql[j])){
							database.execSQL(sql[j]);
						}
					}
				}
			}
		}
	}
	
	/**
	 * 打开数据库
	 */
	@Override
	public void onOpen(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		Log.i("openDB", "DatabaseHelper on Open!");
		super.onOpen(db);
	}
	
	/**
     * 删除数据库
     * @param context
     * @return
     */
    public boolean deleteDatabase(Context context){
        return context.deleteDatabase(Config.DBName);
    }
}
