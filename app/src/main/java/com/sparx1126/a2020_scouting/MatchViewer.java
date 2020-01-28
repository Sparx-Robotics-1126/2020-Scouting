package com.sparx1126.a2020_scouting;
import android.os.Bundle;
import android.util.Log;


import androidx.appcompat.app.AppCompatActivity;

import com.sparx1126.a2020_scouting.Utilities.BlueAllianceNetwork;
import com.sparx1126.a2020_scouting.BlueAllianceData.BlueAllianceMatch;

import java.util.HashMap;
import java.util.Map;


public class MatchViewer extends AppCompatActivity {
    BlueAllianceNetwork network = BlueAllianceNetwork.getInstance();
    //Later will use the event key stored in device settings when implemented.
     private final String HARD_CODED_EKEY = "2019ohcl";
     private final String HARD_CODED_TKEY = "frc1126";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.match_viewer);
        network.downloadEventMatches(HARD_CODED_EKEY, new BlueAllianceNetwork.Callback() {
            @Override
            public void handleFinishDownload(final String _data) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //System.out.println(_data);
                       BlueAllianceMatch.parseDataToBAMMap(_data);

                    }
                });

            }
        });
        HashMap<String,BlueAllianceMatch> map = BlueAllianceMatch.getMatches();
        String str="tfv";
        for(Map.Entry entry:map.entrySet()){
            str+=(entry.getKey()+" ");
        }
        Log.e("BAM TEST","YO");
        Log.e("BAM TEST",String.valueOf(map.size()));
        Log.e("BAM TEST","U");


    }

}
