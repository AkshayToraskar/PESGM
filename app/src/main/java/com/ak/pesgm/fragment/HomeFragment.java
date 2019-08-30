package com.ak.pesgm.fragment;


import android.app.Fragment;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ak.pesgm.R;
import com.ak.pesgm.activity.PdfActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

   /* @BindView(R.id.cv_aarti_collection)
    CardView cvAartiCollection;
    @BindView(R.id.cv_facebook)
    CardView cvFacebook;
    @BindView(R.id.cv_insta)
    CardView cvInsta;*/

    View view;

    String fburl = "https://www.facebook.com/PESGMandal1/";
    String instaurl = "https://www.instagram.com/pesgm/";

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);

       /* cvAartiCollection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), PdfActivity.class));
            }
        });

        cvFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent fbIntent = getFacebookIntent(fburl);
                startActivity(fbIntent);
            }
        });

        cvInsta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getInstagramIntent(instaurl);
            }
        });*/

        return view;
    }



}
