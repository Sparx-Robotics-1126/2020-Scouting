package com.sparx1126.a2020_scouting;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;


import com.google.android.material.bottomnavigation.BottomNavigationView;

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
    private Map<String, BlueAllianceEvent> events;
    String url ="http://www.thebluealliance.com/api/v3/team/frc1126/events/2019";
    String authKeyHeader="X-TBA-Auth-Key";
    String authKey="4EFyOEdszrGNcCJuibGSr6W92SjET2cfhx2QU9Agxv3LNASra77KcsCEv5GnoSIq";

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

        events = new HashMap<>();
        benchmarking = findViewById(R.id.benchmarking);
        scouting = findViewById(R.id.scouting);
        checklist = findViewById(R.id.checklist);
        view = findViewById(R.id.view);
        Log.d("HELLO", "HEllo");
        bugReport = findViewById(R.id.bugReport);
        color = findViewById(R.id.color);
        admin = findViewById(R.id.color);
        OkHttpClient client = new OkHttpClient();
        Request sohail=new Request.Builder()
                .addHeader(authKeyHeader,authKey)
                .url(url)
                .build();
        client.newCall(sohail).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                 e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.d("HELLO2", "HEllo");
                Log.d("response", response.body().string());
            if(response.isSuccessful()){
                events.clear();
                try{
                    JSONArray array = new JSONArray(response.body().string());
                    for(int i = 0; i < array.length(); i++){
                        JSONObject obj = array.getJSONObject(i);
                        BlueAllianceEvent item = new BlueAllianceEvent(obj);
                        events.put(item.getKey(), item);
                    }

                    for (Map.Entry<String, BlueAllianceEvent> entry : events.entrySet()) {
                        Log.e("obj at", entry.getKey());
                        BlueAllianceEvent tmpEvent = entry.getValue();
                        Log.e("blue alliance", getData(tmpEvent));
                    }

                }catch (JSONException e){
                    e.printStackTrace();
                }

                final String responseBody=response.body().string();
                Log.d("TESTING OK", responseBody);
                MainActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.d("TESTING OK", "reponseBody");
                    }
                });
            }
            }
        });




//        benchmarking.setOnClickListener(new android.view.View.OnClickListener(){
//            @Override
//            public void OnClick(android.view.View v){
//            }
//
//            }
//
//;}
    }

    private String getData(BlueAllianceEvent entry) {
        String str = "";
        str += entry.getKey()+ " ";
        str += entry.getLocation() + " ";
        str += entry.getName() + " ";
        str += entry.getLocation() + " ";
        str += entry.getStartDate() + " ";
        str  = entry.getEndDate() + " ";
        str += entry.getWeek();

        return str;
    }
}
