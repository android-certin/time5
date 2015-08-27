package com.ciandt.worldwonders.database;

import android.content.Context;

import com.ciandt.worldwonders.helper.WondersSQLiteHelper;
import com.ciandt.worldwonders.model.WonderBookmark;

/**
 * Created by pmachado on 8/26/15.
 */
public class WonderBookmarkDao extends Dao<WonderBookmark> {
    public WonderBookmarkDao(Context context) {
        super("bookmarks", WonderBookmark.getConverter());
        setHelper(new WondersSQLiteHelper(context));
    }

    @Override
    public void close() {
        super.close();
    }
}
