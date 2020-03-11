package com.sparx1126.a2020_scouting;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.sparx1126.a2020_scouting.BlueAllianceData.BlueAllianceTeam;
import com.sparx1126.a2020_scouting.Data.BenchmarkingData;

import java.util.Map;

public class Benchmarking extends AppCompatActivity {
    private static String TAG = "Sparx: ";
    private static String HEADER = "Benchmarking: ";

    private SharedPreferences settings;

    private AutoCompleteTextView teamNumber;
    private Button exitButton;

    //General
    private Spinner driveType;
    private Spinner wheelType;
    private EditText numWheels;
    private EditText maxSpeed;
    private EditText height;
    private EditText weight;
    private Spinner visionType;

    //Auto
    private Spinner startPos;
    private Spinner maxStartingCells;
    private CheckBox autoScoreBottom;
    private CheckBox autoScoreTop;
    private CheckBox autoAcquireFloor;

    //Teleop
    private CheckBox teleScoreBottom;
    private CheckBox teleScoreTop;
    private CheckBox teleAcquireFloor;

    //End Game
    private CheckBox canClimb;
    private CheckBox canLevel;

    //Comments
    private EditText comments;
    private Button saveButton;

    @Override
    public void onBackPressed() {
        Toast.makeText(Benchmarking.this, "To exit press the \" " + getResources().getString(R.string.benchmark_exit) + "\" button", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_benchmarking);

        Log.d(TAG, HEADER + "onCreate");

        settings = getSharedPreferences(getString(R.string.SPARX_PREFS), 0);

        teamNumber = findViewById(R.id.teamNumber);
        teamNumber.setTransformationMethod(null);
        String selectedEvent = settings.getString(getString(R.string.pref_SelectedEvent), "");;
        Map<String, BlueAllianceTeam> teamsMap = BlueAllianceTeam.getTeams(selectedEvent);
        String[] teams = new String[teamsMap.size()];
        teamsMap.keySet().toArray(teams);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.custom_list_item, teams);
        teamNumber.setAdapter(adapter);
        teamNumber.setThreshold(1);
        teamNumber.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                try {
                    String teamNumberString = teamNumber.getText().toString();
                    String teamNum = String.valueOf(teamNumberString);
                    boolean teamNumberFound = teamsInEvent.contains(teamNum);
                    if (teamNumberFound) {
                        Log.d(TAG, teamNumberStringg);
                        BenchmarkingInformation dataCollect = data.getBenchmarkingInformation(teamNum);
                        if (dataCollect != null) {
                            String msg = "Found Benchmark for " + teamNum;
                            Log.d(TAG, msg);
                            Toast.makeText(Benchmarking.this, TAG + msg, Toast.LENGTH_LONG).show();
                        }
                        dismissKeyboard();
                        restorePreferences(teamNum);
                        benchmark_main_layout.setVisibility(View.VISIBLE);
                    } else {
                        benchmark_main_layout.setVisibility(View.INVISIBLE);
                    }

                } catch (Exception e) {
                    Log.e("Problem w/ team # txt", e.toString());
                }

            }
        });

        numWheels = findViewById(R.id.numWheels);
        maxSpeed = findViewById(R.id.maxSpeed);
        height = findViewById(R.id.height);
        weight = findViewById(R.id.weight);
        maxStartingCells = findViewById(R.id.maxStartingCells);
        autoScoreBottom = findViewById(R.id.autoScoreBottom);
        autoScoreTop = findViewById(R.id.autoScoreTop);
        autoAcquireFloor = findViewById(R.id.autoAcquireFloor);
        teleScoreBottom = findViewById(R.id.teleScoreBottom);
        teleScoreTop = findViewById(R.id.teleScoreTop);
        teleAcquireFloor = findViewById(R.id.teleAcquireFloor);
        canClimb = findViewById(R.id.canClimb);
        canLevel = findViewById(R.id.canLevel);
        comments = findViewById(R.id.comments);

        String[] driveTypesArray = getResources().getStringArray(R.array.driveTypes);
        driveType = findViewById(R.id.driveType);
        SpinnerAdapter driveAdapter = new ArrayAdapter<>(this, R.layout.benchmarking_spinner, driveTypesArray);
        driveType.setAdapter(driveAdapter);
        driveType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                dismissKeyboard();
            }
        });

        String[] wheelTypesArray = getResources().getStringArray(R.array.wheelTypes);
        wheelType = findViewById(R.id.wheelType);
        SpinnerAdapter wheelAdapter = new ArrayAdapter<>(this, R.layout.benchmarking_spinner, wheelTypesArray);
        wheelType.setAdapter(wheelAdapter);
        wheelType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                dismissKeyboard();
            }
        });

        String[] visionTypesArray = getResources().getStringArray(R.array.visionTypes);
        visionType = findViewById(R.id.visionType);
        SpinnerAdapter visionAdapter = new ArrayAdapter<>(this, R.layout.benchmarking_spinner, visionTypesArray);
        visionType.setAdapter(visionAdapter);
        visionType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                dismissKeyboard();
            }
        });

        String[] startPosArray = getResources().getStringArray(R.array.startPos);
        startPos = findViewById(R.id.startPos);
        SpinnerAdapter startAdapter = new ArrayAdapter<>(this, R.layout.benchmarking_spinner, startPosArray);
        startPos.setAdapter(startAdapter);
        startPos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                dismissKeyboard();
            }
        });
        Button exitButton;
        exitButton = findViewById(R.id.exitButton);
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        Button saveButton;
        saveButton = findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveData(Integer.parseInt(teamNumber.getText().toString()));
            }
        });


    }

    private void dismissKeyboard() {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
    }

    private void saveData(int teamNumber) {
        BenchmarkingData benchData = new BenchmarkingData(teamNumber);


        /*
        //General
        private Spinner driveType;
        private Spinner wheelType;
        private EditText numWheels;
        private EditText maxSpeed;
        private EditText height;
        private EditText weight;
        private Spinner visionType;
        //Auto
        private Spinner startPos;
        private EditText startingCells;
        private CheckBox autoScoreBottom;
        private CheckBox autoScoreTop;
        private CheckBox autoAcquireFloor;
        //Teleop
        private CheckBox teleScoreBottom;
        private CheckBox teleScoreTop;
        private CheckBox teleAcquireFloor;
        //End Game
        private CheckBox canClimb;
        private CheckBox canLevel;
        //Comments
        private EditText comments;
         */


    }

    private void restoreData(int teamNumber) {

    }
}