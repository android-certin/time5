package com.ciandt.worldwonders.ui.fragment;

import com.ciandt.worldwonders.R;
import com.ciandt.worldwonders.model.Wonder;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by daianefs on 21/08/15.
 */
public class HighlightFragment extends Fragment {
    private Wonder wonder;

    public static HighlightFragment newInstance(Wonder wonder) {
        Bundle args = new Bundle();
        args.putSerializable("wonder", wonder);

        HighlightFragment fragment = new HighlightFragment();

        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle args = getArguments();
        wonder = (Wonder) args.getSerializable("wonder");
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_highlight, container, false);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView textView = (TextView) view.findViewById(R.id.fragment_world_wonder_label);

        textView.setText(wonder.name + "\n");
        textView.append((new Integer(wonder.id)).toString() + "\n");
    }
}
