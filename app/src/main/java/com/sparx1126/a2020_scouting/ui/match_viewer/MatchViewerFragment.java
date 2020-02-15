package com.sparx1126.a2020_scouting.ui.match_viewer;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.sparx1126.a2020_scouting.BlueAllianceData.BlueAllianceMatch;
import com.sparx1126.a2020_scouting.R;

import java.util.HashMap;
import java.util.Set;

public class MatchViewerFragment extends Fragment {
    private final String HARD_CODED_EKEY = "2019ohcl";
    private final String HARD_CODED_TKEY = "frc1126";


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
       ;


        View root = inflater.inflate(R.layout.fragment_match, container, false);
        TableLayout masterTable= root.findViewById(R.id.masterTable);
        HashMap teamMatchObjs = BlueAllianceMatch.getTeamMatches();
        BlueAllianceMatch matchObj = null;
        Set<String> keys = BlueAllianceMatch.getTeamMatches().keySet();
        Log.i("MATCHVIEWER_FRAGMENT_UI", keys.toString());
        for(int i = 1; i<= BlueAllianceMatch.getMatches().size(); i++){
            if (keys.contains(String.valueOf(i))) {
                boolean isBlue =false;
                Log.i("MATCHVIEWER_FRAGMENT_UI", "ADDING MATCH #" + i);
                matchObj=(BlueAllianceMatch) teamMatchObjs.get((String.valueOf(i)));
                TableRow matchRow = new TableRow(getActivity());

                LinearLayout teamData = new LinearLayout(getActivity());
                teamData.setOrientation(LinearLayout.VERTICAL);
                teamData.setGravity(Gravity.RIGHT);
                teamData.setLayoutParams(new TableRow.LayoutParams(200,LinearLayout.LayoutParams.MATCH_PARENT ));

                LinearLayout blueRow = new LinearLayout(getActivity());
                blueRow.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,40));
                blueRow.setOrientation(LinearLayout.HORIZONTAL);
                blueRow.setBackgroundColor(Color.BLUE);

                for(int b=0;b<3;b++){
                    TextView blueTeamText = new TextView(getActivity());
                    blueTeamText.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT));
                    blueTeamText.setText(" "+matchObj.getBlueTeamKeys().get(b).replace("frc","")+" ");
                    blueTeamText.setTextColor(Color.BLACK);
                    blueTeamText.setTextSize(30);
                    if(matchObj.getBlueTeamKeys().get(b).equals(HARD_CODED_TKEY)){
                        isBlue=true;
                        blueTeamText.setTextColor(Color.YELLOW);
                    }
                    //System.out.println("ADDING BLUE TEAM" + matchObj.getBlueTeamKeys().get(b).replace("frc","")  );
                    blueRow.addView(blueTeamText);

                }
                LinearLayout redRow = new LinearLayout(getActivity());
                redRow.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,40));
                redRow.setOrientation(LinearLayout.HORIZONTAL);
                redRow.setBackgroundColor(Color.RED);

                for(int r=0;r<3;r++){
                    TextView redTeamText = new TextView(getActivity());
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
                TextView matchNum = new TextView(getActivity());
                matchNum.setLayoutParams(new TableRow.LayoutParams(200, TableRow.LayoutParams.MATCH_PARENT));
                matchNum.setGravity(Gravity.CENTER);
                matchNum.setTextSize(30);
                matchNum.setText("MATCH " +matchObj.getMatchNum());
                matchNum.setTextColor(Color.RED);
                if(isBlue){
                    matchNum.setTextColor(Color.BLUE);
                }
                matchRow.addView(matchNum);
                TextView rowDivider = new TextView(getActivity());
                rowDivider.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,6));
                rowDivider.setBackgroundColor(Color.DKGRAY);
                masterTable.addView(rowDivider);

                masterTable.addView(matchRow);
            }

        }
        TextView rowDivider = new TextView(getActivity());
        rowDivider.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,6));
        rowDivider.setBackgroundColor(Color.DKGRAY);
        masterTable.addView(rowDivider);

        return root;
    }

}
