package com.sparx1126.a2020_scouting;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.sparx1126.a2020_scouting.BlueAllianceData.BlueAllianceTeam;
import com.sparx1126.a2020_scouting.Data.BenchmarkingData;
import com.sparx1126.a2020_scouting.Utilities.SendMail;

import java.util.Arrays;
import java.util.Map;

public class Benchmarking extends AppCompatActivity {
    private static String TAG = "Sparx: ";
    private static String HEADER = "Benchmarking: ";

    private SharedPreferences settings;

    private LinearLayout afterTeamSelection;
    private AutoCompleteTextView teamNumber;

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
    private Button exitButton;

    String[] driveTypesArray;
    String[] wheelTypesArray;
    String[] visionTypesArray;
    String[] startPosArray;
    String[] maxStartingCellsArray;

    @Override
    public void onBackPressed() {
        Log.e(TAG, HEADER + "Press Exit to return with no changes...");
        Toast.makeText(Benchmarking.this, "To exit press the \" " + getResources().getString(R.string.benchmark_exit) + "\" button", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_benchmarking);

        Log.d(TAG, HEADER + "onCreate");

        settings = getSharedPreferences(getString(R.string.SPARX_PREFS), 0);

        afterTeamSelection = findViewById(R.id.afterTeamSelection);
        afterTeamSelection.setVisibility(View.INVISIBLE);

        teamNumber = findViewById(R.id.teamNumber);
        teamNumber.setTransformationMethod(null);
        String selectedEvent = settings.getString(getString(R.string.pref_SelectedEvent), "");
        Map<String, BlueAllianceTeam> teamsMap = BlueAllianceTeam.getTeams(selectedEvent);
        String[] teams = new String[teamsMap.size()];
        teamsMap.keySet().toArray(teams);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.custom_list_item, teams);
        teamNumber.setAdapter(adapter);
        teamNumber.setThreshold(1);
        teamNumber.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String teamNum = String.valueOf(teamNumber.getText().toString()); // not sure why I have to sent it to valueof
                Log.d(TAG, HEADER + "teamNumber selected " + teamNum);
                dismissKeyboard();
                afterTeamSelection.setVisibility(View.VISIBLE);
                restoreData(Integer.parseInt(teamNum));
            }
        });

        numWheels = findViewById(R.id.numWheels);
        maxSpeed = findViewById(R.id.maxSpeed);
        height = findViewById(R.id.height);
        weight = findViewById(R.id.weight);
        autoScoreBottom = findViewById(R.id.autoScoreBottom);
        autoScoreTop = findViewById(R.id.autoScoreTop);
        autoAcquireFloor = findViewById(R.id.autoAcquireFloor);
        teleScoreBottom = findViewById(R.id.teleScoreBottom);
        teleScoreTop = findViewById(R.id.teleScoreTop);
        teleAcquireFloor = findViewById(R.id.teleAcquireFloor);
        canClimb = findViewById(R.id.canClimb);
        canLevel = findViewById(R.id.canLevel);
        comments = findViewById(R.id.comments);

        driveTypesArray = getResources().getStringArray(R.array.driveTypes);
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

        wheelTypesArray = getResources().getStringArray(R.array.wheelTypes);
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

        visionTypesArray = getResources().getStringArray(R.array.visionTypes);
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

        startPosArray = getResources().getStringArray(R.array.startPos);
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

        maxStartingCellsArray = getResources().getStringArray(R.array.numStartingCells);
        maxStartingCells = findViewById(R.id.maxStartingCells);
        SpinnerAdapter maxStartingCellsAdapter = new ArrayAdapter<>(this, R.layout.benchmarking_spinner, maxStartingCellsArray);
        maxStartingCells.setAdapter(maxStartingCellsAdapter);
        maxStartingCells.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                dismissKeyboard();
            }
        });

        saveButton = findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveData();
            }
        });
        exitButton = findViewById(R.id.exitButton);
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, HEADER + "exited");
                finish();
            }
        });
    }

    private void dismissKeyboard() {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
    }

    private void saveData() {
        int teamNum = Integer.parseInt(teamNumber.getText().toString());
        Log.d(TAG, HEADER + "save for team " + teamNum);

        BenchmarkingData data = BenchmarkingData.getData().get(teamNum);

        String missing = "";
        int driveTypePos = driveType.getSelectedItemPosition();
        if(driveTypePos == 0) {
            data.setDriveType("");
            missing += " driveType";
        }
        else {
            data.setDriveType(driveTypesArray[driveTypePos]);
        }

        int wheelTypePos = wheelType.getSelectedItemPosition();
        if(wheelTypePos == 0) {
            data.setWheelType("");
            missing += " wheelType";
        }
        else {
            data.setWheelType(wheelTypesArray[wheelTypePos]);
        }
        String numWheelsString = numWheels.getText().toString();
        if (numWheelsString.isEmpty()) {
            missing += " numWheels";
        }
        else {
            data.setNumWheels(Integer.parseInt(numWheelsString));
        }
        String maxSpeedString = maxSpeed.getText().toString();
        if (maxSpeedString.isEmpty()) {
            missing += " maxSpeed";
        }
        else {
            data.setMaxSpeed(Double.parseDouble(maxSpeedString));
        }
        String heightString = height.getText().toString();
        if (heightString.isEmpty()) {
            missing += " height";
        }
        else {
            data.setHeight(Double.parseDouble(heightString));
        }
        String weightString = weight.getText().toString();
        if (weightString.isEmpty()) {
            missing += " weight";
        }
        else {
            data.setWeight(Double.parseDouble(weightString));
        }
        int visionTypePos = visionType.getSelectedItemPosition();
        if(visionTypePos == 0) {
            data.setVisionType("");
            missing += " visionType";
        }
        else {
            data.setVisionType(visionTypesArray[visionTypePos]);
        }
        //Auto
        int startPosPos = startPos.getSelectedItemPosition();
        if(startPosPos == 0) {
            data.setStartPos("");
            missing += " startPos";
        }
        else {
            data.setStartPos(startPosArray[startPosPos]);
        }
        int maxStartingCellsPos = maxStartingCells.getSelectedItemPosition();
        if(maxStartingCellsPos == 0) {
            data.setStartingCells("");
            missing += " maxStartingCells";
        }
        else {
            data.setStartingCells(maxStartingCellsArray[maxStartingCellsPos]);
        }
        data.setAutoScoreBottom(autoScoreBottom.isChecked());
        data.setAutoScoreTop(autoScoreTop.isChecked());
        data.setAutoAcquireFloor(autoAcquireFloor.isChecked());
        //Teleop
        data.setTeleScoreBottom(teleScoreBottom.isChecked());
        data.setTeleScoreTop(teleScoreTop.isChecked());
        data.setTeleAcquireFloor(teleAcquireFloor.isChecked());
        //End Game
        data.setCanClimb(canClimb.isChecked());
        data.setCanLevel(canLevel.isChecked());
        //Comments
        data.setComments(comments.getText().toString());

        if (missing.isEmpty()) {
            String email = settings.getString(getString(R.string.EMAIL), "");
            String password = settings.getString(getString(R.string.PASSWORD), "");
            // example BD.2020ndgf.frc3871.json
            String subject = "BD.";
            subject += settings.getString("pref_SelectedEvent", "") + ".";
            subject += "frc" + String.valueOf(teamNum) + ".json";
            new SendMail(Benchmarking.this, email, password, subject, data.toJson().toString(),
                    new SendMail.Callback() {
                        @Override
                        public void handleFinishDownload() {
                            Log.d(TAG, HEADER + "Data was sent over email");
                            Toast.makeText(Benchmarking.this, "Data was sent over email", Toast.LENGTH_LONG).show();
                        }
                    });
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(Benchmarking.this);
            builder.setTitle("Error");
            builder.setMessage("Please select choices for the following:" + missing + ". And Save Again!");
            // Set an EditText view to get user input
            final EditText input = new EditText(this);
            builder.setView(input);

            builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int whichButton) {
                    dialog.dismiss();
                }
            });
            builder.create().show();
        }
    }

    public void restoreData(Integer _teamNum) {
        Log.d(TAG, HEADER + "restoreData for team " + _teamNum);

        BenchmarkingData data = BenchmarkingData.getData().get(_teamNum);
        setInSpinner(data.getDriveType(), driveTypesArray, driveType);
        setInSpinner(data.getWheelType(), wheelTypesArray, wheelType);
        numWheels.setText(String.valueOf(data.getNumWheels()));
        maxSpeed.setText(String.valueOf(data.getMaxSpeed()));
        height.setText(String.valueOf(data.getHeight()));
        weight.setText(String.valueOf(data.getWeight()));
        setInSpinner(data.getVisionType(), visionTypesArray, visionType);

        //Auto
        setInSpinner(data.getStartPos(), startPosArray, startPos);
        setInSpinner(data.getStartingCells(), maxStartingCellsArray, maxStartingCells);
        autoScoreBottom.setChecked(data.isAutoScoreBottom());
        autoScoreTop.setChecked(data.isAutoScoreTop());
        autoAcquireFloor.setChecked(data.isAutoAcquireFloor());

        //Teleop
        teleScoreBottom.setChecked(data.isTeleScoreBottom());
        teleScoreTop.setChecked(data.isTeleScoreTop());
        teleAcquireFloor.setChecked(data.isTeleAcquireFloor());

        //End Game
        canClimb.setChecked(data.isCanClimb());
        canLevel.setChecked(data.isCanLevel());

        //Comments
        comments.setText(String.valueOf(data.getComments()));
    }

    private void setInSpinner(String currentValue, String[] positionArray, Spinner spinner) {
        spinner.setSelection(0);
        if (!currentValue.isEmpty()) {
            boolean inSpinner = Arrays.asList(positionArray).contains(currentValue);
            if (inSpinner) {
                int positionInList = 0;
                for (int i = 0; i < positionArray.length; i++) {
                    if (positionArray[i].contentEquals(currentValue)) {
                        positionInList = i;
                        break;
                    }
                }
                spinner.setSelection(positionInList);
            } else {
                spinner.setSelection(positionArray.length - 1);
            }
        }
    }
}