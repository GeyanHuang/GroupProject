package cst2335.groupproject.PkgActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

import cst2335.groupproject.R;

/**
 * This class is used for creating the insert GUI of activity tracker
 *
 * @author Geyan Huang
 */
public class T_Insert extends Activity {

    /**
     * TextViews
     */
    TextView textView_date, textView_time, textView_comment;

    /**
     * Spinner
     */
    Spinner spinner_type;

    /**
     * AlertDialog for comment
     */
    AlertDialog commentDialog;

    /**
     * Year, month, day, hour, minute
     */
    int x_year, x_month, x_day, x_hour, x_minute;

    /**
     * Dialog ID for date picker
     */
    static final int DIALOG_ID_DATE = 1;

    /**
     * Dialog ID for time picker
     */
    static final int DIALOG_ID_TIME = 2;

    /**
     * EditTexts
     */
    EditText editText_minute, editText_comment;

    /**
     * On create
     *
     * @param savedInstanceState The savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tracker_insert);

        editText_minute = findViewById(R.id.tracker_insert_editText_minute);
        spinner_type = findViewById(R.id.tracker_insert_spinner_type);
        textView_date = findViewById(R.id.tracker_insert_textView_date);
        textView_time = findViewById(R.id.tracker_insert_textView_time);
        textView_comment = findViewById(R.id.tracker_insert_textView_comment);

        // Set current date
        final Calendar cal = Calendar.getInstance();
        x_year = cal.get(Calendar.YEAR);
        x_month = cal.get(Calendar.MONTH);
        x_day = cal.get(Calendar.DAY_OF_MONTH);
        x_hour = cal.get(Calendar.HOUR_OF_DAY);
        x_minute = cal.get(Calendar.MINUTE);

        setDate();
        setTime();

    }

    /**
     * Function for clicking check
     *
     * @param view The view of activity
     */
    public void tracker_insert_check(View view) {
        if (!editText_minute.getText().toString().equals("")) {
            Intent resultIntent = new Intent();
            resultIntent.putExtra("Minute", editText_minute.getText().toString());
            resultIntent.putExtra("Type", spinner_type.getSelectedItem().toString());
            resultIntent.putExtra("Date", textView_date.getText().toString());
            resultIntent.putExtra("Time", textView_time.getText().toString());
            resultIntent.putExtra("Comment", textView_comment.getText().toString());
            setResult(1, resultIntent);
            finish();
        } else {
            Toast.makeText(this, R.string.tracker_insert_empty, Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Function for clicking cancel
     *
     * @param view The view of activity
     */
    public void tracker_insert_close(View view) {
        finish();
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DIALOG_ID_DATE:
                return new DatePickerDialog(this, dpickerListner, x_year, x_month, x_day);
            case DIALOG_ID_TIME:
                return new TimePickerDialog(this, tpickerListner, x_hour, x_minute, true);
            default:
                return null;
        }
    }

    /**
     * Function for clicking date
     *
     * @param view The view of activity
     */
    public void tracker_insert_date(View view) {
        showDialog(DIALOG_ID_DATE);
    }

    /**
     * Date Picker
     */
    private DatePickerDialog.OnDateSetListener dpickerListner = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            x_year = year;
            x_month = month;
            x_day = dayOfMonth;

            setDate();
        }
    };

    /**
     * Function for setting date
     */
    private void setDate() {
        String year = Integer.toString(x_year);
        String month = Integer.toString((x_month + 1));
        String day = Integer.toString(x_day);
        if (x_month < 9) {

            month = "0" + (x_month + 1);
        }
        if (x_day < 10) {

            day = "0" + x_day;
        }
        textView_date.setText((year + "-" + month + "-" + day));
    }

    /**
     * Function for clicking time
     *
     * @param view The view of activity
     */
    public void tracker_insert_time(View view) {
        showDialog(DIALOG_ID_TIME);
    }

    /**
     * Time picker
     */
    private TimePickerDialog.OnTimeSetListener tpickerListner = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            x_hour = hourOfDay;
            x_minute = minute;

            setTime();
        }
    };

    /**
     * Function for setting time
     */
    private void setTime() {
        String hour = Integer.toString(x_hour);
        String minute = Integer.toString(x_minute);
        if (x_hour < 10) {

            hour = "0" + x_hour;
        }
        if (x_minute < 10) {

            minute = "0" + x_minute;
        }
        textView_time.setText(hour + ":" + minute);
    }

    /**
     * Function for clicking comment
     *
     * @param view The view of activity
     */
    public void tracker_insert_comment_dialog(View view) {
        AlertDialog.Builder commentBuilder = new AlertDialog.Builder(this);
        View commentView = getLayoutInflater().inflate(R.layout.tracker_insert_dialog_comment, null);
        editText_comment = commentView.findViewById(R.id.tracker_insert_dialog_comment_editText);
        editText_comment.setText(textView_comment.getText());

        commentBuilder.setView(commentView);
        commentDialog = commentBuilder.create();
        commentDialog.setCanceledOnTouchOutside(false);
        commentDialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        commentDialog.show();
        editText_comment.requestFocus();
    }

    /**
     * Function for clicking comment check
     *
     * @param view The view of activity
     */
    public void tracker_insert_comment_check(View view) {
        textView_comment.setText(editText_comment.getText());
        commentDialog.dismiss();
    }

    /**
     * Function for clicking minute
     *
     * @param view
     */
    public void tracker_insert_minute(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(this.INPUT_METHOD_SERVICE);
        inputMethodManager.showSoftInput(editText_minute, InputMethodManager.SHOW_IMPLICIT);
    }
}
