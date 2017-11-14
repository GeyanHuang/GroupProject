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

public class ActivityInsert extends Activity {
    TextView textView_date, textView_time, textView_comment;
    Spinner spinner_type;
    AlertDialog commentDialog;
    int x_year, x_month, x_day, x_hour, x_minute;
    static final int DIALOG_ID_DATE = 1;
    static final int DIALOG_ID_TIME = 2;
    EditText editText_minute, editText_comment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        editText_minute = findViewById(R.id.activity_insert_edittext_minute);
        spinner_type = findViewById(R.id.activity_insert_spinner_type);
        textView_date = findViewById(R.id.activity_insert_date);
        textView_time = findViewById(R.id.activity_insert_time);
        textView_comment = findViewById(R.id.activity_insert_textview_comment);


        final Calendar cal = Calendar.getInstance();
        x_year = cal.get(Calendar.YEAR);
        x_month = cal.get(Calendar.MONTH);
        x_day = cal.get(Calendar.DAY_OF_MONTH);
        x_hour = cal.get(Calendar.HOUR_OF_DAY);
        x_minute = cal.get(Calendar.MINUTE);

        setDate();
        setTime();

    }

    public void insert_check(View view) {
        if(!editText_minute.getText().toString().equals("")){
            Intent resultIntent = new Intent();
            resultIntent.putExtra("Minute",editText_minute.getText().toString());
            resultIntent.putExtra("Type",spinner_type.getSelectedItem().toString());
            resultIntent.putExtra("Date",textView_date.getText().toString());
            resultIntent.putExtra("Time",textView_time.getText().toString());
            resultIntent.putExtra("Comment",textView_comment.getText().toString());
            setResult(1, resultIntent);
            finish();
        }else {
            Toast.makeText(this,R.string.activity_insert_empty, Toast.LENGTH_SHORT).show();
        }
    }

    public void insert_close(View view) {
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

    public void activity_insert_date(View view) {
        showDialog(DIALOG_ID_DATE);
    }

    private DatePickerDialog.OnDateSetListener dpickerListner = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            x_year = year;
            x_month = month;
            x_day = dayOfMonth;

            setDate();
        }
    };

    private void setDate(){
        String year = Integer.toString(x_year) ;
        String month = Integer.toString((x_month+1)) ;
        String day = Integer.toString(x_day) ;
        if(x_month < 9){

            month = "0" + (x_month+1);
        }
        if(x_day < 10){

            day  = "0" + x_day ;
        }
        textView_date.setText((year + "-" + month + "-" + day));
    }

    public void activity_insert_time(View view) {
        showDialog(DIALOG_ID_TIME);
    }

    private TimePickerDialog.OnTimeSetListener tpickerListner = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            x_hour = hourOfDay;
            x_minute = minute;

            setTime();
        }
    };

    private void setTime(){
        String hour = Integer.toString(x_hour) ;
        String minute = Integer.toString(x_minute) ;
        if(x_hour < 10){

            hour = "0" + x_hour;
        }
        if(x_minute < 10){

            minute  = "0" + x_minute;
        }
        textView_time.setText(hour + ":" + minute);
    }

    public void activity_insert_comment_dialog(View view) {
        AlertDialog.Builder commentBuilder = new AlertDialog.Builder(this);
        View commentView = getLayoutInflater().inflate(R.layout.activity_insert_comment,null);
        editText_comment = commentView.findViewById(R.id.activity_insert_edittext_comment);
        editText_comment.setText(textView_comment.getText());

        commentBuilder.setView(commentView);
        commentDialog = commentBuilder.create();
        commentDialog.setCanceledOnTouchOutside(false);
        commentDialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        commentDialog.show();
        editText_comment.requestFocus();
    }

    public void activity_insert_comment_check(View view) {
        textView_comment.setText(editText_comment.getText());
        commentDialog.dismiss();
    }

    public void activity_insert_minute(View view) {
        InputMethodManager imm = (InputMethodManager) getSystemService(this.INPUT_METHOD_SERVICE);
        imm.showSoftInput(editText_minute, InputMethodManager.SHOW_IMPLICIT);
    }
}
