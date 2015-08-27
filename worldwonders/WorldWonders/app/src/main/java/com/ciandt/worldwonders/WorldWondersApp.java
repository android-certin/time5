package com.ciandt.worldwonders;

import android.app.Application;

import com.ciandt.worldwonders.helper.WondersSQLiteHelper;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by pmachado on 8/24/15.
 */
public class WorldWondersApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Roboto-Thin.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build());

        if (!WondersSQLiteHelper.verifyDatabase()) {
            WondersSQLiteHelper.copyDatabase(getApplicationContext());
        }
    }
}
