package cst2335.groupproject.PkgAutomobile;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cst2335.groupproject.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class A_Main extends Fragment {


    public A_Main() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_automobile, container, false);

        SharedPreferences sharedPref = view.getContext().getSharedPreferences("Layout", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("Name", "A_Main");
        editor.apply();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.menu_automobile);
    }
}
