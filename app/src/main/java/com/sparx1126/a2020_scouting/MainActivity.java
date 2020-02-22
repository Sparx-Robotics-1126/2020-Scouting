package com.sparx1126.a2020_scouting;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.sparx1126.a2020_scouting.BlueAllianceData.BlueAllianceRank;
import com.sparx1126.a2020_scouting.BlueAllianceData.BlueAllianceTeam;
import com.sparx1126.a2020_scouting.Utilities.BlueAllianceNetwork;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends AppCompatActivity {
    private SharedPreferences settings;
    private static BlueAllianceNetwork blueAlliance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        settings = getSharedPreferences(getString(R.string.SPARX_PREFS), 0);
        blueAlliance = BlueAllianceNetwork.getInstance();

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_rankings,R.id.navigation_match, R.id.navigation_settings, R.id.navigation_scoutings)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        boolean tabletConfigured = settings.getBoolean(getResources().getString(R.string.tablet_Configured), false);
        if(!tabletConfigured) {
            startActivity(new Intent(MainActivity.this, SettingsScreen.class));
        }
    }

    @Override
    public void onRestart(){
        super.onRestart();
        //Log.e("resumimg main", "mian");
        String selectedEvent = settings.getString(getResources().getString(R.string.pref_SelectedEvent), "");
        //Log.e("selected event", selectedEvent);
        if(!selectedEvent.isEmpty()) {
            blueAlliance.downloadEventRanks(selectedEvent, new BlueAllianceNetwork.Callback() {
                @Override
                public void handleFinishDownload(final String _data) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            BlueAllianceRank.parseJson(_data);
                        }
                    });
                }
            });

            blueAlliance.downloadEventTeams(selectedEvent, new BlueAllianceNetwork.Callback() {
                @Override
                public void handleFinishDownload(final String _data) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            BlueAllianceTeam.parseJson(_data);
                        }
                    });
                }
            });
        }
        else {
           // Log.e("finishing" ," main");
            finish();
        }
    }

}