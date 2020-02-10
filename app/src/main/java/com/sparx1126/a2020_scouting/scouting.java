package com.sparx1126.a2020_scouting;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.AutoCompleteTextView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.sparx1126.a2020_scouting.BlueAllianceData.BlueAllianceMatch;
import com.sparx1126.a2020_scouting.Utilities.SendMail;
import com.sparx1126.a2020_scouting.Utilities.BlueAllianceNetwork;

import android.view.View;

import org.w3c.dom.Text;

import java.util.Set;

public class scouting extends AppCompatActivity {
    private SharedPreferences.Editor editor;
    private Button logout;
    private TextView name;
    private SharedPreferences settings;
    private Button plusBalls;
    private Button minusBalls;
    private Button plusMatch;
    private Button minusMatch;
    private TextView match;
    private Button save;
    private AutoCompleteTextView balls;
    private SendMail mail;
    private BlueAllianceNetwork ban;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scouting);

        changeUi();
        login();

        save = findViewById(R.id.Save);
        settings = getSharedPreferences("Sparx_prefs", 0);
        logout = findViewById(R.id.logOut);
        name = findViewById(R.id.name);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ban = BlueAllianceNetwork.getInstance();
                ban.downloadEventMatches("2019ohcl", new BlueAllianceNetwork.Callback() {
                    @Override
                    public void handleFinishDownload(final String _data) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                BlueAllianceMatch.parseDataToBAMMap(_data);
                                Set<String> keys = BlueAllianceMatch.getMatches().keySet();
                                String str = "";
                                for(String k: keys){
                                    if(k.equals(match.getText().toString())) {
                                        str += "\n" + BlueAllianceMatch.getMatches().get(k).toString();
                                    }
                                }
                                TextView name = findViewById(R.id.name);
                                mail = new SendMail(scouting.this,getResources().getString(R.string.sparx_email), (String)name.getText(), str);
                                mail.execute();
                            }
                        });
                    }
                });
            }
        });

