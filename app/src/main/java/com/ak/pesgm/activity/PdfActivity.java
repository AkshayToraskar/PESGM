package com.ak.pesgm.activity;


import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

import com.ak.pesgm.R;
import com.joanzapata.pdfview.PDFView;
import com.joanzapata.pdfview.listener.OnLoadCompleteListener;
import com.joanzapata.pdfview.listener.OnPageChangeListener;

import butterknife.BindView;
import butterknife.ButterKnife;



public class PdfActivity extends AppCompatActivity implements OnPageChangeListener, OnLoadCompleteListener {

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


        pdfView.fromAsset(SAMPLE_FILE)
                .defaultPage(0)
                .showMinimap(false)
                .enableSwipe(true)
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
    }

    //	clear cache to ensure we have good reload
    @Override
    protected void onPause() {
        super.onPause();
    }


    @Override
    public void loadComplete(int nbPages) {
    }

    @Override
    public void onPageChanged(int page, int pageCount) {
        pageNumber = page;
        setTitle(String.format("%s %s / %s", SAMPLE_FILE, page, pageCount));
    }
}
