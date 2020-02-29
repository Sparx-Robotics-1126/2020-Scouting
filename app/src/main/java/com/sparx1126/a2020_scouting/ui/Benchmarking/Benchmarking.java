package com.sparx1126.a2020_scouting.ui.Benchmarking;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import androidx.appcompat.app.AppCompatActivity;

import com.sparx1126.a2020_scouting.R;

public class Benchmarking extends AppCompatActivity {
    private Spinner driveType;
    private Spinner wheelType;
    private Spinner visionType;
    private Spinner startPos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_benchmarking);

        String[] driveTypesArray = getResources().getStringArray(R.array.driveTypes);
        driveType = findViewById(R.id.driveType);
        SpinnerAdapter driveAdapter = new ArrayAdapter<>(this, R.layout.custom_spinner_item, driveTypesArray);
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
        SpinnerAdapter wheelAdapter = new ArrayAdapter<>(this, R.layout.custom_spinner_item, wheelTypesArray);
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
        SpinnerAdapter visionAdapter = new ArrayAdapter<>(this, R.layout.custom_spinner_item, visionTypesArray);
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
        SpinnerAdapter startAdapter = new ArrayAdapter<>(this, R.layout.custom_spinner_item, startPosArray);
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
    }

    public void dismissKeyboard() {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
    }


}
