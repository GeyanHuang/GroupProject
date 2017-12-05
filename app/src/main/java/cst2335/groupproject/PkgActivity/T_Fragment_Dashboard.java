package cst2335.groupproject.PkgActivity;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Calendar;

import cst2335.groupproject.PkgMain.M_SharedPreference;
import cst2335.groupproject.R;

/**
 * This class is used for creating the dashboard GUI of activity tracker
 *
 * @author Geyan Huang
 */
public class T_Fragment_Dashboard extends Fragment implements View.OnClickListener {

    /**
     * Using M_SharedPreference
     */
    private M_SharedPreference sharedPreference = new M_SharedPreference();

    /**
     * The view of the fragment
     */
    private View view;

    /**
     * LinearLayout for set daily goal
     */
    private LinearLayout layout_setDailyGoal;

    /**
     * The textViews
     */
    private TextView textView_setDailyGoal, textView_dailyGoal, textView_todayTime, textView_thisMonth, textView_lastMonth;

    /**
     * The editText
     */
    private EditText editText_setDailyGoal;

    /**
     * The dialog
     */
    private Dialog dialog_setDailyGoal;

    /**
     * The imageView
     */
    private ImageView setDailyGoalCheck;

    /**
     * The progressBar
     */
    private ProgressBar progressBar;

    /**
     * The Strings
     */
    private String year, month, day;

    /**
     * The databaseHelper
     */
    private T_DatabaseHelper databaseHelper;

    /**
     * The constructor
     */
    public T_Fragment_Dashboard() {
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
        view = inflater.inflate(R.layout.tracker_dashboard_fragment, container, false);

        layout_setDailyGoal = view.findViewById(R.id.tracker_dashboard_fragment_setDailyGoal);
        layout_setDailyGoal.setOnClickListener(this);

        sharedPreference.setLayout(view.getContext(), "T_Fragment_Dashboard");
        sharedPreference.setActivityLayout(view.getContext(), "T_Fragment_Dashboard");

        return view;
    }

    /**
     * On activity created
     *
     * @param savedInstanceState The savedInstanceState
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        textView_setDailyGoal = view.findViewById(R.id.tracker_dashboard_fragment_textView_setDailyGoal);
        textView_dailyGoal = view.findViewById(R.id.tracker_dashboard_fragment_textView_dailyGoal);
        textView_todayTime = view.findViewById(R.id.tracker_dashboard_fragment_textView_todayExerciseTime);
        textView_thisMonth = view.findViewById(R.id.tracker_dashboard_fragment_thisMonthTime);
        textView_lastMonth = view.findViewById(R.id.tracker_dashboard_fragment_lastMonthTime);
        progressBar = view.findViewById(R.id.tracker_dashboard_fragment_progressbar);

        SharedPreferences sharedPreferences = view.getContext().getSharedPreferences("User info", Context.MODE_PRIVATE);

        String dailyGoal = sharedPreferences.getString("DailyGoal", "0");
        textView_setDailyGoal.setText(dailyGoal);
        textView_dailyGoal.setText(dailyGoal);

        // Open database
        databaseHelper = new T_DatabaseHelper(view.getContext());
        databaseHelper.openDatabase();

        textView_todayTime.setText(getTodayExerciseTime() + "");
        textView_thisMonth.setText(getThisMonthExerciseTime() + "");
        textView_lastMonth.setText(getLastMonthExerciseTime() + "");

        setProgressBar();
    }

    /**
     * Function used to set today date
     */
    private void setTodayDate() {
        final Calendar cal = Calendar.getInstance();
        int x_year = cal.get(Calendar.YEAR);
        int x_month = cal.get(Calendar.MONTH);
        int x_day = cal.get(Calendar.DAY_OF_MONTH);
        year = Integer.toString(x_year);
        month = Integer.toString((x_month + 1));
        day = Integer.toString(x_day);
        if (x_month < 9) {

            month = "0" + (x_month + 1);
        }
        if (x_day < 10) {

            day = "0" + x_day;
        }
    }

