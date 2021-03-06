package com.ciandt.worldwonders.database;

import android.database.AbstractCursor;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by pmachado on 8/24/15.
 * Contributions by eferraz on 8/25/15
 */
public class DaoBase {
    public List<HashMap<String, Object>> convert(Cursor cursor) {
        List<HashMap<String, Object>> result = new ArrayList<>();

        if (cursor.moveToFirst()) {
            do {
                int nc = cursor.getColumnCount();
                HashMap<String, Object> row = new HashMap<>();

                for (int i = 0; i < nc; ++i) {
                    String key = cursor.getColumnName(i);
                    switch (cursor.getType(i)) {
                        case AbstractCursor.FIELD_TYPE_INTEGER:
                            row.put(key, cursor.getInt(i));
                            break;
                        case AbstractCursor.FIELD_TYPE_FLOAT:
                            row.put(key, cursor.getDouble(i));
                            break;
                        case AbstractCursor.FIELD_TYPE_STRING:
                            row.put(key, cursor.getString(i));
                            break;
                        case AbstractCursor.FIELD_TYPE_BLOB:
                            row.put(key, cursor.getBlob(i));
                            break;
                        case AbstractCursor.FIELD_TYPE_NULL:
                            break;
                        default:
                            row.put(key, cursor.getString(i));
                            break;
                    }
                }

                result.add(row);
            } while (cursor.moveToNext());
        }

        return result;
    }
}
