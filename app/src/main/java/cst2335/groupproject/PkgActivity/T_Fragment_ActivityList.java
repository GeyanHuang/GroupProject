package cst2335.groupproject.PkgActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
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

import cst2335.groupproject.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class T_Fragment_ActivityList extends Fragment {


    public T_Fragment_ActivityList() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.tracker_activitylist_fragment, container, false);

        SharedPreferences sharedPreferences1 = view.getContext().getSharedPreferences("Layout", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences1.edit();
        editor.putString("Name", "T_Fragment_ActivityList");
        editor.apply();

        SharedPreferences sharedPreferences2 = view.getContext().getSharedPreferences("ActivityLayout", Context.MODE_PRIVATE);
        SharedPreferences.Editor anotherEditor = sharedPreferences2.edit();
        anotherEditor.putString("Name", "T_Fragment_ActivityList");
        anotherEditor.apply();

        return view;
    }

    private T_DatabaseHelper databaseHelper;
    private View view;
    private ListView listView;
    private TextView textView_item, textView_min, textView_date, textView_desc;
    private ImageView imageView;
    private FloatingActionButton button_insert;
    private ArrayList<Info> list_info;
    private InfoAdapter adapter;

    private class Info implements Comparable<Info> {
        private int activityId;
        private String item;
        private String minute, date, time, comment;

        public int getActivityId() {
            return activityId;
        }

        public String getItem() {
            return item;
        }

        public String getMinute() {
            return minute;
        }

        public String getDate() {
            return date;
        }

        public String getTime() {
            return time;
        }

        public String getComment() {
            return comment;
        }

        public Info(int activityId, String item, String min, String date, String time, String desc) {
            this.activityId = activityId;
            this.item = item;
            this.minute = min;
            this.date = date;
            this.time = time;
            this.comment = desc;
        }

        @Override
        public int compareTo(Info o) {
            return (o.getDate() + " " + o.getTime()).compareTo(getDate() + " " + getTime());
        }
    }

    class InfoAdapter extends ArrayAdapter<Info> {

        public InfoAdapter(Context context, ArrayList<Info> info) {
            super(context, R.layout.overview_info, info);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            View customView = inflater.inflate(R.layout.tracker_activitylist_info, parent, false);
            textView_item = customView.findViewById(R.id.tracker_activityList_info_textView_type);
            imageView = customView.findViewById(R.id.tracker_activityList_info_imageView_type);
            textView_min = customView.findViewById(R.id.tracker_activityList_info_textView_minute);
            textView_date = customView.findViewById(R.id.tracker_activityList_info_textView_date_and_time);
            textView_desc = customView.findViewById(R.id.tracker_activityList_info_textView_comment);
            textView_item.setText(getItem(position).item);
            textView_min.setText(getItem(position).minute + " " + getResources().getString(R.string.tracker_insert_minute));
            textView_date.setText(getItem(position).date + " " + getItem(position).time);
            textView_desc.setText(getItem(position).comment);
            String[] items = getResources().getStringArray(R.array.tracker_list_type);
            if (getItem(position).item.equals(items[0])) {
                imageView.setImageResource(R.drawable.ic_tracker_run_white);
            } else if (getItem(position).item.equals(items[1])) {
                imageView.setImageResource(R.drawable.ic_tracker_walk_white);
            } else if (getItem(position).item.equals(items[2])) {
                imageView.setImageResource(R.drawable.ic_tracker_bike_white);
            } else if (getItem(position).item.equals(items[3])) {
                imageView.setImageResource(R.drawable.ic_tracker_swim_white);
            } else if (getItem(position).item.equals(items[4])) {
                imageView.setImageResource(R.drawable.ic_tracker_skate_white);
            }
            return customView;
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        list_info = new ArrayList<>();
        listView = view.findViewById(R.id.tracker_activityList_fragment_listView);
        adapter = new InfoAdapter(view.getContext(), list_info);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Info info = (Info) (adapterView.getItemAtPosition(i));
                int numId = info.getActivityId();
                String id = numId + "";
                String type = info.getItem();
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

        button_insert = view.findViewById(R.id.tracker_activityList_fragment_button_insert);

        button_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), T_Insert.class);
                startActivityForResult(intent, 1);
            }
        });
        databaseHelper = new T_DatabaseHelper(view.getContext());
        databaseHelper.openDatabase();
        showHistory();
    }

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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (resultCode) {
            case 1:
                if (!data.getStringExtra("Minute").equals("")) {
                    String minutes = data.getStringExtra("Minute");
                    String type = data.getStringExtra("Type");
                    String date = data.getStringExtra("Date");
                    String time = data.getStringExtra("Time");
                    String comment = data.getStringExtra("Comment");
                    if (Locale.getDefault().getLanguage().equals("zh")) {
                        type = typeToEn(type);
                    }
                    databaseHelper.insert(minutes, type, date, time, comment);
                    showHistory();
                    Snackbar.make(view.findViewById(R.id.tracker_activityList_fragment_button_insert), R.string.tracker_insert_done, Snackbar.LENGTH_SHORT).setAction("Action", null).show();
                }
                break;
            case 2:
                if (!data.getStringExtra("Minute").equals("")) {
                    String id = data.getStringExtra("Id");
                    String minutes = data.getStringExtra("Minute");
                    String type = data.getStringExtra("Type");
                    String date = data.getStringExtra("Date");
                    String time = data.getStringExtra("Time");
                    String comment = data.getStringExtra("Comment");
                    if (Locale.getDefault().getLanguage().equals("zh")) {
                        type = typeToEn(type);
                    }
                    databaseHelper.update(id, minutes, type, date, time, comment);
                    showHistory();
                    Snackbar.make(view.findViewById(R.id.tracker_activityList_fragment_button_insert), R.string.tracker_update_done, Snackbar.LENGTH_SHORT).setAction("Action", null).show();
                }
                break;
            case 3:
                String id = data.getStringExtra("Id");
                databaseHelper.delete(id);
                showHistory();
                Snackbar.make(view.findViewById(R.id.tracker_activityList_fragment_button_insert), R.string.tracker_delete_done, Snackbar.LENGTH_SHORT).setAction("Action", null).show();
                break;
        }
    }

    private void showHistory() {
        list_info.clear();
        Cursor cursor = databaseHelper.read();
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            String type = cursor.getString(cursor.getColumnIndex(databaseHelper.COLUMN_TYPE));
            if (Locale.getDefault().getLanguage().equals("zh")) {
                type = typeToZh(type);
            }
            list_info.add(new Info(cursor.getInt(cursor.getColumnIndex(databaseHelper.COLUMN_ID)), type, cursor.getString(cursor.getColumnIndex(databaseHelper.COLUMN_MINUTE)), cursor.getString(cursor.getColumnIndex(databaseHelper.COLUMN_DATE)), cursor.getString(cursor.getColumnIndex(databaseHelper.COLUMN_TIME)), cursor.getString(cursor.getColumnIndex(databaseHelper.COLUMN_COMMENT))));
        }
        Collections.sort(list_info);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        databaseHelper.closeDatabase();
    }

    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.tracker_nav_bot_list);
    }
}
