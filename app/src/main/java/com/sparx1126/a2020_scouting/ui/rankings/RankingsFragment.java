package com.sparx1126.a2020_scouting.ui.rankings;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.sparx1126.a2020_scouting.BlueAllianceData.BlueAllianceMatch;
import com.sparx1126.a2020_scouting.BlueAllianceData.BlueAllianceRank;
import com.sparx1126.a2020_scouting.MainActivity;
import com.sparx1126.a2020_scouting.R;
import com.sparx1126.a2020_scouting.Utilities.BlueAllianceNetwork;

import java.util.ArrayList;
import java.util.Map;

public class RankingsFragment extends Fragment {

    //private ListView listView;
    //private RankArrayAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_rankings, container, false);
        Map<Integer, BlueAllianceRank> ranks = BlueAllianceRank.getRanks();
        for(int i = 1; i <= ranks.size(); i++) {
            View segment = inflater.inflate(R.layout.rank_item_layout, container, false);
            TextView team = segment.findViewById(R.id.teamNumber);
            Log.e("Hiram3: ", String.valueOf(i));
            team.setText(String.valueOf(ranks.get(i).getTeamNum()));
            ((LinearLayout)root).addView(segment);
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