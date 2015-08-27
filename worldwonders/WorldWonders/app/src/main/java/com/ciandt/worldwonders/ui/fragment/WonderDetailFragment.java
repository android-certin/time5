package com.ciandt.worldwonders.ui.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ciandt.worldwonders.R;
import com.ciandt.worldwonders.database.WonderBookmarkDao;
import com.ciandt.worldwonders.helper.Helpers;
import com.ciandt.worldwonders.model.User;
import com.ciandt.worldwonders.model.Wonder;
import com.ciandt.worldwonders.model.WonderBookmark;
import com.ciandt.worldwonders.protocol.Protocol;
import com.ciandt.worldwonders.repository.BaseRepository;
import com.ciandt.worldwonders.ui.activity.SignUpActivity;
import com.ciandt.worldwonders.ui.activity.WonderDetailActivity;
import com.squareup.picasso.Picasso;

/**
 * Created by daianefs on 21/08/15.
 */
public class WonderDetailFragment extends android.support.v4.app.Fragment {

    private Wonder wonder;

    private OnBookmarkListener onBookmarkListener;

    private View view;

    private ImageView imageView;
    private TextView descriptionTextView;
    public Toolbar toolbar;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private TextView sourceTextView;

    public static WonderDetailFragment newInstance(Wonder wonder) {
        Bundle args = new Bundle();
        args.putSerializable("wonder", wonder);

        WonderDetailFragment fragment = new WonderDetailFragment();

        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        wonder = (Wonder) getArguments().getSerializable("wonder");
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, final Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_wonder_detail, container, false);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_wonder_detail, menu);
        updateBookmarkIcon(menu.findItem(R.id.action_bookmark));
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        imageView = (ImageView) view.findViewById(R.id.wonder_item_image);
        descriptionTextView = (TextView) view.findViewById(R.id.wonder_item_description);
        toolbar = (Toolbar) view.findViewById(R.id.wonder_detail_toolbar);
        collapsingToolbarLayout = (CollapsingToolbarLayout) view.findViewById(R.id.collapsing_toolbar);
        sourceTextView = (TextView) view.findViewById(R.id.source);

        sourceTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SourceFragment.show(wonder, getFragmentManager());
            }
        });

        int resourceId = Helpers.getRawResourceID(view.getContext(), wonder.photo.replace(".jpg", ""));
        Picasso.with(getActivity())
                .load(resourceId)
                .resize(320, 200)
                .centerCrop()
                .config(Bitmap.Config.RGB_565).into(imageView);

        collapsingToolbarLayout.setTitle(wonder.name);
        descriptionTextView.setText(wonder.description);

        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        if (!Helpers.isTablet(getContext()))
            ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.action_bookmark:
                addBookmark(item);
                break;

            case R.id.action_direction:
                getDirection();
                break;

            case R.id.action_share:
                shareContent();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void addBookmark(MenuItem menuItem) {
        final MenuItem item = menuItem;
        BaseRepository<WonderBookmark> repository =
                new BaseRepository<>(new WonderBookmarkDao(getContext()));
        if (wonder.isMarked) {
            wonder.isMarked = false;
            repository.delete(new BaseRepository.OnDeleteListener() {
                @Override
                public void onDeleted(Exception e, boolean result) {
                    updateBookmarkIcon(item);

                    if (onBookmarkListener != null) {
                        onBookmarkListener.onBookmarked(wonder);
                    }
                }
            }, new WonderBookmark(wonder.id));
        } else {
            wonder.isMarked = true;
            repository.insert(new BaseRepository.OnInsertListener() {
                @Override
                public void onInserted(Exception e, boolean result) {
                    updateBookmarkIcon(item);

                    if (onBookmarkListener != null) {
                        onBookmarkListener.onBookmarked(wonder);
                    }
                }
            }, new WonderBookmark(wonder.id));
        }
    }

    private void updateBookmarkIcon(MenuItem item) {
        if (wonder.isMarked) {
            item.setIcon(R.drawable.ic_bookmark_white_24dp);
        } else {
            item.setIcon(R.drawable.ic_bookmark_border_white_24dp);
        }
    }

    private void shareContent() {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TITLE, wonder.name);
        sendIntent.putExtra(Intent.EXTRA_TEXT, wonder.name);
        sendIntent.putExtra(Intent.EXTRA_SUBJECT, wonder.name);
        sendIntent.setType("text/plain");
        startActivity(sendIntent);
    }

    private void getDirection() {
        Uri uri = Uri.parse("geo:" +
                String.valueOf(wonder.latitude) + "," +
                String.valueOf(wonder.longitude));

        if (uri != null) {
            try {
                Intent sendIntent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(sendIntent);
            } catch (Exception e) {
                Toast.makeText(getActivity(), "uri desconhecida!", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(getActivity(), "Desconhecido", Toast.LENGTH_SHORT).show();
        }
    }

    public void setOnBookmarkListener(OnBookmarkListener onBookmarkListener) {
        this.onBookmarkListener = onBookmarkListener;
    }


    public interface OnBookmarkListener {
        void onBookmarked(Wonder wonder);
    }

}

