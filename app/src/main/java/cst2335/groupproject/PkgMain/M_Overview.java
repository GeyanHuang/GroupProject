package cst2335.groupproject.PkgMain;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import cst2335.groupproject.R;


/**
 * This class is used for creating the main GUI of overview
 *
 * @author Geyan Huang
 *
 */
public class M_Overview extends Fragment {
    public M_Overview() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.overview_main, container, false);

        SharedPreferences sharedPreferences = view.getContext().getSharedPreferences("Layout", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Name", "M_Overview");
        editor.apply();
        // Inflate the layout for this fragment
        return view;
    }

    private View view;
    private ListView listView;
    private TextView item, content;
    private ArrayList<Info> info;

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
            super(context, R.layout.overview_info, info);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            View customView = inflater.inflate(R.layout.overview_info, parent, false);
            item = customView.findViewById(R.id.main_overview_info_textView_item);
            content = customView.findViewById(R.id.main_overview_info_textView_content);

            item.setText(getItem(position).item);
            content.setText(getItem(position).content);

            return customView;
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        info = new ArrayList<>();
        String[] items = getResources().getStringArray(R.array.overview_list_item);
        String[] contents = getResources().getStringArray(R.array.overview_list_content);
        for (int i = 0; i < items.length; i++)
            info.add(new Info(items[i], contents[i]));
        listView = view.findViewById(R.id.overview_main_listView);
        InfoAdapter adapter = new InfoAdapter(view.getContext(), info);
        listView.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.overview_title);
    }
}
