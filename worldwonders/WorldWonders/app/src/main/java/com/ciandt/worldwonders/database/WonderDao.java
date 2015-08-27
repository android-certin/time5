package com.ciandt.worldwonders.database;

import android.content.Context;

import com.ciandt.worldwonders.helper.WondersSQLiteHelper;
import com.ciandt.worldwonders.model.Wonder;

/**
 * Created by pmachado on 8/24/15.
 */
public class WonderDao extends Dao<Wonder> {
    public WonderDao(Context context) {
        super("wonders", Wonder.getConverter());
        setHelper(new WondersSQLiteHelper(context));
    }

    @Override
    public void close() {
        super.close();
    }
}
