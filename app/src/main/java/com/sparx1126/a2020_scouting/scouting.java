package com.sparx1126.a2020_scouting;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.sparx1126.a2020_scouting.BlueAllianceData.BlueAllianceMatch;
import com.sparx1126.a2020_scouting.Utilities.SendMail;
import com.sparx1126.a2020_scouting.Utilities.BlueAllianceNetwork;


import android.view.View;

import java.util.Set;

public class scouting extends AppCompatActivity {
    private SharedPreferences.Editor editor;
    private Button logout;
    private TextView name;
    private SharedPreferences settings;
    private Button plusBalls;
    private Button minusBalls;
    private Button plusMatch;
    private Button minusMatch;
    private TextView match;
    private Button save;
    private AutoCompleteTextView balls;
    private SendMail mail;
    private BlueAllianceNetwork ban;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scouting);

        changeUi();
        login();

        save = findViewById(R.id.Save);
        settings = getSharedPreferences("Sparx_prefs", 0);
        logout = findViewById(R.id.logOut);
        name = findViewById(R.id.name);
//        plusBalls = findViewById(R.id.Plus_balls);
//        minusBalls = findViewById(R.id.Minus_balls);
//        balls = findViewById(R.id.Balls);
        match = findViewById(R.id.match_num);
        plusMatch = findViewById(R.id.Plus_match);
        minusMatch = findViewById(R.id.Minus_match);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ban = BlueAllianceNetwork.getInstance();
                ban.downloadEventMatches("2019ohcl", new BlueAllianceNetwork.Callback() {
                    @Override
                    public void handleFinishDownload(final String _data) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                BlueAllianceMatch.parseDataToBAMMap(_data);
                                Set<String> keys = BlueAllianceMatch.getMatches().keySet();
                                String str = "";
                                for(String k: keys){
                                    if(k.equals(match.getText().toString())) {
                                        str += "\n" + BlueAllianceMatch.getMatches().get(k).toString();
                                    }
                                }
                                TextView name = findViewById(R.id.name);
                                mail = new SendMail(scouting.this,getResources().getString(R.string.sparx_email), (String)name.getText(), str);
                                mail.execute();
                            }
                        });
                    }
                });
            }
        });

//        plusBalls.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int value = Integer.parseInt(balls.getText().toString());
//                balls.setText(String.valueOf(value + 1));
//            }
//        });
//
//        minusBalls.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int value = Integer.parseInt(match.getText().toString());
//                balls.setText(String.valueOf(value - 1));
//            }
//        });

        plusMatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int value = Integer.parseInt(match.getText().toString());
                match.setText(String.valueOf(value + 1));
            }
        });

        minusMatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int value = Integer.parseInt(balls.getText().toString());
                match.setText(String.valueOf(value - 1));
            }
        });




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
            TextView text = findViewById(R.id.txtname);
            text.setTextColor(getResources().getColor(R.color.BText));
            TextView name = findViewById(R.id.name);
            name.setTextColor(getResources().getColor(R.color.BText));
