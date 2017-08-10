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
import com.ak.pesgm.model.ImageData;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.navigation)
    BottomNavigationView navigation;


    HomeFragment home = new HomeFragment();
    GalleryFragment gallery = new GalleryFragment();
    AboutFragment about = new AboutFragment();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        home = new HomeFragment();
        gallery = new GalleryFragment();
        about = new AboutFragment();

        navigation.setSelectedItemId(R.id.navigation_home);

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
                    ft.replace(R.id.content, home);
                    ft.commit();
                    return true;
                case R.id.navigation_dashboard:
                    ft.replace(R.id.content, gallery);
                    ft.commit();
                    return true;
                case R.id.navigation_about:
                    ft.replace(R.id.content, about);
                    ft.commit();
                    return true;
            }


            return false;
        }

    };


}
