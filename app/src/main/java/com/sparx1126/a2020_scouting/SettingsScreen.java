package com.sparx1126.a2020_scouting;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.sparx1126.a2020_scouting.Utilities.FileIO;
import com.sparx1126.a2020_scouting.Utilities.BlueAllianceNetwork;
import com.sparx1126.a2020_scouting.Utilities.DataCollection;

import org.json.JSONArray;
import org.json.JSONException;

public class SettingsScreen extends AppCompatActivity {
    private SharedPreferences settings;
    private SharedPreferences.Editor editor;
    private static BlueAllianceNetwork blueAlliance;
    private static DataCollection dataCollection;
    private static FileIO fileIO;
    private View adminLayout;
    private TextView email;
    private TextView teamNum;
    private Button reconfigure;
    private Button saveConfiguration;
    private Spinner eventSpinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_screen);

        settings = getSharedPreferences(getString(R.string.SPARX_PREFS), 0);
        reconfigure = findViewById(R.id.reconfigure);
        email = findViewById(R.id.emailInput);
        teamNum = findViewById(R.id.teamInput);
        saveConfiguration = findViewById(R.id.configure);
        eventSpinner = findViewById(R.id.eventSpinner);
        adminLayout = findViewById(R.id.adminLayout);

        adminLayout.setVisibility(View.INVISIBLE);

        reconfigure.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                reconfigure();
            }
        });
        eventSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                final String selectedItem = eventSpinner.getSelectedItem().toString();
                if (!selectedItem.contentEquals(getResources().getString(R.string.selectEvent))) {
                    String previousSelectedEvent = settings.getString(getResources().getString(R.string.pref_SelectedEvent), "");
                    if (!previousSelectedEvent.equals(selectedItem)) {
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
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });

        email.setText(settings.getString(getString(R.string.EMAIL), "email not found"));
        teamNum.setText(settings.getString(getString(R.string.TEAM), "team number not found"));

        Log.i("email", settings.getString(getString(R.string.EMAIL), "email not found"));
        Log.i("password", settings.getString(getString(R.string.PASSWORD), "password not found"));
        Log.i("team", settings.getString(getString(R.string.TEAM), "team number not found"));
    }

    private void reconfigure(){
        AlertDialog.Builder builder = new AlertDialog.Builder(SettingsScreen.this);
        builder.setTitle("Reconfigure");
        builder.setMessage("Please enter you email password to continue");
        // Set an EditText view to get user input
        final EditText input = new EditText(this);
        builder.setView(input);

        builder.setPositiveButton("Proceed", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int whichButton) {
                String value = input.getText().toString();
                if (!value.equals(settings.getString(getString(R.string.PASSWORD)," "))){
                    Toast.makeText(SettingsScreen.this, "Wrond Password", Toast.LENGTH_LONG).show();
                    input.setText("");
                }else{

                    editor = settings.edit();

                    editor.putString(getString(R.string.EMAIL), "");
                    editor.putString(getString(R.string.PASSWORD), "");
                    editor.putString(getString(R.string.TEAM), "");
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

    private void reset() {
        editor.putBoolean(getResources().getString(R.string.pref_BlueAlliance), false);
        editor.putInt(getResources().getString(R.string.pref_TeamPosition), 0);
        editor.putBoolean(getResources().getString(R.string.tablet_Configured), false);
        editor.apply();
        adminLayout.setVisibility(View.INVISIBLE);
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
