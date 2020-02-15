package com.sparx1126.a2020_scouting;

import android.os.Bundle;
import android.util.Log;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.sparx1126.a2020_scouting.BlueAllianceData.BlueAllianceEvent;
import com.sparx1126.a2020_scouting.Utilities.BlueAllianceNetwork;
import com.sparx1126.a2020_scouting.Utilities.SendMail;
import com.sparx1126.a2020_scouting.Utilities.GetMail;
import com.sparx1126.a2020_scouting.Utilities.*;

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
import java.util.Set;

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
                R.id.navigation_home, R.id.navigation_rankings,R.id.navigation_match, R.id.navigation_settings)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);


        // To be removed: SendMail Testing
        SendMail sm = new SendMail(this, "sparx1126scouts@gmail.com", "jt", "WE GOT THIS!");
        //Executing sendmail to send email
        //sm.execute();

        // To be removed: SendMail Testing
        GetMail gm = new GetMail(this);
        //Executing sendmail to send email
        //gm.execute();
    }
}