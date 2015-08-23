package com.ciandt.worldwonders;

import com.ciandt.worldwonders.model.WorldWonder;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by daianefs on 21/08/15.
 */
public class WorldWonderFragment extends Fragment {

    private int myId;

    public Fragment setWonderId(int id) {
        myId = id;
        return this;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_world_wonder, container, false);
        TextView textView = (TextView) v.findViewById(R.id.fragment_world_wonder_label);

        textView.setText(WorldWonder.getWonderName(myId) + "\n");
        textView.append(WorldWonder.getWonderImage(myId) + "\n");
        textView.append(WorldWonder.getWonderDescription(myId) + "\n");

        return v;
    }
}
