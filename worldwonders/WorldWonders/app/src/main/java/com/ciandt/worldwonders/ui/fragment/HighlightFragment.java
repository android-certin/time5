package com.ciandt.worldwonders.ui.fragment;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ciandt.worldwonders.R;
import com.ciandt.worldwonders.helper.Helpers;
import com.ciandt.worldwonders.model.Wonder;
import com.squareup.picasso.Picasso;

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
        return inflater.inflate(R.layout.fragment_highlight, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ImageView imgWonder = (ImageView) view.findViewById(R.id.fragment_world_wonder_img);
        String namePhoto = wonder.photo;

        TextView textView = (TextView) view.findViewById(R.id.text_frag_hig);
        textView.setText(wonder.name);

        int resourceId = Helpers.getRawResourceID(getContext(), namePhoto.replace(".jpg", ""));
        Picasso.with(getContext()).
                load(resourceId).
                config(Bitmap.Config.RGB_565).into(imgWonder);
    }
}
