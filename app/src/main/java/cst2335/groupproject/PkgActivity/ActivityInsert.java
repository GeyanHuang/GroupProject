package cst2335.groupproject.PkgActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import cst2335.groupproject.R;

public class ActivityInsert extends Activity {
    int year, month, day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);


    }

    public void insert_check(View view) {
        finish();
    }

    public void insert_close(View view) {
        finish();
    }
}
