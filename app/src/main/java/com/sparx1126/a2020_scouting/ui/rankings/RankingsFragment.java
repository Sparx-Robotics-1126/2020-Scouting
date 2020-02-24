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
import com.sparx1126.a2020_scouting.BlueAllianceData.BlueAllianceTeam;
import com.sparx1126.a2020_scouting.MainActivity;
import com.sparx1126.a2020_scouting.R;
import com.sparx1126.a2020_scouting.Utilities.BlueAllianceNetwork;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Map;

public class RankingsFragment extends Fragment {

    private SharedPreferences settings;
    //private ListView listView;
    //private RankArrayAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_rankings, container, false);
        View linearlayout = root.findViewById(R.id.linearLayout);
        settings = getActivity().getSharedPreferences(getString(R.string.SPARX_PREFS), 0);
        Map<Integer, BlueAllianceRank> ranks = BlueAllianceRank.getRanks();
        Map<String, BlueAllianceTeam> teams = BlueAllianceTeam.getTeams();
        for(int i = 1; i <= ranks.size(); i++) {
            View segment = inflater.inflate(R.layout.rank_item_layout, container, false);
            TextView team = segment.findViewById(R.id.teamNumber);
            TextView rank = segment.findViewById(R.id.rank);
            TextView record = segment.findViewById(R.id.record);
            TextView details = segment.findViewById(R.id.details);
            TextView teamName = segment.findViewById(R.id.teamName);
            team.setText(String.valueOf(ranks.get(i).getTeamNum()));
            rank.setText(String.valueOf(ranks.get(i).getRank()));
            record.setText(String.valueOf(ranks.get(i).getRecord()));
            details.setText(String.valueOf(ranks.get(i).getDetails()));
            teamName.setText(String.valueOf(teams.get(ranks.get(i).getTeamNum()).getTeam_name()));
            if(settings.getBoolean(getString(R.string.pref_BlueAlliance), false)){
                root.setBackgroundColor(getResources().getColor(R.color.BBackground));
                team.setTextColor(getResources().getColor(R.color.BText));
                rank.setTextColor(getResources().getColor(R.color.BText));
                record.setTextColor(getResources().getColor(R.color.BText));
                details.setTextColor(getResources().getColor(R.color.BText));
                teamName.setTextColor(getResources().getColor(R.color.BText));
                TextView txtRank = segment.findViewById(R.id.txtrank);
                txtRank.setTextColor(getResources().getColor(R.color.BText));
                LinearLayout background = segment.findViewById(R.id.background);
                background.setBackgroundColor(getResources().getColor(R.color.BButtonBackground));
            }else{
                root.setBackgroundColor(getResources().getColor(R.color.RBackground));
                team.setTextColor(getResources().getColor(R.color.RText));
                rank.setTextColor(getResources().getColor(R.color.RText));
                record.setTextColor(getResources().getColor(R.color.RText));
                details.setTextColor(getResources().getColor(R.color.RText));
                teamName.setTextColor(getResources().getColor(R.color.RText));
                TextView txtRank = segment.findViewById(R.id.txtrank);
                txtRank.setTextColor(getResources().getColor(R.color.RText));
                LinearLayout background = segment.findViewById(R.id.background);
                background.setBackgroundColor(getResources().getColor(R.color.RButtonBackground));
            }
            ((LinearLayout)linearlayout).addView(segment);
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