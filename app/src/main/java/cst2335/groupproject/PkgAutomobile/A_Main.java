package cst2335.groupproject.PkgAutomobile;

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
 */
public class A_Main extends Fragment {

    /**
     * Using M_SharedPreference
     */
    private M_SharedPreference sharedPreference = new M_SharedPreference();

    /**
     * The construction
     */
    public A_Main() {
        // Required empty public constructor
    }

    /**
     * On create view
     *
     * @param inflater           The inflater
     * @param container          The container
     * @param savedInstanceState The savedInstanceState
     * @return The view
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.automobile_main, container, false);

        // Set current layout
        sharedPreference.setLayout(view.getContext(), "A_Main");

        return view;
    }

    /**
     * On resume
     */
    @Override
    public void onResume() {
        super.onResume();

        // Set title for action bar
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.main_menu_automobile);
    }
}
