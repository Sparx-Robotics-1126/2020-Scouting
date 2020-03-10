package com.sparx1126.a2020_scouting.ui.Benchmarking;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.sparx1126.a2020_scouting.R;

public class Benchmarking extends AppCompatActivity {

    private EditText teamNumber;
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




    @Override
    public void onBackPressed() {
        Toast.makeText(Benchmarking.this, "To exit press the \" "+ getResources().getString(R.string.benchmark_exit) + "\" button", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_benchmarking);

        teamNumber = findViewById(R.id.teamNumber);

        numWheels = findViewById(R.id.numWheels);
        maxSpeed = findViewById(R.id.maxSpeed);
        height = findViewById(R.id.height);
        weight = findViewById(R.id.weight);
        startingCells = findViewById(R.id.startingCells);
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
        exitButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        Button saveButton;
        saveButton = findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener(){
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

    private void saveData(int teamNumber){
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

    private void restoreData(int teamNumber){

    }

}
