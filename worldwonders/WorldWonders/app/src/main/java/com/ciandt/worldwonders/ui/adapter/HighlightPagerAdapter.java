package com.ciandt.worldwonders.ui.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.ciandt.worldwonders.repository.WondersRepository;
import com.ciandt.worldwonders.ui.fragment.HighlightFragment;
import com.ciandt.worldwonders.model.Wonder;

import java.util.List;

/**
 * Created by pmachado on 8/23/15.
 */
public class HighlightPagerAdapter extends FragmentStatePagerAdapter {

    private List<Wonder> listWonders;

    public HighlightPagerAdapter(FragmentManager fm, List<Wonder> listWonders) {
        super(fm);
        this.listWonders = listWonders;
    }

    @Override
    public Fragment getItem(int position) {

        Wonder wonder = listWonders.get(position);
        HighlightFragment highlightFragment = HighlightFragment.newInstance(wonder);
        return highlightFragment;
    }

    @Override
    public int getCount() {
        return 3;
    }
}

