package com.sparx1126.a2020_scouting;

import androidx.appcompat.app.AppCompatActivity;

import com.sparx1126.a2020_scouting.BlueAllianceData.*;
import com.sparx1126.a2020_scouting.Data.ScoutingData;
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

public class Settings extends AppCompatActivity {
    static private String TAG = "Sparx: ";
    static private String HEADER = "Settings: ";

    private SharedPreferences settings;
    private SharedPreferences.Editor editor;
    private BlueAllianceNetwork blueAlliance;

    private Button reconfigureButton, configureButton, exitButton, allianceButton;
    private LinearLayout backgroundLayout;
    private RadioButton team1RadioButton, team2RadioButton, team3RadioButton;
    RadioGroup teamsRadioGroup;
    private Spinner eventSpinner;
    private TextView emailInput, teamNumInput, emailTextView, teamNumTextView;

    boolean blueAllianceChosen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Log.d(TAG, HEADER + "onCreate");

        settings = getSharedPreferences(getString(R.string.SPARX_PREFS), 0);
        editor = settings.edit();
        blueAlliance = BlueAllianceNetwork.getInstance();

        reconfigureButton = findViewById(R.id.reconfigure);
        reconfigureButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                reconfigure();
            }
        });

        configureButton = findViewById(R.id.configure);
        configureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                configure();
            }
        });

        exitButton = findViewById(R.id.exit);
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(settings.getBoolean(getString(R.string.tablet_Configured), false)){
                    Log.d(TAG, HEADER + "exited");
                    finish();
                }else{
                    Toast.makeText(Settings.this, "You have not configured the tablet, please do so before you leave", Toast.LENGTH_LONG).show();
                }
            }
        });

        allianceButton = findViewById(R.id.alliance);
        allianceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(allianceButton.getText().toString().equals(getString(R.string.red_alliance))){ // use to be red changing to blue
                    Log.d(TAG, HEADER + "Blue Alliance Chosen");
                    blueAllianceChosen = true;
                    changeUi();
                }else if(allianceButton.getText().toString().equals(getString(R.string.blue_alliance))){ // use to be blue changing to red
                    Log.d(TAG, HEADER + "Red Alliance Chosen");
                    blueAllianceChosen = false;
                    changeUi();
                }
            }
        });

        LinearLayout scoutingTabletLayout = findViewById(R.id.scoutingTabletLayout);
        if(!settings.getBoolean(getString(R.string.SCOUT), false)) {
            Log.d(TAG, HEADER + "not scouting tablet");
            scoutingTabletLayout.setVisibility(View.INVISIBLE);
        }

        backgroundLayout = findViewById(R.id.background);

        team1RadioButton = findViewById(R.id.team1);
        team2RadioButton = findViewById(R.id.team2);
        team3RadioButton = findViewById(R.id.team3);
        teamsRadioGroup = findViewById(R.id.teams);

        eventSpinner = findViewById(R.id.eventSpinner);

        emailInput = findViewById(R.id.emailInput);
        emailInput.setText(settings.getString(getString(R.string.EMAIL), ""));
        teamNumInput = findViewById(R.id.teamInput);
        teamNumInput.setText(settings.getString(getString(R.string.TEAM), ""));
        emailTextView = findViewById(R.id.email);
        teamNumTextView = findViewById(R.id.team);

        blueAllianceChosen = settings.getBoolean(getString(R.string.pref_BlueAlliance), false);
        changeUi();
        downLoadEvents();
        restorePreferences();
    }

    @Override
    public void onBackPressed() {
        if(!settings.getBoolean(getResources().getString(R.string.tablet_Configured), false)) {
            Toast.makeText(Settings.this, "You can't leave until you have configured", Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(Settings.this, "Press Exit to return with no changes...", Toast.LENGTH_LONG).show();
        }
    }

    private void reconfigure() {
        AlertDialog.Builder builder = new AlertDialog.Builder(Settings.this);
        builder.setTitle("Reconfigure");
        builder.setMessage("Please enter the Password to continue. ALL DATA WILL BE LOST!");
        // Set an EditText view to get user input
        final EditText input = new EditText(this);
        builder.setView(input);

        builder.setPositiveButton("Proceed", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int whichButton) {
                String value = input.getText().toString();
                if (!value.equals(settings.getString(getString(R.string.PASSWORD), ""))) {
                    Toast.makeText(Settings.this, "Wrong Password", Toast.LENGTH_LONG).show();
                    input.setText("");
                } else {
                    // The collected Scouting Data needs to be reset because the configuration changed
                    ScoutingData.getData().clear();
                    editor.putBoolean(getString(R.string.tablet_Configured), false);
                    editor.putString(getString(R.string.EMAIL), "");
                    editor.putString(getString(R.string.PASSWORD), "");
                    editor.putString(getString(R.string.TEAM), "");
                    editor.putBoolean(getString(R.string.SCOUT), false);
                    editor.apply();
                    Log.d(TAG, HEADER + "reconfigured");
                    finish();
                }
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                Log.d(TAG, HEADER + "reconfigure cancelled");
                dialog.dismiss();
            }
        });
        builder.create().show();
    }

    private void configure() {
        AlertDialog.Builder builder = new AlertDialog.Builder(Settings.this);
        builder.setTitle("Configure");
        builder.setMessage("Please enter the Password to continue. ALL DATA WILL BE LOST!");
        // Set an EditText view to get user input
        final EditText input = new EditText(this);
        builder.setView(input);

        builder.setPositiveButton("Proceed", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int whichButton) {
                String value = input.getText().toString();
                if (!value.equals(settings.getString(getString(R.string.PASSWORD), ""))) {
                    Toast.makeText(Settings.this, "Wrong Password", Toast.LENGTH_LONG).show();
                    input.setText("");
                } else {
                    if(!(eventSpinner.getSelectedItem().toString().equals("Select Event")) && (teamsRadioGroup.getCheckedRadioButtonId() != -1)) {
                        String selectedEvent = eventSpinner.getSelectedItem().toString();
                        int chosenTeam = 0;
                        if (team1RadioButton.isChecked()) {
                            chosenTeam = 1;
                        } else if (team2RadioButton.isChecked()) {
                            chosenTeam = 2;
                        } else if (team3RadioButton.isChecked()) {
                            chosenTeam = 3;
                        }

                        // The collected Scouting Data needs to be reset because the configuration changed
                        ScoutingData.getData().clear();
                        editor.putBoolean(getResources().getString(R.string.tablet_Configured), true);
                        editor.putBoolean(getResources().getString(R.string.pref_BlueAlliance), blueAllianceChosen);
                        editor.putInt(getResources().getString(R.string.pref_TeamPosition), chosenTeam);
                        editor.putString(getResources().getString(R.string.pref_SelectedEvent), selectedEvent);
                        editor.apply();
                        Log.d(TAG, HEADER + "configured");
                        finish();
                    }else{
                        Toast.makeText(Settings.this, "You didn't change anything",Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                Log.d(TAG, HEADER + "configure cancelled");
                dialog.dismiss();
            }
        });
        builder.create().show();
    }

    public  void changeUi(){
        Log.d(TAG, HEADER + "changeUi " + blueAllianceChosen);
        if(blueAllianceChosen){
            backgroundLayout.setBackgroundColor(getResources().getColor(R.color.BBackground));
            reconfigureButton.setBackgroundColor(getResources().getColor(R.color.BButtonBackground));
            reconfigureButton.setTextColor(getResources().getColor(R.color.BText));
            emailTextView.setTextColor(getResources().getColor(R.color.BText));
            teamNumTextView.setTextColor(getResources().getColor(R.color.BText));
            emailInput.setTextColor(getResources().getColor(R.color.BText));
            teamNumInput.setTextColor(getResources().getColor(R.color.BText));
            eventSpinner.setBackgroundColor(getResources().getColor(R.color.BButtonBackground));
            eventSpinner.setPopupBackgroundResource(R.color.BButtonBackground);
            configureButton.setBackgroundColor(getResources().getColor(R.color.BButtonBackground));
            configureButton.setTextColor(getResources().getColor(R.color.BText));
            teamsRadioGroup.setBackgroundColor(getResources().getColor(R.color.BBackground));
            team1RadioButton.setTextColor(getResources().getColor(R.color.BText));
            team2RadioButton.setTextColor(getResources().getColor(R.color.BText));
            team3RadioButton.setTextColor(getResources().getColor(R.color.BText));
            allianceButton.setText(getString(R.string.blue_alliance));
            allianceButton.setBackgroundColor(getResources().getColor(R.color.BButtonBackground));
            allianceButton.setTextColor(getResources().getColor(R.color.BText));
            exitButton.setTextColor(getResources().getColor(R.color.BText));
            exitButton.setBackgroundColor(getResources().getColor(R.color.BButtonBackground));
        }else{
            backgroundLayout.setBackgroundColor(getResources().getColor(R.color.RBackground));
            reconfigureButton.setBackgroundColor(getResources().getColor(R.color.RButtonBackground));
            reconfigureButton.setTextColor(getResources().getColor(R.color.RText));
            emailTextView.setTextColor(getResources().getColor(R.color.RText));
            teamNumTextView.setTextColor(getResources().getColor(R.color.RText));
            emailInput.setTextColor(getResources().getColor(R.color.RText));
            teamNumInput.setTextColor(getResources().getColor(R.color.RText));
            eventSpinner.setBackgroundColor(getResources().getColor(R.color.RButtonBackground));
            eventSpinner.setPopupBackgroundResource(R.color.RButtonBackground);
            configureButton.setBackgroundColor(getResources().getColor(R.color.RButtonBackground));
            configureButton.setTextColor(getResources().getColor(R.color.RText));
            teamsRadioGroup.setBackgroundColor(getResources().getColor(R.color.RBackground));
            team1RadioButton.setTextColor(getResources().getColor(R.color.RText));
            team2RadioButton.setTextColor(getResources().getColor(R.color.RText));
            team3RadioButton.setTextColor(getResources().getColor(R.color.RText));
            allianceButton.setText(getString(R.string.red_alliance));
            allianceButton.setBackgroundColor(getResources().getColor(R.color.RButtonBackground));
            allianceButton.setTextColor(getResources().getColor(R.color.RText));
            exitButton.setTextColor(getResources().getColor(R.color.RText));
            exitButton.setBackgroundColor(getResources().getColor(R.color.RButtonBackground));
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
                            Log.d(TAG, HEADER + "downLoaded events");
                            BlueAllianceEvent.parseJson(_data, settings.getString(getString(R.string.TEAM), ""));
                            setSpinner();
                        } else {
                            Toast.makeText(Settings.this, "Internet returned BAD DATA for Events, try another wifi!", Toast.LENGTH_LONG).show();
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
            Log.d(TAG, HEADER + "no preferredEvent");
            eventSpinnerList.add(getResources().getString(R.string.selectEvent));
            eventSpinnerList.addAll(BlueAllianceEvent.getEvents(settings.getString(getString(R.string.TEAM), "")).keySet());
        } else {
            Log.d(TAG, HEADER + "pref_SelectedEvent " + pref_SelectedEvent);
            eventSpinnerList.add(pref_SelectedEvent);
            for (String eventName : BlueAllianceEvent.getEvents(settings.getString(getString(R.string.TEAM), "")).keySet()) {
                if (!eventName.equals(pref_SelectedEvent)) {
                    eventSpinnerList.add(eventName);
                }
            }
        }
        SpinnerAdapter eventAdapter = new ArrayAdapter<>(Settings.this, R.layout.color_spinner_layout, eventSpinnerList);
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
            boolean blueAllianceToggled = settings.getBoolean(getResources().getString(R.string.pref_BlueAlliance), false);
            Log.d(TAG, HEADER + "restorePreferences scoutingTablet " + blueAllianceToggled);
            if (blueAllianceToggled && !allianceButton.getText().equals("BLUE ALLIANCE")) {
                allianceButton.performClick();
            }
            int teamPositionNum = settings.getInt(getResources().getString(R.string.pref_TeamPosition), 0);
            if (teamPositionNum == 1) {
                team1RadioButton.setChecked(true);
            } else if (teamPositionNum == 2) {
                team2RadioButton.setChecked(true);
            } else if (teamPositionNum == 3) {
                team3RadioButton.setChecked(true);
            }
        }
    }
}
