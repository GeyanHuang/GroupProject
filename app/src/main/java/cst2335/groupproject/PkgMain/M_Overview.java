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
 * A simple {@link Fragment} subclass.
 */
public class M_Overview extends Fragment {
    public M_Overview() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);

        SharedPreferences sharedPref = view.getContext().getSharedPreferences("Layout", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
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
        String[] items = getResources().getStringArray(R.array.home_item_list);
        String[] contents = getResources().getStringArray(R.array.home_content_list);
        for (int i = 0; i < items.length; i++)
            info.add(new Info(items[i], contents[i]));
        listView = view.findViewById(R.id.listview_home);
        InfoAdapter adapter = new InfoAdapter(view.getContext(), info);
        listView.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.home_title);
    }
}
