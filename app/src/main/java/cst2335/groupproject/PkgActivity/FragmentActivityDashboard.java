package cst2335.groupproject.PkgActivity;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import cst2335.groupproject.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentActivityDashboard extends Fragment implements View.OnClickListener {

    private View view;
    private LinearLayout setDailyGoal;
    private TextView dailyGoal;

    public FragmentActivityDashboard() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_activity_dashboard, container, false);
        setDailyGoal = view.findViewById(R.id.activity_dashboard_setdailygoal);
        setDailyGoal.setOnClickListener(this);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        dailyGoal = view.findViewById(R.id.activity_dashboard_dailygoal);

    }


    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.activity_nav_bot_dashboard);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_dashboard_setdailygoal:

                break;
        }
    }
}

