package cst2335.groupproject.PkgActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.Context;
import android.support.annotation.Nullable;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

import cst2335.groupproject.PkgMain.M_SharedPreference;
import cst2335.groupproject.R;


/**
 * This class is used for creating the list GUI of activity tracker
 *
 * @author Geyan Huang
 */
public class T_Fragment_ActivityList extends Fragment {

    /**
     * Using M_SharedPreference
     */
    private M_SharedPreference sharedPreference = new M_SharedPreference();

    /**
     * The database helper
     */
    private T_DatabaseHelper databaseHelper;

    /**
     * The view of fragment
     */
    private View view;

    /**
     * The listView
     */
    private ListView listView;

    /**
     * The textViews
     */
    private TextView textView_item, textView_min, textView_date, textView_desc;

    /**
     * The imageView
     */
    private ImageView imageView;

    /**
     * Button for inserting data
     */
    private FloatingActionButton button_insert;

    /**
     * The arrayList
     */
    private ArrayList<Info> list_info;

    /**
     * The adapter of listView
     */
    private InfoAdapter adapter;

    /**
     * The asyncTask for reading data from database
     */
    private ReadDatabase readDatabase;

    /**
     * The constructor
     */
    public T_Fragment_ActivityList() {
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
        view = inflater.inflate(R.layout.tracker_activitylist_fragment, container, false);

        sharedPreference.setLayout(view.getContext(), "T_Fragment_ActivityList");
        sharedPreference.setActivityLayout(view.getContext(), "T_Fragment_ActivityList");

        return view;
    }

    /**
     * Customized class for storing information of activity
     */
    private class Info implements Comparable<Info> {

        /**
         * The ID
         */
        private int activityId;

        /**
         * The minute, type, date, time, comment
         */
        private String minute, type, date, time, comment;

        /**
         * Get activity ID
         *
         * @return The activity ID
         */
        public int getActivityId() {
            return activityId;
        }

        /**
         * Get activity minute
         *
         * @return The activity minute
         */
        public String getMinute() {
            return minute;
        }

        /**
         * Get activity type
         *
         * @return The activity type
         */
        public String getType() {
            return type;
        }

        /**
         * Get date
         *
         * @return The date
         */
        public String getDate() {
            return date;
        }

        /**
         * Get time
         *
         * @return The time
         */
        public String getTime() {
            return time;
        }

        /**
         * Get comment
         *
         * @return The comment
         */
        public String getComment() {
            return comment;
        }

        /**
         * Constructor
         *
         * @param activityId The activity ID
         * @param min        The activity minute
         * @param type       The activity type
         * @param date       The date
         * @param time       The time
         * @param desc       The comment
         */
        public Info(int activityId, String type, String min, String date, String time, String desc) {
            this.activityId = activityId;
            this.type = type;
            this.minute = min;
            this.date = date;
            this.time = time;
            this.comment = desc;
        }

        /**
         * Function for sorting arrayList
         *
         * @param o The activity information
         * @return integer
         */
        @Override
        public int compareTo(Info o) {
            return (o.getDate() + " " + o.getTime()).compareTo(getDate() + " " + getTime());
        }
    }

    /***
     * The class for customize arrayAdapter
     */
    class InfoAdapter extends ArrayAdapter<Info> {

        /**
         * The constructor
         *
         * @param context The context
         * @param info    The customize data type
         */
        public InfoAdapter(Context context, ArrayList<Info> info) {
            super(context, R.layout.overview_info, info);
        }

