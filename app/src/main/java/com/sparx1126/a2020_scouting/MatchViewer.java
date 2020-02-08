package com.sparx1126.a2020_scouting;
import android.app.ActionBar;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.solver.widgets.ConstraintHorizontalLayout;

import com.sparx1126.a2020_scouting.Utilities.*;
import com.sparx1126.a2020_scouting.BlueAllianceData.BlueAllianceMatch;


import java.util.HashMap;
import java.util.Set;

public class MatchViewer extends AppCompatActivity {
    BlueAllianceNetwork network = BlueAllianceNetwork.getInstance();

    // JT: Later will use the event key stored in device settings when implemented.
     private final String HARD_CODED_EKEY = "2019ohcl";
     private final String HARD_CODED_TKEY = "frc1126";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.match_viewer);
        TableLayout masterTable= findViewById(R.id.masterTable);
//        network.downloadEventMatches(HARD_CODED_EKEY, new BlueAllianceNetwork.Callback() {
//            @Override
//            public void handleFinishDownload(final String _data) {
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        //Log.i("TAG", _data);
//                        System.out.println(_data);
//                       BlueAllianceMatch.parseDataToBAMMap(_data);
//
//                    }
//                });
//
//            }
//        });

        // JT: Come get me and we will work on populating fragments.
        HashMap teamMatchObjs = BlueAllianceMatch.getTeamMatches();
        BlueAllianceMatch matchObj = null;
        Set<String> keys = BlueAllianceMatch.getTeamMatches().keySet();


        for(int i =1; i<=BlueAllianceMatch.getMatches().size();i++){
            if (keys.contains(String.valueOf(i))) {
                boolean isBlue =false;
                System.out.println("ADDING MATCH" + i);
                matchObj=(BlueAllianceMatch) teamMatchObjs.get((String.valueOf(i)));
                TableRow matchRow = new TableRow(this);

                LinearLayout teamData = new LinearLayout(this);
                teamData.setOrientation(LinearLayout.VERTICAL);
                teamData.setGravity(Gravity.RIGHT);
                teamData.setLayoutParams(new TableRow.LayoutParams(200,LinearLayout.LayoutParams.MATCH_PARENT ));

                LinearLayout blueRow = new LinearLayout(this);
                blueRow.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,40));
                blueRow.setOrientation(LinearLayout.HORIZONTAL);
                blueRow.setBackgroundColor(Color.BLUE);

                for(int b=0;b<3;b++){
                    TextView blueTeamText = new TextView(this);
                    blueTeamText.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT));
                    blueTeamText.setText(" "+matchObj.getBlueTeamKeys().get(b).replace("frc","")+" ");
                    blueTeamText.setTextColor(Color.BLACK);
                    blueTeamText.setTextSize(30);
                    if(matchObj.getBlueTeamKeys().get(b).equals(HARD_CODED_TKEY)){
                        isBlue=true;
                        System.out.println(isBlue);
                        blueTeamText.setTextColor(Color.YELLOW);
                    }
                    //System.out.println("ADDING BLUE TEAM" + matchObj.getBlueTeamKeys().get(b).replace("frc","")  );
                    blueRow.addView(blueTeamText);

                }
                LinearLayout redRow = new LinearLayout(this);
                redRow.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,40));
                redRow.setOrientation(LinearLayout.HORIZONTAL);
                redRow.setBackgroundColor(Color.RED);

                for(int r=0;r<3;r++){
                    TextView redTeamText = new TextView(this);
                    redTeamText.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT));
                    redTeamText.setTextColor(Color.BLACK);
                    redTeamText.setTextSize(30);
                    redTeamText.setText(" "+matchObj.getRedTeamKeys().get(r).replace("frc","")+" ");
                    if(matchObj.getRedTeamKeys().get(r).equals(HARD_CODED_TKEY)){
                        redTeamText.setTextColor(Color.YELLOW);
                    }
                    redRow.addView(redTeamText);
                   // System.out.println("ADDING RED TEAM" + matchObj.getRedTeamKeys().get(r).replace("frc","")  );

                }
                teamData.addView(redRow);
                teamData.addView(blueRow);
                matchRow.addView(teamData);
                TextView matchNum = new TextView(this);
                matchNum.setLayoutParams(new TableRow.LayoutParams(200, TableRow.LayoutParams.MATCH_PARENT));
                matchNum.setGravity(Gravity.CENTER);
                matchNum.setTextSize(30);
                matchNum.setText("MATCH " +matchObj.getMatchNum());
                System.out.println("FINAL"+ isBlue);
                matchNum.setTextColor(Color.RED);
                if(isBlue){
                    matchNum.setTextColor(Color.BLUE);
                }
                matchRow.addView(matchNum);
                TextView rowDivider = new TextView(this);
                rowDivider.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,10));
                rowDivider.setBackgroundColor(Color.DKGRAY);
                masterTable.addView(rowDivider);

                masterTable.addView(matchRow);
            }

        }









//        Log.e("SIZE",String.valueOf(BlueAllianceMatch.getMatches().size()));
//        //Log.e("TIME2",BlueAllianceMatch.getMatches().get("2").getEpochTime());
//        Log.e("TIME 1",BlueAllianceMatch.getMatches().get("1").getEpochTime());
//        Log.e("TEAM",String.valueOf(BlueAllianceMatch.getTeamMatches().get("21").getEpochTime()));
//        Log.e("TIME45",BlueAllianceMatch.getMatches().get("79").getEpochTime());
//        //Log.e("YEE",BlueAllianceMatch.getMatches().get("7").getMatchNum());
        }
//
    }


