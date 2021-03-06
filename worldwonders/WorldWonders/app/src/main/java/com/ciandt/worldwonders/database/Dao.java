package com.ciandt.worldwonders.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import com.ciandt.worldwonders.converter.ConverterBase;
import com.ciandt.worldwonders.helper.WondersSQLiteHelper;

import java.util.HashMap;
import java.util.List;

/**
 * Created by pmachado on 8/24/15.
 * Contributions by eferraz on 8/25/15
 */
public abstract class Dao<T> extends DaoBase {
    protected SQLiteDatabase db;
    protected String tableName;
    protected SQLiteOpenHelper helper;

    private ConverterBase<T> converter;

    public Dao(String tableName, ConverterBase<T> converter) {
        super();

        this.converter = converter;
        this.tableName = tableName;
    }

    public void setHelper(SQLiteOpenHelper helper) {
        setDatabase(helper.getWritableDatabase());
        this.helper = helper;
    }

    public void setDatabase(SQLiteDatabase db) {
        this.db = db;
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
        HashMap<String, Object> values = converter.toHashMap(value);
        String sql = "DELETE FROM " + tableName + " WHERE 1 = 1 ";
        int n = values.size() - 1;
        int i = 0;
        String[] args = new String[n];


        for (String key : values.keySet()) {
            if (key.equals("id")) continue;
            sql += " AND " + key + " = ? ";
            args[i] = values.get(key).toString();
            ++i;
        }

        SQLiteStatement statement = db.compileStatement(sql);

        if (statement == null) return false;


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
            if (i > 0) sql += ", ";
            sql += key + " = ?";
            args[i] = values.get(key).toString();
            ++i;
        }

        sql += " WHERE id = ?";
        args[values.size() + 1] = String.valueOf(converter.getId(value));

        SQLiteStatement statement = db.compileStatement(sql);

        if (statement == null) return false;

        statement.bindAllArgsAsStrings(args);
        statement.execute();

        return true;
    }

    public boolean insert(T value) {
        HashMap<String, Object> values = converter.toHashMap(value);
        int n = values.size() - 1;
        String[] args = new String[n];
        int i = 0;
        boolean isFirst = true;
        String sql = "INSERT INTO " + tableName + " (";
        for (String key : values.keySet()) {
            if (key.equals("id")) continue;
            if (!isFirst) {
                sql += ", ";
            } else isFirst = false;
            sql += key;
            args[i] = values.get(key).toString();
            ++i;
        }
        sql += ") VALUES (";
        for (int p = 0; p < n; ++p) {
            if (p > 0) sql += ", ";
            sql += "?";
        }
        sql += ")";

        SQLiteStatement statement = db.compileStatement(sql);

        if (statement == null) return false;

        statement.bindAllArgsAsStrings(args);
        statement.execute();

        return true;
    }

    public void close() {
        db.close();
        helper.close();
    }
}
