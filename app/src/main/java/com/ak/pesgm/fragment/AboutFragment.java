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
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.IdRes;
import android.support.v7.widget.CardView;
import android.text.Html;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.ak.pesgm.R;
import com.ak.pesgm.activity.MainActivity;
import com.ak.pesgm.activity.PdfActivity;
import com.ak.pesgm.app.SessionManager;
//import com.jackpocket.scratchoff.ScratchoffController;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

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
    /* @BindView(R.id.cv_create_frame)
     CardView cvFrame;*/
    /*@BindView(R.id.ivPreview)
    ImageView imageView;*/
    /*@BindView(R.id.tvTips)
    TextView tvTips;*/

    public static int count = 0;
    String[] tips;

    View view;

    String fburl = "https://www.facebook.com/PESGMandal1/";
    String instaurl = "https://www.instagram.com/pesgm/";

/*    ScratchoffController controller;*/
    static final int CAM_REQUEST = 1;

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


        tips = getResources().getStringArray(R.array.tips_tricks);

      //  setTips();
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

       /* cvFrame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent camera_intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                File file = getFile();
                camera_intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
                startActivityForResult(camera_intent,CAM_REQUEST);
            }
        });*/

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


       /* controller = new ScratchoffController(getActivity())
                .setThresholdPercent(0.50d)
                .setTouchRadiusDip(getActivity(), 20)
                .setFadeOnClear(true)
                .setClearOnThresholdReached(true)
                .attach(view.findViewById(R.id.scratch_view), view.findViewById(R.id.scratch_view_behind));
*/
        //controller.setThresholdPercent(0.40d);
        //controller.setFadeOnClear(true);

        wvAddress.getSettings().setJavaScriptEnabled(true);
        wvAddress.loadData("<iframe src=\"https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d943.3104563228565!2d72.81203811468214!3d18.964924621029503!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x0%3A0x632786d9ba511713!2sSachinam+Heights!5e0!3m2!1sen!2sin!4v1503522801813\" width=\"400\" height=\"300\" frameborder=\"0\" style=\"border:0\" allowfullscreen></iframe>", "text/html", "utf-8");
        WebSettings webViewSettings = wvAddress.getSettings();
        webViewSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webViewSettings.setJavaScriptEnabled(true);
        webViewSettings.setBuiltInZoomControls(false);
        webViewSettings.setPluginState(WebSettings.PluginState.ON);

        return view;
    }

  /*  Date curentTime;
    private File getFile()
    {
        curentTime = new Date();
        String root = Environment.getExternalStorageDirectory().toString();
        File folder = new File(root + "/pesgm");
        if (!folder.mkdirs()) {
            Log.e("asdf", "Directory not created");
        }

        File image_file = new File(folder,"selfie_cam"+curentTime+".jpg");
        return image_file;
    }*/


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


   /* @Override
    public void onActivityResult(int requestCode, int resultCode, Intent     data) {
        setAndSaveImageWithOverlay(getBitmapOfSnappedImage());
    }*/



  /*  public Bitmap getBitmapOfSnappedImage(){


        String root = Environment.getExternalStorageDirectory().toString();
        String path = root + "/pesgm"+"/selfie_cam"+curentTime+".jpg";

        File image = new File(path);
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        Bitmap bitmap =     BitmapFactory.decodeFile(image.getAbsolutePath(),bmOptions);
        return bitmap;
    }

    public void setAndSaveImageWithOverlay(Bitmap snappedImage){
        Bitmap b = Bitmap.createBitmap(snappedImage.getWidth(),     snappedImage.getHeight(), Bitmap.Config.ARGB_8888);

        //the overlay png file from drawable folder
        Bitmap overlay = BitmapFactory.decodeResource(getResources(),     R.drawable.cup);
        overlay =     Bitmap.createScaledBitmap(overlay,snappedImage.getWidth(),snappedImage.getHeight    (),false);

        //create canvas with a clean bitmap
        Canvas canvas = new Canvas(b);
        //draw the snappedImage on the canvas
        canvas.drawBitmap(snappedImage, 0, 0, new Paint());
        //draw the overlay on the canvas
        canvas.drawBitmap(overlay, 0, 0, new Paint());

        imageView.setImageBitmap(b);

        SaveImage(b);
    }


    private void SaveImage(Bitmap finalBitmap) {

        String root = Environment.getExternalStorageDirectory().toString();
        File myDir = new File(root + "/Blue_&_Gold_Perade");
        myDir.mkdirs();
        String fname = "selfie_cam"+curentTime+".jpg";
        File file = new File (myDir, fname);
        if (file.exists ()) file.delete ();
        try {
            FileOutputStream out = new FileOutputStream(file);
            finalBitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.flush();
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/

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


   /* public void setTips() {
        long lastLoginTime = sessionManager.getLastTipsTime();
        Date dt = new Date(lastLoginTime);
        long diff = getDateDiff(dt, new Date(), TimeUnit.HOURS);

        if (diff >= 8) {

            if (count >= tips.length) {
                count = 0;
            } else {
                count = sessionManager.getTipsCount() + 1;
            }
            sessionManager.setTipsTimeAndCount(count);

            tvTips.setText(tips[sessionManager.getTipsCount()]);

        } else {
            tvTips.setText(tips[sessionManager.getTipsCount()]);
        }
    }

    public long getDateDiff(Date date1, Date date2, TimeUnit timeUnit) {
        long diffInMillies = date2.getTime() - date1.getTime();
        Log.v("Date Difference", "is : " + timeUnit.convert(diffInMillies, TimeUnit.MILLISECONDS));
        return timeUnit.convert(diffInMillies, TimeUnit.MILLISECONDS);
    }

    @Override
    public void onPause() {
        controller.onPause();
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        controller.onResume();
    }

    @Override
    public void onDestroy() {
        controller.onDestroy();
        super.onDestroy();
    }*/

}
