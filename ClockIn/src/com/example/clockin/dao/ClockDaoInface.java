
package com.example.clockin.dao;

import java.util.List;
import java.util.Map;

import android.content.ContentValues;

import com.example.clockin.module.clock.entity.ClockModel;

public interface ClockDaoInface {
    public boolean addCache(ClockModel item);

    public boolean deleteCache(String whereClause, String[] whereArgs);

    public boolean updateCache(ContentValues values, String whereClause,
            String[] whereArgs);

    public Map<String, String> viewCache(String selection,
            String[] selectionArgs);

    public List<Map<String, String>> listCache(String selection,
            String[] selectionArgs);

    public void clearFeedTable();
}
