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
    private OnSelectWonderListener onSelectWonderListener;

    public WonderItemAdapter(List<Wonder> wonderList) {
        this.wonderList = wonderList;
    }

    public void setOnSelectWonderListener(OnSelectWonderListener onSelectWonderListener) {
        this.onSelectWonderListener = onSelectWonderListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        context = parent.getContext();

        View v = LayoutInflater.from(context).inflate(R.layout.fragment_wonder_item, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int i) {
        final Wonder wonder = wonderList.get(i);

        String namePhoto = wonder.photo;
        int resourceId = Helpers.getRawResourceID(context, namePhoto.replace(".jpg",""));
        Picasso.with(context)
                .load(resourceId)
                .resize(100, 100)
                .centerCrop()
                .config(Bitmap.Config.RGB_565)
                .into(holder.image);

        holder.text.setText(wonder.name);
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onSelectWonderListener != null) {
                    onSelectWonderListener.onSelectWonder(wonder);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return wonderList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView text;
        public ImageView image;
        public View view;

        public ViewHolder(View v) {
            super(v);


            this.text  = (TextView) v.findViewById(R.id.wonder_item_nome);
            this.image = (ImageView) v.findViewById(R.id.wonder_item_imagem);
            this.view = v;

        }
    }

    public interface OnSelectWonderListener {
        public void onSelectWonder(Wonder wonder);
    }
}
