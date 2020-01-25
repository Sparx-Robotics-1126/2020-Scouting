package com.sparx1126.a2020_scouting;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Welcome extends AppCompatActivity {

    private EditText emailInput, passwordInput, teamInput;
    private SharedPreferences loginData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        loginData = getSharedPreferences(getString(R.string.SPARX_PREFS), 0);

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

    public void checkPreferences(){
        // Jaren: Please check all fields: email, password, team
        // I found that for email u can use -> test if not empty and
        // Patterns.EMAIL_ADDRESS.matcher(loginData.getString("email", ""))
        // For the team test -> not empty and Integer.parseInt(loginData.getString("team", ""))
        if(!loginData.getString("password", "").isEmpty()) {
            switchScreen();
        }
        // Jaren: Please add an else with a Toast that says something is wrong...
        // Tell on a dialog exactly what went wrong
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

        // Jaren: I suggest that instead of switching you call checkPreferences
        switchScreen();
    }

    public void switchScreen(){
        Log.i("switchScreen", "unknown");
        startActivity(new Intent(Welcome.this, SettingsScreen.class));
    }
}
