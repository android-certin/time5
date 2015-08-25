package com.ciandt.worldwonders.database;

import android.content.Context;
import android.database.AbstractCursor;
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
    private String tableName;

    WonderDao(Context context, String tableName) {
        this.tableName = tableName;
        dbHelper = new WondersSQLiteHelper(context);
        dbHelper.initialize();
        this.db = dbHelper.getWritableDatabase();
    }

    WonderDao(Context context) {
        this.tableName = "wonders";
        dbHelper = new WondersSQLiteHelper(context);
        dbHelper.initialize();
        this.db = dbHelper.getWritableDatabase();
    }

    @Override
    public List<Wonder> getAll() {
        String sql = "SELECT * FROM " + tableName;
        Cursor cursor = db.rawQuery(sql, null);

        List<Wonder> result = Wonder.fromListHashMap(convert(cursor));

        return result;
    }

    @Override
    public Wonder getById(int id) {
        String sql = "SELECT * FROM " + tableName + " WHERE id = ?";

        String args[] = {String.valueOf(id)};
        Cursor cursor = db.rawQuery(sql, args);

        List<Wonder> result = Wonder.fromListHashMap(convert(cursor));

        if (result.size() > 0) {
            return result.get(0);
        }

        return null;
    }

    @Override
    public List<Wonder> search(String sql) {
        return Wonder.fromListHashMap(convert(db.rawQuery(sql, null)));
    }

    private List<HashMap<String, Object>> convert(Cursor cursor) {
        List<HashMap<String, Object>> result = new ArrayList<HashMap<String, Object>>();

        cursor.moveToFirst();
        while (cursor.isAfterLast()) {
            int nc = cursor.getColumnCount();
            HashMap<String, Object> row = new HashMap<String, Object>();

            for (int i = 0; i < nc; ++i) {
                String key = cursor.getColumnName(i);
                switch (cursor.getType(i)) {
                    case AbstractCursor.FIELD_TYPE_INTEGER: row.put(key, cursor.getInt(i)); break;
                    case AbstractCursor.FIELD_TYPE_FLOAT: row.put(key, cursor.getFloat(i)); break;
                    case AbstractCursor.FIELD_TYPE_STRING: row.put(key, cursor.getString(i));break;
                    case AbstractCursor.FIELD_TYPE_BLOB: row.put(key, cursor.getBlob(i)); break;
                    case AbstractCursor.FIELD_TYPE_NULL: break;
                    default: row.put(key, cursor.getString(i)); break;
                }
            }

            result.add(row);
            cursor.moveToNext();
        }

        return result;
    }

    @Override
    public boolean delete(Wonder wonder) {
        String sql = "DELETE FROM " + tableName + " WHERE id=?";
        String[] args = { String.valueOf(wonder.id) };

        SQLiteStatement statement = db.compileStatement(sql);
        statement.bindAllArgsAsStrings(args);
        statement.execute();

        return true;
    }

    @Override
    public boolean update(Wonder wonder) {
        HashMap<String, Object> values = wonder.toHashMap();
        String sql = "UPDATE " + tableName + " SET ";
        
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
        String sql = "INSERT INTO " + tableName + " (";
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
