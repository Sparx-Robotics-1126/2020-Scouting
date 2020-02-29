package com.sparx1126.a2020_scouting;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.sparx1126.a2020_scouting.BlueAllianceData.BlueAllianceMatch;
import com.sparx1126.a2020_scouting.BlueAllianceData.BlueAllianceRank;
import com.sparx1126.a2020_scouting.BlueAllianceData.BlueAllianceTeam;
import com.sparx1126.a2020_scouting.Utilities.BlueAllianceNetwork;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.util.Map;

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
            Log.d("MainActivity: ", "tablet not configured");
            startActivity(new Intent(MainActivity.this, SettingsScreen.class));
        }
    }

    @Override
    public void onRestart(){
        super.onRestart();
        if(!settings.getBoolean(getResources().getString(R.string.tablet_Configured), false)) {
            Log.d("MainActivity: ", "going back to welcome screen");
            finish();
        }
    }

    @Override
    public void onStart(){
        super.onStart();
        String selectedEvent = settings.getString(getResources().getString(R.string.pref_SelectedEvent), "");
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

            blueAlliance.downloadEventMatches(selectedEvent, new BlueAllianceNetwork.Callback() {
                @Override
                public void handleFinishDownload(final String _data) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            BlueAllianceMatch.parseDataToBAMMap(_data);
                            if(ScoutingData.getData().isEmpty()){
                                Map<String, BlueAllianceMatch> matchs = BlueAllianceMatch.getMatches();
                                for(BlueAllianceMatch data : matchs.values()){
                                    ScoutingData emptyData = new ScoutingData(data.getMatchNum());
                                    ScoutingData.getData().put(data.getMatchNum(), emptyData);
                                }
                            }
                        }
                    });
                }
            });
        }
    }

}