package com.sparx1126.a2020_scouting.Fragments.rankings;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.sparx1126.a2020_scouting.BlueAllianceData.BlueAllianceRank;
import com.sparx1126.a2020_scouting.BlueAllianceData.BlueAllianceTeam;
import com.sparx1126.a2020_scouting.R;

import java.util.Map;

public class RankingsFragment extends Fragment {
    static String TAG = "Sparx: ";
    static String HEADER = "RankingsFragment: ";

    private SharedPreferences settings;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, HEADER + "onCreateView");

        View root = inflater.inflate(R.layout.fragment_rankings, container, false);
        View linearlayout = root.findViewById(R.id.linearLayout);
        settings = getActivity().getSharedPreferences(getString(R.string.SPARX_PREFS), 0);
        Map<Integer, BlueAllianceRank> ranks = BlueAllianceRank.getRanks();
        Map<String, BlueAllianceTeam> teams = BlueAllianceTeam.getTeams();

        if(ranks.size() == 0){
            Toast.makeText(getActivity(), "No Rankings Data", Toast.LENGTH_LONG).show();
            getFragmentManager().popBackStack();
        }else {
            for (int i = 1; i <= ranks.size() + 1; i++) {
                View segment = inflater.inflate(R.layout.rank_item_layout, container, false);
                TextView team = segment.findViewById(R.id.teamNumber);
                TextView rank = segment.findViewById(R.id.rank);
                TextView record = segment.findViewById(R.id.record);
                TextView details = segment.findViewById(R.id.details);
                TextView teamName = segment.findViewById(R.id.teamName);

                if (i <= ranks.size()) {
                    team.setText(String.valueOf(ranks.get(i).getTeamNum()));
                    rank.setText(String.valueOf(ranks.get(i).getRank()));
                    record.setText(String.valueOf(ranks.get(i).getRecord()));
                    details.setText(String.valueOf(ranks.get(i).getDetails()));
                    teamName.setText(String.valueOf(teams.get(ranks.get(i).getTeamNum()).getTeam_name()));
                    if (settings.getBoolean(getString(R.string.pref_BlueAlliance), false)) {
                        root.setBackgroundColor(getResources().getColor(R.color.BBackground));
                        team.setTextColor(getResources().getColor(R.color.BText));
                        rank.setTextColor(getResources().getColor(R.color.BText));
                        record.setTextColor(getResources().getColor(R.color.BText));
                        details.setTextColor(getResources().getColor(R.color.BText));
                        teamName.setTextColor(getResources().getColor(R.color.BText));
                        TextView txtRank = segment.findViewById(R.id.txtrank);
                        txtRank.setTextColor(getResources().getColor(R.color.BText));
                        LinearLayout background1 = segment.findViewById(R.id.background1);
                        LinearLayout background2 = segment.findViewById(R.id.background2);
                        background1.setBackgroundColor(getResources().getColor(R.color.BButtonBackground));
                        background2.setBackgroundColor(getResources().getColor(R.color.BButtonBackground));
                    } else {
                        root.setBackgroundColor(getResources().getColor(R.color.RBackground));
                        team.setTextColor(getResources().getColor(R.color.RText));
                        rank.setTextColor(getResources().getColor(R.color.RText));
                        record.setTextColor(getResources().getColor(R.color.RText));
                        details.setTextColor(getResources().getColor(R.color.RText));
                        teamName.setTextColor(getResources().getColor(R.color.RText));
                        TextView txtRank = segment.findViewById(R.id.txtrank);
                        txtRank.setTextColor(getResources().getColor(R.color.RText));
                        LinearLayout background1 = segment.findViewById(R.id.background1);
                        LinearLayout background2 = segment.findViewById(R.id.background2);
                        background1.setBackgroundColor(getResources().getColor(R.color.RButtonBackground));
                        background2.setBackgroundColor(getResources().getColor(R.color.RButtonBackground));
                    }
                } else {
                    //This is padding for the last item that ends up under the navigation bar
                }
                ((LinearLayout) linearlayout).addView(segment);
            }
        }

        return root;
    }
}