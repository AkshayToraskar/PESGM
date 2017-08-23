package com.ak.pesgm.fragment;


import android.app.Fragment;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.widget.CardView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.ak.pesgm.R;
import com.ak.pesgm.activity.MainActivity;
import com.ak.pesgm.activity.PdfActivity;
import com.ak.pesgm.app.SessionManager;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class AboutFragment extends Fragment {


    @BindView(R.id.rb_marathi)
    RadioButton rbMarathi;
    @BindView(R.id.rb_english)
    RadioButton rbEnglish;
    @BindView(R.id.rg_lang)
    RadioGroup rgLang;

    Locale myLocale;
    SessionManager sessionManager;
    @BindView(R.id.cv_aarti_collection)
    CardView cvAartiCollection;
    @BindView(R.id.cv_facebook)
    CardView cvFacebook;
    @BindView(R.id.cv_insta)
    CardView cvInsta;

    View view;

    String fburl = "https://www.facebook.com/PESGMandal1/";
    String instaurl = "https://www.instagram.com/pesgm/";

    public AboutFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_about, container, false);
        ButterKnife.bind(this, view);
        sessionManager = new SessionManager(getActivity());

        if (sessionManager.getLanguage().equals("mr")) {
            rbMarathi.setChecked(true);
        } else if (sessionManager.getLanguage().equals("en")) {
            rbEnglish.setChecked(true);
        }

        rgLang.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId) {
                    case R.id.rb_marathi:
                        sessionManager.setLanguage("mr");
                        setLocale("mr");
                        break;

                    case R.id.rb_english:
                        sessionManager.setLanguage("en");
                        setLocale("en");
                        break;
                }
            }
        });

        cvAartiCollection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //CopyReadAssets();


              /*  try {

                    // your intent here
                    Uri path = Uri.parse("android.resource://com.ak.pesgm/raw/aarti.pdf");
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setDataAndType(path, "application/pdf");
                    // intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    getActivity().startActivity(intent);

                } catch (ActivityNotFoundException e) {
                    // show message to user

                    Log.v("asf",""+e.getMessage());*/

                    startActivity(new Intent(getActivity(), PdfActivity.class));
                //}


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
        });


        return view;
    }


    public void setLocale(String lang) {

        myLocale = new Locale(lang);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);

        Intent refresh = new Intent(getActivity(), MainActivity.class);
        refresh.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(refresh);
    }


    public Intent getFacebookIntent(String url) {

        PackageManager pm = getActivity().getPackageManager();
        Uri uri = Uri.parse(url);

        try {
            ApplicationInfo applicationInfo = pm.getApplicationInfo("com.facebook.katana", 0);
            if (applicationInfo.enabled) {
                uri = Uri.parse("fb://facewebmodal/f?href=" + url);
            }
        } catch (PackageManager.NameNotFoundException ignored) {
        }

        return new Intent(Intent.ACTION_VIEW, uri);
    }

    public void getInstagramIntent(String url) {

        Uri uri = Uri.parse(url);
        Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);
        likeIng.setPackage("com.instagram.android");
        startActivity(likeIng);
    }


    private void CopyReadAssets() {
        AssetManager assetManager = getActivity().getAssets();

        InputStream in = null;
        OutputStream out = null;
        File file = new File(getActivity().getFilesDir(), "aarti.pdf");
        try {
            in = assetManager.open("aarti.pdf");
            out = getActivity().openFileOutput(file.getName(), Context.MODE_WORLD_READABLE);

            copyFile(in, out);
            in.close();
            in = null;
            out.flush();
            out.close();
            out = null;
        } catch (Exception e) {
            Log.e("tag", e.getMessage());
        }

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(
                Uri.parse("file://" + getActivity().getFilesDir() + "/aarti.pdf"),
                "application/pdf");

        startActivity(intent);
    }

    private void copyFile(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[1024];
        int read;
        while ((read = in.read(buffer)) != -1) {
            out.write(buffer, 0, read);
        }
    }

}
