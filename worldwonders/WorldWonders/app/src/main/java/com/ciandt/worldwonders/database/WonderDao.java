package com.ciandt.worldwonders.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.ciandt.worldwonders.helper.WondersSQLiteHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * Created by pmachado on 8/24/15.
 */
public class WonderDao implements Dao {

    private SQLiteDatabase db;

    WonderDao(Context context) {
        WondersSQLiteHelper dbHelper = new WondersSQLiteHelper(context);
        dbHelper.initialize();
        this.db = dbHelper.getWritableDatabase();
    }

    @Override
    public List<HashMap<String, Object>> getAll() {
        String sql = "SELECT id, name, description, url, photo, latitude, longitude FROM wonders";

        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();

        List<HashMap<String, Object>> result = new ArrayList<HashMap<String, Object>>();

        while (cursor.isAfterLast()) {
            int nc = cursor.getColumnCount();
            HashMap<String, Object> row = new HashMap<String, Object>();

            row.put("id", cursor.getInt(0));
            row.put("name", cursor.getString(1));
            row.put("description", cursor.getString(2));
            row.put("url", cursor.getString(3));
            row.put("photo", cursor.getString(4));
            row.put("latitude", cursor.getDouble(5));
            row.put("longitude", cursor.getDouble(6));

            result.add(row);

            cursor.moveToNext();
        }

        return result;
    }

    @Override
    public HashMap<String, Object> getById(int id) {

        String sql = "SELECT id, name, description, url, photo, latitude, longitude FROM wonders WHERE id = ?";

        String[] args = new String[1];
        args[0] = (new Integer(id)).toString();

        Cursor cursor = db.rawQuery(sql, args);
        cursor.moveToFirst();

        List<HashMap<String, Object>> result = new ArrayList<HashMap<String, Object>>();

        while (cursor.isAfterLast()) {
            int nc = cursor.getColumnCount();
            HashMap<String, Object> row = new HashMap<String, Object>();

            row.put("id", cursor.getInt(0));
            row.put("name", cursor.getString(1));
            row.put("description", cursor.getString(2));
            row.put("url", cursor.getString(3));
            row.put("photo", cursor.getString(4));
            row.put("latitude", cursor.getDouble(5));
            row.put("longitude", cursor.getDouble(6));

            result.add(row);

            cursor.moveToNext();
        }

        if (result.size() > 0) {
            return result.get(0);
        }

        return null;
    }

    @Override
    public List<HashMap<String, Object>> search(String word) {

        String sql = "SELECT id, name, description, url, photo, latitude, longitude FROM wonders";

        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();

        List<HashMap<String, Object>> result = new ArrayList<HashMap<String, Object>>();

        while (cursor.isAfterLast()) {
            int nc = cursor.getColumnCount();
            HashMap<String, Object> row = new HashMap<String, Object>();

            row.put("id", cursor.getInt(0));
            row.put("name", cursor.getString(1));
            row.put("description", cursor.getString(2));
            row.put("url", cursor.getString(3));
            row.put("photo", cursor.getString(4));
            row.put("latitude", cursor.getDouble(5));
            row.put("longitude", cursor.getDouble(6));

            result.add(row);

            cursor.moveToNext();
        }

        return result;
    }

    @Override
    public boolean delete(HashMap<String, Object> in) {
        if (in.size() > 0) {
            String sql = "DELETE FROM wonders WHERE ";
            boolean isFirst = true;

            String[] args = new String[in.size()];

            int i = 0;
            for (String key : in.keySet()) {
                if (isFirst) {
                    isFirst = false;
                } else {
                    sql += ", ";
                }

                sql += key + " = ?";
                Object value = in.get(key);
                args[i] = value.toString();
                ++i;
            }


            SQLiteStatement stmnt = db.compileStatement(sql);


            db.rawQuery(sql, args);
            return true;
        }
        return false;
    }

    @Override
    public boolean update(HashMap<String, Object> values, HashMap<String, Object> where) {
        if (values.size() == where.size() && values.size() > 0) {

        }
        return false;
    }

    @Override
    public boolean insert(HashMap<String, Object> values) {
        return false;
    }

    @Override
    public void close() {

    }


}
