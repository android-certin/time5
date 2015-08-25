package com.ciandt.worldwonders.ui.adapter;


import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ciandt.worldwonders.R;
import com.ciandt.worldwonders.helper.Helpers;
import com.ciandt.worldwonders.model.Wonder;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by pmachado on 8/25/15.
 */
public class WonderItemAdapter extends RecyclerView.Adapter<WonderItemAdapter.ViewHolder> {

    private List<Wonder> wonderList;
    private Context context;

    public WonderItemAdapter(List<Wonder> wonderList) {
        this.wonderList = wonderList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        context = parent.getContext();

        View v = LayoutInflater.from(context)
                               .inflate(R.layout.fragment_wonder_item, parent, false);

        TextView textView = (TextView) v.findViewById(R.id.wonder_item_nome);
        ImageView imageView = (ImageView) v.findViewById(R.id.wonder_item_imagem);

        ViewHolder vh = new ViewHolder(textView, imageView);

        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int i) {
        Wonder wonder = wonderList.get(i);

        String namePhoto = wonder.photo;

        /*int resourceId = Helpers.getRawResourceID(context, namePhoto.replace(".jpg", ""));
        Picasso.with(context).
                load(resourceId).
                config(Bitmap.Config.RGB_565).into(holder.image);*/

        holder.text.setText(wonder.name);
    }

    @Override
    public int getItemCount() {
        return wonderList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView text;
        public ImageView image;

        public ViewHolder(TextView text, ImageView image) {
            super(text);
            this.text = text;
            this.image = image;
        }
    }


}
