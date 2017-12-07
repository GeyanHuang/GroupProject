package cst2335.groupproject.PkgActivity;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cst2335.groupproject.PkgMain.M_SharedPreference;
import cst2335.groupproject.R;


/**
 * This class is used for creating the main GUI of activity tracker which include fragment container and bottom navigation bar
 *
 * @author Geyan Huang
 */
public class T_Main extends Fragment {

    /**
     * The view of fragment
     */
    View view;

    /**
     * Using M_SharedPreference
     */
    private M_SharedPreference sharedPreference = new M_SharedPreference();

    public T_Main() {
        // Required empty public constructor
    }

    /**
     * The constructor
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.tracker_main, container, false);
        return view;
    }

    /**
     * Set action bar title when on resume
     */
    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.main_menu_activity);
    }
}
