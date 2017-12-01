package cst2335.groupproject.PkgActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import cst2335.groupproject.R;

public class T_Update extends AppCompatActivity {
    TextView textView_date, textView_time, textView_comment;
    Spinner spinner_type;
    AlertDialog commentDialog;
    int x_year, x_month, x_day, x_hour, x_minute;
    static final int DIALOG_ID_DATE = 1;
    static final int DIALOG_ID_TIME = 2;
    EditText editText_minute, editText_comment;

    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tracker_update);

        editText_minute = findViewById(R.id.tracker_insert_editText_minute);
        spinner_type = findViewById(R.id.tracker_insert_spinner_type);
        textView_date = findViewById(R.id.tracker_insert_textView_date);
        textView_time = findViewById(R.id.tracker_insert_textView_time);
        textView_comment = findViewById(R.id.tracker_insert_textView_comment);

        id = getIntent().getStringExtra("Id");
        String type = getIntent().getStringExtra("Type");
        String minute = getIntent().getStringExtra("Minute");
        String comment = getIntent().getStringExtra("Comment");
        String date = getIntent().getStringExtra("Date");
        String time = getIntent().getStringExtra("Time");

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.tracker_list_type, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_type.setAdapter(adapter);
        if (!type.equals(null)) {
            int spinnerPosition = adapter.getPosition(type);
            spinner_type.setSelection(spinnerPosition);
        }

        editText_minute.setText(minute.replace(" Min", ""));
        textView_comment.setText(comment);

        if (!date.equals("")) {
            String[] dates = date.split("-");
            x_year = Integer.parseInt(dates[0]);
            x_month = Integer.parseInt(dates[1]) - 1;
            x_day = Integer.parseInt(dates[2]);
        }

        if (!time.equals("")) {
            String[] times = time.split(":");
            x_hour = Integer.parseInt(times[0]);
            x_minute = Integer.parseInt(times[1]);
        }

        setDate();
        setTime();

        editText_minute.requestFocus();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }

    public void tracker_insert_check(View view) {

        if (!editText_minute.getText().toString().equals("")) {
            Intent resultIntent = new Intent();
            resultIntent.putExtra("Id", id);
            resultIntent.putExtra("Minute", editText_minute.getText().toString());
            resultIntent.putExtra("Type", spinner_type.getSelectedItem().toString());
            resultIntent.putExtra("Date", textView_date.getText().toString());
            resultIntent.putExtra("Time", textView_time.getText().toString());
            resultIntent.putExtra("Comment", textView_comment.getText().toString());
            setResult(2, resultIntent);
            finish();
        } else {
            Toast.makeText(this, R.string.tracker_insert_empty, Toast.LENGTH_SHORT).show();

        }
    }

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

    public void tracker_insert_date(View view) {
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

    public void tracker_insert_time(View view) {
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

    public void tracker_insert_comment_check(View view) {
        textView_comment.setText(editText_comment.getText());
        commentDialog.dismiss();
    }

    public void tracker_update(View view) {
        final Intent resultIntent = new Intent();
        resultIntent.putExtra("Id", id);
        AlertDialog.Builder builder = new AlertDialog.Builder(T_Update.this);
        builder.setMessage(R.string.tracker_delete_dialog_message);
        builder.setPositiveButton(R.string.tracker_delete_dialog_ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                setResult(3, resultIntent);
                finish();
            }
        })
                .setNegativeButton(R.string.tracker_delete_dialog_cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                })
                .show();
    }

    public void tracker_insert_minute(View view) {
        InputMethodManager imm = (InputMethodManager) getSystemService(this.INPUT_METHOD_SERVICE);
        imm.showSoftInput(editText_minute, InputMethodManager.SHOW_IMPLICIT);
    }
}
