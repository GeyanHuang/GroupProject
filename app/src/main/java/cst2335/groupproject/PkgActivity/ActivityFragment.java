package cst2335.groupproject.PkgActivity;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
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
import android.widget.Toast;

import java.util.ArrayList;

import cst2335.groupproject.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class ActivityFragment extends Fragment {


    public ActivityFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_activity, container, false);
        return view;
    }

    private View view;
    private ListView listView;
    private TextView item, min, date, desc;
    private ImageView image;
    private FloatingActionButton insert;
    private ArrayList<Info> info;
    private InfoAdapter adapter;

    private class Info {
        private String item;
        private String min, date, desc;

        public Info(String item, String min, String date, String desc) {
            this.item = item;
            this.min = min;
            this.date = date;
            this.desc = desc;
        }

        public String getItem() {
            return item;
        }
    }

    class InfoAdapter extends ArrayAdapter<Info> {

        public InfoAdapter(Context context, ArrayList<Info> info) {
            super(context, R.layout.home_info, info);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            View customView = inflater.inflate(R.layout.activity_info, parent, false);
            item = customView.findViewById(R.id.textview_activity_item);
            image = customView.findViewById(R.id.imageview_activity_item);
            min = customView.findViewById(R.id.textview_activity_min);
            date = customView.findViewById(R.id.textview_activity_date);
            desc = customView.findViewById(R.id.textview_activity_desc);
            item.setText(getItem(position).item);
            min.setText(getItem(position).min);
            date.setText(getItem(position).date);
            desc.setText(getItem(position).desc);

            switch (getItem(position).item) {
                case "Running":
                case "跑步":
                    image.setImageResource(R.drawable.ic_directions_run_black_48dp);
                    break;
                case "Walking":
                case "走路":
                    image.setImageResource(R.drawable.ic_directions_walk_black_48dp);
                    break;
                case "Biking":
                case "骑车":
                    image.setImageResource(R.drawable.ic_directions_bike_black_48dp);
                    break;
                case "Swimming":
                case "游泳":
                    image.setImageResource(R.drawable.ic_directions_swim_black_48dp);
                    break;
                case "Skating":
                case "滑冰":
                    image.setImageResource(R.drawable.ic_directions_skate_black_48dp);
                    break;
                default:
                    break;
            }
            return customView;
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        info = new ArrayList<>();
        String[] items = getResources().getStringArray(R.array.activity_item_list);
        String[] mins = getResources().getStringArray(R.array.activity_item_min);
        String[] dates = getResources().getStringArray(R.array.activity_item_date);
        String[] descs = getResources().getStringArray(R.array.activity_item_desc);
        for (int i = 0; i < items.length; i++)
            info.add(new Info(items[i], mins[i], dates[i], descs[i]));
        listView = view.findViewById(R.id.listview_activity);
        adapter = new InfoAdapter(view.getContext(), info);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Info info = (Info) (adapterView.getItemAtPosition(i));
                String item = info.getItem();
                Toast.makeText(view.getContext(), item, Toast.LENGTH_SHORT).show();
            }
        });

        insert = view.findViewById(R.id.activity_insertbutton);

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), ActivityInsert.class);
                startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (resultCode) {
            case 1:
                if (!data.getStringExtra("Minute").equals("")) {
                    String minutes = data.getStringExtra("Minute") + " Min";
                    String type = data.getStringExtra("Type");
                    String date = data.getStringExtra("Date");
                    String time = data.getStringExtra("Time");
                    String combo = date + " " + time;
                    String comment = data.getStringExtra("Comment");
                    info.add(new Info(type, minutes, combo, comment));
                    adapter.notifyDataSetChanged();
                    scrollMyListViewToBottom();
                }
                break;
        }
    }

    private void scrollMyListViewToBottom() {
        listView.post(new Runnable() {
            @Override
            public void run() {
                // Select the last row so it will scroll into view...
                listView.setSelection(listView.getCount() - 1);
            }
        });
    }
}