        /**
         * Get view
         *
         * @param position    The position of arrayList
         * @param convertView The convert view
         * @param parent      The parent
         * @return The view for listView
         */
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            View customView = inflater.inflate(R.layout.tracker_activitylist_info, parent, false);
            textView_item = customView.findViewById(R.id.tracker_activityList_info_textView_type);
            imageView = customView.findViewById(R.id.tracker_activityList_info_imageView_type);
            textView_min = customView.findViewById(R.id.tracker_activityList_info_textView_minute);
            textView_date = customView.findViewById(R.id.tracker_activityList_info_textView_date_and_time);
            textView_desc = customView.findViewById(R.id.tracker_activityList_info_textView_comment);
            textView_item.setText(getItem(position).type);
            textView_min.setText(getItem(position).minute + " " + getResources().getString(R.string.tracker_insert_minute));
            textView_date.setText(getItem(position).date + " " + getItem(position).time);
            textView_desc.setText(getItem(position).comment);
            String[] items = getResources().getStringArray(R.array.tracker_list_type);
            if (getItem(position).type.equals(items[0])) {
                imageView.setImageResource(R.drawable.ic_tracker_run_white);
            } else if (getItem(position).type.equals(items[1])) {
                imageView.setImageResource(R.drawable.ic_tracker_walk_white);
            } else if (getItem(position).type.equals(items[2])) {
                imageView.setImageResource(R.drawable.ic_tracker_bike_white);
            } else if (getItem(position).type.equals(items[3])) {
                imageView.setImageResource(R.drawable.ic_tracker_swim_white);
            } else if (getItem(position).type.equals(items[4])) {
                imageView.setImageResource(R.drawable.ic_tracker_skate_white);
            }
            return customView;
        }
    }

    /**
     * On activity created
     *
     * @param savedInstanceState The savedInstanceState
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Connect listView, arrayList, and arrayAdapter
        list_info = new ArrayList<>();
        listView = view.findViewById(R.id.tracker_activityList_fragment_listView);
        adapter = new InfoAdapter(view.getContext(), list_info);
        listView.setAdapter(adapter);

        // Function for clicking listView
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Info info = (Info) (adapterView.getItemAtPosition(i));
                int numId = info.getActivityId();
                String id = numId + "";
                String type = info.getType();
                String minute = info.getMinute();
                String date = info.getDate();
                String time = info.getTime();
                String comment = info.getComment();
                Intent intent = new Intent(view.getContext(), T_Update.class);
                intent.putExtra("Id", id);
                intent.putExtra("Type", type);
                intent.putExtra("Minute", minute);
                intent.putExtra("Date", date);
                intent.putExtra("Time", time);
                intent.putExtra("Comment", comment);
                startActivityForResult(intent, 2);
            }
        });


        // Function for clicking insert button
        button_insert = view.findViewById(R.id.tracker_activityList_fragment_button_insert);

        button_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), T_Insert.class);
                startActivityForResult(intent, 1);
            }
        });

        //open database and read database
        databaseHelper = new T_DatabaseHelper(view.getContext());
        databaseHelper.openDatabase();
        showHistory();
    }

    /**
     * Translate activity from Chinese to English
     *
     * @param type The activity type
     * @return The English activity type
     */
    private String typeToEn(String type) {
        switch (type) {
            case "跑步":
                return "Running";
            case "走路":
                return "Walking";
            case "骑车":
                return "Biking";
            case "游泳":
                return "Swimming";
            case "滑冰":
                return "Skating";
        }
        return null;
    }

    /**
     * Translate activity type from English to Chinese
     *
     * @param type The activity type
     * @return The Chinese activity type
     */
    private String typeToZh(String type) {
        switch (type) {
            case "Running":
                return "跑步";
            case "Walking":
                return "走路";
            case "Biking":
                return "骑车";
            case "Swimming":
                return "游泳";
            case "Skating":
                return "滑冰";
        }
        return null;
    }

    /**
     * On activity result, it used to get data and do actions when insert, update, and delete
     *
     * @param requestCode The requestCode
     * @param resultCode  The resultCode
     * @param data        The data
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        // Check resultCode
        switch (resultCode) {

            // Insert
            case 1:
                if (!data.getStringExtra("Minute").equals("")) {
                    String minutes = data.getStringExtra("Minute");
                    String type = data.getStringExtra("Type");
                    String date = data.getStringExtra("Date");
                    String time = data.getStringExtra("Time");
                    String comment = data.getStringExtra("Comment");

                    // Always insert English activity type to database
                    if (Locale.getDefault().getLanguage().equals("zh")) {
                        type = typeToEn(type);
                    }
                    databaseHelper.insert(minutes, type, date, time, comment);
                    showHistory();

                    // Snack bar shows notification
                    Snackbar.make(view.findViewById(R.id.tracker_activityList_fragment_button_insert), R.string.tracker_insert_done, Snackbar.LENGTH_SHORT).setAction("Action", null).show();
                }
                break;

            // Update
            case 2:
                if (!data.getStringExtra("Minute").equals("")) {
                    String id = data.getStringExtra("Id");
                    String minutes = data.getStringExtra("Minute");
                    String type = data.getStringExtra("Type");
                    String date = data.getStringExtra("Date");
                    String time = data.getStringExtra("Time");
                    String comment = data.getStringExtra("Comment");

                    // Always update English activity type to database
                    if (Locale.getDefault().getLanguage().equals("zh")) {
                        type = typeToEn(type);
                    }
                    databaseHelper.update(id, minutes, type, date, time, comment);
                    showHistory();

                    // Snack bar shows notification
                    Snackbar.make(view.findViewById(R.id.tracker_activityList_fragment_button_insert), R.string.tracker_update_done, Snackbar.LENGTH_SHORT).setAction("Action", null).show();
                }
                break;

            // Delete
            case 3:

                // Delete row using ID
                String id = data.getStringExtra("Id");
                databaseHelper.delete(id);
                showHistory();

                // Snack bar shows notification
                Snackbar.make(view.findViewById(R.id.tracker_activityList_fragment_button_insert), R.string.tracker_delete_done, Snackbar.LENGTH_SHORT).setAction("Action", null).show();
                break;
        }
    }

    /**
     * Read from database by using AsyncTask
     */
    private void showHistory() {
        readDatabase = new ReadDatabase();
        readDatabase.execute(list_info);
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
     * Set action bar title when on resume
     */
    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.tracker_nav_bot_list);
    }

    /**
     * AsyncTask for reading data from database
     */
    public class ReadDatabase extends AsyncTask<ArrayList<Info>, Integer, ArrayList<Info>> {

        /**
         * Function to do in background, it is used to read data from database to arrayList
         *
         * @param arrayLists The current used arrayList
         * @return null
         */
        @Override
        protected ArrayList<Info> doInBackground(ArrayList<Info>[] arrayLists) {

            // Use try catch to avoid crash when rotation
            try {
                Cursor cursor = databaseHelper.read();
                for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
                    String type = cursor.getString(cursor.getColumnIndex(databaseHelper.COLUMN_TYPE));
                    if (Locale.getDefault().getLanguage().equals("zh")) {
                        type = typeToZh(type);
                    }
                    arrayLists[0].add(new Info(cursor.getInt(cursor.getColumnIndex(databaseHelper.COLUMN_ID)), type, cursor.getString(cursor.getColumnIndex(databaseHelper.COLUMN_MINUTE)), cursor.getString(cursor.getColumnIndex(databaseHelper.COLUMN_DATE)), cursor.getString(cursor.getColumnIndex(databaseHelper.COLUMN_TIME)), cursor.getString(cursor.getColumnIndex(databaseHelper.COLUMN_COMMENT))));
                }
                Collections.sort(arrayLists[0]);
            } catch (Exception ex) {

            }
            return null;
        }

        /**
         * Before execute
         */
        @Override
        protected void onPreExecute() {
            list_info.clear();
        }

        /**
         * After execute
         *
         * @param infos It is not been used
         */
        @Override
        protected void onPostExecute(ArrayList<Info> infos) {
            adapter.notifyDataSetChanged();
        }
    }
}
