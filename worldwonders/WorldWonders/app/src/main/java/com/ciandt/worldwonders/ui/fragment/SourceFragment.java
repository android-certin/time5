package com.ciandt.worldwonders.ui.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.ciandt.worldwonders.R;
import com.ciandt.worldwonders.model.Wonder;


/**
 * Created by pmachado on 8/27/15.
 */
public class SourceFragment extends android.support.v4.app.DialogFragment {


    static public DialogFragment show(Wonder wonder, FragmentManager manager)  {
        SourceFragment fragment = new SourceFragment();

        Bundle args = new Bundle();

        args.putSerializable("wonder", wonder);
        fragment.setArguments(args);
        fragment.setCancelable(false);

        fragment.show(manager, "source_dialog");

        return fragment;
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_source, null);

        Bundle args = getArguments();
        Wonder wonder = (Wonder) args.getSerializable("wonder");

        AlertDialog alertDialog = new AlertDialog
                .Builder(getActivity())
                .setTitle(wonder.name)
                .setView(view)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create();


        WebView sourceView = (WebView) view.findViewById(R.id.view_source);

        sourceView.setWebViewClient(new WebViewClient());
        sourceView.loadUrl(wonder.url);

        return alertDialog;
    }
}
