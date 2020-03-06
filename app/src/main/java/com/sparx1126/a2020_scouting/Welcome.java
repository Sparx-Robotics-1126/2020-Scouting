package com.sparx1126.a2020_scouting;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.util.Patterns;
import android.widget.Toast;

import com.sparx1126.a2020_scouting.Utilities.*;

public class Welcome extends AppCompatActivity {
    private EditText emailInput, passwordInput, teamInput;
    private CheckBox scoutingCheck;
    private SharedPreferences loginData;
    private boolean toggledBlue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        // This has to be done once
        FileIO.getInstance().intializeStorage(Welcome.this);

        loginData = getSharedPreferences(getString(R.string.SPARX_PREFS), 0);
        toggledBlue = loginData.getBoolean(getString(R.string.pref_BlueAlliance), false);

        emailInput = findViewById(R.id.emailInput);
        passwordInput = findViewById(R.id.passwordInput);
        teamInput = findViewById(R.id.teamInput);
        scoutingCheck = findViewById(R.id.scoutingCheckBox);

        Button login = findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });

        changeUi();
        checkPreferences();
    }

    @Override
    public void onRestart(){
        super.onRestart();
        emailInput.setText("");
        passwordInput.setText("");
        teamInput.setText("");
        scoutingCheck.setChecked(false);
    }

//This change UI may never be actually used since the tablet will always be configured after this screen is seen.
    public  void changeUi(){
        Log.d("Welcome: ", "toggleBlue " + toggledBlue);
        if(toggledBlue){
            LinearLayout li = findViewById(R.id.background);
            li.setBackgroundColor(getResources().getColor(R.color.BBackground));
            //TextView welcome = findViewById(R.id.welcomeMessage);
            TextView scoutingTextView = findViewById(R.id.scoutingTextView);
            CheckBox scouting = findViewById(R.id.scoutingCheckBox);
            scouting.setTextColor(getResources().getColor(R.color.BText));
            //welcome.setTextColor(getResources().getColor(R.color.BText));
            scoutingTextView.setTextColor(getResources().getColor(R.color.BText));
            Button log = findViewById(R.id.login);
            log.setBackgroundColor(getResources().getColor(R.color.BButtonBackground));
            log.setTextColor(getResources().getColor(R.color.BText));
            TextView email = findViewById(R.id.email);
            email.setTextColor(getResources().getColor(R.color.BText));
            TextView password = findViewById(R.id.password);
            password.setTextColor(getResources().getColor(R.color.BText));
            TextView team = findViewById(R.id.team);
            team.setTextColor(getResources().getColor(R.color.BText));
            EditText emailInput = findViewById(R.id.emailInput);
            emailInput.setTextColor(getResources().getColor(R.color.BText));
            EditText passwordInput = findViewById(R.id.passwordInput);
            passwordInput.setTextColor(getResources().getColor(R.color.BText));
            EditText teamInput = findViewById(R.id.teamInput);
            teamInput.setTextColor(getResources().getColor(R.color.BText));
        }else{
            LinearLayout li = findViewById(R.id.background);
            li.setBackgroundColor(getResources().getColor(R.color.RBackground));
            //TextView welcome = findViewById(R.id.welcomeMessage);
            TextView scoutingTextView = findViewById(R.id.scoutingTextView);
            CheckBox scouting = findViewById(R.id.scoutingCheckBox);
            scouting.setTextColor(getResources().getColor(R.color.RText));
            //welcome.setTextColor(getResources().getColor(R.color.RText));
            scoutingTextView.setTextColor(getResources().getColor(R.color.RText));
            Button log = findViewById(R.id.login);
            log.setBackgroundColor(getResources().getColor(R.color.RButtonBackground));
            log.setTextColor(getResources().getColor(R.color.RText));
            TextView email = findViewById(R.id.email);
            email.setTextColor(getResources().getColor(R.color.RText));
            TextView password = findViewById(R.id.password);
            password.setTextColor(getResources().getColor(R.color.RText));
            TextView team = findViewById(R.id.team);
            team.setTextColor(getResources().getColor(R.color.RText));
            EditText emailInput = findViewById(R.id.emailInput);
            emailInput.setTextColor(getResources().getColor(R.color.RText));
            EditText passwordInput = findViewById(R.id.passwordInput);
            passwordInput.setTextColor(getResources().getColor(R.color.RText));
            EditText teamInput = findViewById(R.id.teamInput);
            teamInput.setTextColor(getResources().getColor(R.color.RText));
        }
    }

    public void checkPreferences(){
        String email = loginData.getString(getString(R.string.EMAIL), "");
        String password = loginData.getString(getString(R.string.PASSWORD), "");
        String team = loginData.getString(getString(R.string.TEAM), "");

        // If anything has ever been entered
        if(!email.isEmpty() || !password.isEmpty() || !team.isEmpty()) {
            if (password.isEmpty()) {
                Toast.makeText(Welcome.this, "checkPreferences: No password found", Toast.LENGTH_LONG).show();
            } else if (team.isEmpty()) { // team can only be a number based on xml
                Toast.makeText(Welcome.this, "checkPreferences: No team found", Toast.LENGTH_LONG).show();
            } else if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                Toast.makeText(Welcome.this, "checkPreferences: No email or bad email found (" + email + ")", Toast.LENGTH_LONG).show();
            } else {
                Log.d("Welcome: ", "switching to main");
                BlueAllianceNetwork.getInstance().seteamKey("frc" + team);
                startActivity(new Intent(Welcome.this, MainActivity.class));
            }
        }
        else {
            Log.d("Welcome: ", "found nothing");
        }
    }

    public void login() {
        Log.d("Welcome: ", "login pushed");
        String email, password, team;
        boolean isScouting;
        email = emailInput.getText().toString();
        password = passwordInput.getText().toString();
        team = teamInput.getText().toString();
        isScouting = scoutingCheck.isChecked();

        SharedPreferences.Editor editor;
        editor = loginData.edit();
        editor.putString(getString(R.string.EMAIL), email);
        editor.putString(getString(R.string.PASSWORD), password);
        editor.putString(getString(R.string.TEAM), team);
        editor.putBoolean(getString(R.string.SCOUT),isScouting);
        editor.apply();

        //Temporary
        editor.putString(getString(R.string.EMAIL), "sparx1126scouts@gmail.com");
        editor.putString(getString(R.string.PASSWORD), "h");
        editor.putString(getString(R.string.TEAM), "876"); // participated in Week 1
        editor.putBoolean(getString(R.string.SCOUT), true);
        editor.apply();

        checkPreferences();
    }
}
