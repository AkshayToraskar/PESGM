package com.ak.pesgm.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.ak.pesgm.R;
import com.ak.pesgm.fragment.AboutFragment;
import com.ak.pesgm.fragment.GalleryFragment;
import com.ak.pesgm.fragment.HomeFragment;
import com.ak.pesgm.model.ImageData;

import butterknife.BindView;
import butterknife.ButterKnife;
//import io.realm.Realm;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.navigation)
    BottomNavigationView navigation;


   // HomeFragment home = new HomeFragment();
    GalleryFragment gallery = new GalleryFragment();
    AboutFragment about = new AboutFragment();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


      //  home = new HomeFragment();
        gallery = new GalleryFragment();
        about = new AboutFragment();

        // Select first menu item by default and show Fragment accordingly.
        Menu menu = navigation.getMenu();
        selectFragment(menu.getItem(0));

        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                selectFragment(item);

                return true;
            }
        });


    }


    protected void selectFragment(MenuItem item) {

        item.setChecked(true);

        switch (item.getItemId()) {

          /*  case R.id.navigation_home:
                pushFragment(home);
                break;*/
            case R.id.navigation_dashboard:
                pushFragment(gallery);
                break;
            case R.id.navigation_about:
                pushFragment(about);
                break;

        }
    }


    protected void pushFragment(Fragment fragment) {
        if (fragment == null)
            return;

        FragmentManager fragmentManager = getFragmentManager();
        if (fragmentManager != null) {
            FragmentTransaction ft = fragmentManager.beginTransaction();
            if (ft != null) {
                ft.replace(R.id.content, fragment);
                ft.commit();
            }
        }
    }


}
