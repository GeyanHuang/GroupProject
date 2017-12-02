package cst2335.groupproject.PkgMain;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * This class is used for record data by sharePreference
 *
 * @author Geyan Huang
 *
 */

public class M_SharedPreference {

    public void setLayout(Context context, String name){
        SharedPreferences sharedPreferences = context.getSharedPreferences("Layout", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Name", name);
        editor.apply();
    }

    public String getLayout(Context context){
        SharedPreferences sharedPref = context.getSharedPreferences("Layout", Context.MODE_PRIVATE);
        String name = sharedPref.getString("Name", "0");
        return name;
    }

}
