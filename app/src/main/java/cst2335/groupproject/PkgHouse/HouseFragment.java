package cst2335.groupproject.PkgHouse;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cst2335.groupproject.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class HouseFragment extends Fragment {


    public HouseFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_house, container, false);
    }

}