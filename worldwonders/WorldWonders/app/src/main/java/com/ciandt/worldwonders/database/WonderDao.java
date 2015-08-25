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
        String sql = "SELECT * FROM wonders";
        Cursor cursor = db.rawQuery(sql, null);

        List<Wonder> result = convert(cursor);

        return result;
    }

    @Override
    public Wonder getById(int id) {
        String sql = "SELECT * FROM wonders WHERE id = ?";

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
        List<HashMap<String, Object>> result = new ArrayList<HashMap<String, Object>>();

        cursor.moveToFirst();

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
        String sql = "DELETE FROM wonders WHERE id=?";
        String[] args = { String.valueOf(wonder.id) };

        SQLiteStatement statement = db.compileStatement(sql);
        statement.bindAllArgsAsStrings(args);
        statement.execute();

        return true;
    }

    @Override
    public boolean update(Wonder wonder) {
        HashMap<String, Object> values = wonder.toHashMap();
        String sql = "UPDATE wonders SET ";
        
        int i = 0;
        String[] args = new String[values.size() + 1];

        for (String key : values.keySet()) {
            if (i> 0) sql += ", ";
            sql += key + " = ?";
            args[i] = values.get(key).toString();
            ++i;
        }

        sql += " WHERE id = ?";
        args[values.size() + 1] = String.valueOf(wonder.id);

        SQLiteStatement statement = db.compileStatement(sql);
        statement.bindAllArgsAsStrings(args);
        statement.execute();

        return true;
    }

    @Override
    public boolean insert(Wonder wonder) {
        HashMap<String, Object> values = wonder.toHashMap();
        int n = values.size();
        String[] args = new String[n];
        int i = 0;
        String sql = "INSERT INTO wonders (";
        for (String key: values.keySet()) {
            if (i > 0) sql += ", ";
            sql += key;
            args[i] = values.get(i).toString();
            ++i;
        }
        sql += ") VALUES (";
        for (int p = 0; p < n; ++p) {
            if (p > 0) sql += ", ";
            sql += "?";
        }
        sql += ")";

        SQLiteStatement statement = db.compileStatement(sql);
        statement.bindAllArgsAsStrings(args);
        statement.execute();

        return true;
    }

    @Override
    public void close() {
        db.close();
        dbHelper.close();
    }
}
