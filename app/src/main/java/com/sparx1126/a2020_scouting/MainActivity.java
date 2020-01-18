package com.sparx1126.a2020_scouting;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;


import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.sparx1126.a2020_scouting.Utilities.BlueAllianceNetwork;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.OkHttpClient;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    private Button benchmarking;
    private Button scouting;
    private Button checklist;
    private Button view;
    private Button bugReport;
    private Button color;
    private Button admin;
    private BlueAllianceNetwork network = BlueAllianceNetwork.getInstance();
    private Map<String, BlueAllianceEvent> events;
    String url = "http://www.thebluealliance.com/api/v3/team/frc1126/events/2019";
    String authKeyHeader = "X-TBA-Auth-Key";
    String authKey = "4EFyOEdszrGNcCJuibGSr6W92SjET2cfhx2QU9Agxv3LNASra77KcsCEv5GnoSIq";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
        //BLUE ALLIACNE TESTING
        network.downloadEvents(new BlueAllianceNetwork.Callback() {
            @Override
            public void handleFinishDownload(final String _data) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.e("JT" , _data);
                    }
                });

            }
        });






    }

}