package com.example.clockin.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.clockin.db.DBInitScript;
import com.example.clockin.db.DataBaseManager;
import com.example.clockin.module.clock.entity.ClockModel;
import com.example.clockin.utils.AesEncrypt;
import com.example.clockin.utils.TimeUtils;

public class ClockDao {
	private DataBaseManager manager;

	private SQLiteDatabase reader;
	private SQLiteDatabase writer;

	private Context context;

	public ClockDao(Context mContext) {
		this.context = mContext;
		if (null == manager) {
			manager = new DataBaseManager(context);
		}
		reader = manager.getReadableDB();
		writer = manager.getWritableDB();
	}

	public ArrayList<ClockModel> getList(String month) {

		Cursor cursor = reader.query(DBInitScript.TABLE_CLOCK, formatColumn(),
				" month ='" +AesEncrypt.encrypt(month)+"'", new String[] {}, null, null, "today desc ;",null);
		ArrayList<ClockModel> list = new ArrayList<ClockModel>();
		while (cursor.moveToNext()) {
			ClockModel model = new ClockModel(
					Long.valueOf(AesEncrypt.decrypt(cursor.getString(cursor.getColumnIndex("in_time")))),
					Long.valueOf(AesEncrypt.decrypt(cursor.getString(cursor.getColumnIndex("out_time")))),
					cursor.getString(cursor.getColumnIndex("today")),
					Integer.valueOf(AesEncrypt.decrypt(cursor.getString(cursor.getColumnIndex("month"))))
					);
			list.add(model);
		}
		cursor.close();
		return list;
	}

	public String[] formatColumn() {
		String[] columnArr = new String[DBInitScript.clock_column.size()];
		Iterator iter = DBInitScript.clock_column.entrySet().iterator();
		int i = 0;
		while (iter.hasNext()) {
			Map.Entry entry = (Map.Entry) iter.next();
			String key = entry.getKey().toString();
			columnArr[i] = key;
			i++;
		}
		return columnArr;
	}

	public void insert(ClockModel model) {
		String sql = "insert into " + DBInitScript.TABLE_CLOCK
				+ "(in_time,out_time,today,month) values('" + AesEncrypt.encrypt(model.getInTime()+"")
				+ "','" +AesEncrypt.encrypt(model.getOutTime()+"") 
				+ "','"+ model.getClockDate()
				+ "','"+ AesEncrypt.encrypt(model.getMonth()+"")+"')";
		writer.execSQL(sql);
	}
	
    
    public void update(ClockModel model){
    	String out_time = AesEncrypt.encrypt(model.getOutTime()+"");
    	String sql = "update "+DBInitScript.TABLE_CLOCK+
    				" set out_time = '"+out_time+"'"+
    				" where "+" today = '"+model.getClockDate()+"'";
    	writer.execSQL(sql);
    }
    
    public void deleteByDay(ClockModel model){
    	String sql = "delete from "+DBInitScript.TABLE_CLOCK+" where "+" today = '"+model.getClockDate()+"'";
    	writer.execSQL(sql);
    }
    
    public void deleteByMonth(String month){
    	String sql = "delete from "+DBInitScript.TABLE_CLOCK+" where "+" month = '"+AesEncrypt.encrypt(month)+"'";
    	writer.execSQL(sql);
    }
}
