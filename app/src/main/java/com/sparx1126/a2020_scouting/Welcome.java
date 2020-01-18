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
    private Button login;
    private EditText emailInput;
    private EditText passwordInput;
    private EditText teamInput;
    private SharedPreferences loginData;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        needsAName();
        setContentView(R.layout.activity_welcome);

        emailInput = findViewById(R.id.emailInput);
        passwordInput = findViewById(R.id.passwordInput);
        teamInput = findViewById(R.id.teamInput);

        login = findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });




    }

    public void login(){
        String email, password, team;
        email = emailInput.getText().toString();
        password = passwordInput.getText().toString();
        team = teamInput.getText().toString();

        loginData = getSharedPreferences("Sparx_prefs", 0);
        editor = loginData.edit();

        editor.putString("email", email);
        editor.putString("password", password);
        editor.putString("team", team);
        editor.apply();

        Log.i("email", loginData.getString("email", "email not found"));
        Log.i("password", loginData.getString("password", "password not found"));
        Log.i("team", loginData.getString("team", "team number not found"));

        Intent switchToSettings = new Intent(Welcome.this, SettingsScreen.class);
        startActivity(switchToSettings);


    }

    public void needsAName(){

    }

}
