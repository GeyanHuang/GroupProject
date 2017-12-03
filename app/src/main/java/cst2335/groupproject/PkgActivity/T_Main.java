package cst2335.groupproject.PkgActivity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
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
     * Bottom navigation bar
     */
    private BottomNavigationView botNav;

    /**
     * Using M_SharedPreference
     */
    private M_SharedPreference sharedPreference = new M_SharedPreference();

    /**
     * The constructor
     */
    public T_Main() {
        // Required empty public constructor
    }

    /**
     * On create view
     *
     * @param inflater           The inflater
     * @param container          The container
     * @param savedInstanceState The savedInstanceState
     * @return The view of fragment
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.tracker_main, container, false);
        return view;
    }

    /**
     * Open activity list
     */
    private void openList() {
        T_Fragment_ActivityList fragment = new T_Fragment_ActivityList();
        android.support.v4.app.FragmentTransaction fragmentTransaction =
                getChildFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.tracker_main_container_fragment, fragment);
        fragmentTransaction.commit();
    }

    /**
     * Open activity dashboard
     */
    private void openDashboard() {
        T_Fragment_Dashboard fragment = new T_Fragment_Dashboard();
        android.support.v4.app.FragmentTransaction fragmentTransaction =
                getChildFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.tracker_main_container_fragment, fragment);
        fragmentTransaction.commit();
    }

    /**
     * On activity created
     *
     * @param savedInstanceState The savedInstanceState
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        botNav = view.findViewById(R.id.tracker_main_nav_bar);
        botNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.tracker_nav_bot_list) {
                    openList();
                }
                if (id == R.id.tracker_nav_bot_dashboard) {
                    openDashboard();
                }
                return true;
            }
        });

        // Open the record fragment
        String name = sharedPreference.getActivityLayout(view.getContext());

        if (name.equals("T_Fragment_ActivityList")) {
            botNav.getMenu().getItem(0).setChecked(true);
            openList();
        } else {
            botNav.getMenu().getItem(1).setChecked(true);
            openDashboard();
        }
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
