package com.sparx1126.a2020_scouting;

import androidx.appcompat.app.AppCompatActivity;

import com.sparx1126.a2020_scouting.BlueAllianceData.BlueAllianceEvent;
import com.sparx1126.a2020_scouting.BlueAllianceData.BlueAllianceMatch;
import com.sparx1126.a2020_scouting.Utilities.*;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SettingsScreen extends AppCompatActivity {
    private SharedPreferences settings;
    private SharedPreferences.Editor editor;
    private static BlueAllianceNetwork blueAlliance;
    private TextView email;
    private TextView teamNum;
    private Button reconfigure;

    private boolean configured;
    private Spinner eventSpinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_screen);

        changeUi();

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

        eventSpinner = findViewById(R.id.eventSpinner);
        eventSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                final String selectedItem = eventSpinner.getSelectedItem().toString();
                if (!selectedItem.contentEquals(getResources().getString(R.string.selectEvent))) {
                    String previousSelectedEvent = settings.getString(getResources().getString(R.string.pref_SelectedEvent), "");
                    Log.e("selected Event:", selectedItem);
                   /* if (!previousSelectedEvent.equals(selectedItem)) {
                        // Sohail: You will download the matches here.
                        reset();
                        blueAlliance.downloadEventMatches(selectedItem, new BlueAllianceNetwork.Callback() {

                            @Override
                            public void handleFinishDownload(final String _data) {
                                // this needs to run on the ui thread because of ui components in it
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        if (isValidJsonArray(_data)) {
                                            //dataCollection.setEventMatches(_data);
                                            //fileIO.storeEventMatches(_data);
                                            blueAlliance.downloadEventTeams(selectedItem, new BlueAllianceNetwork.Callback() {

                                                @Override
                                                public void handleFinishDownload(final String _data) {
                                                    // this needs to run on the ui thread because of ui components in it
                                                    runOnUiThread(new Runnable() {
                                                        @Override
                                                        public void run() {
                                                            if (isValidJsonArray(_data)) {
                                                                //dataCollection.setEventTeams(_data);
                                                                //fileIO.storeEventTeams(_data);
                                                                adminLayout.setVisibility(View.VISIBLE);
                                                            } else {
                                                                Toast.makeText(SettingsScreen.this, "Internet returned BAD DATA for Teams, try another wifi!", Toast.LENGTH_LONG).show();
                                                            }
                                                        }
                                                    });
                                                }
                                            });
                                        } else {
                                            Toast.makeText(SettingsScreen .this, "Internet returned BAD DATA for Matches, try another wifi!", Toast.LENGTH_LONG).show();
                                        }
                                    }
                                });
                            }
                        });
                    } else {
                        adminLayout.setVisibility(View.VISIBLE);
                    }*/
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });

        email = findViewById(R.id.emailInput);
        email.setText(settings.getString(getString(R.string.EMAIL), "email not found"));

        teamNum = findViewById(R.id.teamInput);
        teamNum.setText(settings.getString(getString(R.string.TEAM), "team number not found"));

        Log.i("email", settings.getString(getString(R.string.EMAIL), "email not found"));
        Log.i("password", settings.getString(getString(R.string.PASSWORD), "password not found"));
        Log.i("team", settings.getString(getString(R.string.TEAM), "team number not found"));

        downLoadEvents();
        //get rid of this when finished
        configured = false;
    }

    @Override
    public void onBackPressed() {
        if(!configured)
            Toast.makeText(SettingsScreen.this, "You can't leave until you have configured", Toast.LENGTH_LONG).show();
    }

    private void reconfigure() {
        AlertDialog.Builder builder = new AlertDialog.Builder(SettingsScreen.this);
        builder.setTitle("Reconfigure");
        builder.setMessage("Please enter you email password to continue");
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
                    editor.putString(getString(R.string.EMAIL), "");
                    editor.putString(getString(R.string.PASSWORD), "");
                    editor.putString(getString(R.string.TEAM), "");
                    editor.apply();

                    Intent switchToWelcome = new Intent(SettingsScreen.this, Welcome.class);
                    startActivity(switchToWelcome);
                }
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                Intent stayHere = new Intent(SettingsScreen.this, SettingsScreen.class);
                startActivity(stayHere);
            }
        });
        builder.create().show();
    }

    public  void changeUi(){
        if(Welcome.toggledBlue){
            LinearLayout li = (LinearLayout)findViewById(R.id.background);
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
        }else{
            LinearLayout li = (LinearLayout)findViewById(R.id.background);
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
        }
    }

    // Sohail: We should move this to the Welcome Screen after JT is done making the BAM
    // Copy the way he stored the matches.
    private void downLoadEvents() {
        blueAlliance.downloadEvents(new BlueAllianceNetwork.Callback() {
            @Override
            public void handleFinishDownload(final String _data) {
                // this needs to run on the ui thread because of ui components in it
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("JT " + _data);
                        if (isValidJsonArray(_data)) {
//                            dataCollection.setTeamEvents(_data);
//                            fileIO.storeTeamEvents(_data);
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
        // Sohail: This is where you will get the events from BAE (BlueAllianceEvent) after is completed
        // following JTs format
        //        Map<String, BlueAllianceEvent> data = dataCollection.getEvents();

        //if you look at this haram like this
        //Map<String, BlueAllianceEvent> data = BlueAllianceMatch.getMatches();
        List<String> eventSpinnerList = new ArrayList<>();

        if (pref_SelectedEvent.isEmpty()) {
            eventSpinnerList.add("Select Event");
            // Sohail: You will use all events here
            //            eventSpinnerList.addAll(data.keySet());
        } else {
            eventSpinnerList.add(pref_SelectedEvent);
            // Sohail You will add the events here one by one execpt for the currently seleceted
           /* for (String eventName : data.keySet()) {
                if (!eventName.equals(pref_SelectedEvent)) {
                    eventSpinnerList.add(eventName);
                }*/
            }
        System.out.println(eventSpinnerList.toString());
        SpinnerAdapter eventAdapter = new ArrayAdapter<>(SettingsScreen.this, R.layout.support_simple_spinner_dropdown_item, eventSpinnerList);
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
}
