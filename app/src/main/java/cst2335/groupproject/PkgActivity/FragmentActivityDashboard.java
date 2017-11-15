package cst2335.groupproject.PkgActivity;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
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
    private TextView textView_dailyGoal1, textView_dailyGoal2;
    private EditText editText_setDailyGoal;
    private Dialog dialog_setDailyGoal;
    private ImageView setDailyGoalCheck;

    private ActivityDatabaseHelper databaseHelper;

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
        textView_dailyGoal1 = view.findViewById(R.id.activity_dashboard_dailygoal);
        textView_dailyGoal2 = view.findViewById(R.id.activity_dashboard_textview_dailygoal);

        SharedPreferences sharedPref = view.getContext().getSharedPreferences("User info", Context.MODE_PRIVATE);

        String dailyGoal = sharedPref.getString("DailyGoal", "0");
        textView_dailyGoal1.setText(dailyGoal);
        textView_dailyGoal2.setText(dailyGoal);

        databaseHelper = new ActivityDatabaseHelper(view.getContext());
        databaseHelper.openDatabase();
    }


    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.activity_nav_bot_dashboard);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        databaseHelper.closeDatabase();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_dashboard_setdailygoal:
                AlertDialog.Builder dailyGoalBuilder = new AlertDialog.Builder(view.getContext());
                View dailyGoalView = getLayoutInflater().inflate(R.layout.activity_dashboard_dailygoal, null);
                setDailyGoalCheck = dailyGoalView.findViewById(R.id.activity_dashboard_dailygoal_check);
                setDailyGoalCheck.setOnClickListener(this);
                editText_setDailyGoal = dailyGoalView.findViewById(R.id.activity_dashboard_edittext_dailygoal);
                editText_setDailyGoal.setText(textView_dailyGoal1.getText());

                dailyGoalBuilder.setView(dailyGoalView);
                dialog_setDailyGoal = dailyGoalBuilder.create();
                dialog_setDailyGoal.setCanceledOnTouchOutside(false);
                dialog_setDailyGoal.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
                dialog_setDailyGoal.show();
                editText_setDailyGoal.requestFocus();
                break;
            case R.id.activity_dashboard_dailygoal_check:
                textView_dailyGoal1.setText(editText_setDailyGoal.getText());
                textView_dailyGoal2.setText(editText_setDailyGoal.getText());

                SharedPreferences sharedPref = view.getContext().getSharedPreferences("User info", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("DailyGoal", editText_setDailyGoal.getText().toString());
                editor.apply();

                dialog_setDailyGoal.dismiss();
                break;
        }
    }

}

