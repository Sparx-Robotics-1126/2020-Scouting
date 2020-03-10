package com.sparx1126.a2020_scouting;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
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

import com.sparx1126.a2020_scouting.BlueAllianceData.BlueAllianceMatch;
import com.sparx1126.a2020_scouting.BlueAllianceData.BlueAllianceTeam;
import com.sparx1126.a2020_scouting.Data.OurRankingData;
import com.sparx1126.a2020_scouting.Data.ScoutingData;
import com.sparx1126.a2020_scouting.Utilities.*;
import android.app.AlertDialog;

import org.json.JSONObject;

import java.util.Map;

public class Welcome extends AppCompatActivity {
    static private String TAG = "Sparx: ";
    static private String HEADER = "Welcome: ";

    SharedPreferences settings;
    SharedPreferences.Editor editor;
    private BlueAllianceNetwork blueAlliance;

    private Button loggingButton;
    private EditText emailInput, passwordInput, teamInput;
    private LinearLayout backgroundLayout;
    private TextView emailTextView, passwordTextView, teamTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        Log.d(TAG, HEADER + "onCreate");

        // This has to be done once
        FileIO.getInstance().intializeStorage(Welcome.this);
        settings = getSharedPreferences(getString(R.string.SPARX_PREFS), 0);
        editor = settings.edit();
        blueAlliance = BlueAllianceNetwork.getInstance();

        loggingButton = findViewById(R.id.login);
        loggingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
        emailInput = findViewById(R.id.emailInput);
        passwordInput = findViewById(R.id.passwordInput);
        teamInput = findViewById(R.id.teamInput);
        backgroundLayout = findViewById(R.id.background);
        emailTextView = findViewById(R.id.email);
        passwordTextView = findViewById(R.id.password);
        teamTextView = findViewById(R.id.team);

