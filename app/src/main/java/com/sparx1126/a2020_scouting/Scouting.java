package com.sparx1126.a2020_scouting;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.sparx1126.a2020_scouting.BlueAllianceData.*;
import com.sparx1126.a2020_scouting.Data.ScoutingData;
import com.sparx1126.a2020_scouting.Utilities.SendMail;

import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Scouting extends AppCompatActivity {
    private static String TAG = "Sparx: ";
    private static String HEADER = "Scouting: ";

    private SharedPreferences settings;
    private SharedPreferences.Editor editor;

    private ScrollView backgroundScrollView;
    private TextView assignmentTextView;
    private TextView scouterNameTextView;
    private TextView scouterNameInput;
    private TextView matchNumTextView;
    private TextView matchNumInput;
    private Button plusMatch;
    private Button minusMatch;
    private Button logOutButton;
    private TextView teamNumTextView;
    private TextView teamNumInput;
    private TextView allianceTextView;
    private TextView allianceInput;
    private TextView autonomousTextView;
    private TextView startingPosTextView;
    private RadioGroup startingPositionRadioGroup;
    private RadioButton closestRadioButton;
    private RadioButton middleRadioButton;
    private RadioButton farthestRadioButton;
    private TextView startBallsTextView;
    private RadioGroup startingBallsRadioGroup;
    private RadioButton noneRadioButton;
    private RadioButton oneRadioButton;
    private RadioButton twoRadioButton;
    private RadioButton threeRadioButton;
    private TextView ballsScoredAutoTextView;
    private TextView bottomPortAutoTextView;
    private TextView ballsBottomAutoInput;
    private Button plusBallsBottomAuto;
    private Button minusBallsBottomAuto;
    private TextView outerPortAutoTextView;
    private TextView ballsOuterAutoInput;
    private Button plusBallsOuterAuto;
    private Button minusBallsOuterAuto;
    private TextView innerPortAutoTextView;
    private TextView ballsInnerAutoInput;
    private Button plusBallsInnerAuto;
    private Button minusBallsInnerAuto;
    private TextView ballsAcquiredAutoTextView;
    private TextView floorAutoTextView;
    private TextView ballsFloorAutoInput;
    private Button plusBallsFloorAuto;
    private Button minusBallsFloorAuto;
    private TextView crossesLineTextView;
    private TextView crossedLineTextView;
    private CheckBox crossedLineCheckBox;
    private TextView teleoperatedTextView;
    private TextView ballsScoredTeleTextView;
    private TextView bottomPortTeleTextView;
    private TextView ballsBottomTeleInput;
    private Button plusBallsBottomTele;
    private Button minusBallsBottomTele;
    private TextView outerPortTeleTextView;
    private TextView ballsOuterTeleInput;
    private Button plusBallsOuterTele;
    private Button minusBallsOuterTele;
    private TextView innerPortTeleTextView;
    private TextView ballsInnerTeleInput;
    private Button plusBallsInnerTele;
    private Button minusBallsInnerTele;
    private TextView ballsAcquiredTeleTextView;
    private TextView floorTeleTextView;
    private TextView floorTeleInput;
    private Button plusBallsFloorTele;
    private Button minusBallsFloorTele;
    private TextView lowChuteTeleTextView;
    private TextView ballsLowChuteTeleInput;
    private Button plusBallsLowChuteTele;
    private Button minusBallsLowChuteTele;
    private TextView highChuteTeleTextView;
    private TextView ballsHighChuteTeleInput;
    private Button plusBallsHighChuteTele;
    private Button minusBallsHighChuteTele;
    private TextView controlPanelInteractionTextView;
    private TextView performedRotationControlTextView;
    private CheckBox performedRotationControlCheckBox;
    private TextView performedPositionControlTextView;
    private CheckBox performedPositionControlCheckBox;
    private TextView endGameTextView;
    private TextView rendezuousTextView;
    private RadioGroup rendezuousRadioGroup;
    private RadioButton noparkRadioButton;
    private RadioButton parkedRadioButton;
    private RadioButton hangedRadioButton;
    private TextView levelingTextView;
    private RadioGroup activeLevelingRadioGroup;
    private RadioButton noLevelingRadioButton;
    private RadioButton triedToLevelRadioButton;
    private RadioButton successfullyLeveledRadioButton;
    private TextView otherTextView;
    private TextView defenseTextView;
    private TextView excellecentDefenseTextView;
    private CheckBox excellecentDefenseCheckBox;
    private TextView foulTextView;
    private TextView causedFouldTextView;
    private CheckBox causedFouldCheckBox;
    private TextView robotStateTextView;
    private TextView brokeDisabledTextView;
    private CheckBox brokeDisabledCheckBox;
    private Button saveButton;

    private SendMail mail;
    private boolean blueAllianceChosen;
    private int lastMatchScouted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scouting);
        Log.d(TAG, HEADER + "onCreate");

        settings = getSharedPreferences(getString(R.string.SPARX_PREFS), 0);
        editor = settings.edit();
        blueAllianceChosen = settings.getBoolean(getResources().getString(R.string.pref_BlueAlliance), false);
        lastMatchScouted = settings.getInt(getResources().getString(R.string.last_match_scouted), 1);

        backgroundScrollView = findViewById(R.id.backgroundScrollView);
        assignmentTextView = findViewById(R.id.assignmentTextView);
        scouterNameTextView = findViewById(R.id.scouterNameTextView);
        scouterNameInput = findViewById(R.id.scouterNameInput);
        matchNumTextView = findViewById(R.id.matchNumTextView);
        matchNumInput = findViewById(R.id.matchNumInput);
        matchNumInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                if(findTeam()) {
                    restoreData(Integer.parseInt(matchNumInput.getText().toString()));
                }
            }
        });
        plusMatch = findViewById(R.id.plusMatch);
        plusMatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentValue = Integer.parseInt(matchNumInput.getText().toString());
                int newValue = currentValue + 1;
                int numberOfMatches = BlueAllianceMatch.getMatches().size();
                if(newValue > numberOfMatches) {
                    Toast.makeText(Scouting.this, "Cannot exceed number of matches " + numberOfMatches, Toast.LENGTH_LONG).show();
                }
                else {
                    matchNumInput.setText(String.valueOf(newValue));
                }
            }
        });
        minusMatch = findViewById(R.id.minusMatch);
        minusMatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentValue = Integer.parseInt(matchNumInput.getText().toString());
                int newValue = currentValue - 1;
                if(newValue < 1) {
                    Toast.makeText(Scouting.this, "Value cannot be less than 1" , Toast.LENGTH_LONG).show();
                }
                else {
                    matchNumInput.setText(String.valueOf(newValue));
                }
            }
        });
        logOutButton = findViewById(R.id.logOutButton);
        logOutButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(Scouting.this);
                builder.setTitle("Log Out");
                builder.setMessage("Are you sure you want to log out");

                builder.setPositiveButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
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
        teamNumTextView = findViewById(R.id.teamNumTextView);
        teamNumInput = findViewById(R.id.teamNumInput);
        allianceTextView = findViewById(R.id.allianceTextView);
        allianceInput = findViewById(R.id.allianceInput);
        autonomousTextView = findViewById(R.id.autonomousTextView);
        startingPosTextView = findViewById(R.id.startingPosTextView);
        startingPositionRadioGroup = findViewById(R.id.startingPositionRadioGroup);
        closestRadioButton = findViewById(R.id.closestRadioButton);
        middleRadioButton = findViewById(R.id.middleRadioButton);
        farthestRadioButton = findViewById(R.id.farthestRadioButton);
        startBallsTextView = findViewById(R.id.startBallsTextView);
        startingBallsRadioGroup = findViewById(R.id.startingBallsRadioGroup);
        noneRadioButton = findViewById(R.id.noneRadioButton);
        oneRadioButton = findViewById(R.id.oneRadioButton);
        twoRadioButton = findViewById(R.id.twoRadioButton);
        threeRadioButton = findViewById(R.id.threeRadioButton);
        ballsScoredAutoTextView = findViewById(R.id.ballsScoredAutoTextView);
        bottomPortAutoTextView = findViewById(R.id.bottomPortAutoTextView);
        ballsBottomAutoInput = findViewById(R.id.ballsBottomAutoInput);
        plusBallsBottomAuto = findViewById(R.id.plusBallsBottomAuto);
        plusBallsBottomAuto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                plusBalls(ballsBottomAutoInput);
            }
        });
        minusBallsBottomAuto = findViewById(R.id.minusBallsBottomAuto);
        minusBallsBottomAuto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                minusBalls(ballsBottomAutoInput);
            }
        });
        outerPortAutoTextView = findViewById(R.id.outerPortAutoTextView);
        ballsOuterAutoInput = findViewById(R.id.ballsOuterAutoInput);
        plusBallsOuterAuto = findViewById(R.id.plusBallsOuterAuto);
        plusBallsOuterAuto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                plusBalls(ballsOuterAutoInput);
            }
        });
        minusBallsOuterAuto = findViewById(R.id.minusBallsOuterAuto);
        minusBallsOuterAuto.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                minusBalls(ballsOuterAutoInput);
            }
        });
        innerPortAutoTextView = findViewById(R.id.innerPortAutoTextView);
        ballsInnerAutoInput = findViewById(R.id.ballsInnerAutoInput);
        plusBallsInnerAuto = findViewById(R.id.plusBallsInnerAuto);
        plusBallsInnerAuto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                plusBalls(ballsInnerAutoInput);
            }
        });
        minusBallsInnerAuto = findViewById(R.id.minusBallsInnerAuto);
        minusBallsInnerAuto.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                minusBalls(ballsInnerAutoInput);
            }
        });
        ballsAcquiredAutoTextView = findViewById(R.id.ballsAcquiredAutoTextView);
        floorAutoTextView = findViewById(R.id.floorAutoTextView);
        ballsFloorAutoInput = findViewById(R.id.ballsFloorAutoInput);
        plusBallsFloorAuto = findViewById(R.id.plusBallsFloorAuto);
        plusBallsFloorAuto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                plusBalls(ballsFloorAutoInput);
            }
        });
        minusBallsFloorAuto = findViewById(R.id.minusBallsFloorAuto);
        minusBallsFloorAuto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                minusBalls(ballsFloorAutoInput);
            }
        });
        crossesLineTextView = findViewById(R.id.crossesLineTextView);
        crossedLineTextView = findViewById(R.id.crossedLineTextView);
        crossedLineCheckBox = findViewById(R.id.crossedLineCheckBox);
        teleoperatedTextView = findViewById(R.id.teleoperatedTextView);
        ballsScoredTeleTextView = findViewById(R.id.ballsScoredTeleTextView);
        bottomPortTeleTextView = findViewById(R.id.bottomPortTeleTextView);
        ballsBottomTeleInput = findViewById(R.id.ballsBottomTeleInput);
        plusBallsBottomTele = findViewById(R.id.plusBallsBottomTele);
        plusBallsBottomTele.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                plusBalls(ballsBottomTeleInput);
            }
        });
        minusBallsBottomTele = findViewById(R.id.minusBallsBottomTele);
        minusBallsBottomTele.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                minusBalls(ballsBottomTeleInput);
            }
        });
        outerPortTeleTextView = findViewById(R.id.outerPortTeleTextView);
        ballsOuterTeleInput = findViewById(R.id.ballsOuterTeleInput);
        plusBallsOuterTele = findViewById(R.id.plusBallsOuterTele);
        plusBallsOuterTele.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                plusBalls(ballsOuterTeleInput);
            }
        });
        minusBallsOuterTele = findViewById(R.id.minusBallsOuterTele);
        minusBallsOuterTele.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                minusBalls(ballsOuterTeleInput);
            }
        });
        innerPortTeleTextView = findViewById(R.id.innerPortTeleTextView);
        ballsInnerTeleInput = findViewById(R.id.ballsInnerTeleInput);
        plusBallsInnerTele = findViewById(R.id.plusBallsInnerTele);
        plusBallsInnerTele.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                plusBalls(ballsInnerTeleInput);
            }
        });
        minusBallsInnerTele = findViewById(R.id.minusBallsInnerTele);
        minusBallsInnerTele.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                minusBalls(ballsInnerTeleInput);
            }
        });
        ballsAcquiredTeleTextView = findViewById(R.id.ballsAcquiredTeleTextView);
        floorTeleTextView = findViewById(R.id.floorTeleTextView);
        floorTeleInput = findViewById(R.id.floorTeleInput);
        plusBallsFloorTele = findViewById(R.id.plusBallsFloorTele);
        plusBallsFloorTele.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                plusBalls(floorTeleInput);
            }
        });
        minusBallsFloorTele = findViewById(R.id.minusBallsFloorTele);
        minusBallsFloorTele.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                minusBalls(floorTeleInput);
            }
        });
        lowChuteTeleTextView = findViewById(R.id.lowChuteTeleTextView);
        ballsLowChuteTeleInput = findViewById(R.id.ballsLowChuteTeleInput);
        plusBallsLowChuteTele = findViewById(R.id.plusBallsLowChuteTele);
        plusBallsLowChuteTele.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                plusBalls(ballsLowChuteTeleInput);
            }
        });
        minusBallsLowChuteTele = findViewById(R.id.minusBallsLowChuteTele);
        minusBallsLowChuteTele.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                minusBalls(ballsLowChuteTeleInput);
            }
        });
        highChuteTeleTextView = findViewById(R.id.highChuteTeleTextView);
        ballsHighChuteTeleInput = findViewById(R.id.ballsHighChuteTeleInput);
        plusBallsHighChuteTele = findViewById(R.id.plusBallsHighChuteTele);
        plusBallsHighChuteTele.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                plusBalls(ballsHighChuteTeleInput);
            }
        });
        minusBallsHighChuteTele = findViewById(R.id.minusBallsHighChuteTele);
        minusBallsHighChuteTele.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                minusBalls(ballsHighChuteTeleInput);
            }
        });
        controlPanelInteractionTextView = findViewById(R.id.controlPanelInteractionTextView);
        performedRotationControlTextView = findViewById(R.id.performedRotationControlTextView);
        performedRotationControlCheckBox = findViewById(R.id.performedRotationControlCheckBox);
        performedPositionControlTextView = findViewById(R.id.performedPositionControlTextView);
        performedPositionControlCheckBox = findViewById(R.id.performedPositionControlCheckBox);
        endGameTextView = findViewById(R.id.endGameTextView);
        rendezuousTextView = findViewById(R.id.rendezuousTextView);
        rendezuousRadioGroup = findViewById(R.id.rendezuousRadioGroup);
        noparkRadioButton = findViewById(R.id.noparkRadioButton);
        parkedRadioButton = findViewById(R.id.parkedRadioButton);
        hangedRadioButton = findViewById(R.id.hangedRadioButton);
        levelingTextView = findViewById(R.id.levelingTextView);
        activeLevelingRadioGroup = findViewById(R.id.activeLevelingRadioGroup);
        noLevelingRadioButton = findViewById(R.id.noLevelingRadioButton);
        triedToLevelRadioButton = findViewById(R.id.triedToLevelRadioButton);
        successfullyLeveledRadioButton = findViewById(R.id.successfullyLeveledRadioButton);
        otherTextView = findViewById(R.id.otherTextView);
        defenseTextView = findViewById(R.id.defenseTextView);
        excellecentDefenseTextView = findViewById(R.id.excellecentDefenseTextView);
        excellecentDefenseCheckBox = findViewById(R.id.excellecentDefenseCheckBox);
        foulTextView = findViewById(R.id.foulTextView);
        causedFouldTextView = findViewById(R.id.causedFouldTextView);
        causedFouldCheckBox = findViewById(R.id.causedFouldCheckBox);
        robotStateTextView = findViewById(R.id.robotStateTextView);
        brokeDisabledTextView = findViewById(R.id.brokeDisabledTextView);
        brokeDisabledCheckBox = findViewById(R.id.brokeDisabledCheckBox);
        saveButton = findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Save();
            }
        });

        changeUi();
        String scouter = settings.getString(getString(R.string.scouter), "");
        if(scouter.isEmpty()) {
            login();
        }
        else {
            scouterNameInput.setText(scouter);
        }
        matchNumInput.setText(String.valueOf(lastMatchScouted));
    }

    public void restoreData(Integer matchNum){
        ScoutingData data = ScoutingData.getData().get(matchNum);

        teamNumInput.setText(String.valueOf(data.getTeamNumber()));
        if(data.getAutoStartingPosition().equals(ScoutingData.STARTING_POSITION_CLOSEST)){
            startingPositionRadioGroup.check(R.id.closestRadioButton);
        }else if(data.getAutoStartingPosition().equals(ScoutingData.STARTING_POSITION_MIDDLE)){
            startingPositionRadioGroup.check(R.id.middleRadioButton);
        }else if(data.getAutoStartingPosition().equals(ScoutingData.STARTING_POSITION_FARTHEST)){
            startingPositionRadioGroup.check(R.id.farthestRadioButton);
        }else{
            startingPositionRadioGroup.clearCheck();
        }

        if(data.getAutoPowerCellsInRobot() == 0){
            startingBallsRadioGroup.check(R.id.noneRadioButton);
        }else if(data.getAutoPowerCellsInRobot() == 1){
            startingBallsRadioGroup.check(R.id.oneRadioButton);
        }else if(data.getAutoPowerCellsInRobot() == 2){
            startingBallsRadioGroup.check(R.id.twoRadioButton);
        }else if(data.getAutoPowerCellsInRobot() == 3){
            startingBallsRadioGroup.check(R.id.threeRadioButton);
        }else {
            startingBallsRadioGroup.clearCheck();
        }

        ballsBottomAutoInput.setText(String.valueOf(data.getAutoPowerCellsScoredBottomPort()));
        ballsOuterAutoInput.setText(String.valueOf(data.getAutoPowerCellsScoredOuterPort()));
        ballsInnerAutoInput.setText(String.valueOf(data.getAutoPowerCellsScoredInnerPort()));
        ballsFloorAutoInput.setText(String.valueOf(data.getAutoPowerCellsAcquiredFloor()));
        crossedLineCheckBox.setChecked(data.isAutoCrossedInitiationLine());
        ballsBottomTeleInput.setText(String.valueOf(data.getTelePowerCellsScoredBottomPort()));
        ballsOuterTeleInput.setText(String.valueOf(data.getTelePowerCellsScoredOuterPort()));
        ballsInnerTeleInput.setText(String.valueOf(data.getTelePowerCellsScoredInnerPort()));
        floorTeleInput.setText(String.valueOf(data.getTelePowerCellsAcquiredFloor()));
        ballsLowChuteTeleInput.setText(String.valueOf(data.getTelePowerCellsAcquiredLowChute()));
        ballsHighChuteTeleInput.setText(String.valueOf(data.getTelePowerCellsAcquiredHighChute()));
        performedRotationControlCheckBox.setChecked(data.isTelePerformedRotationControl());
        performedPositionControlCheckBox.setChecked(data.isTelePerformedPositionControl());

        if(data.getEndRendezvous().equals(ScoutingData.RENDEZVOUS_NO_PARK)){
            rendezuousRadioGroup.check(R.id.noparkRadioButton);
        }else if(data.getEndRendezvous().equals(ScoutingData.RENDEZVOUS_PARKED)){
            rendezuousRadioGroup.check(R.id.middleRadioButton);
        }else if(data.getEndRendezvous().equals(ScoutingData.RENDEZVOUS_HANGED)){
            rendezuousRadioGroup.check(R.id.farthestRadioButton);
        }else{
            rendezuousRadioGroup.clearCheck();
        }

        if(data.getEndActiveLeveling().equals(ScoutingData.ACTIVE_LEVELING_NO_LEVEL)){
            activeLevelingRadioGroup.check(R.id.noLevelingRadioButton);
        }else if(data.getEndActiveLeveling().equals(ScoutingData.ACTIVE_LEVELING_TRIED_TO_LEVEL)){
            activeLevelingRadioGroup.check(R.id.triedToLevelRadioButton);
        }else if(data.getEndActiveLeveling().equals(ScoutingData.ACTIVE_LEVELING_LEVELED)){
            activeLevelingRadioGroup.check(R.id.successfullyLeveledRadioButton);
        }else{
            activeLevelingRadioGroup.clearCheck();
        }

        excellecentDefenseCheckBox.setChecked(data.isOtherPlayedExcellentDefense());
        causedFouldCheckBox.setChecked(data.isOtherCausedFoul());
        brokeDisabledCheckBox.setChecked(data.isOtherBrokeOrGotDisabled());
    }

    public void Save(){
        ScoutingData data = ScoutingData.getData().get(Integer.parseInt(matchNumInput.getText().toString()));

        String missing = "";
        data.setScouterName(scouterNameInput.getText().toString());
        data.setTeamNumber(Integer.parseInt(teamNumInput.getText().toString()));
        if(blueAllianceChosen) {
            data.setAllianceColor(getResources().getString(R.string.blue_alliance));
        }
        else {
            data.setAllianceColor(getResources().getString(R.string.red_alliance));
        }

        if(startingPositionRadioGroup.getCheckedRadioButtonId() == -1) {
            data.setAutoStartingPosition("");
            missing += " startingPositionRadioGroup";
        }
        else {
            if (closestRadioButton.isChecked()) {
                data.setAutoStartingPosition(ScoutingData.STARTING_POSITION_CLOSEST);
            } else if (middleRadioButton.isChecked()) {
                data.setAutoStartingPosition(ScoutingData.STARTING_POSITION_MIDDLE);
            } else if (farthestRadioButton.isChecked()) {
                data.setAutoStartingPosition(ScoutingData.STARTING_POSITION_FARTHEST);
            }
        }

        if(startingBallsRadioGroup.getCheckedRadioButtonId() == -1) {
            data.setAutoPowerCellsInRobot(-1);
            missing += " startingBallsRadioGroup";
        }
        else {
            if(noneRadioButton.isChecked()){
            data.setAutoPowerCellsInRobot(0);
            }
            else if(oneRadioButton.isChecked()) {
                data.setAutoPowerCellsInRobot(1);
            }
            else if(twoRadioButton.isChecked()) {
                data.setAutoPowerCellsInRobot(2);
            }
            else if(threeRadioButton.isChecked()) {
                data.setAutoPowerCellsInRobot(3);
            }
        }

        data.setAutoPowerCellsScoredBottomPort(Integer.parseInt(ballsBottomAutoInput.getText().toString()));
        data.setAutoPowerCellsScoredInnerPort(Integer.parseInt(ballsInnerAutoInput.getText().toString()));
        data.setAutoPowerCellsScoredOuterPort(Integer.parseInt(ballsOuterAutoInput.getText().toString()));
        data.setAutoPowerCellsAcquiredFloor(Integer.parseInt(ballsFloorAutoInput.getText().toString()));
        data.setAutoCrossedInitiationLine(crossedLineCheckBox.isChecked());
        data.setTelePowerCellsScoredBottomPort(Integer.parseInt(ballsBottomTeleInput.getText().toString()));
        data.setTelePowerCellsScoredInnerPort(Integer.parseInt(ballsInnerTeleInput.getText().toString()));
        data.setTelePowerCellsScoredOuterPort(Integer.parseInt(ballsOuterTeleInput.getText().toString()));
        data.setTelePowerCellsAcquiredFloor(Integer.parseInt(floorTeleInput.getText().toString()));
        data.setTelePowerCellsAcquiredHighChute(Integer.parseInt(ballsHighChuteTeleInput.getText().toString()));
        data.setTelePowerCellsAcquiredLowChute(Integer.parseInt(ballsLowChuteTeleInput.getText().toString()));
        data.setTelePerformedPositionControl(performedPositionControlCheckBox.isChecked());
        data.setTelePerformedRotationControl(performedRotationControlCheckBox.isChecked());

        if(rendezuousRadioGroup.getCheckedRadioButtonId() == -1) {
            data.setEndRendezvous("");
            missing += " rendezuousRadioGroup";
        }
        else {
            if (noparkRadioButton.isChecked()) {
                data.setEndRendezvous(ScoutingData.RENDEZVOUS_NO_PARK);
            } else if (parkedRadioButton.isChecked()) {
                data.setEndRendezvous(ScoutingData.RENDEZVOUS_PARKED);
            } else if (hangedRadioButton.isChecked()) {
                data.setEndRendezvous(ScoutingData.RENDEZVOUS_HANGED);
            }
        }

        if(activeLevelingRadioGroup.getCheckedRadioButtonId() == -1) {
            data.setEndActiveLeveling("");
            missing += " activeLevelingRadioGroup";
        }
        else {
            if (noLevelingRadioButton.isChecked()) {
                data.setEndActiveLeveling(ScoutingData.ACTIVE_LEVELING_NO_LEVEL);
            } else if (triedToLevelRadioButton.isChecked()) {
                data.setEndActiveLeveling(ScoutingData.ACTIVE_LEVELING_TRIED_TO_LEVEL);
            } else if (successfullyLeveledRadioButton.isChecked()) {
                data.setEndActiveLeveling(ScoutingData.ACTIVE_LEVELING_LEVELED);
            }
        }

        data.setOtherPlayedExcellentDefense(excellecentDefenseCheckBox.isChecked());
        data.setOtherCausedFoul(causedFouldCheckBox.isChecked());
        data.setOtherBrokeOrGotDisabled(brokeDisabledCheckBox.isChecked());

        if(missing.isEmpty()) {
            String email = settings.getString(getString(R.string.EMAIL), "");
            String subject = settings.getString("pref_SelectedEvent","") + ".";
            subject += "frc" + teamNumInput.getText().toString() + ".";
            subject += ScoutingData.getPrefAllianceColor() + ".";
            subject += ScoutingData.getPrefTeamPosition() + ".";
            subject += matchNumInput.getText().toString() + ".json";
            mail = new SendMail(Scouting.this, email, subject, data.toJson().toString());
            mail.execute();
            lastMatchScouted = Integer.parseInt(matchNumInput.getText().toString());
            Toast.makeText(this, "Data was saved on device", Toast.LENGTH_LONG).show();
        }
        else {
            AlertDialog.Builder builder = new AlertDialog.Builder(Scouting.this);
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

    public void plusBalls(TextView view){
        int value = Integer.parseInt(view.getText().toString());
        view.setText(String.valueOf(value + 1));
    }
    public void minusBalls(TextView view){
        int currentValue = Integer.parseInt(view.getText().toString());
        int newValue = currentValue - 1;
        if(newValue < 0) {
            Toast.makeText(this, "Value cannot be negative", Toast.LENGTH_LONG).show();
        }
        else {
            view.setText(String.valueOf(newValue));
        }
    }

    public  void changeUi(){
        int backgroundColor = getResources().getColor(R.color.RBackground);
        int textColor = getResources().getColor(R.color.RText);
        int buttonColor = getResources().getColor(R.color.RButtonBackground);
        allianceInput.setBackgroundColor(getResources().getColor(R.color.RED));
        if(blueAllianceChosen) {
            backgroundColor = getResources().getColor(R.color.BBackground);
            textColor = getResources().getColor(R.color.BText);
            buttonColor = getResources().getColor(R.color.BButtonBackground);
            allianceInput.setBackgroundColor(getResources().getColor(R.color.BLUE));
        }

        backgroundScrollView.setBackgroundColor(backgroundColor);
        assignmentTextView.setTextColor(textColor);
        scouterNameTextView.setTextColor(textColor);
        scouterNameInput.setTextColor(textColor);
        matchNumTextView.setTextColor(textColor);
        matchNumInput.setTextColor(textColor);
        plusMatch.setBackgroundColor(buttonColor);
        plusMatch.setTextColor(textColor);
        minusMatch.setBackgroundColor(buttonColor);
        minusMatch.setTextColor(textColor);
        logOutButton.setBackgroundColor(buttonColor);
        logOutButton.setTextColor(textColor);
        teamNumTextView.setTextColor(textColor);
        teamNumInput.setTextColor(textColor);
        allianceTextView.setTextColor(textColor);
        allianceInput.setTextColor(textColor);
        autonomousTextView.setTextColor(textColor);
        startingPosTextView.setTextColor(textColor);
        startingPositionRadioGroup.setBackgroundColor(backgroundColor);
        closestRadioButton.setTextColor(textColor);
        middleRadioButton.setTextColor(textColor);
        farthestRadioButton.setTextColor(textColor);
        startBallsTextView.setTextColor(textColor);
        startingBallsRadioGroup.setBackgroundColor(backgroundColor);
        noneRadioButton.setTextColor(textColor);
        oneRadioButton.setTextColor(textColor);
        twoRadioButton.setTextColor(textColor);
        threeRadioButton.setTextColor(textColor);
        ballsScoredAutoTextView.setTextColor(textColor);
        bottomPortAutoTextView.setTextColor(textColor);
        ballsBottomAutoInput.setTextColor(textColor);
        plusBallsBottomAuto.setBackgroundColor(buttonColor);
        plusBallsBottomAuto.setTextColor(textColor);
        minusBallsBottomAuto.setBackgroundColor(buttonColor);
        minusBallsBottomAuto.setTextColor(textColor);
        outerPortAutoTextView.setTextColor(textColor);
        ballsOuterAutoInput.setTextColor(textColor);
        plusBallsOuterAuto.setBackgroundColor(buttonColor);
        plusBallsOuterAuto.setTextColor(textColor);
        minusBallsOuterAuto.setBackgroundColor(buttonColor);
        minusBallsOuterAuto.setTextColor(textColor);
        innerPortAutoTextView.setTextColor(textColor);
        ballsInnerAutoInput.setTextColor(textColor);
        plusBallsInnerAuto.setBackgroundColor(buttonColor);
        plusBallsInnerAuto.setTextColor(textColor);
        minusBallsInnerAuto.setBackgroundColor(buttonColor);
        minusBallsInnerAuto.setTextColor(textColor);
        ballsAcquiredAutoTextView.setTextColor(textColor);
        floorAutoTextView.setTextColor(textColor);
        ballsFloorAutoInput.setTextColor(textColor);
        plusBallsFloorAuto.setBackgroundColor(buttonColor);
        plusBallsFloorAuto.setTextColor(textColor);
        minusBallsFloorAuto.setBackgroundColor(buttonColor);
        minusBallsFloorAuto.setTextColor(textColor);
        crossesLineTextView.setTextColor(textColor);
        crossedLineTextView.setTextColor(textColor);
        teleoperatedTextView.setTextColor(textColor);
        ballsScoredTeleTextView.setTextColor(textColor);
        bottomPortTeleTextView.setTextColor(textColor);
        ballsBottomTeleInput.setTextColor(textColor);
        plusBallsBottomTele.setBackgroundColor(buttonColor);
        plusBallsBottomTele.setTextColor(textColor);
        minusBallsBottomTele.setBackgroundColor(buttonColor);
        minusBallsBottomTele.setTextColor(textColor);
        outerPortTeleTextView.setTextColor(textColor);
        ballsOuterTeleInput.setTextColor(textColor);
        plusBallsOuterTele.setBackgroundColor(buttonColor);
        plusBallsOuterTele.setTextColor(textColor);
        minusBallsOuterTele.setBackgroundColor(buttonColor);
        minusBallsOuterTele.setTextColor(textColor);
        innerPortTeleTextView.setTextColor(textColor);
        ballsInnerTeleInput.setTextColor(textColor);
        plusBallsInnerTele.setBackgroundColor(buttonColor);
        plusBallsInnerTele.setTextColor(textColor);
        minusBallsInnerTele.setBackgroundColor(buttonColor);
        minusBallsInnerTele.setTextColor(textColor);
        ballsAcquiredTeleTextView.setTextColor(textColor);
        floorTeleTextView.setTextColor(textColor);
        floorTeleInput.setTextColor(textColor);
        plusBallsFloorTele.setBackgroundColor(buttonColor);
        plusBallsFloorTele.setTextColor(textColor);
        minusBallsFloorTele.setBackgroundColor(buttonColor);
        minusBallsFloorTele.setTextColor(textColor);
        lowChuteTeleTextView.setTextColor(textColor);
        ballsLowChuteTeleInput.setTextColor(textColor);
        plusBallsLowChuteTele.setBackgroundColor(buttonColor);
        plusBallsLowChuteTele.setTextColor(textColor);
        minusBallsLowChuteTele.setBackgroundColor(buttonColor);
        minusBallsLowChuteTele.setTextColor(textColor);
        highChuteTeleTextView.setTextColor(textColor);
        ballsHighChuteTeleInput.setTextColor(textColor);
        plusBallsHighChuteTele.setBackgroundColor(buttonColor);
        plusBallsHighChuteTele.setTextColor(textColor);
        minusBallsHighChuteTele.setBackgroundColor(buttonColor);
        minusBallsHighChuteTele.setTextColor(textColor);
        controlPanelInteractionTextView.setTextColor(textColor);
        performedRotationControlTextView.setTextColor(textColor);
        performedPositionControlTextView.setTextColor(textColor);
        endGameTextView.setTextColor(textColor);
        rendezuousTextView.setTextColor(textColor);
        rendezuousRadioGroup.setBackgroundColor(backgroundColor);
        noparkRadioButton.setTextColor(textColor);
        parkedRadioButton.setTextColor(textColor);
        hangedRadioButton.setTextColor(textColor);
        levelingTextView.setTextColor(textColor);
        activeLevelingRadioGroup.setBackgroundColor(backgroundColor);
        noLevelingRadioButton.setTextColor(textColor);
        triedToLevelRadioButton.setTextColor(textColor);
        successfullyLeveledRadioButton.setTextColor(textColor);
        otherTextView.setTextColor(textColor);
        defenseTextView.setTextColor(textColor);
        excellecentDefenseTextView.setTextColor(textColor);
        foulTextView.setTextColor(textColor);
        causedFouldTextView.setTextColor(textColor);
        robotStateTextView.setTextColor(textColor);
        brokeDisabledTextView.setTextColor(textColor);
        saveButton.setBackgroundColor(buttonColor);
        saveButton.setTextColor(textColor);
    }

    private void login(){
        AlertDialog.Builder builder = new AlertDialog.Builder(Scouting.this);
        builder.setTitle("Login");
        builder.setMessage("Please log in to continue");
        // Set an EditText view to get user input
        final EditText input = new EditText(this);
        builder.setView(input);

        builder.setPositiveButton("login", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int whichButton) {
                String value = input.getText().toString();
                editor.putString(getString(R.string.scouter), value);
                editor.apply();
                scouterNameInput.setText(value);
            }
        });
        builder.setNegativeButton("Cancel" ,new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                finish();
            }
        });
        builder.create().show();
    }

    private boolean findTeam(){
        boolean found = false;
        String matchnum = matchNumInput.getText().toString();
        HashMap<String, BlueAllianceMatch> matches = BlueAllianceMatch.getMatches();

        if(matches.containsKey(matchnum)) {
            BlueAllianceMatch matchObj = matches.get(matchnum);
            ArrayList<String> allainceKeySet;
            if(blueAllianceChosen){
                allainceKeySet = matchObj.getBlueTeamKeys();
            }
            else {
                allainceKeySet = matchObj.getRedTeamKeys();
            }
            int teamPosition = settings.getInt(getResources().getString(R.string.pref_TeamPosition),0);
            String teamText = (allainceKeySet.get(teamPosition-1)).replace("frc","");
            teamNumInput.setText(teamText);
            found = true;
        }
        else {
            Toast.makeText(Scouting.this, "Match Not Found " + matchnum,Toast.LENGTH_LONG).show();
        }
        return found;
    }
}