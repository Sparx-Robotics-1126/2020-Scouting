package com.sparx1126.a2020_scouting;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Welcome extends AppCompatActivity {

    private EditText emailInput,  passwordInput, teamInput;
    private SharedPreferences loginData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        loginData = getSharedPreferences("Sparx_prefs", 0);

        checkPreferences();

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




    }

    public void checkPreferences(){
        if(!loginData.getString("password", "").isEmpty())
            switchScreen();
    }

    public void login() {
        String email, password, team;
        email = emailInput.getText().toString();
        password = passwordInput.getText().toString();
        team = teamInput.getText().toString();

        SharedPreferences.Editor editor;
        editor = loginData.edit();
        editor.putString("email", email);
        editor.putString("password", password);
        editor.putString("team", team);
        editor.apply();

        Log.i("loginSave", "email: " + loginData.getString("email", "email not found"));
        Log.i("loginSave", "password: " + loginData.getString("password", "password not found"));
        Log.i("loginSave", "team: " + loginData.getString("team", "team number not found"));

        switchScreen();


    }

    public void switchScreen(){
        Log.e("switchScreen", "unknown");
        startActivity(new Intent(Welcome.this, MainActivity.class));
    }


}
