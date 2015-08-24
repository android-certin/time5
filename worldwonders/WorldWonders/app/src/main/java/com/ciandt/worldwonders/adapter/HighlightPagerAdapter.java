package com.ciandt.worldwonders.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.ciandt.worldwonders.fragment.HighlightFragment;
import com.ciandt.worldwonders.model.Wonder;

/**
 * Created by pmachado on 8/23/15.
 */
public class HighlightPagerAdapter extends FragmentStatePagerAdapter {

    public HighlightPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Wonder wonder = new Wonder("My id: " + (new Integer(position)).toString(), position);
        HighlightFragment highlightFragment = HighlightFragment.newInstance(wonder);
        return highlightFragment;
    }

    @Override
    public int getCount() {
        return 3;
    }
}

