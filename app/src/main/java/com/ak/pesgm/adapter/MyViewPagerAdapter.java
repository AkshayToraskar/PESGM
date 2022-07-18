package com.ak.pesgm.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.viewpager.widget.PagerAdapter;

import com.ak.pesgm.R;
import com.ak.pesgm.helper.TouchImageView;
import com.ak.pesgm.model.ImageData;
import com.bumptech.glide.Glide;

import java.util.ArrayList;


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
        final ImageData image = images.get(position);

        Glide.with(activity)
                .load(image.getPath())
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