package com.ciandt.worldwonders.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.ciandt.worldwonders.helper.WondersSQLiteHelper;
import com.ciandt.worldwonders.model.Wonder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by pmachado on 8/24/15.
 */
public class WonderDao implements Dao<Wonder> {
    private SQLiteDatabase db;
    private WondersSQLiteHelper dbHelper;
    
    WonderDao(Context context) {
        dbHelper = new WondersSQLiteHelper(context);
        dbHelper.initialize();
        this.db = dbHelper.getWritableDatabase();
    }

    @Override
    public List<Wonder> getAll() {
        String sql = "SELECT id, name, description, url, photo, latitude, longitude FROM wonders";
        List<HashMap<String, Object>> result = new ArrayList<HashMap<String, Object>>();
        
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();

        while (cursor.isAfterLast()) {
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

        return Wonder.fromListHashMap(result);
    }

    @Override
    public Wonder getById(int id) {
        String sql = "SELECT id, name, description, url, photo, latitude, longitude FROM wonders WHERE id = ?";

        String args[] = {String.valueOf(id)};
        Cursor cursor = db.rawQuery(sql, args);

        List<Wonder> result = convert(cursor);

        if (result.size() > 0) {
            return result.get(0);
        }

        return null;
    }

    @Override
    public List<Wonder> search(String sql) {
        return convert(db.rawQuery(sql, null));
    }

    private List<Wonder> convert(Cursor cursor) {
        cursor.moveToFirst();

        List<HashMap<String, Object>> result = new ArrayList<HashMap<String, Object>>();

        while (cursor.isAfterLast()) {
            int nc = cursor.getColumnCount();
            HashMap<String, Object> row = new HashMap<String, Object>();

            row.put("id", cursor.getInt(cursor.getColumnIndex("id")));
            row.put("name", cursor.getString(cursor.getColumnIndex("name")));
            row.put("description", cursor.getString(cursor.getColumnIndex("description")));
            row.put("url", cursor.getString(cursor.getColumnIndex("url")));
            row.put("photo", cursor.getString(cursor.getColumnIndex("photo")));
            row.put("latitude", cursor.getDouble(cursor.getColumnIndex("latitude")));
            row.put("longitude", cursor.getDouble(cursor.getColumnIndex("longitude")));

            result.add(row);

            cursor.moveToNext();
        }

        return Wonder.fromListHashMap(result);
    }

    @Override
    public boolean delete(Wonder wonder) {
        HashMap<String, Object> in = wonder.toHashMap();
        if (in.size() > 0) {
            String sql = "DELETE FROM wonders WHERE 1=1";
            String[] args = new String[in.size()];
            int i = 0;
            
            for (String key : in.keySet()) {
                sql += " AND " + key + " = ?";
                args[i] = in.get(key).toString();
                ++i;
            }

            SQLiteStatement stmnt = db.compileStatement(sql);

            stmnt.bindAllArgsAsStrings(args);
            stmnt.execute();
            
            return true;
        }
        
        return false;
    }

    @Override
    public boolean update(Wonder wonder, int id) {
        HashMap<String, Object> values = wonder.toHashMap();
        boolean isUpdated = false;
        String sql = "UPDATE wonders SET ";
        
        if(!values.isEmpty()) {
            boolean isFirst = true;
            int i = 0;
            String[] args = new String[values.size() + 1];
            
            for (String key : values.keySet()) {
                if (isFirst) {
                    isFirst = false;
                } else {
                    sql += ", ";
                }

                sql += key + " = ?";
                Object value = values.get(key);
                args[i] = value.toString();
                ++i;
            }

            sql += " WHERE id = ?";
            args[values.size() + 1] = String.valueOf(id);

            db.rawQuery(sql, args);
        }

        return isUpdated;
    }

    @Override
    public boolean insert(Wonder wonder) {
        HashMap<String, Object> values = wonder.toHashMap();
        String sql = "INSERT INTO wonders (name, description, url, photo, latitude, longitude) VALUES (?, ?, ?, ?, ?, ?)";

        if (values.size() == 6) {
            int i = 0;
            String[] args = new String[values.size()];
            
            for (String key : values.keySet()) {
                args[i] = values.get(key).toString();
                ++i;
            }
            
            db.rawQuery(sql, args);
            
            return true;
        }

        return false;
    }

    @Override
    public void close() {
        db.close();
        dbHelper.close();
    }
}
