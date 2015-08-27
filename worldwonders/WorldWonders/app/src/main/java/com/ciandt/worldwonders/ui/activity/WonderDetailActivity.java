package com.ciandt.worldwonders.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import com.ciandt.worldwonders.R;
import com.ciandt.worldwonders.model.Wonder;
import com.ciandt.worldwonders.protocol.Protocol;
import com.ciandt.worldwonders.ui.fragment.WonderDetailFragment;

public class WonderDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wonder_detail);

        Wonder wonder = (Wonder) getIntent().getSerializableExtra("wonder");

        WonderDetailFragment wonderDetail = WonderDetailFragment.newInstance(wonder);


        wonderDetail.setOnBookmarkListener(new WonderDetailFragment.OnBookmarkListener() {
            @Override
            public void onBookmarked(Wonder w) {
                Intent intent = new Intent();

                intent.putExtra("wonder", w);
                setResult(Protocol.UPDATE_BOOKMARK, intent);
            }
        });

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.detailContainer, wonderDetail)
                .commit();
    }


}
