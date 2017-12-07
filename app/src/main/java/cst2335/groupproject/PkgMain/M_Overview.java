package cst2335.groupproject.PkgMain;


import android.content.Context;
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
 */
public class M_Overview extends Fragment {

    /**
     * Using M_SharedPreference
     */
    private M_SharedPreference sharedPreference = new M_SharedPreference();

    /**
     * The fragment view
     */
    private View view;

    /**
     * The list view for showing overview
     */
    private ListView listView;

    /**
     * The view for item and content
     */
    private TextView item, content;

    /**
     * ArrayList for showing overview information
     */
    private ArrayList<Info> info;

    /**
     * Constructor
     */
    public M_Overview() {
        // Required empty public constructor
    }

    /**
     * Create view
     *
     * @param inflater           The inflater
     * @param container          The container
     * @param savedInstanceState The savedInstanceState
     * @return The view
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.overview_main, container, false);

        // Record current layout
        sharedPreference.setLayout(view.getContext(), "M_Overview");

        return view;
    }

    /**
     * Customize class for store overview information
     */
    private class Info {

        /**
         * The item
         */
        private String item;

        /**
         * The content
         */
        private String content;

        /**
         * The constructor
         *
         * @param item    Overview item
         * @param content Overview content
         */
        public Info(String item, String content) {
            this.item = item;
            this.content = content;
        }
    }

    /**
     * The array adapter for overview
     */
    class InfoAdapter extends ArrayAdapter<Info> {

        /**
         * The constructor
         *
         * @param context The context
         * @param info    Customize data type
         */
        public InfoAdapter(Context context, ArrayList<Info> info) {
            super(context, R.layout.overview_info, info);
        }

        /**
         * Get view
         *
         * @param position    The position of arrayList
         * @param convertView The convertView
         * @param parent      The parent
         * @return The view to listView
         */
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

    /**
     * On activity created
     *
     * @param savedInstanceState The savedInstanceState
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        info = new ArrayList<>();

        // Get list from String XML
        String[] items = getResources().getStringArray(R.array.overview_list_item);
        String[] contents = getResources().getStringArray(R.array.overview_list_content);
        for (int i = 0; i < items.length; i++)
            info.add(new Info(items[i], contents[i]));
        listView = view.findViewById(R.id.overview_main_listView);
        InfoAdapter adapter = new InfoAdapter(view.getContext(), info);
        listView.setAdapter(adapter);
    }

    /**
     * On resume
     */
    @Override
    public void onResume() {
        super.onResume();
        // Set title for action bar
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.overview_title);
    }
}
