package com.ak.pesgm.activity;

import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.ak.pesgm.R;
import com.joanzapata.pdfview.PDFView;
import com.joanzapata.pdfview.listener.OnLoadCompleteListener;
import com.joanzapata.pdfview.listener.OnPageChangeListener;

import butterknife.BindView;
import butterknife.ButterKnife;



public class PdfActivity extends AppCompatActivity implements OnPageChangeListener, OnLoadCompleteListener {

   // @BindView(R.id.wv_pdf)
   // WebView webView;

    @BindView(R.id.pdfview)
    PDFView pdfView;

    private static final String TAG = PdfActivity.class.getSimpleName();
    public static final String SAMPLE_FILE = "aarti.pdf";
    Integer pageNumber = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf);
        ButterKnife.bind(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

     //   WebSettings settings = webView.getSettings();
      //  settings.setJavaScriptEnabled(true);

        //The default value is true for API level android.os.Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1 and below,
        //and false for API level android.os.Build.VERSION_CODES.JELLY_BEAN and above.
    /*    if(android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN)
            settings.setAllowUniversalAccessFromFileURLs(true);

        settings.setBuiltInZoomControls(true);
        webView.setWebChromeClient(new WebChromeClient());
        webView.loadUrl("file:///android_asset/pdfviewer/index.html");*/


        pdfView.fromAsset(SAMPLE_FILE)
                //.pages(0, 2, 1, 3, 3, 3)
                .defaultPage(0)
                .showMinimap(false)
                .enableSwipe(true)
               // .onDraw(onDrawListener)
                .onLoad(this)
                .onPageChange(this)
                .load();

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
      //  webView.loadUrl( "javascript:window.location.reload( true )" );

    }

    //	clear cache to ensure we have good reload
    @Override
    protected void onPause() {
        super.onPause();
     //   webView.clearCache(true);

    }


    @Override
    public void loadComplete(int nbPages) {
      /*  PdfDocument.Meta meta = pdfView.getDocumentMeta();
        Log.e(TAG, "title = " + meta.getTitle());
        Log.e(TAG, "author = " + meta.getAuthor());
        Log.e(TAG, "subject = " + meta.getSubject());
        Log.e(TAG, "keywords = " + meta.getKeywords());
        Log.e(TAG, "creator = " + meta.getCreator());
        Log.e(TAG, "producer = " + meta.getProducer());
        Log.e(TAG, "creationDate = " + meta.getCreationDate());
        Log.e(TAG, "modDate = " + meta.getModDate());

        printBookmarksTree(pdfView.getTableOfContents(), "-");*/

    }

    @Override
    public void onPageChanged(int page, int pageCount) {
        pageNumber = page;
        setTitle(String.format("%s %s / %s", SAMPLE_FILE, page, pageCount));
    }
}
