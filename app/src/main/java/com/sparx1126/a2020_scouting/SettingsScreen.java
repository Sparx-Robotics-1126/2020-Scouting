package com.sparx1126.a2020_scouting;

import androidx.appcompat.app.AppCompatActivity;

import com.sparx1126.a2020_scouting.BlueAllianceData.*;
import com.sparx1126.a2020_scouting.Utilities.*;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class SettingsScreen extends AppCompatActivity {
    private SharedPreferences settings;
    private SharedPreferences.Editor editor;
    private static BlueAllianceNetwork blueAlliance;
    private TextView email;
    private TextView teamNum;
    private Button reconfigure;
    private Button configure;
    private Button exit;
    private LinearLayout scoutingTabletLayout;
    private Button alliance;
    private RadioGroup teams;
    private RadioButton team1;
    private RadioButton team2;
    private RadioButton team3;

    private boolean configured;
    private boolean blueAllianceChosen;
    private int chosenTeam;
    private String selectedEvent;

    private Spinner eventSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_screen);

        settings = getSharedPreferences(getString(R.string.SPARX_PREFS), 0);
        editor = settings.edit();

        blueAlliance = BlueAllianceNetwork.getInstance();

        reconfigure = findViewById(R.id.reconfigure);
        reconfigure.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                reconfigure();
            }
        });

        configure = findViewById(R.id.configure);
        configure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                configure();
            }
        });

        eventSpinner = findViewById(R.id.eventSpinner);

        exit = findViewById(R.id.exit);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(settings.getBoolean(getString(R.string.tablet_Configured), false)){
                    finish();
                }else{
                    Toast.makeText(SettingsScreen.this, "You have not configured the tablet, please do so before you leave", Toast.LENGTH_LONG).show();
                }
            }
        });

        scoutingTabletLayout = findViewById(R.id.scoutingTabletLayout);
        if(!settings.getBoolean(getString(R.string.SCOUT), false)) {
            scoutingTabletLayout.setVisibility(View.INVISIBLE);
        }

        alliance = findViewById(R.id.aliiance);
        alliance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(alliance.getText().toString().equals("RED ALLIANCE")){ // use to be red changing to blue
                    blueAllianceChosen = true;
                    changeUi();
                }else if(alliance.getText().toString().equals("BLUE ALLIANCE")){ // use to be blue changing to red
                    blueAllianceChosen = false;
                    changeUi();
                }
            }
        });

        teams = findViewById(R.id.teams);
        team1 = findViewById(R.id.team1);
        team2 = findViewById(R.id.team2);
        team3 = findViewById(R.id.team3);

        email = findViewById(R.id.emailInput);
        email.setText(settings.getString(getString(R.string.EMAIL), "email not found"));

        teamNum = findViewById(R.id.teamInput);
        teamNum.setText(settings.getString(getString(R.string.TEAM), "team number not found"));

        blueAllianceChosen = settings.getBoolean(getString(R.string.pref_BlueAlliance), false);
        configured = settings.getBoolean(getResources().getString(R.string.tablet_Configured), false);
        changeUi();
        downLoadEvents();
        restorePreferences();
    }

    @Override
    public void onBackPressed() {
        if(!configured) {
            Toast.makeText(SettingsScreen.this, "You can't leave until you have configured", Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(SettingsScreen.this, "Press Exit to return with no changes...", Toast.LENGTH_LONG).show();
        }
    }

    private void reconfigure() {
        AlertDialog.Builder builder = new AlertDialog.Builder(SettingsScreen.this);
        builder.setTitle("Reconfigure");
        builder.setMessage("Please enter the Password to continue");
        // Set an EditText view to get user input
        final EditText input = new EditText(this);
        builder.setView(input);

        builder.setPositiveButton("Proceed", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int whichButton) {
                String value = input.getText().toString();
                if (!value.equals(settings.getString(getString(R.string.PASSWORD), ""))) {
                    Toast.makeText(SettingsScreen.this, "Wrong Password", Toast.LENGTH_LONG).show();
                    input.setText("");
                } else {
                    editor.putBoolean(getString(R.string.tablet_Configured), false);
                    editor.putString(getString(R.string.EMAIL), "");
                    editor.putString(getString(R.string.PASSWORD), "");
                    editor.putString(getString(R.string.TEAM), "");
                    editor.putBoolean(getString(R.string.SCOUT), false);
                    editor.apply();
                    Log.d("Settings: ", "reconfiguring");

                    finish();
                }
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }

    private void configure() {
        AlertDialog.Builder builder = new AlertDialog.Builder(SettingsScreen.this);
        builder.setTitle("Configure");
        builder.setMessage("Please enter the Password to continue");
        // Set an EditText view to get user input
        final EditText input = new EditText(this);
        builder.setView(input);

        builder.setPositiveButton("Proceed", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int whichButton) {
                String value = input.getText().toString();
                if (!value.equals(settings.getString(getString(R.string.PASSWORD), ""))) {
                    Toast.makeText(SettingsScreen.this, "Wrong Password", Toast.LENGTH_LONG).show();
                    input.setText("");
                } else {
                    boolean buttonChecked = team1.isChecked() || team2.isChecked() || team3.isChecked();
                    if(!(eventSpinner.getSelectedItem().toString().equals("Select Event")) && buttonChecked) {
                        configured = true;
                        selectedEvent = eventSpinner.getSelectedItem().toString();
                        if (team1.isChecked()) {
                            chosenTeam = 1;
                        } else if (team2.isChecked()) {
                            chosenTeam = 2;
                        } else if (team3.isChecked()) {
                            chosenTeam = 3;
                        }

                        editor.putBoolean(getResources().getString(R.string.tablet_Configured), configured);
                        editor.putBoolean(getResources().getString(R.string.pref_BlueAlliance), blueAllianceChosen);
                        editor.putInt(getResources().getString(R.string.pref_TeamPosition), chosenTeam);
                        editor.putString(getResources().getString(R.string.pref_SelectedEvent), selectedEvent);
                        editor.apply();

                        Log.d("selected event:", selectedEvent);
                        finish();
                    }else{
                        Toast.makeText(SettingsScreen.this, "You didn't change anything",Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }

    public  void changeUi(){
        Log.d("Settings: ", "blueAllianceChosen " + blueAllianceChosen);
        if(blueAllianceChosen){
            LinearLayout li = findViewById(R.id.background);
            li.setBackgroundColor(getResources().getColor(R.color.BBackground));
            Button recon = findViewById(R.id.reconfigure);
            recon.setBackgroundColor(getResources().getColor(R.color.BButtonBackground));
            recon.setTextColor(getResources().getColor(R.color.BText));
            TextView email = findViewById(R.id.email);
            email.setTextColor(getResources().getColor(R.color.BText));
            TextView team = findViewById(R.id.team);
            team.setTextColor(getResources().getColor(R.color.BText));
            TextView emailInput = findViewById(R.id.emailInput);
            emailInput.setTextColor(getResources().getColor(R.color.BText));
            TextView teamInput = findViewById(R.id.teamInput);
            teamInput.setTextColor(getResources().getColor(R.color.BText));
            Spinner events = findViewById(R.id.eventSpinner);
            events.setBackgroundColor(getResources().getColor(R.color.BButtonBackground));
            events.setPopupBackgroundResource(R.color.BButtonBackground);
            Button con = findViewById(R.id.configure);
            con.setBackgroundColor(getResources().getColor(R.color.BButtonBackground));
            con.setTextColor(getResources().getColor(R.color.BText));
            RadioGroup teams = findViewById(R.id.teams);
            teams.setBackgroundColor(getResources().getColor(R.color.BBackground));
            RadioButton t1 = findViewById(R.id.team1);
            t1.setTextColor(getResources().getColor(R.color.BText));
            RadioButton t2 = findViewById(R.id.team2);
            t2.setTextColor(getResources().getColor(R.color.BText));
            RadioButton t3 = findViewById(R.id.team3);
            t3.setTextColor(getResources().getColor(R.color.BText));
            alliance.setText("BLUE ALLIANCE");
            alliance.setBackgroundColor(getResources().getColor(R.color.BButtonBackground));
            alliance.setTextColor(getResources().getColor(R.color.BText));
            exit.setTextColor(getResources().getColor(R.color.BText));
            exit.setBackgroundColor(getResources().getColor(R.color.BButtonBackground));
        }else{
            LinearLayout li = findViewById(R.id.background);
            li.setBackgroundColor(getResources().getColor(R.color.RBackground));
            Button recon = findViewById(R.id.reconfigure);
            recon.setBackgroundColor(getResources().getColor(R.color.RButtonBackground));
            recon.setTextColor(getResources().getColor(R.color.RText));
            TextView email = findViewById(R.id.email);
            email.setTextColor(getResources().getColor(R.color.RText));
            TextView team = findViewById(R.id.team);
            team.setTextColor(getResources().getColor(R.color.RText));
            TextView emailInput = findViewById(R.id.emailInput);
            emailInput.setTextColor(getResources().getColor(R.color.RText));
            TextView teamInput = findViewById(R.id.teamInput);
            teamInput.setTextColor(getResources().getColor(R.color.RText));
            Spinner events = findViewById(R.id.eventSpinner);
            events.setBackgroundColor(getResources().getColor(R.color.RButtonBackground));
            events.setPopupBackgroundResource(R.color.RButtonBackground);
            Button con = findViewById(R.id.configure);
            con.setBackgroundColor(getResources().getColor(R.color.RButtonBackground));
            con.setTextColor(getResources().getColor(R.color.RText));
            RadioGroup teams = findViewById(R.id.teams);
            teams.setBackgroundColor(getResources().getColor(R.color.RBackground));
            RadioButton t1 = findViewById(R.id.team1);
            t1.setTextColor(getResources().getColor(R.color.RText));
            RadioButton t2 = findViewById(R.id.team2);
            t2.setTextColor(getResources().getColor(R.color.RText));
            RadioButton t3 = findViewById(R.id.team3);
            t3.setTextColor(getResources().getColor(R.color.RText));
            alliance.setText("RED ALLIANCE");
            alliance.setBackgroundColor(getResources().getColor(R.color.RButtonBackground));
            alliance.setTextColor(getResources().getColor(R.color.RText));
            exit.setTextColor(getResources().getColor(R.color.RText));
            exit.setBackgroundColor(getResources().getColor(R.color.RButtonBackground));

        }
    }

    private void downLoadEvents() {
        blueAlliance.downloadEvents(new BlueAllianceNetwork.Callback() {
                @Override
                public void handleFinishDownload(final String _data) {
                // this needs to run on the ui thread because of ui components in it
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (isValidJsonArray(_data)) {
                            BlueAllianceEvent.parseJson(_data, settings.getString(getString(R.string.TEAM), ""));
                            setSpinner();
                        } else {
                            Toast.makeText(SettingsScreen.this, "Internet returned BAD DATA for Events, try another wifi!", Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
    }

    private void setSpinner() {
        String pref_SelectedEvent = settings.getString(getResources().getString(R.string.pref_SelectedEvent), "");
        List<String> eventSpinnerList = new ArrayList<>();

        if (pref_SelectedEvent.isEmpty()) {
            Log.d("Settings: ", "no preferredEvent ");
            eventSpinnerList.add(getResources().getString(R.string.selectEvent));
            eventSpinnerList.addAll(BlueAllianceEvent.getEvents(settings.getString(getString(R.string.TEAM), "")).keySet());
        } else {
            Log.d("Settings: ", "pref_SelectedEvent " + pref_SelectedEvent);
            eventSpinnerList.add(pref_SelectedEvent);
            for (String eventName : BlueAllianceEvent.getEvents(settings.getString(getString(R.string.TEAM), "")).keySet()) {
                if (!eventName.equals(pref_SelectedEvent)) {
                    eventSpinnerList.add(eventName);
                }
            }
        }
        SpinnerAdapter eventAdapter = new ArrayAdapter<>(SettingsScreen.this, R.layout.color_spinner_layout, eventSpinnerList);
        eventSpinner.setAdapter(eventAdapter);
    }

    private boolean isValidJsonArray(String _data) {
        try {
            new JSONArray(_data);
        } catch (JSONException jsExcp) {
            return false;
        }
        return true;
    }

    private void restorePreferences() {
        boolean scoutingTablet = settings.getBoolean(getResources().getString(R.string.SCOUT), false);
        if(scoutingTablet){
            Log.d("Settings: ", "restorePreferences scoutingTablet");
            boolean blueAllianceToggled = settings.getBoolean(getResources().getString(R.string.pref_BlueAlliance), false);
            if (blueAllianceToggled && !alliance.getText().equals("BLUE ALLIANCE")) {
                alliance.performClick();
            }
            int teamPositionNum = settings.getInt(getResources().getString(R.string.pref_TeamPosition), 0);
            if (teamPositionNum == 1) {
                team1.setChecked(true);
            } else if (teamPositionNum == 2) {
                team2.setChecked(true);
            } else if (teamPositionNum == 3) {
                team3.setChecked(true);
            }
        }
    }
}
