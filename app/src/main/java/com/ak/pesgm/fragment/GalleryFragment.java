package com.ak.pesgm.fragment;


import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.RealmList;

/**
 * A simple {@link Fragment} subclass.
 */
public class GalleryFragment extends Fragment implements PreviewData {

    View view;
    private RealmList<ImageData> ImageList;
    public ImageAdapter mAdapter;
    @BindView(R.id.rvImageCollection)
    RecyclerView rvImageCollection;

    public static ArrayList<ImageData> imageData;

    public static PreviewData previewData;

    public GalleryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_gallery, container, false);
        ButterKnife.bind(this, view);

        imageData = new ArrayList<>();
        ImageList = new RealmList<>();
        previewData = this;

        mAdapter = new ImageAdapter(getActivity(), ImageList, previewData);
        GridLayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 2);
        rvImageCollection.setLayoutManager(mLayoutManager);
        rvImageCollection.setItemAnimator(new DefaultItemAnimator());
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

    public void createDummyImageData() {
        for (int i = 0; i < 10; i++) {

            ImageData imgData=new ImageData();
            imgData.setId((long)i);
            imgData.setDate("1-2-3");
            imgData.setPath("");
            imgData.setInfo("");

            imageData.add(imgData);
        }
    }
}
