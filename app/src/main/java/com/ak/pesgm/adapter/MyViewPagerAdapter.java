package com.ak.pesgm.adapter;

import android.app.Activity;
import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.VideoView;

import com.ak.pesgm.R;
import com.ak.pesgm.helper.TouchImageView;
import com.ak.pesgm.model.ImageData;
import com.bumptech.glide.Glide;

import java.io.File;
import java.util.ArrayList;

import static android.view.View.GONE;

/**
 * Created by dg hdghfd on 02-06-2017.
 */

public class MyViewPagerAdapter extends PagerAdapter {

    private LayoutInflater layoutInflater;
    Activity activity;
    private ArrayList<ImageData> images;

    public MyViewPagerAdapter(Activity activity, ArrayList<ImageData> images) {
        this.activity = activity;
        this.images = images;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        layoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.image_fullscreen_preview, container, false);

        final TouchImageView imageViewPreview = (TouchImageView) view.findViewById(R.id.image_preview);
       // final VideoView videoView = (VideoView) view.findViewById(R.id.video_preview);
      //  final ImageView btnPlayVideo = (ImageView) view.findViewById(R.id.ivPlayButton);
      //  RelativeLayout rlVideo = (RelativeLayout) view.findViewById(R.id.rlVideo);

        final ImageData image = images.get(position);



            Glide.with(activity)//.load(image.getByteArrayImage())
                    .load(Uri.fromFile(new File(image.getPath())))

                    .into(imageViewPreview);





        container.addView(view);

        return view;
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object obj) {
        return view == ((View) obj);
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}