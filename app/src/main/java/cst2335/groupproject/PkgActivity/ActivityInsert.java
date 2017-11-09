package cst2335.groupproject.PkgActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import cst2335.groupproject.R;

public class ActivityInsert extends Activity {
    private Button insert, cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        insert = findViewById(R.id.activity_insert_insertbutton);
        cancel = findViewById((R.id.activity_insert_cancelbutton));

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();;
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();;
            }
        });
    }


}
