package com.example.clockin.db;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class DBInitScript {

    //Clock table
    public static final String TABLE_CLOCK= "t_clock";
    
    public static HashMap<String, Class<?>> clock_column = new HashMap<String, Class<?>>(){
    	 {
             put("id", Integer.class);
             put("in_time", String.class);
             put("out_time", String.class);        
             put("today", String.class);
             put("month",String.class);
         }
    };

    private static String formatSqlStr(String tableName,
            HashMap<String, Class<?>> tableColumn) {
        StringBuffer buffer = new StringBuffer();
        buffer.append("CREATE TABLE IF NOT EXISTS ");
        buffer.append(tableName + " (");
        Iterator iter = tableColumn.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            String key = entry.getKey().toString();
            if (key.contains("_id")) {
                buffer.append(" " + key + " integer primary key autoincrement,");
            } else {
                buffer.append(" " + key + " "
                        + getColumnType((Class<?>) entry.getValue()) + ",");
            }
        }
        buffer.deleteCharAt(buffer.length() - 1);
        buffer.append(")");
        return buffer.toString();
    }

    private static String getColumnType(Class<?> classTpye) {
        if (classTpye.equals(Integer.class)) {
            return "integer";
        }
        if (classTpye.equals(String.class)) {
            return "varchar";
        }
        if (classTpye.equals(Date.class)) {
            return "varchar";
        }
        return "varchar";
    }

    public static List<String> DBScript = new ArrayList<String>() {
        {
            add(formatSqlStr(TABLE_CLOCK, clock_column));
        }
    };
    
    public static void main(String[] args) {
        List<String> list = DBScript;
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }
}
