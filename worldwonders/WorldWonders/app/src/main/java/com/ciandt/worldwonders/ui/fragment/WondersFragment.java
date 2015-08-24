package com.ciandt.worldwonders.ui.fragment;

import com.ciandt.worldwonders.R;
import com.ciandt.worldwonders.ui.adapter.HighlightPagerAdapter;

import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class WondersFragment extends android.support.v4.app.Fragment {

    private ViewPager viewPager;
    private PagerAdapter pagerAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_wonders, container, false);

        viewPager = (ViewPager) v.findViewById(R.id.slide);
        pagerAdapter = new HighlightPagerAdapter(getActivity().getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);

        return v;
    }
}