//        plusBalls.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int value = Integer.parseInt(balls.getText().toString());
//                balls.setText(String.valueOf(value + 1));
//            }
//        });
//
//        minusBalls.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int value = Integer.parseInt(match.getText().toString());
//                balls.setText(String.valueOf(value - 1));
//            }
//        });
//
//        plusMatch.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int value = Integer.parseInt(match.getText().toString());
//                match.setText(String.valueOf(value + 1));
//            }
//        });
//
//        minusMatch.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int value = Integer.parseInt(balls.getText().toString());
//                match.setText(String.valueOf(value - 1));
//            }
//        });




        logout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(scouting.this);
                builder.setTitle("Log Out");
                builder.setMessage("Are you sure you want to log out");

                builder.setPositiveButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(scouting.this ,scouting.class));
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

        if(!settings.getString("email", "email not found").equals("email not found")){
            name.setText(settings.getString("email", "email ont found"));
            name.setTextSize(30f);
        }else{
            login();
        }
    }

    public  void changeUi(){
        if(Welcome.toggledBlue){

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
            AutoCompleteTextView txtMatch = findViewById(R.id.txtMatch);
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
            AutoCompleteTextView txtBallsBottemAuto = findViewById(R.id.txtBallsBottomAuto);
            txtBallsBottemAuto.setTextColor(getResources().getColor(R.color.RText));
            Button minusBallsBottemAuto = findViewById(R.id.minusBallsBottemAuto);
            minusBallsBottemAuto.setTextColor(getResources().getColor(R.color.RText));
            minusBallsBottemAuto.setBackgroundColor(getResources().getColor(R.color.RButtonBackground));
            TextView outerPortAuto = findViewById(R.id.outerPortAuto);
            outerPortAuto.setTextColor(getResources().getColor(R.color.RText));
            Button plusBallsOuterAuto = findViewById(R.id.plusBallsOuterAuto);
            plusBallsOuterAuto.setTextColor(getResources().getColor(R.color.RText));
            plusBallsOuterAuto.setBackgroundColor(getResources().getColor(R.color.RButtonBackground));
            AutoCompleteTextView txtBallsOuterAuto = findViewById(R.id.txtBallsOuterAuto);
            txtBallsOuterAuto.setTextColor(getResources().getColor(R.color.RText));
            Button minusBallsOuterAuto = findViewById(R.id.minusBallsOuterAuto);
            minusBallsOuterAuto.setTextColor(getResources().getColor(R.color.RText));
            minusBallsOuterAuto.setBackgroundColor(getResources().getColor(R.color.RButtonBackground));
            TextView innerPortAuto = findViewById(R.id.innerPortAuto);
            innerPortAuto.setTextColor(getResources().getColor(R.color.RText));
            Button plusBallsInnerAuto = findViewById(R.id.plusBallsInnerAuto);
            plusBallsInnerAuto.setTextColor(getResources().getColor(R.color.RText));
            plusBallsInnerAuto.setBackgroundColor(getResources().getColor(R.color.RButtonBackground));
            AutoCompleteTextView txtBallsInnerAuto = findViewById(R.id.txtBallsInnerAuto);
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
            AutoCompleteTextView txtBallsFloorAuto = findViewById(R.id.txtBallsFloorAuto);
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
            AutoCompleteTextView txtBallsBottemTele = findViewById(R.id.txtBallsBottemTele);
            txtBallsBottemTele.setTextColor(getResources().getColor(R.color.RText));
            Button minusBallsBottemTele = findViewById(R.id.minusBallsBottemTele);
            minusBallsBottemTele.setTextColor(getResources().getColor(R.color.RText));
            minusBallsBottemTele.setBackgroundColor(getResources().getColor(R.color.RButtonBackground));
            TextView outerPortTele = findViewById(R.id.outerPortTele);
            outerPortTele.setTextColor(getResources().getColor(R.color.RText));
            Button plusBallsOuterTele = findViewById(R.id.plusBallsOuterTele);
            plusBallsOuterTele.setTextColor(getResources().getColor(R.color.RText));
            plusBallsOuterTele.setBackgroundColor(getResources().getColor(R.color.RButtonBackground));
            AutoCompleteTextView txtBallsOuterTele = findViewById(R.id.txtBallsOuterTele);
            txtBallsOuterTele.setTextColor(getResources().getColor(R.color.RText));
            Button minusBallsOuterTele = findViewById(R.id.minusBallsOuterTele);
            minusBallsOuterTele.setTextColor(getResources().getColor(R.color.RText));
            minusBallsOuterTele.setBackgroundColor(getResources().getColor(R.color.RButtonBackground));
            TextView innerPortTele = findViewById(R.id.innerPortTele);
            innerPortTele.setTextColor(getResources().getColor(R.color.RText));
            Button plusBallsInnerTele = findViewById(R.id.plusBallsInnerTele);
            plusBallsInnerTele.setTextColor(getResources().getColor(R.color.RText));
            plusBallsInnerTele.setBackgroundColor(getResources().getColor(R.color.RButtonBackground));
            AutoCompleteTextView txtBallsInnerTele = findViewById(R.id.txtBallsInnerTele);
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
            AutoCompleteTextView txtBallsFloorTele = findViewById(R.id.txtBallsFloorTele);
            txtBallsFloorTele.setTextColor(getResources().getColor(R.color.RText));
            Button minusBallsFloorTele = findViewById(R.id.minusBallsFloorTele);
            minusBallsFloorTele.setTextColor(getResources().getColor(R.color.RText));
            minusBallsFloorTele.setBackgroundColor(getResources().getColor(R.color.RButtonBackground));
            TextView lowChuteTele = findViewById(R.id.lowChuteTele);
            lowChuteTele.setTextColor(getResources().getColor(R.color.RText));
            Button plusBallsLowChuteTele = findViewById(R.id.plusBallsLowChuteTele);
            plusBallsLowChuteTele.setTextColor(getResources().getColor(R.color.RText));
            plusBallsLowChuteTele.setBackgroundColor(getResources().getColor(R.color.RButtonBackground));
            AutoCompleteTextView txtBallsLowChuteTele = findViewById(R.id.txtBallsLowChuteTele);
            txtBallsLowChuteTele.setTextColor(getResources().getColor(R.color.RText));
            Button minusBallsLowChuteTele = findViewById(R.id.minusBallsLowChuteTele);
            minusBallsLowChuteTele.setTextColor(getResources().getColor(R.color.RText));
            minusBallsLowChuteTele.setBackgroundColor(getResources().getColor(R.color.RButtonBackground));
            TextView highChuteTele = findViewById(R.id.highChuteTele);
            highChuteTele.setTextColor(getResources().getColor(R.color.RText));
            Button plusBallsHighChuteTele = findViewById(R.id.plusBallsHighChuteTele);
            plusBallsHighChuteTele.setTextColor(getResources().getColor(R.color.RText));
            plusBallsHighChuteTele.setBackgroundColor(getResources().getColor(R.color.RButtonBackground));
            AutoCompleteTextView txtBallsHighChuteTele = findViewById(R.id.txtBallsHighChuteTele);
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
        }
    }

    private void login(){
        AlertDialog.Builder builder = new AlertDialog.Builder(scouting.this);
        builder.setTitle("Login");
        builder.setMessage("Please log in to continue");
        // Set an EditText view to get user input
        final EditText input = new EditText(this);
        builder.setView(input);

        builder.setPositiveButton("login", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int whichButton) {
                String value = input.getText().toString();
                name.setText(value);

            }
        });
        builder.setNegativeButton("Cancel" ,new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                startActivity(new Intent(scouting.this, Welcome.class));
            }
        });
        builder.create().show();

    }
}

