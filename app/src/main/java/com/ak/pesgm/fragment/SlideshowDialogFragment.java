package com.ak.pesgm.fragment;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.ProgressDialog;
import android.app.WallpaperManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


import com.ak.pesgm.R;
import com.ak.pesgm.adapter.MyViewPagerAdapter;
import com.ak.pesgm.model.ImageData;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

//import io.realm.Realm;


public class SlideshowDialogFragment extends DialogFragment {
    private String TAG = SlideshowDialogFragment.class.getSimpleName();
    private ArrayList<ImageData> images;
    private ViewPager viewPager;
    private MyViewPagerAdapter myViewPagerAdapter;
    private TextView lblTitle;//lblCount,, lblDate;
    ImageButton btnInfo, btnShare, btnSetWallpaper;
    private int selectedPosition = 0;
    //Realm realm;

    int pos;

    public static SlideshowDialogFragment newInstance() {
        SlideshowDialogFragment f = new SlideshowDialogFragment();
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_image_slider, container, false);
        viewPager = (ViewPager) v.findViewById(R.id.viewpager);
        //lblCount = (TextView) v.findViewById(R.id.lbl_count);
        lblTitle = (TextView) v.findViewById(R.id.title);
        //lblDate = (TextView) v.findViewById(R.id.date);
        btnInfo = (ImageButton) v.findViewById(R.id.btnInfo);
         btnShare = (ImageButton) v.findViewById(R.id.btnShare);
        btnSetWallpaper = (ImageButton) v.findViewById(R.id.btnWallpaper);
       // realm = Realm.getDefaultInstance();

        images = GalleryFragment.imageData; //(ArrayList<ImageData>) getArguments().getSerializable("images");
        selectedPosition = getArguments().getInt("position");


        Log.e(TAG, "position: " + selectedPosition);
        Log.e(TAG, "images size: " + images.size());

        myViewPagerAdapter = new MyViewPagerAdapter(getActivity(), images);
        viewPager.setAdapter(myViewPagerAdapter);
        viewPager.addOnPageChangeListener(viewPagerPageChangeListener);

        setCurrentItem(selectedPosition);


        btnInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Toast.makeText(getActivity(),"ImageInfo",Toast.LENGTH_SHORT).show();
                ImageData image = images.get(pos);

                // custom dialog
                final Dialog dialog = new Dialog(getActivity());
                dialog.setContentView(R.layout.info_dialog);
                dialog.setTitle(image.getDate() + "");

                // set the custom dialog components - text, image and button
                TextView text = (TextView) dialog.findViewById(R.id.text);
                text.setText(image.getInfo() + "");


                Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonOK);
                // if button is clicked, close the custom dialog
                dialogButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                dialog.show();


            }

        });

        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bitmap b = BitmapFactory.decodeResource(getResources(),images.get(pos).getPath());
                Intent share = new Intent(Intent.ACTION_SEND);
                share.setType("image/jpeg");
                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                b.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
                String path = MediaStore.Images.Media.insertImage(getActivity().getContentResolver(),
                        b, "Title", null);
                Uri imageUri =  Uri.parse(path);
                share.putExtra(Intent.EXTRA_STREAM, imageUri);
                startActivity(Intent.createChooser(share, "Select"));
            }
        });

        btnSetWallpaper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder1 = new AlertDialog.Builder(getActivity());
                builder1.setMessage("Set this image as wallpaper");
                builder1.setCancelable(true);

                builder1.setPositiveButton(
                        "Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                WallpaperManager myWallpaperManager
                                        = WallpaperManager.getInstance(getActivity().getApplicationContext());
                                try {
                                    myWallpaperManager.setResource(images.get(pos).getPath());

                                    Toast.makeText(getActivity(),"Wallpaper set !",Toast.LENGTH_SHORT).show();
                                } catch (IOException e) {
                                    // TODO Auto-generated catch block
                                    e.printStackTrace();
                                }

                                dialog.cancel();
                            }
                        });

                builder1.setNegativeButton(
                        "No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert11 = builder1.create();
                alert11.show();



            }
        });

        return v;
    }

    private void setCurrentItem(int position) {
        viewPager.setCurrentItem(position, false);
        displayMetaInfo(selectedPosition);
    }

    //	page change listener
    ViewPager.OnPageChangeListener viewPagerPageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageSelected(int position) {
            displayMetaInfo(position);
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }
    };


    private void displayMetaInfo(int position) {
        // lblCount.setText((position + 1) + " of " + images.size());

        pos = position;

        ImageData image = images.get(position);
        lblTitle.setText(String.valueOf(image.getDate()));

        if(image.getInfo().equals("")){
            btnInfo.setVisibility(View.GONE);
        }
        else {
            btnInfo.setVisibility(View.VISIBLE);
        }

        //lblDate.setText(image.getDate());
/*
        if (image.getMediaType() == 1) {
            btnEdit.setVisibility(View.VISIBLE);
            //btnEdit.setText("Edit");
        } else if (image.getMediaType() == 2) {
            btnEdit.setVisibility(View.INVISIBLE);
//            btnEdit.setText("Play");


        }*/
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, android.R.style.Theme_Black_NoTitleBar_Fullscreen);
    }


}
