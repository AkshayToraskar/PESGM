package com.ak.pesgm.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.ak.pesgm.R;
import com.ak.pesgm.fragment.AboutFragment;
import com.ak.pesgm.fragment.GalleryFragment;
import com.ak.pesgm.fragment.HomeFragment;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //  mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }


   // private TextView mTextMessage;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

            switch (item.getItemId()) {
                case R.id.navigation_home:
                 //   mTextMessage.setText(R.string.title_home);

                    ft.replace(R.id.content, new HomeFragment());
                    ft.commit();
                    return true;
                case R.id.navigation_dashboard:
                  //  mTextMessage.setText(R.string.title_dashboard);
                    ft.replace(R.id.content, new GalleryFragment());
                    ft.commit();
                    return true;
                case R.id.navigation_about:
                 //   mTextMessage.setText(R.string.title_notifications);

                    ft.replace(R.id.content, new AboutFragment());
                    ft.commit();
                    return true;
            }



            return false;
        }

    };



}
