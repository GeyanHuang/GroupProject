package cst2335.groupproject.PkgActivity;


import android.app.AlertDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import cst2335.groupproject.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentActivityDashboard extends Fragment implements View.OnClickListener {

    private View view;
    private LinearLayout setDailyGoal;
    private TextView dailyGoal1, dailyGoal2;
    private EditText editText_dailyGoal;
    private AlertDialog dailyGoalDialog;
    private ImageView dailyGoalcheck;

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
        dailyGoal1 = view.findViewById(R.id.activity_dashboard_dailygoal);
        dailyGoal2 = view.findViewById(R.id.activity_dashboard_textview_dailygoal);

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
                AlertDialog.Builder commentBuilder = new AlertDialog.Builder(view.getContext());
                View commentView = getLayoutInflater().inflate(R.layout.activity_dashboard_dailygoal, null);
                editText_dailyGoal = commentView.findViewById(R.id.activity_dashboard_edittext_dailygoal);
                editText_dailyGoal.setText(dailyGoal1.getText());

                commentBuilder.setView(commentView);
                dailyGoalDialog = commentBuilder.create();
                dailyGoalDialog.setCanceledOnTouchOutside(false);
                dailyGoalDialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
                dailyGoalDialog.show();
                editText_dailyGoal.requestFocus();
                break;
        }
    }

}

