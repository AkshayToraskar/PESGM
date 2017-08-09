package com.ak.pesgm.adapter;

import android.app.Activity;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.ak.pesgm.R;
import com.ak.pesgm.app.PreviewData;
import com.ak.pesgm.model.ImageData;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;

/**
 * Created by dg hdghfd on 12-04-2017.
 */

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.MyViewHolder> {

    private List<ImageData> sessionList;
    private Activity context;
    Realm realm;
    PreviewData previewData;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.ivImg)
        ImageView ivImage;



        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    previewData.previewInfo(getPosition());
                }
            });
        }
    }


    public ImageAdapter(Activity context, List<ImageData> sessionList, PreviewData previewData) {
        this.sessionList = sessionList;
        this.context = context;
        this.previewData = previewData;
        realm = Realm.getDefaultInstance();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.session_image_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

            Glide.with(context).load(sessionList.get(position).getByteArrayImage())
                    //.load(new File(sessionList.get(position).getPath()))
                    .thumbnail(0.5f)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(holder.ivImage);
           }

    @Override
    public int getItemCount() {
        return sessionList.size();
    }



}