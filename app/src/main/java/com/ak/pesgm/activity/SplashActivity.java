package com.ak.pesgm.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.Window;
import android.view.WindowManager;

import com.ak.pesgm.R;
import com.ak.pesgm.app.SessionManager;
import com.ak.pesgm.model.ImageData;

import java.util.Locale;

import butterknife.ButterKnife;
import io.realm.Realm;

public class SplashActivity extends AppCompatActivity {

//    @BindView(R.id.ivGifImg)
//    ImageView ivGifImg;

    Locale myLocale;
    public static int SPLASH_TIME_OUT = 1000;
    private SessionManager sessionManager;
    Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        realm = Realm.getDefaultInstance();
        setContentView(R.layout.activity_splash);

        ButterKnife.bind(this);
        sessionManager = new SessionManager(getApplicationContext());

        if (!sessionManager.isDataGenerated()) {
            createDummyImageData();
        }

        if (sessionManager.getLanguage().equals("mr")) {
            setLocale("mr");
        } else if (sessionManager.getLanguage().equals("en")) {
            setLocale("en");
        }

       /* Glide.with(this)
                .load(R.raw.survey_collection)
                .asGif()
               // .placeholder(R.drawable.loading2)
             //   .crossFade()
                .into(ivGifImg);*/

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                //permissionCheck();


                Intent i = new Intent(SplashActivity.this, MainActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);

            }
        }, SPLASH_TIME_OUT);
    }

    public void setLocale(String lang) {

        myLocale = new Locale(lang);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);
        //Intent refresh = new Intent(this, SplashActivity.class);
        //startActivity(refresh);
    }

    public void createDummyImageData() {

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {

                for (int i = 0; i < 10; i++) {

                    ImageData imgData = realm.createObject(ImageData.class, i);
                    //imgData.setId((long) i);
                    imgData.setDate("201"+i);
                    imgData.setPath(R.raw.team);
                    imgData.setInfo("asdfsadff");

                    realm.copyToRealmOrUpdate(imgData);
                }

                sessionManager.setGenerateData(true);
            }
        });
    }
}
