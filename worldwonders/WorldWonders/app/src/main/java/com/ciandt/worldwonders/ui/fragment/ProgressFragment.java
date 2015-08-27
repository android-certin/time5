package com.ciandt.worldwonders.ui.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.ciandt.worldwonders.R;
import com.ciandt.worldwonders.model.Wonder;


/**
 * Created by pmachado on 8/27/15.
 */
public class ProgressFragment extends DialogFragment {

    private ImageView imageView;

    static public ProgressFragment show(FragmentManager manager)  {
        ProgressFragment fragment = new ProgressFragment();
        fragment.setCancelable(false);
        fragment.show(manager, "source_dialog");
        return fragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_progress, null);

        imageView = (ImageView) view.findViewById(R.id.progress_image_view);
        ((AnimationDrawable) imageView.getBackground()).start();

        AlertDialog alertDialog = new AlertDialog
                .Builder(getActivity())
                .setView(view)
                .create();

        return alertDialog;
    }
}
