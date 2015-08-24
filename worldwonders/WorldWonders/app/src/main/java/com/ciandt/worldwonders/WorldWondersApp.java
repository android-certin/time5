package com.ciandt.worldwonders;

import android.app.Application;

import com.ciandt.worldwonders.helper.WondersSQLiteHelper;

/**
 * Created by pmachado on 8/24/15.
 */
public class WorldWondersApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        WondersSQLiteHelper.initialize();
    }
}
