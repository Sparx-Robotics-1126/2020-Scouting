package com.sparx1126.a2020_scouting;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.sparx1126.a2020_scouting.BlueAllianceData.BlueAllianceRank;
import com.sparx1126.a2020_scouting.Data.OurRankingData;
import com.sparx1126.a2020_scouting.Data.ScoutingData;
import com.sparx1126.a2020_scouting.Utilities.BlueAllianceNetwork;
import com.sparx1126.a2020_scouting.Utilities.GetMail;
import com.sparx1126.a2020_scouting.Utilities.JsonData;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import org.json.JSONObject;

import java.util.Map;

public class ViewData extends AppCompatActivity {
    static private String TAG = "Sparx: ";
    static private String HEADER = "ViewData: ";

    private SharedPreferences settings;
    private static BlueAllianceNetwork blueAlliance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_data);
        Log.d(TAG, HEADER + "onCreate");

        settings = getSharedPreferences(getString(R.string.SPARX_PREFS), 0);
        blueAlliance = BlueAllianceNetwork.getInstance();

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

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, HEADER + "onStart");
        String selectedEvent = settings.getString(getResources().getString(R.string.pref_SelectedEvent), "");
        blueAlliance.downloadEventRanks(ViewData.this, selectedEvent, new BlueAllianceNetwork.Callback() {
            @Override
            public void handleFinishDownload(final String _data) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (JsonData.isValidJsonObject(_data)) {
                            Log.d(TAG, HEADER + "downLoaded event ranks");
                            BlueAllianceRank.parseJson(_data);
                            new GetMail(ViewData.this, settings.getString(getString(R.string.EMAIL), ""),
                                    settings.getString(getString(R.string.PASSWORD), ""),
                                    new GetMail.Callback() {
                                        @Override
                                        public void handleFinishDownload(Map<String, JSONObject> mails) {
                                            Log.d(TAG, HEADER + "email handleFinishDownload");
                                            String pref_SelectedEvent = settings.getString(getResources().getString(R.string.pref_SelectedEvent), "");
                                            OurRankingData.parseJsons(pref_SelectedEvent, mails);
                                        }
                                    });
                        } else {
                            Log.e(TAG, HEADER + "Internet returned BAD DATA for EventRanks, try another wifi!");
                            Toast.makeText(ViewData.this, "Internet returned BAD DATA for EventRanks, try another wifi!", Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
    }
}