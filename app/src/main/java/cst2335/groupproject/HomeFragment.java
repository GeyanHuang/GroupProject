package cst2335.groupproject;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    private View view;
    private ListView listView;
    private TextView item, content;
    private ArrayList<Info> info;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        // Inflate the layout for this fragment
        return view;
    }
    private class Info {
        private String item;
        private String content;

        public Info(String item, String content) {
            this.item = item;
            this.content = content;
        }
    }

    class InfoAdapter extends ArrayAdapter<Info> {

        public InfoAdapter(Context context, ArrayList<Info> info) {
            super(context, R.layout.home_info, info);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            View customView = inflater.inflate(R.layout.home_info, parent, false);
            item = customView.findViewById(R.id.textview_home_item);
            content = customView.findViewById(R.id.textview_home_content);

            item.setText(getItem(position).item);
            content.setText(getItem(position).content);

            return customView;
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        info = new ArrayList<>();
        info.add(new Info("Season", "2017 Fall"));
        info.add(new Info("Course", "CST2335 Mobile Graphical Interface Prog"));
        info.add(new Info("Assignment", "Final Assignment"));
        info.add(new Info("Type", "GroupWork"));
        info.add(new Info("Authors", "Hao Liu, Zhan Shen, Bin Yang, Geyan Huang"));
        listView = view.findViewById(R.id.listview_home);
        InfoAdapter adapter = new InfoAdapter(view.getContext(),info);
        listView.setAdapter(adapter);
    }




}
