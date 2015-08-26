package com.ciandt.worldwonders.database;

import android.content.Context;

import com.ciandt.worldwonders.helper.WondersSQLiteHelper;
import com.ciandt.worldwonders.model.Wonder;
import com.ciandt.worldwonders.model.WonderBookmark;

/**
 * Created by pmachado on 8/26/15.
 */
public class WonderBookmarkDao extends Dao<WonderBookmark> {
    WondersSQLiteHelper dbHelper;

    public WonderBookmarkDao(Context context, String tableName) {
        super(tableName, WonderBookmark.getConverter());

        dbHelper = new WondersSQLiteHelper(context);

        setDatabase(dbHelper.getWritableDatabase());
    }

    public WonderBookmarkDao(Context context) {
        super("bookmarks", WonderBookmark.getConverter());

        dbHelper = new WondersSQLiteHelper(context);

        setDatabase(dbHelper.getWritableDatabase());
    }


    @Override
    public void close() {
        super.close();
        dbHelper.close();
    }
}