    /**
     * Function to get today exercise time
     *
     * @return Today exercise time
     */
    private int getTodayExerciseTime() {
        setTodayDate();
        String date = (year + "-" + month + "-" + day);

        Cursor cur = databaseHelper.database.rawQuery("SELECT SUM(Minute) FROM Activity WHERE Date = '" + date + "'", null);
        if (cur.moveToFirst()) {
            return cur.getInt(0);
        }
        return 0;
    }

    /**
     * Function used to get this month exercise time
     *
     * @return This month exercise time
     */
    private int getThisMonthExerciseTime() {
        setTodayDate();
        String date = (year + "-" + month);

        Cursor cur = databaseHelper.getWritableDatabase().rawQuery("SELECT SUM(Minute) FROM Activity WHERE Date LIKE '%" + date + "%'", null);
        if (cur.moveToFirst()) {
            return cur.getInt(0);
        }
        return 0;
    }

    /**
     * Function used to get last month exercise time
     *
     * @return Last month exercise time
     */
    private int getLastMonthExerciseTime() {
        setTodayDate();
        String date;
        if (!month.equals("01")) {
            int lastMonth = Integer.parseInt(month) - 1;
            if (lastMonth < 10) {
                month = "0" + lastMonth;
                date = (year + "-" + month);
            } else {
                date = (year + "-" + lastMonth);
            }
        } else {
            date = ((Integer.parseInt(year) - 1) + "-" + "12");
        }


        Cursor cur = databaseHelper.getWritableDatabase().rawQuery("SELECT SUM(Minute) FROM Activity WHERE Date LIKE '%" + date + "%'", null);
        if (cur.moveToFirst()) {
            return cur.getInt(0);
        }
        return 0;
    }

    /**
     * Function used to set progress bar
     */
    private void setProgressBar() {
        int progress = (int) ((Double.parseDouble(textView_todayTime.getText().toString()) / Double.parseDouble(textView_dailyGoal.getText().toString())) * 100);
        progressBar.setProgress(progress);
    }

    /**
     * Set action bar title when on resume
     */
    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.tracker_nav_bot_dashboard);
    }

    /**
     * Close database when destroy view
     */
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        databaseHelper.closeDatabase();
    }

    /**
     * On click function
     *
     * @param v The view of fragment
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tracker_dashboard_fragment_setDailyGoal:
                AlertDialog.Builder dailyGoalBuilder = new AlertDialog.Builder(view.getContext());
                View dailyGoalView = getLayoutInflater().inflate(R.layout.tracker_dashboard_dialog_dailygoal, null);
                setDailyGoalCheck = dailyGoalView.findViewById(R.id.tracker_dashboard_dialog_dailyGoal_check);
                setDailyGoalCheck.setOnClickListener(this);
                editText_setDailyGoal = dailyGoalView.findViewById(R.id.tracker_dashboard_dialog_dailyGoal_editText);
                editText_setDailyGoal.setText(textView_setDailyGoal.getText());

                dailyGoalBuilder.setView(dailyGoalView);
                dialog_setDailyGoal = dailyGoalBuilder.create();
                dialog_setDailyGoal.setCanceledOnTouchOutside(false);
                dialog_setDailyGoal.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
                dialog_setDailyGoal.show();
                editText_setDailyGoal.requestFocus();
                break;
            case R.id.tracker_dashboard_dialog_dailyGoal_check:
                textView_setDailyGoal.setText(editText_setDailyGoal.getText());
                textView_dailyGoal.setText(editText_setDailyGoal.getText());

                SharedPreferences sharedPreferences = view.getContext().getSharedPreferences("User info", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("DailyGoal", editText_setDailyGoal.getText().toString());
                editor.apply();

                setProgressBar();
                dialog_setDailyGoal.dismiss();
                break;
        }
    }
}

