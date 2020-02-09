package com.sparx1126.a2020_scouting;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.util.Patterns;
import android.widget.Toast;

import com.sparx1126.a2020_scouting.Utilities.*;

public class Welcome extends AppCompatActivity {
    //once the tablet has been configured to a team change this value
    public static boolean toggledBlue;

    private EditText emailInput, passwordInput, teamInput;
    private SharedPreferences loginData;
    private FileIO IO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        IO = FileIO.getInstance();
        IO.intializeStorage(Welcome.this);
        loginData = getSharedPreferences(getString(R.string.SPARX_PREFS), 0);

        changeUi();

        emailInput = findViewById(R.id.emailInput);
        passwordInput = findViewById(R.id.passwordInput);
        teamInput = findViewById(R.id.teamInput);

        Button login = findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });

        checkPreferences();
    }

    public  void changeUi(){
        if(toggledBlue == true){
            LinearLayout li = findViewById(R.id.background);
            li.setBackgroundColor(getResources().getColor(R.color.BBackground));
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
        if(loginData.getString("password", "").isEmpty()) {
            Toast.makeText(Welcome.this, "checkPreferences: No password found", Toast.LENGTH_LONG).show();
            Log.e("checkPreferences","No password found");
        } else if(loginData.getString("team", "").isEmpty()) { // team can only be a number based on xml
            Toast.makeText(Welcome.this, "checkPreferences: No team found", Toast.LENGTH_LONG).show();
            Log.e("checkPreferences","No team found");
        } else if(loginData.getString("email", "").isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(loginData.getString("email", "")).matches()) {
            Toast.makeText(Welcome.this, "checkPreferences: No email or bad email found" + loginData.getString("email", ""), Toast.LENGTH_LONG).show();
            Log.e("checkPreferences","No email found");
        } else {
            BlueAllianceNetwork.getInstance().seteamKey("frc" + loginData.getString("team", ""));
            switchScreen();
        }

    }

    public void login() {
        String email, password, team;
        email = emailInput.getText().toString();
        password = passwordInput.getText().toString();
        team = teamInput.getText().toString();

        SharedPreferences.Editor editor;
        editor = loginData.edit();

        editor.putString(getString(R.string.EMAIL), email);
        editor.putString(getString(R.string.PASSWORD), password);
        editor.putString(getString(R.string.TEAM), team);
        editor.apply();

        Log.i("loginSave", "email: " + loginData.getString(getString(R.string.EMAIL), "email not found"));
        Log.i("loginSave", "password: " + loginData.getString(getString(R.string.PASSWORD), "password not found"));
        Log.i("loginSave", "team: " + loginData.getString(getString(R.string.TEAM), "team number not found"));


        checkPreferences();
    }

    public void switchScreen(){
        Log.i("switchScreen", "unknown");
        startActivity(new Intent(Welcome.this, SettingsScreen.class));
    }
}
