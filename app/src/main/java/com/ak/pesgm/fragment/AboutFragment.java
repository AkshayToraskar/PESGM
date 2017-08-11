package com.ak.pesgm.fragment;


import android.app.Fragment;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.ak.pesgm.R;
import com.ak.pesgm.activity.MainActivity;
import com.ak.pesgm.app.SessionManager;

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
    View view;

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


}
