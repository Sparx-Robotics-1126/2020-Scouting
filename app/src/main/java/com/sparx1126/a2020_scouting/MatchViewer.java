package com.sparx1126.a2020_scouting;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.sparx1126.a2020_scouting.Utilities.BlueAllianceNetwork;

public class MatchViewer extends AppCompatActivity {
    BlueAllianceNetwork network = BlueAllianceNetwork.getInstance();
    //Later will use the event key stored in device settings when implemented.
     private final String HARD_CODED_EKEY = "2019ohcl";
     private final String HARD_CODED_TKEY = "frc1126";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.match_viewer);
        network.downloadTeamEventMatches(HARD_CODED_EKEY,HARD_CODED_TKEY, new BlueAllianceNetwork.Callback() {
            @Override
            public void handleFinishDownload(final String _data) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                       //Log.i("MATCH", _data);
                        System.out.println(_data);
                    }
                });

            }
        });
    }

}
