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
import com.sparx1126.a2020_scouting.Utilities.ScoutingData;
import com.sparx1126.a2020_scouting.Utilities.SendMail;

import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Scouting extends AppCompatActivity {
    static String TAG = "Sparx: ";
    static String HEADER = "Scouting: ";

    private SharedPreferences.Editor editor;
    private Button logout;
    private TextView name;
    private SharedPreferences settings;

    private Button plusMatch;
    private TextView txtMatch;
    private TextView teamScouting;
    private Button minusMatch;
    private Button plusBallsBottemAuto;
    private TextView txtBallsBottomAuto;
    private Button minusBallsBottemAuto;
    private Button plusBallsOuterAuto;
    private TextView txtBallsOuterAuto;
    private Button minusBallsOuterAuto;
    private Button plusBallsInnerAuto;
    private TextView txtBallsInnerAuto;
    private Button minusBallsInnerAuto;
    private Button plusBallsFloorAuto;
    private TextView txtBallsFloorAuto;
    private Button minusBallsFloorAuto;
    private Button plusBallsBottemTele;
    private TextView txtBallsBottemTele;
    private Button minusBallsBottemTele;
    private Button plusBallsOuterTele;
    private TextView txtBallsOuterTele;
    private Button minusBallsOuterTele;
    private Button plusBallsInnerTele;
    private TextView txtBallsInnerTele;
    private Button minusBallsInnerTele;
    private Button plusBallsFloorTele;
    private TextView txtBallsFloorTele;
    private Button minusBallsFloorTele;
    private Button plusBallsLowChuteTele;
    private TextView txtBallsLowChuteTele;
    private Button minusBallsLowChuteTele;
    private Button plusBallsHighChuteTele;
    private TextView txtBallsHighChuteTele;
    private Button minusBallsHighChuteTele;

    private Button save;
    private SendMail mail;
    private boolean blueAllianceChosen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scouting);
        Log.d(TAG, HEADER + "onCreate");

        settings = getSharedPreferences(getString(R.string.SPARX_PREFS), 0);
        editor = settings.edit();
        blueAllianceChosen = settings.getBoolean(getResources().getString(R.string.pref_BlueAlliance), false);

        save = findViewById(R.id.Save);
        logout = findViewById(R.id.logOut);
        name = findViewById(R.id.name);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Save();
            }
        });

        plusMatch = findViewById(R.id.plusMatch);
        txtMatch = findViewById(R.id.txtMatch);
        minusMatch = findViewById(R.id.minusMatch);
        teamScouting = findViewById(R.id.scoutingTeam);

        txtMatch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
               if(findTeam()) {
                   RestoreData(txtMatch.getText().toString());
               }
            }
        });

        plusMatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                plusBalls(txtMatch);
            }
        });

        minusMatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                minusBalls(txtMatch);
            }
        });

        plusBallsBottemAuto = findViewById(R.id.plusBallsBottemAuto);
        txtBallsBottomAuto = findViewById(R.id.txtBallsBottomAuto);
        minusBallsBottemAuto = findViewById(R.id.minusBallsBottemAuto);

        plusBallsBottemAuto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                plusBalls(txtBallsBottomAuto);
            }
        });
        minusBallsBottemAuto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                minusBalls(txtBallsBottomAuto);
            }
        });

        plusBallsOuterAuto = findViewById(R.id.plusBallsOuterAuto);
        txtBallsOuterAuto = findViewById(R.id.txtBallsOuterAuto);
        minusBallsOuterAuto = findViewById(R.id.minusBallsOuterAuto);

        plusBallsOuterAuto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                plusBalls(txtBallsOuterAuto);
            }
        });
        minusBallsOuterAuto.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                minusBalls(txtBallsOuterAuto);
            }
        });

        plusBallsInnerAuto = findViewById(R.id.plusBallsInnerAuto);
        txtBallsInnerAuto = findViewById(R.id.txtBallsInnerAuto);
        minusBallsInnerAuto = findViewById(R.id.minusBallsInnerAuto);

        plusBallsInnerAuto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                plusBalls(txtBallsInnerAuto);
            }
        });
        minusBallsInnerAuto.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                minusBalls(txtBallsInnerAuto);
            }
        });

        plusBallsFloorAuto = findViewById(R.id.plusBallsFloorAuto);
        txtBallsFloorAuto = findViewById(R.id.txtBallsFloorAuto);
        minusBallsFloorAuto = findViewById(R.id.minusBallsFloorAuto);

        plusBallsFloorAuto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                plusBalls(txtBallsFloorAuto);
            }
        });
        minusBallsFloorAuto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                minusBalls(txtBallsFloorAuto);
            }
        });

        plusBallsBottemTele = findViewById(R.id.plusBallsBottemTele);
        txtBallsBottemTele = findViewById(R.id.txtBallsBottemTele);
        minusBallsBottemTele = findViewById(R.id.minusBallsBottemTele);

        plusBallsBottemTele.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                plusBalls(txtBallsBottemTele);
            }
        });
        minusBallsBottemTele.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                minusBalls(txtBallsBottemTele);
            }
        });

        plusBallsOuterTele = findViewById(R.id.plusBallsOuterTele);
        txtBallsOuterTele = findViewById(R.id.txtBallsOuterTele);
        minusBallsOuterTele = findViewById(R.id.minusBallsOuterTele);

        plusBallsOuterTele.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                plusBalls(txtBallsOuterTele);
            }
        });
        minusBallsOuterTele.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                minusBalls(txtBallsOuterTele);
            }
        });

        plusBallsInnerTele = findViewById(R.id.plusBallsInnerTele);
        txtBallsInnerTele = findViewById(R.id.txtBallsInnerTele);
        minusBallsInnerTele = findViewById(R.id.minusBallsInnerTele);

        plusBallsInnerTele.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                plusBalls(txtBallsInnerTele);
            }
        });
        minusBallsInnerTele.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                minusBalls(txtBallsInnerTele);
            }
        });

        plusBallsFloorTele = findViewById(R.id.plusBallsFloorTele);
        txtBallsFloorTele = findViewById(R.id.txtFloorTele);
        minusBallsFloorTele = findViewById(R.id.minusBallsFloorTele);

        plusBallsFloorTele.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                plusBalls(txtBallsFloorTele);
            }
        });
        minusBallsFloorTele.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                minusBalls(txtBallsFloorTele);
            }
        });

        plusBallsLowChuteTele = findViewById(R.id.plusBallsLowChuteTele);
        txtBallsLowChuteTele = findViewById(R.id.txtBallsLowChuteTele);
        minusBallsLowChuteTele = findViewById(R.id.minusBallsLowChuteTele);

        plusBallsLowChuteTele.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                plusBalls(txtBallsLowChuteTele);
            }
        });
        minusBallsLowChuteTele.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                minusBalls(txtBallsLowChuteTele);
            }
        });

        plusBallsHighChuteTele = findViewById(R.id.plusBallsHighChuteTele);
        txtBallsHighChuteTele = findViewById(R.id.txtBallsHighChuteTele);
        minusBallsHighChuteTele = findViewById(R.id.minusBallsHighChuteTele);

        plusBallsHighChuteTele.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                plusBalls(txtBallsHighChuteTele);
            }
        });
        minusBallsHighChuteTele.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                minusBalls(txtBallsHighChuteTele);
            }
        });


        logout.setOnClickListener(new View.OnClickListener()
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

        changeUi();
        String scouter = settings.getString(getString(R.string.scouter), "");
        if(scouter.isEmpty()) {
            login();
        }
        else {
            name.setText(scouter);
        }
    }

    public void RestoreData(final String matchNum){
       Map<String, ScoutingData> data = ScoutingData.getData();

       if(data.get(matchNum).getStartingPos().equals("Closest to Power Port")){
           RadioGroup startingGroup = findViewById(R.id.startingPosition);
           startingGroup.check(R.id.closest);
       }else if(data.get(matchNum).getStartingPos().equals("Middle")){
           RadioGroup startingGroup = findViewById(R.id.startingPosition);
           startingGroup.check(R.id.middle);
       }else if(data.get(matchNum).getStartingPos().equals("Farthest to Power Port")){
           RadioGroup startingGroup = findViewById(R.id.startingPosition);
           startingGroup.check(R.id.farthest);
       }else{
           RadioGroup startingGroup = findViewById(R.id.startingPosition);
           startingGroup.clearCheck();
       }

       if(data.get(matchNum).getPowerCellAtStart().equals("None")){
            RadioGroup startingBalls = findViewById(R.id.startingBalls);
            startingBalls.check(R.id.none);
       }else if(data.get(matchNum).getPowerCellAtStart().equals("One")){
           RadioGroup startingBalls = findViewById(R.id.startingBalls);
           startingBalls.check(R.id.one);
       }else if(data.get(matchNum).getPowerCellAtStart().equals("Two")){
           RadioGroup startingBalls = findViewById(R.id.startingBalls);
           startingBalls.check(R.id.one);
       }else if(data.get(matchNum).getPowerCellAtStart().equals("Three")){
           RadioGroup startingBalls = findViewById(R.id.startingBalls);
           startingBalls.check(R.id.one);
       }else {
           RadioGroup startingBalls = findViewById(R.id.startingBalls);
           startingBalls.clearCheck();
       }

       txtBallsBottomAuto.setText(String.valueOf(data.get(matchNum).getBallsScoredOnBottomAuto()));
       txtBallsOuterAuto.setText(String.valueOf(data.get(matchNum).getBallsScoredOnOuterAuto()));
       txtBallsInnerAuto.setText(String.valueOf(data.get(matchNum).getBallsScoredOnInnerAuto()));

       txtBallsFloorAuto.setText(String.valueOf(data.get(matchNum).getBallsAcquiredFloorAuto()));

        CheckBox crossesLine = findViewById(R.id.crossesLineCheckBox);
        crossesLine.setChecked(Boolean.valueOf(String.valueOf(data.get(matchNum).isCrossesInitiatoinLine())));

        txtBallsBottemTele.setText(String.valueOf(data.get(matchNum).getBallsScoredOnBottomTele()));
        txtBallsOuterTele.setText(String.valueOf(data.get(matchNum).getBallsScoredOnOuterTele()));
        txtBallsInnerTele.setText(String.valueOf(data.get(matchNum).getBallsScoredOnInnerTele()));

        txtBallsFloorTele.setText(String.valueOf(data.get(matchNum).getBallsAcquiredFloorTele()));
        txtBallsLowChuteTele.setText(String.valueOf(data.get(matchNum).getBallsAcquiredLowChuteTele()));
        txtBallsHighChuteTele.setText(String.valueOf(data.get(matchNum).getBallsAcquiredHighChuteTele()));

        CheckBox performedRotationControlCheckBox = findViewById(R.id.performedRotationControlCheckBox);
        performedRotationControlCheckBox.setChecked(Boolean.valueOf(String.valueOf(data.get(matchNum).isPreformedRotationControl())));

        CheckBox performedPositionControlCheckBox = findViewById(R.id.performedPositionControlCheckBox);
        performedPositionControlCheckBox.setChecked(Boolean.valueOf(String.valueOf(data.get(matchNum).isPerformedPositionControl())));

        CheckBox hangingCheckBox = findViewById(R.id.hangingCheckBox);
        hangingCheckBox.setChecked(Boolean.valueOf(String.valueOf(data.get(matchNum).isHanging())));

        CheckBox parkedCheckBox = findViewById(R.id.parkedCheckBox);
        parkedCheckBox.setChecked(Boolean.valueOf(String.valueOf(data.get(matchNum).isParked())));

        if(data.get(matchNum).getLeveling().equals("No leveling")){
            RadioGroup level = findViewById(R.id.level);
            level.check(R.id.noLeveling);
        }else if(data.get(matchNum).getLeveling().equals("Tried to level")){
            RadioGroup level = findViewById(R.id.level);
            level.check(R.id.triedToLevel);
        }else if(data.get(matchNum).getLeveling().equals("successfullyLeveled")){
            RadioGroup level = findViewById(R.id.level);
            level.check(R.id.successfullyLeveled);
        }else{
            RadioGroup level = findViewById(R.id.level);
            level.clearCheck();
        }
    }

    public void Save(){
        Map<String, ScoutingData> data = ScoutingData.getData();

        RadioGroup starting = findViewById(R.id.startingPosition);
        RadioButton startingpos = findViewById(starting.getCheckedRadioButtonId());
        RadioGroup startingb = findViewById(R.id.startingBalls);
        RadioButton startingballs = findViewById(startingb.getCheckedRadioButtonId());
        CheckBox crossesLine = findViewById(R.id.crossesLineCheckBox);
        CheckBox performendRot = findViewById(R.id.performedRotationControlCheckBox);
        CheckBox performedPos = findViewById(R.id.performedPositionControlCheckBox);
        CheckBox hanging = findViewById(R.id.hangingCheckBox);
        CheckBox parked  = findViewById(R.id.parkedCheckBox);
        RadioGroup level = findViewById(R.id.level);
        RadioButton leveled = findViewById(level.getCheckedRadioButtonId());

        if(!(startingpos == null) && !(startingballs == null) && !(leveled == null)){
            ScoutingData scoutingData = new ScoutingData(name.getText().toString(), txtMatch.getText().toString(), teamScouting.getText().toString(), startingpos.getText().toString(),
                    startingballs.getText().toString(), txtBallsBottomAuto.getText().toString(), txtBallsOuterAuto.getText().toString(),
                    txtBallsInnerAuto.getText().toString(), txtBallsFloorAuto.getText().toString(), ""+ crossesLine.isChecked(), txtBallsBottemTele.getText().toString(),
                    txtBallsOuterTele.getText().toString(), txtBallsInnerTele.getText().toString(), txtBallsFloorTele.getText().toString(), txtBallsLowChuteTele.getText().toString(),
                    txtBallsHighChuteTele.getText().toString(), ""+performendRot.isChecked(), ""+performedPos.isChecked(), ""+hanging.isChecked(),
                    ""+parked.isChecked(), leveled.getText().toString());
            String email = settings.getString(getString(R.string.EMAIL), "");
            String subject = settings.getString("pref_SelectedEvent",null)+".frc" + teamScouting.getText().toString()+ "."+ txtMatch.getText().toString()+".json";
            mail = new SendMail(Scouting.this, email, subject, scoutingData.toJson());
            mail.execute();
        }else{
            Toast.makeText(this, "Don't forget to fill out hte entire form", Toast.LENGTH_LONG).show();
        }

        data.get(txtMatch.getText().toString()).setScouterName(name.getText().toString());
        data.get(txtMatch.getText().toString()).setMatchNumber(txtMatch.getText().toString());
        data.get(txtMatch.getText().toString()).setStartingPos(startingpos.getText().toString());
        data.get(txtMatch.getText().toString()).setPowerCellAtStart(startingballs.getText().toString());
        data.get(txtMatch.getText().toString()).setBallsScoredOnBottomAuto(txtBallsBottomAuto.getText().toString());
        data.get(txtMatch.getText().toString()).setBallsScoredOnOuterAuto(txtBallsOuterAuto.getText().toString());
        data.get(txtMatch.getText().toString()).setBallsScoredOnInnerAuto(txtBallsInnerAuto.getText().toString());
        data.get(txtMatch.getText().toString()).setBallsAcquiredFloorAuto(txtBallsFloorAuto.getText().toString());
        data.get(txtMatch.getText().toString()).setCrossesInitiatoinLine(String.valueOf(crossesLine.isChecked()));
        data.get(txtMatch.getText().toString()).setBallsScoredOnBottomTele(txtBallsBottemTele.getText().toString());
        data.get(txtMatch.getText().toString()).setBallsScoredOnOuterTele(txtBallsOuterTele.getText().toString());
        data.get(txtMatch.getText().toString()).setBallsScoredOnInnerTele(txtBallsInnerTele.getText().toString());
        data.get(txtMatch.getText().toString()).setBallsAcquiredFloorTele(txtBallsFloorTele.getText().toString());
        data.get(txtMatch.getText().toString()).setBallsAcquiredLowChuteTele(txtBallsLowChuteTele.getText().toString());
        data.get(txtMatch.getText().toString()).setBallsAcquiredHighChuteTele(txtBallsHighChuteTele.getText().toString());
        data.get(txtMatch.getText().toString()).setPreformedRotationControl(String.valueOf(performendRot.isChecked()));
        data.get(txtMatch.getText().toString()).setPerformedPositionControl(String.valueOf(performedPos.isChecked()));
        data.get(txtMatch.getText().toString()).setHanging(String.valueOf(hanging.isChecked()));
        data.get(txtMatch.getText().toString()).setParked(String.valueOf(parked.isChecked()));
        data.get(txtMatch.getText().toString()).setLeveling(leveled.getText().toString());
        Toast.makeText(this, "Data was saved on device", Toast.LENGTH_LONG).show();

    }

    public void plusBalls(TextView view){
        int value = Integer.parseInt(view.getText().toString());
        view.setText(String.valueOf(value + 1));
    }
    public void minusBalls(TextView view){
        int value = Integer.parseInt(view.getText().toString());
        view.setText(String.valueOf(value - 1));
    }

    public  void changeUi(){
        if(blueAllianceChosen){
            ScrollView background = findViewById(R.id.background);
            background.setBackgroundColor(getResources().getColor(R.color.BBackground));
            TextView assignment = findViewById(R.id.assignment);
            assignment.setTextColor(getResources().getColor(R.color.BText));
            TextView txtName = findViewById(R.id.txtname);
            txtName.setTextColor(getResources().getColor(R.color.BText));
            TextView name = findViewById(R.id.name);
            name.setTextColor(getResources().getColor(R.color.BText));
            TextView matchNum = findViewById(R.id.matchNum);
            matchNum.setTextColor(getResources().getColor(R.color.BText));
            Button plusMatch = findViewById(R.id.plusMatch);
            plusMatch.setTextColor(getResources().getColor(R.color.BText));
            plusMatch.setBackgroundColor(getResources().getColor(R.color.BButtonBackground));
            TextView txtMatch = findViewById(R.id.txtMatch);
            txtMatch.setTextColor(getResources().getColor(R.color.BText));
            Button minusMatch = findViewById(R.id.minusMatch);
            minusMatch.setTextColor(getResources().getColor(R.color.BText));
            minusMatch.setBackgroundColor(getResources().getColor(R.color.BButtonBackground));
            Button logOut = findViewById(R.id.logOut);
            logOut.setBackgroundColor(getResources().getColor(R.color.BButtonBackground));
            logOut.setTextColor(getResources().getColor(R.color.BText));
            TextView team = findViewById(R.id.team);
            team.setTextColor(getResources().getColor(R.color.BText));
            TextView teamScouting = findViewById(R.id.scoutingTeam);
            teamScouting.setTextColor(getResources().getColor(R.color.BText));
            TextView alliance = findViewById(R.id.alliance);
            alliance.setTextColor(getResources().getColor(R.color.BText));
            TextView allianceColor = findViewById(R.id.allianceColor);
            allianceColor.setBackgroundColor(getResources().getColor(R.color.BLUE));
            TextView auto = findViewById(R.id.autonomous);
            auto.setTextColor(getResources().getColor(R.color.BText));
            TextView startingPos = findViewById(R.id.startingPos);
            startingPos.setTextColor(getResources().getColor(R.color.BText));
            RadioButton closest = findViewById(R.id.closest);
            closest.setTextColor(getResources().getColor(R.color.BText));
            RadioButton middle = findViewById(R.id.middle);
            middle.setTextColor(getResources().getColor(R.color.BText));
            RadioButton farthest = findViewById(R.id.farthest);
            farthest.setTextColor(getResources().getColor(R.color.BText));
            TextView startBalls = findViewById(R.id.startBalls);
            startBalls.setTextColor(getResources().getColor(R.color.BText));
            RadioButton none = findViewById(R.id.none);
            none.setTextColor(getResources().getColor(R.color.BText));
            RadioButton one = findViewById(R.id.one);
            one.setTextColor(getResources().getColor(R.color.BText));
            RadioButton two = findViewById(R.id.two);
            two.setTextColor(getResources().getColor(R.color.BText));
            RadioButton three = findViewById(R.id.three);
            three.setTextColor(getResources().getColor(R.color.BText));
            TextView scoredBalls = findViewById(R.id.ballsScoredAuto);
            scoredBalls.setTextColor(getResources().getColor(R.color.BText));
            TextView bottemPortAuto = findViewById(R.id.bottomPortAuto);
            bottemPortAuto.setTextColor(getResources().getColor(R.color.BText));
            Button plusBallsBottemAuto = findViewById(R.id.plusBallsBottemAuto);
            plusBallsBottemAuto.setTextColor(getResources().getColor(R.color.BText));
            plusBallsBottemAuto.setBackgroundColor(getResources().getColor(R.color.BButtonBackground));
            TextView txtBallsBottemAuto = findViewById(R.id.txtBallsBottomAuto);
            txtBallsBottemAuto.setTextColor(getResources().getColor(R.color.BText));
            Button minusBallsBottemAuto = findViewById(R.id.minusBallsBottemAuto);
            minusBallsBottemAuto.setTextColor(getResources().getColor(R.color.BText));
            minusBallsBottemAuto.setBackgroundColor(getResources().getColor(R.color.BButtonBackground));
            TextView outerPortAuto = findViewById(R.id.outerPortAuto);
            outerPortAuto.setTextColor(getResources().getColor(R.color.BText));
            Button plusBallsOuterAuto = findViewById(R.id.plusBallsOuterAuto);
            plusBallsOuterAuto.setTextColor(getResources().getColor(R.color.BText));
            plusBallsOuterAuto.setBackgroundColor(getResources().getColor(R.color.BButtonBackground));
            TextView txtBallsOuterAuto = findViewById(R.id.txtBallsOuterAuto);
            txtBallsOuterAuto.setTextColor(getResources().getColor(R.color.BText));
            Button minusBallsOuterAuto = findViewById(R.id.minusBallsOuterAuto);
            minusBallsOuterAuto.setTextColor(getResources().getColor(R.color.BText));
            minusBallsOuterAuto.setBackgroundColor(getResources().getColor(R.color.BButtonBackground));
            TextView innerPortAuto = findViewById(R.id.innerPortAuto);
            innerPortAuto.setTextColor(getResources().getColor(R.color.BText));
            Button plusBallsInnerAuto = findViewById(R.id.plusBallsInnerAuto);
            plusBallsInnerAuto.setTextColor(getResources().getColor(R.color.BText));
            plusBallsInnerAuto.setBackgroundColor(getResources().getColor(R.color.BButtonBackground));
            TextView txtBallsInnerAuto = findViewById(R.id.txtBallsInnerAuto);
            txtBallsInnerAuto.setTextColor(getResources().getColor(R.color.BText));
            Button minusBallsInnerAuto = findViewById(R.id.minusBallsInnerAuto);
            minusBallsInnerAuto.setTextColor(getResources().getColor(R.color.BText));
            minusBallsInnerAuto.setBackgroundColor(getResources().getColor(R.color.BButtonBackground));
            TextView ballsAquAuto = findViewById(R.id.ballsAcquiredAuto);
            ballsAquAuto.setTextColor(getResources().getColor(R.color.BText));
            TextView floorAuto = findViewById(R.id.floorAuto);
            floorAuto.setTextColor(getResources().getColor(R.color.BText));
            Button plusBallsFloorAuto = findViewById(R.id.plusBallsFloorAuto);
            plusBallsFloorAuto.setTextColor(getResources().getColor(R.color.BText));
            plusBallsFloorAuto.setBackgroundColor(getResources().getColor(R.color.BButtonBackground));
            TextView txtBallsFloorAuto = findViewById(R.id.txtBallsFloorAuto);
            txtBallsFloorAuto.setTextColor(getResources().getColor(R.color.BText));
            Button minusBallsFloorAuto = findViewById(R.id.minusBallsFloorAuto);
            minusBallsFloorAuto.setTextColor(getResources().getColor(R.color.BText));
            minusBallsFloorAuto.setBackgroundColor(getResources().getColor(R.color.BButtonBackground));
            TextView crossesLine = findViewById(R.id.crossesLine);
            crossesLine.setTextColor(getResources().getColor(R.color.BText));
            TextView txtCrossesLine = findViewById(R.id.txtCrossesLine);
            txtCrossesLine.setTextColor(getResources().getColor(R.color.BText));
            TextView tele = findViewById(R.id.teleoperated);
            tele.setTextColor(getResources().getColor(R.color.BText));
            TextView ballsScoredTele = findViewById(R.id.ballsScoredTele);
            ballsScoredTele.setTextColor(getResources().getColor(R.color.BText));
            TextView bottomPortTele = findViewById(R.id.bottomPortTele);
            bottomPortTele.setTextColor(getResources().getColor(R.color.BText));
            Button plusBallsBottemtele = findViewById(R.id.plusBallsBottemTele);
            plusBallsBottemtele.setTextColor(getResources().getColor(R.color.BText));
            plusBallsBottemtele.setBackgroundColor(getResources().getColor(R.color.BButtonBackground));
            TextView txtBallsBottemTele = findViewById(R.id.txtBallsBottemTele);
            txtBallsBottemTele.setTextColor(getResources().getColor(R.color.BText));
            Button minusBallsBottemTele = findViewById(R.id.minusBallsBottemTele);
            minusBallsBottemTele.setTextColor(getResources().getColor(R.color.BText));
            minusBallsBottemTele.setBackgroundColor(getResources().getColor(R.color.BButtonBackground));
            TextView outerPortTele = findViewById(R.id.outerPortTele);
            outerPortTele.setTextColor(getResources().getColor(R.color.BText));
            Button plusBallsOuterTele = findViewById(R.id.plusBallsOuterTele);
            plusBallsOuterTele.setTextColor(getResources().getColor(R.color.BText));
            plusBallsOuterTele.setBackgroundColor(getResources().getColor(R.color.BButtonBackground));
            TextView txtBallsOuterTele = findViewById(R.id.txtBallsOuterTele);
            txtBallsOuterTele.setTextColor(getResources().getColor(R.color.BText));
            Button minusBallsOuterTele = findViewById(R.id.minusBallsOuterTele);
            minusBallsOuterTele.setTextColor(getResources().getColor(R.color.BText));
            minusBallsOuterTele.setBackgroundColor(getResources().getColor(R.color.BButtonBackground));
            TextView innerPortTele = findViewById(R.id.innerPortTele);
            innerPortTele.setTextColor(getResources().getColor(R.color.BText));
            Button plusBallsInnerTele = findViewById(R.id.plusBallsInnerTele);
            plusBallsInnerTele.setTextColor(getResources().getColor(R.color.BText));
            plusBallsInnerTele.setBackgroundColor(getResources().getColor(R.color.BButtonBackground));
            TextView txtBallsInnerTele = findViewById(R.id.txtBallsInnerTele);
            txtBallsInnerTele.setTextColor(getResources().getColor(R.color.BText));
            Button minusBallsInnerTele = findViewById(R.id.minusBallsInnerTele);
            minusBallsInnerTele.setTextColor(getResources().getColor(R.color.BText));
            minusBallsInnerTele.setBackgroundColor(getResources().getColor(R.color.BButtonBackground));
            TextView ballsAquTele = findViewById(R.id.ballsAcquiredTele);
            ballsAquTele.setTextColor(getResources().getColor(R.color.BText));
            TextView floorTele = findViewById(R.id.floorTele);
            floorTele.setTextColor(getResources().getColor(R.color.BText));
            Button plusBallsFloorTele = findViewById(R.id.plusBallsFloorTele);
            plusBallsFloorTele.setTextColor(getResources().getColor(R.color.BText));
            plusBallsFloorTele.setBackgroundColor(getResources().getColor(R.color.BButtonBackground));
            TextView txtBallsFloorTele = findViewById(R.id.txtFloorTele);
            txtBallsFloorTele.setTextColor(getResources().getColor(R.color.BText));
            Button minusBallsFloorTele = findViewById(R.id.minusBallsFloorTele);
            minusBallsFloorTele.setTextColor(getResources().getColor(R.color.BText));
            minusBallsFloorTele.setBackgroundColor(getResources().getColor(R.color.BButtonBackground));
            TextView lowChuteTele = findViewById(R.id.lowChuteTele);
            lowChuteTele.setTextColor(getResources().getColor(R.color.BText));
            Button plusBallsLowChuteTele = findViewById(R.id.plusBallsLowChuteTele);
            plusBallsLowChuteTele.setTextColor(getResources().getColor(R.color.BText));
            plusBallsLowChuteTele.setBackgroundColor(getResources().getColor(R.color.BButtonBackground));
            TextView txtBallsLowChuteTele = findViewById(R.id.txtBallsLowChuteTele);
            txtBallsLowChuteTele.setTextColor(getResources().getColor(R.color.BText));
            Button minusBallsLowChuteTele = findViewById(R.id.minusBallsLowChuteTele);
            minusBallsLowChuteTele.setTextColor(getResources().getColor(R.color.BText));
            minusBallsLowChuteTele.setBackgroundColor(getResources().getColor(R.color.BButtonBackground));
            TextView highChuteTele = findViewById(R.id.highChuteTele);
            highChuteTele.setTextColor(getResources().getColor(R.color.BText));
            Button plusBallsHighChuteTele = findViewById(R.id.plusBallsHighChuteTele);
            plusBallsHighChuteTele.setTextColor(getResources().getColor(R.color.BText));
            plusBallsHighChuteTele.setBackgroundColor(getResources().getColor(R.color.BButtonBackground));
            TextView txtBallsHighChuteTele = findViewById(R.id.txtBallsHighChuteTele);
            txtBallsHighChuteTele.setTextColor(getResources().getColor(R.color.BText));
            Button minusBallsHighChuteTele = findViewById(R.id.minusBallsHighChuteTele);
            minusBallsHighChuteTele.setTextColor(getResources().getColor(R.color.BText));
            minusBallsHighChuteTele.setBackgroundColor(getResources().getColor(R.color.BButtonBackground));
            TextView controlPannel = findViewById(R.id.controlPanelInteraction);
            controlPannel.setTextColor(getResources().getColor(R.color.BText));
            TextView performdRotation = findViewById(R.id.performedRotationControl);
            performdRotation.setTextColor(getResources().getColor(R.color.BText));
            TextView performedPosition = findViewById(R.id.performedPositoinControl);
            performedPosition.setTextColor(getResources().getColor(R.color.BText));
            TextView endGame = findViewById(R.id.endGame);
            endGame.setTextColor(getResources().getColor(R.color.BText));
            TextView rendezuous = findViewById(R.id.rendezuous);
            rendezuous.setTextColor(getResources().getColor(R.color.BText));
            TextView hanging = findViewById(R.id.hanging);
            hanging.setTextColor(getResources().getColor(R.color.BText));
            TextView parked = findViewById(R.id.parked);
            parked.setTextColor(getResources().getColor(R.color.BText));
            TextView level = findViewById(R.id.leveling);
            level.setTextColor(getResources().getColor(R.color.BText));
            RadioButton noLeveling = findViewById(R.id.noLeveling);
            noLeveling.setTextColor(getResources().getColor(R.color.BText));
            RadioButton triedToLevel = findViewById(R.id.triedToLevel);
            triedToLevel.setTextColor(getResources().getColor(R.color.BText));
            RadioButton successfullyLeveled = findViewById(R.id.successfullyLeveled);
            successfullyLeveled.setTextColor(getResources().getColor(R.color.BText));
            Button save = findViewById(R.id.Save);
            save.setTextColor(getResources().getColor(R.color.BText));
            save.setBackgroundColor(getResources().getColor(R.color.BButtonBackground));
        }else{
            ScrollView background = findViewById(R.id.background);
            background.setBackgroundColor(getResources().getColor(R.color.RBackground));
            TextView assignment = findViewById(R.id.assignment);
            assignment.setTextColor(getResources().getColor(R.color.RText));
            TextView txtName = findViewById(R.id.txtname);
            txtName.setTextColor(getResources().getColor(R.color.RText));
            TextView name = findViewById(R.id.name);
            name.setTextColor(getResources().getColor(R.color.RText));
            TextView matchNum = findViewById(R.id.matchNum);
            matchNum.setTextColor(getResources().getColor(R.color.RText));
            Button plusMatch = findViewById(R.id.plusMatch);
            plusMatch.setTextColor(getResources().getColor(R.color.RText));
            plusMatch.setBackgroundColor(getResources().getColor(R.color.RButtonBackground));
            TextView txtMatch = findViewById(R.id.txtMatch);
            txtMatch.setTextColor(getResources().getColor(R.color.RText));
            Button minusMatch = findViewById(R.id.minusMatch);
            minusMatch.setTextColor(getResources().getColor(R.color.RText));
            minusMatch.setBackgroundColor(getResources().getColor(R.color.RButtonBackground));
            Button logOut = findViewById(R.id.logOut);
            logOut.setBackgroundColor(getResources().getColor(R.color.RButtonBackground));
            logOut.setTextColor(getResources().getColor(R.color.RText));
            TextView team = findViewById(R.id.team);
            team.setTextColor(getResources().getColor(R.color.RText));
            TextView teamScouting = findViewById(R.id.scoutingTeam);
            teamScouting.setTextColor(getResources().getColor(R.color.RText));
            TextView alliance = findViewById(R.id.alliance);
            alliance.setTextColor(getResources().getColor(R.color.RText));
            TextView allianceColor = findViewById(R.id.allianceColor);
            allianceColor.setBackgroundColor(getResources().getColor(R.color.RED));
            TextView auto = findViewById(R.id.autonomous);
            auto.setTextColor(getResources().getColor(R.color.RText));
            TextView startingPos = findViewById(R.id.startingPos);
            startingPos.setTextColor(getResources().getColor(R.color.RText));
            RadioButton closest = findViewById(R.id.closest);
            closest.setTextColor(getResources().getColor(R.color.RText));
            RadioButton middle = findViewById(R.id.middle);
            middle.setTextColor(getResources().getColor(R.color.RText));
            RadioButton farthest = findViewById(R.id.farthest);
            farthest.setTextColor(getResources().getColor(R.color.RText));
            TextView startBalls = findViewById(R.id.startBalls);
            startBalls.setTextColor(getResources().getColor(R.color.RText));
            RadioButton none = findViewById(R.id.none);
            none.setTextColor(getResources().getColor(R.color.RText));
            RadioButton one = findViewById(R.id.one);
            one.setTextColor(getResources().getColor(R.color.RText));
            RadioButton two = findViewById(R.id.two);
            two.setTextColor(getResources().getColor(R.color.RText));
            RadioButton three = findViewById(R.id.three);
            three.setTextColor(getResources().getColor(R.color.RText));
            TextView scoredBalls = findViewById(R.id.ballsScoredAuto);
            scoredBalls.setTextColor(getResources().getColor(R.color.RText));
            TextView bottemPortAuto = findViewById(R.id.bottomPortAuto);
            bottemPortAuto.setTextColor(getResources().getColor(R.color.RText));
            Button plusBallsBottemAuto = findViewById(R.id.plusBallsBottemAuto);
            plusBallsBottemAuto.setTextColor(getResources().getColor(R.color.RText));
            plusBallsBottemAuto.setBackgroundColor(getResources().getColor(R.color.RButtonBackground));
            TextView txtBallsBottemAuto = findViewById(R.id.txtBallsBottomAuto);
            txtBallsBottemAuto.setTextColor(getResources().getColor(R.color.RText));
            Button minusBallsBottemAuto = findViewById(R.id.minusBallsBottemAuto);
            minusBallsBottemAuto.setTextColor(getResources().getColor(R.color.RText));
            minusBallsBottemAuto.setBackgroundColor(getResources().getColor(R.color.RButtonBackground));
            TextView outerPortAuto = findViewById(R.id.outerPortAuto);
            outerPortAuto.setTextColor(getResources().getColor(R.color.RText));
            Button plusBallsOuterAuto = findViewById(R.id.plusBallsOuterAuto);
            plusBallsOuterAuto.setTextColor(getResources().getColor(R.color.RText));
            plusBallsOuterAuto.setBackgroundColor(getResources().getColor(R.color.RButtonBackground));
            TextView txtBallsOuterAuto = findViewById(R.id.txtBallsOuterAuto);
            txtBallsOuterAuto.setTextColor(getResources().getColor(R.color.RText));
            Button minusBallsOuterAuto = findViewById(R.id.minusBallsOuterAuto);
            minusBallsOuterAuto.setTextColor(getResources().getColor(R.color.RText));
            minusBallsOuterAuto.setBackgroundColor(getResources().getColor(R.color.RButtonBackground));
            TextView innerPortAuto = findViewById(R.id.innerPortAuto);
            innerPortAuto.setTextColor(getResources().getColor(R.color.RText));
            Button plusBallsInnerAuto = findViewById(R.id.plusBallsInnerAuto);
            plusBallsInnerAuto.setTextColor(getResources().getColor(R.color.RText));
            plusBallsInnerAuto.setBackgroundColor(getResources().getColor(R.color.RButtonBackground));
            TextView txtBallsInnerAuto = findViewById(R.id.txtBallsInnerAuto);
            txtBallsInnerAuto.setTextColor(getResources().getColor(R.color.RText));
            Button minusBallsInnerAuto = findViewById(R.id.minusBallsInnerAuto);
            minusBallsInnerAuto.setTextColor(getResources().getColor(R.color.RText));
            minusBallsInnerAuto.setBackgroundColor(getResources().getColor(R.color.RButtonBackground));
            TextView ballsAquAuto = findViewById(R.id.ballsAcquiredAuto);
            ballsAquAuto.setTextColor(getResources().getColor(R.color.RText));
            TextView floorAuto = findViewById(R.id.floorAuto);
            floorAuto.setTextColor(getResources().getColor(R.color.RText));
            Button plusBallsFloorAuto = findViewById(R.id.plusBallsFloorAuto);
            plusBallsFloorAuto.setTextColor(getResources().getColor(R.color.RText));
            plusBallsFloorAuto.setBackgroundColor(getResources().getColor(R.color.RButtonBackground));
            TextView txtBallsFloorAuto = findViewById(R.id.txtBallsFloorAuto);
            txtBallsFloorAuto.setTextColor(getResources().getColor(R.color.RText));
            Button minusBallsFloorAuto = findViewById(R.id.minusBallsFloorAuto);
            minusBallsFloorAuto.setTextColor(getResources().getColor(R.color.RText));
            minusBallsFloorAuto.setBackgroundColor(getResources().getColor(R.color.RButtonBackground));
            TextView crossesLine = findViewById(R.id.crossesLine);
            crossesLine.setTextColor(getResources().getColor(R.color.RText));
            TextView txtCrossesLine = findViewById(R.id.txtCrossesLine);
            txtCrossesLine.setTextColor(getResources().getColor(R.color.RText));
            TextView tele = findViewById(R.id.teleoperated);
            tele.setTextColor(getResources().getColor(R.color.RText));
            TextView ballsScoredTele = findViewById(R.id.ballsScoredTele);
            ballsScoredTele.setTextColor(getResources().getColor(R.color.RText));
            TextView bottomPortTele = findViewById(R.id.bottomPortTele);
            bottomPortTele.setTextColor(getResources().getColor(R.color.RText));
            Button plusBallsBottemtele = findViewById(R.id.plusBallsBottemTele);
            plusBallsBottemtele.setTextColor(getResources().getColor(R.color.RText));
            plusBallsBottemtele.setBackgroundColor(getResources().getColor(R.color.RButtonBackground));
            TextView txtBallsBottemTele = findViewById(R.id.txtBallsBottemTele);
            txtBallsBottemTele.setTextColor(getResources().getColor(R.color.RText));
            Button minusBallsBottemTele = findViewById(R.id.minusBallsBottemTele);
            minusBallsBottemTele.setTextColor(getResources().getColor(R.color.RText));
            minusBallsBottemTele.setBackgroundColor(getResources().getColor(R.color.RButtonBackground));
            TextView outerPortTele = findViewById(R.id.outerPortTele);
            outerPortTele.setTextColor(getResources().getColor(R.color.RText));
            Button plusBallsOuterTele = findViewById(R.id.plusBallsOuterTele);
            plusBallsOuterTele.setTextColor(getResources().getColor(R.color.RText));
            plusBallsOuterTele.setBackgroundColor(getResources().getColor(R.color.RButtonBackground));
            TextView txtBallsOuterTele = findViewById(R.id.txtBallsOuterTele);
            txtBallsOuterTele.setTextColor(getResources().getColor(R.color.RText));
            Button minusBallsOuterTele = findViewById(R.id.minusBallsOuterTele);
            minusBallsOuterTele.setTextColor(getResources().getColor(R.color.RText));
            minusBallsOuterTele.setBackgroundColor(getResources().getColor(R.color.RButtonBackground));
            TextView innerPortTele = findViewById(R.id.innerPortTele);
            innerPortTele.setTextColor(getResources().getColor(R.color.RText));
            Button plusBallsInnerTele = findViewById(R.id.plusBallsInnerTele);
            plusBallsInnerTele.setTextColor(getResources().getColor(R.color.RText));
            plusBallsInnerTele.setBackgroundColor(getResources().getColor(R.color.RButtonBackground));
            TextView txtBallsInnerTele = findViewById(R.id.txtBallsInnerTele);
            txtBallsInnerTele.setTextColor(getResources().getColor(R.color.RText));
            Button minusBallsInnerTele = findViewById(R.id.minusBallsInnerTele);
            minusBallsInnerTele.setTextColor(getResources().getColor(R.color.RText));
            minusBallsInnerTele.setBackgroundColor(getResources().getColor(R.color.RButtonBackground));
            TextView ballsAquTele = findViewById(R.id.ballsAcquiredTele);
            ballsAquTele.setTextColor(getResources().getColor(R.color.RText));
            TextView floorTele = findViewById(R.id.floorTele);
            floorTele.setTextColor(getResources().getColor(R.color.RText));
            Button plusBallsFloorTele = findViewById(R.id.plusBallsFloorTele);
            plusBallsFloorTele.setTextColor(getResources().getColor(R.color.RText));
            plusBallsFloorTele.setBackgroundColor(getResources().getColor(R.color.RButtonBackground));
            TextView txtBallsFloorTele = findViewById(R.id.txtFloorTele);
            txtBallsFloorTele.setTextColor(getResources().getColor(R.color.RText));
            Button minusBallsFloorTele = findViewById(R.id.minusBallsFloorTele);
            minusBallsFloorTele.setTextColor(getResources().getColor(R.color.RText));
            minusBallsFloorTele.setBackgroundColor(getResources().getColor(R.color.RButtonBackground));
            TextView lowChuteTele = findViewById(R.id.lowChuteTele);
            lowChuteTele.setTextColor(getResources().getColor(R.color.RText));
            Button plusBallsLowChuteTele = findViewById(R.id.plusBallsLowChuteTele);
            plusBallsLowChuteTele.setTextColor(getResources().getColor(R.color.RText));
            plusBallsLowChuteTele.setBackgroundColor(getResources().getColor(R.color.RButtonBackground));
            TextView txtBallsLowChuteTele = findViewById(R.id.txtBallsLowChuteTele);
            txtBallsLowChuteTele.setTextColor(getResources().getColor(R.color.RText));
            Button minusBallsLowChuteTele = findViewById(R.id.minusBallsLowChuteTele);
            minusBallsLowChuteTele.setTextColor(getResources().getColor(R.color.RText));
            minusBallsLowChuteTele.setBackgroundColor(getResources().getColor(R.color.RButtonBackground));
            TextView highChuteTele = findViewById(R.id.highChuteTele);
            highChuteTele.setTextColor(getResources().getColor(R.color.RText));
            Button plusBallsHighChuteTele = findViewById(R.id.plusBallsHighChuteTele);
            plusBallsHighChuteTele.setTextColor(getResources().getColor(R.color.RText));
            plusBallsHighChuteTele.setBackgroundColor(getResources().getColor(R.color.RButtonBackground));
            TextView txtBallsHighChuteTele = findViewById(R.id.txtBallsHighChuteTele);
            txtBallsHighChuteTele.setTextColor(getResources().getColor(R.color.RText));
            Button minusBallsHighChuteTele = findViewById(R.id.minusBallsHighChuteTele);
            minusBallsHighChuteTele.setTextColor(getResources().getColor(R.color.RText));
            minusBallsHighChuteTele.setBackgroundColor(getResources().getColor(R.color.RButtonBackground));
            TextView controlPannel = findViewById(R.id.controlPanelInteraction);
            controlPannel.setTextColor(getResources().getColor(R.color.RText));
            TextView performdRotation = findViewById(R.id.performedRotationControl);
            performdRotation.setTextColor(getResources().getColor(R.color.RText));
            TextView performedPosition = findViewById(R.id.performedPositoinControl);
            performedPosition.setTextColor(getResources().getColor(R.color.RText));
            TextView endGame = findViewById(R.id.endGame);
            endGame.setTextColor(getResources().getColor(R.color.RText));
            TextView rendezuous = findViewById(R.id.rendezuous);
            rendezuous.setTextColor(getResources().getColor(R.color.RText));
            TextView level = findViewById(R.id.leveling);
            level.setTextColor(getResources().getColor(R.color.RText));
            TextView hanging = findViewById(R.id.hanging);
            hanging.setTextColor(getResources().getColor(R.color.RText));
            TextView parked = findViewById(R.id.parked);
            parked.setTextColor(getResources().getColor(R.color.RText));
            RadioButton noLeveling = findViewById(R.id.noLeveling);
            noLeveling.setTextColor(getResources().getColor(R.color.RText));
            RadioButton triedToLevel = findViewById(R.id.triedToLevel);
            triedToLevel.setTextColor(getResources().getColor(R.color.RText));
            RadioButton successfullyLeveled = findViewById(R.id.successfullyLeveled);
            successfullyLeveled.setTextColor(getResources().getColor(R.color.RText));
            Button save = findViewById(R.id.Save);
            save.setTextColor(getResources().getColor(R.color.RText));
            save.setBackgroundColor(getResources().getColor(R.color.RButtonBackground));
        }
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
                editor.putString(getString(R.string.scouter), "");
                editor.apply();
                name.setText(value);
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
        String matchnum = txtMatch.getText().toString();
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
            teamScouting.setText(teamText);
            found = true;
        }
        else {
            Toast.makeText(Scouting.this, "Match Not Found " + matchnum,Toast.LENGTH_LONG).show();
        }
        return found;
    }
}

