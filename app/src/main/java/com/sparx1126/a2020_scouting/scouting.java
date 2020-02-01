package com.sparx1126.a2020_scouting;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.TestLooperManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Toast;

public class scouting extends AppCompatActivity {
    private SharedPreferences.Editor editor;
    private Button logout;;
    private TextView name;
    private SharedPreferences settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scouting);

        changeUi();

        settings = getSharedPreferences("Sparx_prefs", 0);
        logout = findViewById(R.id.logOut);
        name = findViewById(R.id.name);

        logout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(scouting.this);
                builder.setTitle("Log Out");
                builder.setMessage("Are you sure you want to log out");

                builder.setPositiveButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(scouting.this ,scouting.class));
                    }
                });

                builder.setNegativeButton("log out", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        login();
                    }
                });
                builder.create().show();
            }
        });

        if(!settings.getString("email", "email not found").equals("email not found")){
            name.setText(settings.getString("email", "email ont found"));
            name.setTextSize(30f);
        }else{
            login();
        }
    }

    public  void changeUi(){
        if(Welcome.toggledBlue){
            LinearLayout li = (LinearLayout)findViewById(R.id.background);
           li.setBackgroundColor(getResources().getColor(R.color.BBackground));
           Button logOut = findViewById(R.id.logOut);
           logOut.setBackgroundColor(getResources().getColor(R.color.BButtonBackground));
           logOut.setTextColor(getResources().getColor(R.color.BText));
           TextView text = findViewById(R.id.text);
           text.setTextColor(getResources().getColor(R.color.BText));
           TextView name = findViewById(R.id.name);
           name.setTextColor(getResources().getColor(R.color.BText));
        }else{
            LinearLayout li = (LinearLayout)findViewById(R.id.background);
            li.setBackgroundColor(getResources().getColor(R.color.RBackground));
            Button logOut = findViewById(R.id.logOut);
            logOut.setBackgroundColor(getResources().getColor(R.color.RButtonBackground));
            logOut.setTextColor(getResources().getColor(R.color.RText));
            TextView text = findViewById(R.id.text);
            text.setTextColor(getResources().getColor(R.color.RText));
            TextView name = findViewById(R.id.name);
            name.setTextColor(getResources().getColor(R.color.RText));
        }
    }

    private void login(){
        AlertDialog.Builder builder = new AlertDialog.Builder(scouting.this);
        builder.setTitle("Login");
        builder.setMessage("Please log in to continue");
        // Set an EditText view to get user input
        final EditText input = new EditText(this);
        builder.setView(input);

        builder.setPositiveButton("login", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int whichButton) {
                String value = input.getText().toString();
                name.setText(value);

            }
        });
        builder.setNegativeButton("Cancel" ,new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int whichButton) {
                startActivity(new Intent(scouting.this, Welcome.class));
            }
        });
        builder.create().show();

    }
    }

