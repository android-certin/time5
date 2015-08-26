package com.ciandt.worldwonders.ui.activity;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.ciandt.worldwonders.R;
import com.ciandt.worldwonders.helper.Helpers;
import com.ciandt.worldwonders.model.Wonder;
import com.squareup.picasso.Picasso;

public class WonderDetailActivity extends AppCompatActivity {

    Wonder wonder;

    ImageView imageView;
    TextView nameTextView;
    TextView descriptionTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wonder_detail);

        wonder = (Wonder)getIntent().getSerializableExtra("wonder");

        imageView = (ImageView)findViewById(R.id.wonder_item_image);
        nameTextView = (TextView)findViewById(R.id.wonder_item_name);
        descriptionTextView = (TextView)findViewById(R.id.wonder_item_description);


        int resourceId = Helpers.getRawResourceID(this, wonder.photo.replace(".jpg", ""));
        Picasso.with(this).
                load(resourceId).
                config(Bitmap.Config.RGB_565).into(imageView);

        nameTextView.setText(wonder.name);
        descriptionTextView.setText(wonder.description);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_wonder_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}