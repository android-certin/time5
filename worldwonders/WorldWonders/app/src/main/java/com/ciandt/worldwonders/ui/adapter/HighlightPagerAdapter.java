package com.ciandt.worldwonders.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.ciandt.worldwonders.model.Wonder;
import com.ciandt.worldwonders.ui.fragment.HighlightFragment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by pmachado on 8/23/15.
 */
public class HighlightPagerAdapter extends FragmentStatePagerAdapter {

    private final int LIST_SIZE = 3;
    private List<Wonder> listWonders;

    public HighlightPagerAdapter(FragmentManager fm, List<Wonder> listWonders) {
        super(fm);
        List<Wonder> allListWonders = new ArrayList<>(listWonders);
        Collections.shuffle(allListWonders);
        this.listWonders = allListWonders.subList(0, LIST_SIZE);
    }

    @Override
    public Fragment getItem(int position) {
        return HighlightFragment.newInstance(listWonders.get(position));
    }

    @Override
    public int getCount() {
        return LIST_SIZE;
    }
}

