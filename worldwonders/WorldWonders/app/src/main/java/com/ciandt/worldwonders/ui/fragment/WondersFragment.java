package com.ciandt.worldwonders.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ciandt.worldwonders.R;
import com.ciandt.worldwonders.model.Wonder;
import com.ciandt.worldwonders.protocol.Protocol;
import com.ciandt.worldwonders.repository.WondersRepository;
import com.ciandt.worldwonders.ui.activity.WonderDetailActivity;
import com.ciandt.worldwonders.ui.adapter.HighlightPagerAdapter;
import com.ciandt.worldwonders.ui.adapter.WonderItemAdapter;

import java.util.List;

public class WondersFragment extends Fragment {
    private ViewPager viewPager;
    private PagerAdapter pagerAdapter;

    private RecyclerView recyclerView;
    private WonderItemAdapter wonderItemAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_wonders, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewPager = (ViewPager) view.findViewById(R.id.slide);

        recyclerView = (RecyclerView) view.findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        final ProgressFragment progressFragment = ProgressFragment.show(getFragmentManager());

        WondersRepository repository = new WondersRepository(getContext());
        repository.getAll(new WondersRepository.WonderAllListener() {
            @Override
            public void onWonderAll(Exception e, List<Wonder> wonders) {
                pagerAdapter = new HighlightPagerAdapter(getFragmentManager(), wonders);
                viewPager.setAdapter(pagerAdapter);

                wonderItemAdapter = new WonderItemAdapter(wonders);
                wonderItemAdapter.setOnSelectWonderListener(new WonderItemAdapter.OnSelectWonderListener() {
                    @Override
                    public void onSelectWonder(Wonder wonder) {
                        Intent intent = new Intent(getContext(), WonderDetailActivity.class);
                        intent.putExtra("wonder", wonder);
                        startActivityForResult(intent, Protocol.UPDATE_BOOKMARK);
                    }
                });
                recyclerView.setAdapter(wonderItemAdapter);

                progressFragment.dismiss();
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case Protocol.UPDATE_BOOKMARK:
                if (data != null && resultCode == Protocol.UPDATE_BOOKMARK) {
                    Wonder w = (Wonder) data.getSerializableExtra("wonder");
                    wonderItemAdapter.updateWonder(w);
                }
                break;
        }
    }
}
