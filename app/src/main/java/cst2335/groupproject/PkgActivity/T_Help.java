package cst2335.groupproject.PkgActivity;

import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;

import java.util.Locale;

import cst2335.groupproject.R;

public class T_Help extends AppCompatActivity {

    /**
     * This class is used for creating the help GUI of activity tracker
     *
     * @author Geyan Huang
     *
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tracker_help);
        this.setFinishOnTouchOutside(false);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.tracker_help_container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

    }

    public void tracker_help_dialog_ok(View view) {
        finish();
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.tracker_help_fragment, container, false);
            ImageView imageView = (ImageView) rootView.findViewById(R.id.tracker_help_fragment_section_label);
            ImageView help_progress = (ImageView) rootView.findViewById(R.id.tracker_help_fragment_progress);

            switch (getArguments().getInt(ARG_SECTION_NUMBER)) {
                case 1:
                    if (Locale.getDefault().getLanguage().equals("zh")) {
                        imageView.setImageResource(R.drawable.ic_tracker_help_p1_zh);
                    }else {
                        imageView.setImageResource(R.drawable.ic_tracker_help_p1);

                    }
                    help_progress.setImageResource(R.drawable.ic_tracker_help_tab1);
                    break;
                case 2:
                    if (Locale.getDefault().getLanguage().equals("zh")) {
                        imageView.setImageResource(R.drawable.ic_tracker_help_p2_zh);
                    }else{
                        imageView.setImageResource(R.drawable.ic_tracker_help_p2);
                    }
                    help_progress.setImageResource(R.drawable.ic_tracker_help_tab2);
                    break;
                case 3:
                    if (Locale.getDefault().getLanguage().equals("zh")) {
                        imageView.setImageResource(R.drawable.ic_tracker_help_p3_zh);
                    }else{
                        imageView.setImageResource(R.drawable.ic_tracker_help_p3);
                    }
                    help_progress.setImageResource(R.drawable.ic_tracker_help_tab3);
                    break;
            }

            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getType is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            return 3;
        }
    }

}
