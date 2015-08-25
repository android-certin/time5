package com.ciandt.worldwonders.ui.fragment;

import com.ciandt.worldwonders.R;
import com.ciandt.worldwonders.model.Wonder;
import com.ciandt.worldwonders.repository.WondersRepository;
import com.ciandt.worldwonders.ui.adapter.HighlightPagerAdapter;
import com.ciandt.worldwonders.ui.adapter.WonderItemAdapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class WondersFragment extends Fragment {

    private ViewPager viewPager;
    private PagerAdapter pagerAdapter;

    private RecyclerView recyclerView;
    private WonderItemAdapter wonderItemAdapter;

    private FragmentManager manager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_wonders, container, false);

        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewPager = (ViewPager) view.findViewById(R.id.slide);
        recyclerView = (RecyclerView) view.findViewById(R.id.list);

        manager  = getFragmentManager();

        WondersRepository repository = new WondersRepository(getContext());
        repository.getAll(new WondersRepository.WonderAllListener() {
            @Override
            public void onWonderAll(Exception e, List<Wonder> wonders) {
                pagerAdapter = new HighlightPagerAdapter(manager, wonders);
                viewPager.setAdapter(pagerAdapter);

                wonderItemAdapter = new WonderItemAdapter(wonders);
                recyclerView.setAdapter(wonderItemAdapter);
            }
        });
    }
}
