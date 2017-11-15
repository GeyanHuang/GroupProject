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

import cst2335.groupproject.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentNavBot extends Fragment {
    View view;
    private BottomNavigationView botNav;

    public FragmentNavBot() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_nav_bot, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        botNav = view.findViewById(R.id.activity_nav_bar);
        botNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.activity_nav_bot_list) {
                    ActivityFragment fragment = new ActivityFragment();
                    android.support.v4.app.FragmentTransaction fragmentTransaction =
                            getChildFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.activity_fragment_container, fragment);
                    fragmentTransaction.commit();
                }
                if (id == R.id.activity_nav_bot_dashboard) {
                    FragmentActivityDashboard fragment = new FragmentActivityDashboard();
                    android.support.v4.app.FragmentTransaction fragmentTransaction =
                            getChildFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.activity_fragment_container, fragment);
                    fragmentTransaction.commit();
                }
                return true;
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.menu_activity);
    }
}
