package com.sparx1126.a2020_scouting;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.sparx1126.a2020_scouting.BlueAllianceData.BlueAllianceMatch;
import com.sparx1126.a2020_scouting.BlueAllianceData.BlueAllianceRank;
import com.sparx1126.a2020_scouting.BlueAllianceData.BlueAllianceTeam;
import com.sparx1126.a2020_scouting.Data.OurRankingData;
import com.sparx1126.a2020_scouting.Utilities.BlueAllianceNetwork;
import com.sparx1126.a2020_scouting.Utilities.GetMail;
import com.sparx1126.a2020_scouting.Data.ScoutingData;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

public class Home extends AppCompatActivity {
    static private String TAG = "Sparx: ";
    static private String HEADER = "Home: ";

    private SharedPreferences settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, HEADER + "onCreate");

        settings = getSharedPreferences(getString(R.string.SPARX_PREFS), 0);

        if(settings.getBoolean(getString(R.string.SCOUT), false)) {
            setContentView(R.layout.activity_home);
        }
        else {
            setContentView(R.layout.activity_home_no_scout);
        }

        if(settings.getBoolean(getString(R.string.SCOUT), false)) {
            BottomNavigationView navView = findViewById(R.id.nav_view);
            // Passing each menu ID as a set of Ids because each
            // menu should be considered as top level destinations.
            AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                    R.id.navigation_home, R.id.navigation_view_data, R.id.navigation_scouting, R.id.navigation_benchmarking, R.id.navigation_settings)
                    .build();
            NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
            NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
            NavigationUI.setupWithNavController(navView, navController);
        }
        else {
            BottomNavigationView navView = findViewById(R.id.nav_view_no_scout);
            // Passing each menu ID as a set of Ids because each
            // menu should be considered as top level destinations.
            AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                    R.id.navigation_home, R.id.navigation_view_data, R.id.navigation_settings)
                    .build();
            NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_no_scout);
            NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
            NavigationUI.setupWithNavController(navView, navController);
        }
    }

    @Override
    public void onBackPressed() {
        Log.e(TAG, HEADER + "Reconfigure in Settings to go back to logging screen...");
        Toast.makeText(Home.this, "Reconfigure in Settings to go back to logging screen...", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onRestart() {
        super.onRestart();
        Log.d(TAG, HEADER + "onRestart");
        if (!settings.getBoolean(getResources().getString(R.string.tablet_Configured), false)) {
            Log.d(TAG, HEADER + "going back to welcome screen");
            finish();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, HEADER + "onStart");

        if (!settings.getBoolean(getResources().getString(R.string.tablet_Configured), false)) {
            Log.d(TAG, HEADER + "tablet not configured");
            Log.d(TAG, HEADER + "switching to settings");
            startActivity(new Intent(Home.this, Settings.class));
        }
    }
}