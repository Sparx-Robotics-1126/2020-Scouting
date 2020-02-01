package com.sparx1126.a2020_scouting;
import android.os.Bundle;
import android.util.Log;


import androidx.appcompat.app.AppCompatActivity;

import com.sparx1126.a2020_scouting.Utilities.BlueAllianceNetwork;
import com.sparx1126.a2020_scouting.BlueAllianceData.BlueAllianceMatch;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;


public class MatchViewer extends AppCompatActivity {
    BlueAllianceNetwork network = BlueAllianceNetwork.getInstance();
    //Later will use the event key stored in device settings when implemented.
     private final String HARD_CODED_EKEY = "2019ohcl";
     private final String HARD_CODED_TKEY = "frc1126";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.match_viewer);
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
        Set<String> keys = BlueAllianceMatch.getMatches().keySet();
        for(String k: keys){
            System.out.println(k);
        }
        Log.e("SIZE",String.valueOf(BlueAllianceMatch.getMatches().size()));
        //Log.e("TIME2",BlueAllianceMatch.getMatches().get("2").getEpochTime());
        Log.e("TIME 1",BlueAllianceMatch.getMatches().get("1").getEpochTime());
        Log.e("TEAM",String.valueOf(BlueAllianceMatch.getTeamMatches().get("21").getEpochTime()));
        Log.e("TIME45",BlueAllianceMatch.getMatches().get("79").getEpochTime());
        //Log.e("YEE",BlueAllianceMatch.getMatches().get("7").getMatchNum());
        }
//


    }


