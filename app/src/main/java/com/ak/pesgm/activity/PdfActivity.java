package com.ak.pesgm.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.ak.pesgm.R;

import butterknife.BindView;
import butterknife.ButterKnife;



public class PdfActivity extends AppCompatActivity {

    @BindView(R.id.wv_pdf)
    WebView webView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf);
        ButterKnife.bind(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);

        //The default value is true for API level android.os.Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1 and below,
        //and false for API level android.os.Build.VERSION_CODES.JELLY_BEAN and above.
        if(android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN)
            settings.setAllowUniversalAccessFromFileURLs(true);

        settings.setBuiltInZoomControls(true);
        webView.setWebChromeClient(new WebChromeClient());
        webView.loadUrl("file:///android_asset/pdfviewer/index.html");


    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }



    //	reload on resume
    @Override
    protected void onResume() {
        super.onResume();
        webView.loadUrl( "javascript:window.location.reload( true )" );

    }

    //	clear cache to ensure we have good reload
    @Override
    protected void onPause() {
        super.onPause();
        webView.clearCache(true);

    }


}
