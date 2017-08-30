package com.ak.pesgm.fragment;


import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ak.pesgm.R;
import com.ak.pesgm.adapter.ImageAdapter;
import com.ak.pesgm.app.PreviewData;
import com.ak.pesgm.model.ImageData;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
//import io.realm.Realm;
//import io.realm.RealmList;

/**
 * A simple {@link Fragment} subclass.
 */
public class GalleryFragment extends Fragment implements PreviewData {

    View view;
    private List<ImageData> ImageList;
    public ImageAdapter mAdapter;
    @BindView(R.id.rvImageCollection)
    RecyclerView rvImageCollection;

    public static ArrayList<ImageData> imageData;

    public static PreviewData previewData;
    // Realm realm;

    public GalleryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_gallery, container, false);
        ButterKnife.bind(this, view);
        // realm = Realm.getDefaultInstance();

        imageData = new ArrayList<>();
        ImageList = new ArrayList<>();
        previewData = this;
        generateImageData();
        //ImageList.addAll(realm.where(ImageData.class).findAll());

        mAdapter = new ImageAdapter(getActivity(), ImageList, previewData);
        GridLayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 2);
        rvImageCollection.setLayoutManager(mLayoutManager);

        rvImageCollection.setAdapter(mAdapter);


        return view;
    }

    @Override
    public void previewInfo(int position) {
        imageData.clear();

        imageData.addAll(ImageList);


        Bundle bundle = new Bundle();
        // bundle.putSerializable("images", (Serializable) imageData);
        bundle.putInt("position", position);

        FragmentTransaction ft = getActivity().getFragmentManager().beginTransaction();
        SlideshowDialogFragment newFragment = SlideshowDialogFragment.newInstance();
        newFragment.setArguments(bundle);
        newFragment.show(ft, "slideshow");
    }

    int[] imgpath = {
            R.raw.fd1984,
            R.raw.fd1985,
            R.raw.fd1986,
            R.raw.fd1991,
            R.raw.fd1992,
            R.raw.fd1993,
            R.raw.fd1994,
            R.raw.fd1995,
            R.raw.fd1996,
            R.raw.fd1997,
            R.raw.fd1998,
            R.raw.fd1999,
            R.raw.fd2000,
            R.raw.fd2001,
            R.raw.fd2002,
            R.raw.fd2003,
            R.raw.fd2004,
            R.raw.fd2005,
            R.raw.fd2006,
            R.raw.fd2007,
            R.raw.fd2008,
            R.raw.fd2009,
            R.raw.fd2010,
            R.raw.fd2011,
            R.raw.fd2012,
            R.raw.fd2013,
            R.raw.fd2014,
            R.raw.fd2015,
            R.raw.fd2016,
            R.raw.fd2017
    };

    public void generateImageData() {
        String[] year = getActivity().getResources().getStringArray(R.array.year);
        String[] info = getActivity().getResources().getStringArray(R.array.info);


        for (int i = 0; i < year.length; i++) {
            ImageData img = new ImageData();
            img.setDate(year[i]);
            img.setInfo(info[i]);
            img.setPath(imgpath[i]);

            ImageList.add(img);
        }


    }

}
