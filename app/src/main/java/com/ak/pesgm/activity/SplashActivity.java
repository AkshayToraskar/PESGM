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
    public static int SPLASH_TIME_OUT = 2000;
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


                // for (int i = 0; i < 10; i++) {

                ImageData imgData1 = realm.createObject(ImageData.class, 1);
                //imgData.setId((long) i);
                imgData1.setDate("1984");
                imgData1.setPath(R.raw.fd1984);
                imgData1.setInfo("asdfsadff");
                realm.copyToRealmOrUpdate(imgData1);
                // }


                ImageData imgData2 = realm.createObject(ImageData.class, 2);
                //imgData.setId((long) i);
                imgData2.setDate("1985");
                imgData2.setPath(R.raw.fd1985);
                imgData2.setInfo("asdfsadff");
                realm.copyToRealmOrUpdate(imgData2);


                ImageData imgData3 = realm.createObject(ImageData.class, 3);
                //imgData.setId((long) i);
                imgData3.setDate("1986");
                imgData3.setPath(R.raw.fd1986);
                imgData3.setInfo("asdfsadff");
                realm.copyToRealmOrUpdate(imgData3);


                ImageData imgData4 = realm.createObject(ImageData.class, 4);
                //imgData.setId((long) i);
                imgData4.setDate("1991");
                imgData4.setPath(R.raw.fd1991);
                imgData4.setInfo("asdfsadff");
                realm.copyToRealmOrUpdate(imgData4);


                ImageData imgData5 = realm.createObject(ImageData.class, 5);
                //imgData.setId((long) i);
                imgData5.setDate("1992");
                imgData5.setPath(R.raw.fd1992);
                imgData5.setInfo("asdfsadff");
                realm.copyToRealmOrUpdate(imgData5);


                ImageData imgData6 = realm.createObject(ImageData.class, 6);
                //imgData.setId((long) i);
                imgData6.setDate("1993");
                imgData6.setPath(R.raw.fd1993);
                imgData6.setInfo("asdfsadff");
                realm.copyToRealmOrUpdate(imgData6);


                ImageData imgData7 = realm.createObject(ImageData.class, 7);
                //imgData.setId((long) i);
                imgData7.setDate("1994");
                imgData7.setPath(R.raw.fd1994);
                imgData7.setInfo("asdfsadff");
                realm.copyToRealmOrUpdate(imgData7);


                ImageData imgData8 = realm.createObject(ImageData.class, 8);
                //imgData.setId((long) i);
                imgData8.setDate("1995");
                imgData8.setPath(R.raw.fd1995);
                imgData8.setInfo("asdfsadff");
                realm.copyToRealmOrUpdate(imgData8);


                ImageData imgData9 = realm.createObject(ImageData.class, 9);
                //imgData.setId((long) i);
                imgData9.setDate("1996");
                imgData9.setPath(R.raw.fd1996);
                imgData9.setInfo("asdfsadff");
                realm.copyToRealmOrUpdate(imgData9);


                ImageData imgData10 = realm.createObject(ImageData.class, 10);
                //imgData.setId((long) i);
                imgData10.setDate("1997");
                imgData10.setPath(R.raw.fd1997);
                imgData10.setInfo("asdfsadff");
                realm.copyToRealmOrUpdate(imgData10);


                ImageData imgData11 = realm.createObject(ImageData.class, 11);
                //imgData.setId((long) i);
                imgData11.setDate("1998");
                imgData11.setPath(R.raw.fd1998);
                imgData11.setInfo("asdfsadff");
                realm.copyToRealmOrUpdate(imgData11);


                ImageData imgData12 = realm.createObject(ImageData.class, 12);
                //imgData.setId((long) i);
                imgData12.setDate("1999");
                imgData12.setPath(R.raw.fd1999);
                imgData12.setInfo("asdfsadff");
                realm.copyToRealmOrUpdate(imgData12);


                ImageData imgData13 = realm.createObject(ImageData.class, 13);
                //imgData.setId((long) i);
                imgData13.setDate("2000");
                imgData13.setPath(R.raw.fd2000);
                imgData13.setInfo("asdfsadff");
                realm.copyToRealmOrUpdate(imgData13);


                ImageData imgData14 = realm.createObject(ImageData.class, 14);
                //imgData.setId((long) i);
                imgData14.setDate("2001");
                imgData14.setPath(R.raw.fd2001);
                imgData14.setInfo("asdfsadff");
                realm.copyToRealmOrUpdate(imgData14);

                // for (int i = 0; i < 10; i++) {

                ImageData imgData15 = realm.createObject(ImageData.class, 15);
                //imgData.setId((long) i);
                imgData15.setDate("2002");
                imgData15.setPath(R.raw.fd2002);
                imgData15.setInfo("asdfsadff");
                realm.copyToRealmOrUpdate(imgData15);
                // }


                ImageData imgData16 = realm.createObject(ImageData.class, 16);
                //imgData.setId((long) i);
                imgData16.setDate("2003");
                imgData16.setPath(R.raw.fd2003);
                imgData16.setInfo("asdfsadff");
                realm.copyToRealmOrUpdate(imgData16);


                ImageData imgData17 = realm.createObject(ImageData.class, 17);
                //imgData.setId((long) i);
                imgData17.setDate("2004");
                imgData17.setPath(R.raw.fd2004);
                imgData17.setInfo("asdfsadff");
                realm.copyToRealmOrUpdate(imgData17);


                ImageData imgData18 = realm.createObject(ImageData.class, 18);
                //imgData.setId((long) i);
                imgData18.setDate("2005");
                imgData18.setPath(R.raw.fd2005);
                imgData18.setInfo("asdfsadff");
                realm.copyToRealmOrUpdate(imgData18);


                ImageData imgData19 = realm.createObject(ImageData.class, 19);
                //imgData.setId((long) i);
                imgData19.setDate("2006");
                imgData19.setPath(R.raw.fd2006);
                imgData19.setInfo("asdfsadff");
                realm.copyToRealmOrUpdate(imgData19);


                ImageData imgData20 = realm.createObject(ImageData.class, 20);
                //imgData.setId((long) i);
                imgData20.setDate("2007");
                imgData20.setPath(R.raw.fd2007);
                imgData20.setInfo("asdfsadff");
                realm.copyToRealmOrUpdate(imgData20);


                ImageData imgData21 = realm.createObject(ImageData.class, 21);
                //imgData.setId((long) i);
                imgData21.setDate("2008");
                imgData21.setPath(R.raw.fd2008);
                imgData21.setInfo("asdfsadff");
                realm.copyToRealmOrUpdate(imgData21);


                ImageData imgData22 = realm.createObject(ImageData.class, 22);
                //imgData.setId((long) i);
                imgData22.setDate("2009");
                imgData22.setPath(R.raw.fd2009);
                imgData22.setInfo("asdfsadff");
                realm.copyToRealmOrUpdate(imgData22);


                ImageData imgData23 = realm.createObject(ImageData.class, 23);
                //imgData.setId((long) i);
                imgData23.setDate("2010");
                imgData23.setPath(R.raw.fd2010);
                imgData23.setInfo("asdfsadff");
                realm.copyToRealmOrUpdate(imgData23);


                ImageData imgData24 = realm.createObject(ImageData.class, 24);
                //imgData.setId((long) i);
                imgData24.setDate("2011");
                imgData24.setPath(R.raw.fd2011);
                imgData24.setInfo("asdfsadff");
                realm.copyToRealmOrUpdate(imgData24);


                ImageData imgData25 = realm.createObject(ImageData.class, 25);
                //imgData.setId((long) i);
                imgData25.setDate("2012");
                imgData25.setPath(R.raw.fd2012);
                imgData25.setInfo("asdfsadff");
                realm.copyToRealmOrUpdate(imgData25);


                ImageData imgData26 = realm.createObject(ImageData.class, 26);
                //imgData.setId((long) i);
                imgData26.setDate("2013");
                imgData26.setPath(R.raw.fd2013);
                imgData26.setInfo("asdfsadff");
                realm.copyToRealmOrUpdate(imgData26);


                ImageData imgData27 = realm.createObject(ImageData.class, 27);
                //imgData.setId((long) i);
                imgData27.setDate("2014");
                imgData27.setPath(R.raw.fd2014);
                imgData27.setInfo("asdfsadff");
                realm.copyToRealmOrUpdate(imgData27);


                sessionManager.setGenerateData(true);
            }
        });
    }
}
