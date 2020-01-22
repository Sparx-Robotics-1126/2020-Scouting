package com.sparx1126.a2020_scouting;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SettingsScreen extends AppCompatActivity {
    private SharedPreferences settings;
    private SharedPreferences.Editor editor;
    private TextView email;
    private TextView teamNum;
    private Button reconfigure;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_screen);

        settings = getSharedPreferences("Sparx_prefs", 0);
        reconfigure = findViewById(R.id.reconfigure);
        email = findViewById(R.id.emailInput);
        teamNum = findViewById(R.id.teamInput);

        reconfigure.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                reconfigure();
            }
        });

        email.setText(settings.getString("email", ""));
        teamNum.setText(settings.getString("team", " "));

        Log.i("email", settings.getString("email", "email not found"));
        Log.i("password", settings.getString("password", "password not found"));
        Log.i("team", settings.getString("team", "team number not found"));
    }

    private void reconfigure(){
        AlertDialog.Builder builder = new AlertDialog.Builder(SettingsScreen.this);
        builder.setTitle("Reconfigure");
        builder.setMessage("Please enter Admin password to continue");
        // Set an EditText view to get user input
        final EditText input = new EditText(this);
        builder.setView(input);

        builder.setPositiveButton("proceed", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int whichButton) {
                String value = input.getText().toString();
                if (!value.equals(settings.getString("password"," "))){
                    Toast.makeText(SettingsScreen.this, "Wrond Password", Toast.LENGTH_LONG).show();
                    input.setText("");
                }else{

                    editor = settings.edit();

                    editor.putString("email", "");
                    editor.putString("password", "");
                    editor.putString("team", "");
                    editor.apply();

                    Intent switchToWelcome = new Intent(SettingsScreen.this, Welcome.class);
                    startActivity(switchToWelcome);
                }


            }
        });
        builder.setNegativeButton("Cancel" ,new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int whichButton) {
                finish();
            }
        });
        builder.create().show();

    }
}