//            Button plus = findViewById(R.id.Plus_balls);
//            plus.setBackgroundColor(getResources().getColor(R.color.BButtonBackground));
//            plus.setTextColor(getResources().getColor(R.color.BText));
//            Button minus = findViewById(R.id.Minus_balls);
//            minus.setBackgroundColor(getResources().getColor(R.color.BButtonBackground));
//            minus.setTextColor(getResources().getColor(R.color.BText));
//            TextView scoredBalls = findViewById(R.id.txtBalls);
//            scoredBalls.setBackgroundColor(getResources().getColor(R.color.BBackground));
//            scoredBalls.setTextColor(getResources().getColor(R.color.BText));
//            AutoCompleteTextView numBalls = findViewById(R.id.Balls);
//            numBalls.setBackgroundColor(getResources().getColor(R.color.BBackground));
//            numBalls.setTextColor(getResources().getColor(R.color.BText));
            Button save = findViewById(R.id.Save);
            save.setBackgroundColor(getResources().getColor(R.color.BButtonBackground));
            save.setTextColor(getResources().getColor(R.color.BText));
            TextView txtnums = findViewById(R.id.match_num);
            txtnums.setBackgroundColor(getResources().getColor(R.color.BBackground));
            txtnums.setTextColor(getResources().getColor(R.color.BText));
            Button plusmatch = findViewById(R.id.Plus_match);
            plusmatch.setBackgroundColor(getResources().getColor(R.color.BButtonBackground));
            plusmatch.setTextColor(getResources().getColor(R.color.BText));
            Button minusmatch = findViewById(R.id.Minus_match);
            minusmatch.setBackgroundColor(getResources().getColor(R.color.BButtonBackground));
            minusmatch.setTextColor(getResources().getColor(R.color.BText));
            TextView txtmatch = findViewById(R.id.txtMatch);
            txtmatch.setBackgroundColor(getResources().getColor(R.color.BBackground));
            txtmatch.setTextColor(getResources().getColor(R.color.BText));
        }else{
            ScrollView li = (ScrollView) findViewById(R.id.background);
            li.setBackgroundColor(getResources().getColor(R.color.RBackground));
            Button logOut = findViewById(R.id.logOut);
            logOut.setBackgroundColor(getResources().getColor(R.color.RButtonBackground));
            logOut.setTextColor(getResources().getColor(R.color.RText));
            TextView text = findViewById(R.id.txtname);
            text.setTextColor(getResources().getColor(R.color.RText));
            TextView name = findViewById(R.id.name);
            name.setTextColor(getResources().getColor(R.color.RText));
//            Button plus = findViewById(R.id.Plus_balls);
//            plus.setBackgroundColor(getResources().getColor(R.color.RButtonBackground));
//            plus.setTextColor(getResources().getColor(R.color.RText));
//            Button minus = findViewById(R.id.Minus_balls);
//            minus.setBackgroundColor(getResources().getColor(R.color.RButtonBackground));
//            minus.setTextColor(getResources().getColor(R.color.RText));
//            TextView scoredBalls = findViewById(R.id.txtBalls);
//            scoredBalls.setBackgroundColor(getResources().getColor(R.color.RBackground));
//            scoredBalls.setTextColor(getResources().getColor(R.color.RText));
//            AutoCompleteTextView numBalls = findViewById(R.id.Balls);
//            numBalls.setBackgroundColor(getResources().getColor(R.color.RBackground));
//            numBalls.setTextColor(getResources().getColor(R.color.RText));
            Button save = findViewById(R.id.Save);
            save.setBackgroundColor(getResources().getColor(R.color.RButtonBackground));
            save.setTextColor(getResources().getColor(R.color.RText));
            TextView txtnums = findViewById(R.id.match_num);
            txtnums.setBackgroundColor(getResources().getColor(R.color.RBackground));
            txtnums.setTextColor(getResources().getColor(R.color.RText));
            Button plusmatch = findViewById(R.id.Plus_match);
            plusmatch.setBackgroundColor(getResources().getColor(R.color.RButtonBackground));
            plusmatch.setTextColor(getResources().getColor(R.color.RText));
            Button minusmatch = findViewById(R.id.Minus_match);
            minusmatch.setBackgroundColor(getResources().getColor(R.color.RButtonBackground));
            minusmatch.setTextColor(getResources().getColor(R.color.RText));
            TextView txtmatch = findViewById(R.id.txtMatch);
            txtmatch.setBackgroundColor(getResources().getColor(R.color.RBackground));
            txtmatch.setTextColor(getResources().getColor(R.color.RText));
            TextView assignment = findViewById(R.id.assignment);
            assignment.setBackgroundColor(getResources().getColor(R.color.RBackground));
            assignment.setTextColor(getResources().getColor(R.color.RText));
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

