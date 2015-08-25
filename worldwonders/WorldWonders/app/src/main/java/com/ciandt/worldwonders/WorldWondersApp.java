package com.ciandt.worldwonders;

import android.app.Application;
import android.util.Log;

import com.ciandt.worldwonders.database.WonderDao;
import com.ciandt.worldwonders.model.Wonder;

import java.util.List;

/**
 * Created by pmachado on 8/24/15.
 */
public class WorldWondersApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        WonderDao dao = new WonderDao(getApplicationContext());
        List<Wonder> l = dao.getAll();
        for (Wonder w: l) {
            Log.i("DB", w.name);
        }
    }
}
