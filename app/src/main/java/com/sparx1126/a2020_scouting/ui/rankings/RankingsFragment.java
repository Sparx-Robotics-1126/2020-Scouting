package com.sparx1126.a2020_scouting.ui.rankings;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.sparx1126.a2020_scouting.BlueAllianceData.BlueAllianceEvent;
import com.sparx1126.a2020_scouting.BlueAllianceData.BlueAllianceMatch;
import com.sparx1126.a2020_scouting.BlueAllianceData.BlueAllianceRanking;
import com.sparx1126.a2020_scouting.MainActivity;
import com.sparx1126.a2020_scouting.R;
import com.sparx1126.a2020_scouting.SettingsScreen;
import com.sparx1126.a2020_scouting.Utilities.BlueAllianceNetwork;
import com.sparx1126.a2020_scouting.scouting;

import java.util.ArrayList;

public class RankingsFragment extends Fragment { private ListView listView;
    private RankArrayAdapter adapter;
    public static SharedPreferences settings;
    private static BlueAllianceNetwork blueAlliance;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_rankings, container, false);
        settings = getActivity().getSharedPreferences("Sparx_prefs", 0);
        blueAlliance = BlueAllianceNetwork.getInstance();
        //event key hard coded for now temporory2
        blueAlliance.downloadEventRanks("2020week0", new BlueAllianceNetwork.Callback() {
            @Override
            public void handleFinishDownload(final String _data) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        BlueAllianceRanking.parseJson(_data);
                        //Log.e("sohialRankings", _data);
                    }
                });
            }
        });
            for(int i = 0; i < 27; i++) {
                View segment = inflater.inflate(R.layout.rank_item_layout, container, false);
                TextView team = segment.findViewById(R.id.teamNumber);
                TextView rank = segment.findViewById(R.id.rank);
                rank.setText(String.valueOf(i));
                team.setText(BlueAllianceRanking.ranks.get(String.valueOf(i)).team_key);
                ((LinearLayout) root).addView(segment);
            }

        //listView = (ListView) root.findViewById(R.id.rankList);
        //ArrayList<RankListItem> rankList = new ArrayList<>();
        //multiple of these:
        //rankList.add(new RankListItem());
        //rankList.add(new RankListItem());
        //rankList.add(new RankListItem());
        //rankList.add(new RankListItem());
        //RankListItem hasData = new RankListItem();
        //hasData.setDetails("I live in a boat with a bunch of Justins");
        //rankList.add(hasData);

        //adapter = new RankArrayAdapter(getActivity(), rankList);
        //listView.setAdapter(adapter);

        return root;

    }
}