package com.ciandt.worldwonders.ui.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ciandt.worldwonders.R;
import com.ciandt.worldwonders.helper.Helpers;
import com.ciandt.worldwonders.model.User;
import com.ciandt.worldwonders.model.Wonder;
import com.ciandt.worldwonders.model.WonderBookmark;
import com.ciandt.worldwonders.protocol.Protocol;
import com.ciandt.worldwonders.repository.WondersRepository;
import com.squareup.picasso.Picasso;

public class WonderDetailActivity extends AppCompatActivity {

    private Wonder wonder;

    private ImageView imageView;
    private TextView descriptionTextView;
    private Toolbar toolbar;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private MenuItem bookmarkItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wonder_detail);

        wonder = (Wonder) getIntent().getSerializableExtra("wonder");

        imageView = (ImageView)findViewById(R.id.wonder_item_image);
        descriptionTextView = (TextView)findViewById(R.id.wonder_item_description);
        toolbar = (Toolbar)findViewById(R.id.wonder_detail_toolbar);
        collapsingToolbarLayout = (CollapsingToolbarLayout)findViewById(R.id.collapsing_toolbar);


        int resourceId = Helpers.getRawResourceID(this, wonder.photo.replace(".jpg", ""));
        Picasso.with(this)
                .load(resourceId)
                .resize(320, 200)
                .centerCrop()
                .config(Bitmap.Config.RGB_565).into(imageView);

        collapsingToolbarLayout.setTitle(wonder.name);
        descriptionTextView.setText(wonder.description);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_wonder_detail, menu);
        bookmarkItem = menu.findItem(R.id.action_bookmark);
        updateBookmarkIcon();
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            case R.id.action_bookmark:
                addBookmark();
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

    private void addBookmark() {
        WondersRepository repository = new WondersRepository(this);
        if (wonder.isMarked) {
            wonder.isMarked = false;
            repository.delete(new WondersRepository.WonderDeleteListener() {
                @Override
                public void onBookmarkDeleted(Exception e, boolean result) {
                    updateBookmarkIcon();

                    Intent intent = new Intent();

                    intent.putExtra("wonder", wonder);
                    setResult(Protocol.UPDATE_BOOKMARK, intent);
                }
            }, new WonderBookmark(wonder.id));
        } else {
            wonder.isMarked = true;
            repository.insert(new WondersRepository.WonderInsertListener() {
                @Override
                public void onBookmarkInserted(Exception e, boolean result) {
                    updateBookmarkIcon();

                    Intent intent = new Intent();

                    intent.putExtra("wonder", wonder);
                    setResult(Protocol.UPDATE_BOOKMARK, intent);
                }
            }, new WonderBookmark(wonder.id));
        }
    }

    private void updateBookmarkIcon() {
        if (wonder.isMarked) {
            bookmarkItem.setIcon(R.drawable.ic_bookmark_white_24dp);
        }
        else {
            bookmarkItem.setIcon(R.drawable.ic_bookmark_border_white_24dp);
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
            Intent sendIntent  = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(sendIntent);
        } else {
            Toast.makeText(this, "Desconhecido", Toast.LENGTH_SHORT).show();
        }

    }
}
