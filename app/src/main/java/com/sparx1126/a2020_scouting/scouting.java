package com.sparx1126.a2020_scouting;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.TestLooperManager;
import android.util.Log;
import android.util.Patterns;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import com.sparx1126.a2020_scouting.BlueAllianceData.BlueAllianceMatch;
import com.sparx1126.a2020_scouting.Utilities.SendMail;
import com.sparx1126.a2020_scouting.Utilities.BlueAllianceNetwork;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Set;

public class scouting extends AppCompatActivity {
    private SharedPreferences.Editor editor;
    private Button logout;;
    private TextView name;
    private SharedPreferences settings;
    private Button plusButton;
    private Button minusButton;
    private Button save;
    private AutoCompleteTextView balls;
    private SendMail mail;
    private BlueAllianceNetwork ban;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scouting);

        changeUi();

        save = findViewById(R.id.Save);
        settings = getSharedPreferences("Sparx_prefs", 0);
        logout = findViewById(R.id.logOut);
        name = findViewById(R.id.name);
        plusButton = findViewById(R.id.Plus_balls);
        minusButton = findViewById(R.id.Minus_balls);
        balls = findViewById(R.id.Balls);

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
                                    str  += "\n" + BlueAllianceMatch.getMatches().get(k).toString();
                                }
                                TextView name = findViewById(R.id.name);
                                mail = new SendMail(scouting.this,getResources().getString(R.string.sparx_email), name.toString(), str);
                                mail.execute();
                            }
                        });
                    }
                });
            }
        });

        plusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int value = Integer.parseInt(balls.getText().toString());
                balls.setText(String.valueOf(value + 1));
            }
        });

        minusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int value = Integer.parseInt(balls.getText().toString());
                balls.setText(String.valueOf(value - 1));
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
            TextView text = findViewById(R.id.text);
            text.setTextColor(getResources().getColor(R.color.BText));
            TextView name = findViewById(R.id.name);
            name.setTextColor(getResources().getColor(R.color.BText));
            Button plus = findViewById(R.id.Plus_balls);
            plus.setBackgroundColor(getResources().getColor(R.color.BButtonBackground));
            plus.setTextColor(getResources().getColor(R.color.BText));
            Button minus = findViewById(R.id.Minus_balls);
            minus.setBackgroundColor(getResources().getColor(R.color.BButtonBackground));
            minus.setTextColor(getResources().getColor(R.color.BText));
            TextView scoredBalls = findViewById(R.id.txtBalls);
            scoredBalls.setBackgroundColor(getResources().getColor(R.color.BBackground));
            scoredBalls.setTextColor(getResources().getColor(R.color.BText));
            AutoCompleteTextView numBalls = findViewById(R.id.Balls);
            numBalls.setBackgroundColor(getResources().getColor(R.color.BBackground));
            numBalls.setTextColor(getResources().getColor(R.color.BText));
            Button save = findViewById(R.id.Save);
            save.setBackgroundColor(getResources().getColor(R.color.BButtonBackground));
            save.setTextColor(getResources().getColor(R.color.BText));
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
            Button plus = findViewById(R.id.Plus_balls);
            plus.setBackgroundColor(getResources().getColor(R.color.RButtonBackground));
            plus.setTextColor(getResources().getColor(R.color.RText));
            Button minus = findViewById(R.id.Minus_balls);
            minus.setBackgroundColor(getResources().getColor(R.color.RButtonBackground));
            minus.setTextColor(getResources().getColor(R.color.RText));
            TextView scoredBalls = findViewById(R.id.txtBalls);
            scoredBalls.setBackgroundColor(getResources().getColor(R.color.RBackground));
            scoredBalls.setTextColor(getResources().getColor(R.color.RText));
            AutoCompleteTextView numBalls = findViewById(R.id.Balls);
            numBalls.setBackgroundColor(getResources().getColor(R.color.RBackground));
            numBalls.setTextColor(getResources().getColor(R.color.RText));
            Button save = findViewById(R.id.Save);
            save.setBackgroundColor(getResources().getColor(R.color.RButtonBackground));
            save.setTextColor(getResources().getColor(R.color.RText));
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