        changeUi();
        checkPreferences();
    }

    @Override
    public void onRestart() {
        super.onRestart();

        Log.d(TAG, HEADER + "onRestart");

        emailInput.setText("");
        passwordInput.setText("");
        teamInput.setText("");
    }

    //This change UI may never be actually used since the tablet will always be configured after this screen is seen.
    public void changeUi() {
        boolean blueAlliancePref = settings.getBoolean(getString(R.string.pref_BlueAlliance), false);
        Log.d(TAG, HEADER + "toggleBlue " + blueAlliancePref);
        if (blueAlliancePref) {
            backgroundLayout.setBackgroundColor(getResources().getColor(R.color.BBackground));
            loggingButton.setBackgroundColor(getResources().getColor(R.color.BButtonBackground));
            loggingButton.setTextColor(getResources().getColor(R.color.BText));
            emailTextView.setTextColor(getResources().getColor(R.color.BText));
            passwordTextView.setTextColor(getResources().getColor(R.color.BText));
            teamTextView.setTextColor(getResources().getColor(R.color.BText));
            emailInput.setTextColor(getResources().getColor(R.color.BText));
            passwordInput.setTextColor(getResources().getColor(R.color.BText));
            teamInput.setTextColor(getResources().getColor(R.color.BText));
        } else {
            backgroundLayout.setBackgroundColor(getResources().getColor(R.color.RBackground));
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

    public void checkPreferences() {
        String email = settings.getString(getString(R.string.EMAIL), "");
        String password = settings.getString(getString(R.string.PASSWORD), "");
        String team = settings.getString(getString(R.string.TEAM), "");
        boolean isScouting = settings.getBoolean(getString(R.string.SCOUT), false);

        // If anything has ever been entered
        if (!email.isEmpty() || !password.isEmpty() || !team.isEmpty()) {
            if (password.isEmpty()) {
                Log.e("Welcome: ", "checkPreferences: No password found");
                Toast.makeText(Welcome.this, "checkPreferences: No password found", Toast.LENGTH_LONG).show();
            } else if (team.isEmpty()) { // team can only be a number based on xml
                Log.e("Welcome: ", "checkPreferences: No team found");
                Toast.makeText(Welcome.this, "checkPreferences: No team found", Toast.LENGTH_LONG).show();
            } else if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                Log.e("Welcome: ", "checkPreferences: No email or bad email found (" + email + ")");
                Toast.makeText(Welcome.this, "checkPreferences: No email or bad email found (" + email + ")", Toast.LENGTH_LONG).show();
            } else {
                blueAlliance.seteamKey("frc" + team);
                Log.d(TAG, HEADER + "email:" + email);
                Log.d(TAG, HEADER + "password:" + password);
                Log.d(TAG, HEADER + "team:" + team);
                Log.d(TAG, HEADER + "isScouting:" + isScouting);
                boolean isConfigured = settings.getBoolean(getResources().getString(R.string.tablet_Configured), false);
                if(isConfigured) {
                    final String pref_SelectedEvent = settings.getString(getResources().getString(R.string.pref_SelectedEvent), "");
                    downloadAllEventData(pref_SelectedEvent);
                }
                else{
                    Log.d(TAG, HEADER + "switching to home");
                    startActivity(new Intent(Welcome.this, Home.class));
                }
            }
        } else {
            Log.d(TAG, HEADER + "found nothing");
        }
    }

    public void login() {
        Log.d(TAG, HEADER + "login pushed");
        String email = emailInput.getText().toString();
        String password = passwordInput.getText().toString();
        String team = teamInput.getText().toString();

        // added final because I could not access editor inside dialog
        editor.putString(getString(R.string.EMAIL), email);
        editor.putString(getString(R.string.PASSWORD), password);
        editor.putString(getString(R.string.TEAM), team);
        editor.apply();

        //Temporary
        editor.putString(getString(R.string.EMAIL), "sparx1126scouts@gmail.com");
        editor.putString(getString(R.string.PASSWORD), "gosparx!"); // use the real password or you will break email
        if(team.length() == 0) {
            editor.putString(getString(R.string.TEAM), "876"); // participated in Week 1
        }
        editor.apply();

        AlertDialog.Builder builder = new AlertDialog.Builder(Welcome.this);
        builder.setTitle("Scouting Tablet");
        builder.setMessage("Is this tablet going to be used for scouting?");

        builder.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                Log.d(TAG, HEADER + "scouting tablet");
                editor.putBoolean(getString(R.string.SCOUT), true);
                editor.apply();
                dialog.dismiss();
                checkPreferences();
            }
        });
        builder.setPositiveButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                Log.d(TAG, HEADER + "not a scouting tablet");
                editor.putBoolean(getString(R.string.SCOUT), false);
                editor.apply();
                dialog.dismiss();
                checkPreferences();
            }
        });
        Dialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }

    private void downloadAllEventData(final String _selectedEvent) {
        Log.d(TAG, HEADER + "download All Event Data for evnet = " + _selectedEvent);
        blueAlliance.downloadEventTeams(Welcome.this, _selectedEvent, new BlueAllianceNetwork.Callback() {
            @Override
            public void handleFinishDownload(final String _data) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (JsonData.isValidJsonArray(_data)) {
                            Log.d(TAG, HEADER + "event teams downloaded");
                            BlueAllianceTeam.parseJson(_data, _selectedEvent);
                            blueAlliance.downloadEventMatches(Welcome.this, _selectedEvent, new BlueAllianceNetwork.Callback() {
                                @Override
                                public void handleFinishDownload(final String _data) {
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            if (JsonData.isValidJsonArray(_data)) {
                                                Log.d(TAG, HEADER + "downLoaded event matches");
                                                BlueAllianceMatch.parseDataToBAMMap(_data, _selectedEvent);
                                                final boolean scoutingTablet = settings.getBoolean(getString(R.string.SCOUT), false);
                                                if(scoutingTablet) {
                                                    boolean blueAllianceChosen = settings.getBoolean(getResources().getString(R.string.pref_BlueAlliance), false);
                                                    int chosenTeam = settings.getInt(getResources().getString(R.string.pref_TeamPosition), 0);

                                                    String prefColorStr = getResources().getString(R.string.red_alliance);
                                                    if (blueAllianceChosen) {
                                                        prefColorStr = getResources().getString(R.string.blue_alliance);
                                                    }
                                                    String prefPositionStr = getResources().getString(R.string.position_1);
                                                    if (chosenTeam == 2) {
                                                        prefPositionStr = getResources().getString(R.string.position_2);
                                                    } else if (chosenTeam == 3) {
                                                        prefPositionStr = getResources().getString(R.string.position_3);
                                                    }
                                                    ScoutingData.initializeData(blueAllianceChosen, chosenTeam, _selectedEvent, prefColorStr,
                                                            prefPositionStr, BlueAllianceMatch.getMatches(_selectedEvent));
                                                    Log.d(TAG, HEADER + "intialized scouting data " + ScoutingData.getData().size());
                                                }

                                                new GetMail(Welcome.this, settings.getString(getString(R.string.EMAIL), ""),
                                                        settings.getString(getString(R.string.PASSWORD), ""),
                                                        new GetMail.Callback() {
                                                            @Override
                                                            public void handleFinishDownload(Map<String, JSONObject> mails) {
                                                                Log.d(TAG, HEADER + "downloaded emails");
                                                                ScoutingData.parseJsons(mails);
                                                                OurRankingData.parseJsons(_selectedEvent, mails);
                                                                Log.d(TAG, HEADER + "switching to home");
                                                                startActivity(new Intent(Welcome.this, Home.class));
                                                            }
                                                        });
                                            } else {
                                                Log.e(TAG, HEADER + "Internet returned BAD DATA for EventMatches, try another wifi!");
                                                Toast.makeText(Welcome.this, "Internet returned BAD DATA for EventMatches, try another wifi!", Toast.LENGTH_LONG).show();
                                            }
                                        }
                                    });
                                }
                            });
                        } else {
                            Log.e(TAG, HEADER + "Internet returned BAD DATA for EventTeams, try another wifi!");
                            Toast.makeText(Welcome.this, "Internet returned BAD DATA for EventTeams, try another wifi!", Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
    }
}
