package cst2335.groupproject.PkgActivity;


import android.content.res.TypedArray;
import android.media.Image;
import android.os.Bundle;
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
    private TextView item;
    private ImageView image;
    private ArrayList<Info> info;

    private class Info {
        private String item;
        private int image;

        public Info(String item, int image) {
            this.item = item;
            this.image = image;
        }
        public String getItem(){
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
            item.setText(getItem(position).item);
            image.setImageResource(getItem(position).image);
            return customView;
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        info = new ArrayList<>();
        String[] items = getResources().getStringArray(R.array.activity_item_list);
        TypedArray images = getResources().obtainTypedArray(R.array.activity_image_list);
        for(int i = 0; i < items.length; i++)
            info.add(new Info(items[i], images.getResourceId(i,-1)));
        listView = view.findViewById(R.id.listview_activity);
        InfoAdapter adapter = new InfoAdapter(view.getContext(),info);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Info info = (Info) (adapterView.getItemAtPosition(i));
                String item = info.getItem();
                Toast.makeText(view.getContext(),item, Toast.LENGTH_SHORT).show();
            }
        });

    }
}
