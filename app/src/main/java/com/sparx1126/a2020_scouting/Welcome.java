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
    static String TAG = "Sparx: ";
    static String HEADER = "Welcome: ";

    private SharedPreferences settings;

    private Button loggingButton;
    private CheckBox scoutingCheckBox;
    private EditText emailInput, passwordInput, teamInput;
    private LinearLayout backgroundLayout;
    private TextView scoutingTextView, emailTextView, passwordTextView, teamTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        Log.d(TAG, HEADER + "onCreate");

        // This has to be done once
        FileIO.getInstance().intializeStorage(Welcome.this);

        settings = getSharedPreferences(getString(R.string.SPARX_PREFS), 0);

        loggingButton = findViewById(R.id.login);
        loggingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
        scoutingCheckBox = findViewById(R.id.scoutingCheckBox);
        emailInput = findViewById(R.id.emailInput);
        passwordInput = findViewById(R.id.passwordInput);
        teamInput = findViewById(R.id.teamInput);
        backgroundLayout = findViewById(R.id.background);
        scoutingTextView = findViewById(R.id.scoutingTextView);
        emailTextView = findViewById(R.id.email);
        passwordTextView = findViewById(R.id.password);
        teamTextView = findViewById(R.id.team);

        changeUi();
        checkPreferences();
    }

    @Override
    public void onRestart(){
        super.onRestart();

        Log.d(TAG, HEADER + "onRestart");

        emailInput.setText("");
        passwordInput.setText("");
        teamInput.setText("");
        scoutingCheckBox.setChecked(false);
    }

//This change UI may never be actually used since the tablet will always be configured after this screen is seen.
    public  void changeUi(){
        boolean blueAlliancePref = settings.getBoolean(getString(R.string.pref_BlueAlliance), false);
        Log.d(TAG, HEADER + "toggleBlue " + blueAlliancePref);
        if(blueAlliancePref){
            backgroundLayout.setBackgroundColor(getResources().getColor(R.color.BBackground));
            scoutingCheckBox.setTextColor(getResources().getColor(R.color.BText));
            scoutingTextView.setTextColor(getResources().getColor(R.color.BText));
            loggingButton.setBackgroundColor(getResources().getColor(R.color.BButtonBackground));
            loggingButton.setTextColor(getResources().getColor(R.color.BText));
            emailTextView.setTextColor(getResources().getColor(R.color.BText));
            passwordTextView.setTextColor(getResources().getColor(R.color.BText));
            teamTextView.setTextColor(getResources().getColor(R.color.BText));
            emailInput.setTextColor(getResources().getColor(R.color.BText));
            passwordInput.setTextColor(getResources().getColor(R.color.BText));
            teamInput.setTextColor(getResources().getColor(R.color.BText));
        }else{
            backgroundLayout.setBackgroundColor(getResources().getColor(R.color.RBackground));
            scoutingCheckBox.setTextColor(getResources().getColor(R.color.RText));
            scoutingTextView.setTextColor(getResources().getColor(R.color.RText));
            loggingButton.setBackgroundColor(getResources().getColor(R.color.RButtonBackground));
            loggingButton.setTextColor(getResources().getColor(R.color.RText));
            emailTextView.setTextColor(getResources().getColor(R.color.RText));
            passwordTextView.setTextColor(getResources().getColor(R.color.RText));
            teamTextView.setTextColor(getResources().getColor(R.color.RText));
            emailInput.setTextColor(getResources().getColor(R.color.RText));
            passwordInput.setTextColor(getResources().getColor(R.color.RText));
            teamInput.setTextColor(getResources().getColor(R.color.RText));
        }
    }

    public void checkPreferences(){
        String email = settings.getString(getString(R.string.EMAIL), "");
        String password = settings.getString(getString(R.string.PASSWORD), "");
        String team = settings.getString(getString(R.string.TEAM), "");

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
                startActivity(new Intent(Welcome.this, Home.class));
            }
        }
        else {
            Log.d(TAG, HEADER + "found nothing");
        }
    }

    public void login() {
        Log.d(TAG, HEADER + "login pushed");
        String email = emailInput.getText().toString();
        String password = passwordInput.getText().toString();
        String team = teamInput.getText().toString();
        boolean isScouting = scoutingCheckBox.isChecked();

        SharedPreferences.Editor editor;
        editor = settings.edit();
        editor.putString(getString(R.string.EMAIL), email);
        editor.putString(getString(R.string.PASSWORD), password);
        editor.putString(getString(R.string.TEAM), team);
        editor.putBoolean(getString(R.string.SCOUT),isScouting);
        editor.apply();

        //Temporary
        editor.putString(getString(R.string.EMAIL), "sparx1126scouts@gmail.com");
        editor.putString(getString(R.string.PASSWORD), "gosparx!"); // use the real password or you will break email
        editor.putString(getString(R.string.TEAM), "876"); // participated in Week 1
        editor.putBoolean(getString(R.string.SCOUT), true);
        editor.apply();

        checkPreferences();
    }
}
