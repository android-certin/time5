package com.ciandt.worldwonders.database;

import android.content.Context;

import com.ciandt.worldwonders.helper.WondersSQLiteHelper;
import com.ciandt.worldwonders.model.Wonder;

/**
 * Created by pmachado on 8/24/15.
 */
public class WonderDao extends Dao<Wonder> {
    WondersSQLiteHelper dbHelper;

    public WonderDao(Context context) {
        super("wonders", Wonder.getConverter());

        dbHelper = new WondersSQLiteHelper(context);

        setDatabase(dbHelper.getWritableDatabase());
    }


    @Override
    public void close() {
        super.close();
        dbHelper.close();
    }
}
