package cst2335.groupproject.PkgMain;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import cst2335.groupproject.PkgActivity.T_Help;
import cst2335.groupproject.PkgActivity.T_Main;
import cst2335.groupproject.PkgAutomobile.A_Main;
import cst2335.groupproject.PkgFood.F_Main;
import cst2335.groupproject.PkgHouse.H_Main;
import cst2335.groupproject.R;

/**
 * This class is used for creating the main GUI of program
 *
 * @author Geyan Huang
 *
 */
public class M_MainPage extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    /**
     * Navigation drawer
     */
    private NavigationView navigationView;

    /**
     * The menu
     */
    private Menu menu;

    /**
     * Using M_SharedPreference
     */
    private M_SharedPreference sharedPreference  = new M_SharedPreference();

    /**
     * On create function
     * @param savedInstanceState The savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_page);

        // Record current layout
        sharedPreference.setLayout(this,"M_MainPage");

        // Set toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.main_app_bar_toolbar);
        setSupportActionBar(toolbar);

        // Set navigation drawer
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.main_navigation_drawer_open, R.string.main_navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.main_page_nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }


    /**
     * On back pressed function
     */
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        this.menu = menu;
        getMenuInflater().inflate(R.menu.tracker_menu, menu);

        // Only show menu when using activity tracker
        String name = sharedPreference.getLayout(this);
        if(name.equals("T_Fragment_ActivityList")||name.equals("T_Fragment_Dashboard")){
            showTrackerHelp(true);
        }else {
            showTrackerHelp(false);
        }
        return true;
    }

    /**
     * Function for hiding and showing menu
     * @param showMenu The boolean that decide show or hide menu
     */
    public void showTrackerHelp(boolean showMenu){
        if(menu == null)
            return;
        menu.setGroupVisible(R.id.tracker_menu_group, showMenu);
    }

    /**
     * Function for menu actions
     * @param item  The menu item
     * @return On option item selected
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the M_MainPage/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        // Menu item help
        if (id == R.id.tracker_menu_action_help) {
            Intent intent = new Intent(M_MainPage.this, T_Help.class);
            startActivity(intent);
            return true;
        }

        // Menu item quit
        if (id == R.id.tracker_menu_action_quit) {
            finishAffinity();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Function for navigation drawer action
     * @param item The navigation drawer item
     * @return The boolean
     */
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        showTrackerHelp(false);

        int id = item.getItemId();

        if (id == R.id.main_drawer_nav_activity) {
            showTrackerHelp(true);
            T_Main fragment = new T_Main();
            android.support.v4.app.FragmentTransaction fragmentTransaction =
                    getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.main_app_bar_container_fragment, fragment);
            fragmentTransaction.commit();
        } else if (id == R.id.main_drawer_nav_food) {
            F_Main fragment = new F_Main();
            android.support.v4.app.FragmentTransaction fragmentTransaction =
                    getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.main_app_bar_container_fragment, fragment);
            fragmentTransaction.commit();
        } else if (id == R.id.main_drawer_nav_house) {
            H_Main fragment = new H_Main();
            android.support.v4.app.FragmentTransaction fragmentTransaction =
                    getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.main_app_bar_container_fragment, fragment);
            fragmentTransaction.commit();
        } else if (id == R.id.main_drawer_nav_automobile) {
            A_Main fragment = new A_Main();
            android.support.v4.app.FragmentTransaction fragmentTransaction =
                    getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.main_app_bar_container_fragment, fragment);
            fragmentTransaction.commit();
        } else if (id == R.id.main_drawer_nav_home) {
            M_Overview fragment = new M_Overview();
            android.support.v4.app.FragmentTransaction fragmentTransaction =
                    getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.main_app_bar_container_fragment, fragment);
            fragmentTransaction.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }}
