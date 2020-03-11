package com.sparx1126.a2020_scouting;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class ViewData extends AppCompatActivity {
    static private String TAG = "Sparx: ";
    static private String HEADER = "ViewData: ";

    private SharedPreferences settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_data);
        Log.d(TAG, HEADER + "onCreate");

        settings = getSharedPreferences(getString(R.string.SPARX_PREFS), 0);

        BottomNavigationView navView = findViewById(R.id.nav_view_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_view_data_home, R.id.navigation_rankings, R.id.navigation_our_rankings, R.id.navigation_match)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_view);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
    }
}