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

import java.io.IOException;

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
            if(response.isSuccessful()){
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
}
