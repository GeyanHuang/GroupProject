package cst2335.groupproject.PkgAutomobile;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cst2335.groupproject.PkgMain.M_SharedPreference;
import cst2335.groupproject.R;


/**
 * This class is used for creating the main GUI of automobile
 *
 *
 *
 */
public class A_Main extends Fragment {

    /**
     * Using M_SharedPreference
     */
    private M_SharedPreference sharedPreference  = new M_SharedPreference();

    public A_Main() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.automobile_main, container, false);

        SharedPreferences sharedPreferences = view.getContext().getSharedPreferences("Layout", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Name", "A_Main");
        editor.apply();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.main_menu_automobile);
    }
}
