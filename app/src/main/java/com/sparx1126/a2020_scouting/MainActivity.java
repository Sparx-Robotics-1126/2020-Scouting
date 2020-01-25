package com.sparx1126.a2020_scouting;

import android.os.Bundle;
import android.util.Log;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.sparx1126.a2020_scouting.Utilities.BlueAllianceEvent;
import com.sparx1126.a2020_scouting.Utilities.BlueAllianceNetwork;
import com.sparx1126.a2020_scouting.Utilities.SendMail;
import com.sparx1126.a2020_scouting.Utilities.GetMail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private BlueAllianceNetwork network = BlueAllianceNetwork.getInstance();
    private Map<String, BlueAllianceEvent> events;

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
        //SendMail Testing
        SendMail sm = new SendMail(this, "sparx1126scouts@gmail.com", "jt", "WE GOT THIS!");

        //Executing sendmail to send email
        //sm.execute();

        GetMail gm = new GetMail(this);
        //Executing sendmail to send email
        //gm.execute();

        //BLUE ALLIACNE TESTING
        events = new HashMap<>();
        network.downloadEvents(new BlueAllianceNetwork.Callback() {
            @Override
            public void handleFinishDownload(final String _data) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.e("JT", _data);
                        if (_data.length() > 0) {
                            events.clear();
                            try {
                                JSONArray array = new JSONArray(_data);
                                for (int i = 0; i < array.length(); i++) {
                                    JSONObject obj = array.getJSONObject(i);
                                    BlueAllianceEvent item = new BlueAllianceEvent(obj);
                                    events.put(item.getKey(), item);
                                }

                                for (Map.Entry<String, BlueAllianceEvent> entry : events.entrySet()) {
                                    Log.e("obj at", entry.getKey());
                                    final BlueAllianceEvent tmpEvent = entry.getValue();
                                    Log.e("key", tmpEvent.getKey());
                                    Log.e("location", tmpEvent.getLocation());
                                    Log.e("name", tmpEvent.getName());
                                    Log.e("start date", tmpEvent.getStartDate());
                                    Log.e("end date", tmpEvent.getEndDate());
                                    Log.e("week", tmpEvent.getWeek());
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
            }

        });
    }
}