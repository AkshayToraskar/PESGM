package com.ak.pesgm.fragment;


import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.IdRes;
import androidx.cardview.widget.CardView;

import com.ak.pesgm.R;
import com.ak.pesgm.activity.PdfActivity;
import com.ak.pesgm.activity.PesgmActivity;
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

    @BindView(R.id.wvAddress)
    WebView wvAddress;


    public static int count = 0;
    String[] tips;

    View view;

    String fburl = "https://www.facebook.com/PESGMandal1/";
    String instaurl = "https://www.instagram.com/pesgm/";


    public AboutFragment() {
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

        tips = getResources().getStringArray(R.array.tips_tricks);
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
        });

        wvAddress.getSettings().setJavaScriptEnabled(true);
        wvAddress.loadData("<iframe src=\"https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d943.3104563228565!2d72.81203811468214!3d18.964924621029503!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x0%3A0x632786d9ba511713!2sSachinam+Heights!5e0!3m2!1sen!2sin!4v1503522801813\" width=\"400\" height=\"300\" frameborder=\"0\" style=\"border:0\" allowfullscreen></iframe>", "text/html", "utf-8");
        WebSettings webViewSettings = wvAddress.getSettings();
        webViewSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webViewSettings.setJavaScriptEnabled(true);
        webViewSettings.setBuiltInZoomControls(false);
        webViewSettings.setPluginState(WebSettings.PluginState.ON);

        return view;
    }

    public void setLocale(String lang) {

        myLocale = new Locale(lang);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);

        Intent refresh = new Intent(getActivity(), PesgmActivity.class);
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
