package com.ciandt.worldwonders.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.ciandt.worldwonders.fragment.WorldWonderFragment;
import com.ciandt.worldwonders.model.WorldWonder;

/**
 * Created by pmachado on 8/23/15.
 */
public class WorldWondersPagerAdapter extends FragmentStatePagerAdapter {

    public WorldWondersPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return (new WorldWonderFragment()).setWonderId(position);
    }

    @Override
    public int getCount() {
        return WorldWonder.getWondersCount();
    }
}

