package com.ciandt.worldwonders.database;

import android.database.AbstractCursor;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;


import com.ciandt.worldwonders.model.DataConverter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by pmachado on 8/24/15.
 */
public class Dao<T> {
    protected SQLiteDatabase db;
    protected String tableName;

    private DataConverter<T> converter;

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

    public Dao(String tableName, DataConverter<T> converter) {
        this.converter = converter;
        this.tableName = tableName;
        this.db = null;
    }


    public List<T> getAll() {
        String sql = "SELECT * FROM " + tableName;
        Cursor cursor = db.rawQuery(sql, null);

        List<T> result = converter.fromListHashMap(convert(cursor));

        return result;
    }

    public T getById(int id) {
        String sql = "SELECT * FROM " + tableName + " WHERE id = ?";

        String args[] = {String.valueOf(id)};
        Cursor cursor = db.rawQuery(sql, args);

        List<T> result = converter.fromListHashMap(convert(cursor));

        if (result.size() > 0) {
            return result.get(0);
        }

        return null;
    }

    public List<T> search(String sql) {
        return converter.fromListHashMap(convert(db.rawQuery(sql, null)));
    }

    public boolean delete(T value) {
        String sql = "DELETE FROM " + tableName + " WHERE id=?";
        String[] args = { String.valueOf(converter.getId(value)) };

        SQLiteStatement statement = db.compileStatement(sql);
        statement.bindAllArgsAsStrings(args);
        statement.execute();

        return true;
    }

    public boolean update(T value) {
        HashMap<String, Object> values = converter.toHashMap(value);
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
        args[values.size() + 1] = String.valueOf(converter.getId(value));

        SQLiteStatement statement = db.compileStatement(sql);
        statement.bindAllArgsAsStrings(args);
        statement.execute();

        return true;
    }

    public boolean insert(T value) {
        HashMap<String, Object> values = converter.toHashMap(value);
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

    public void close() {
        db.close();
    }
}
