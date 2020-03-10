package com.sparx1126.a2020_scouting.Fragments;

import android.content.SharedPreferences;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.sparx1126.a2020_scouting.BlueAllianceData.BlueAllianceTeam;
import com.sparx1126.a2020_scouting.Data.OurRankingData;
import com.sparx1126.a2020_scouting.R;

import java.util.HashMap;
import java.util.Map;

public class OurRankingsFragment extends Fragment {
    private static final String TAG = "Sparx: ";
    private static final String HEADER = "OurRankingsFragment: ";

    private SharedPreferences settings;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, HEADER + "onCreateView");
        View root = inflater.inflate(R.layout.fragment_our_rankings, container, false);
        settings = getActivity().getSharedPreferences(getString(R.string.SPARX_PREFS), 0);
        Map<Integer, Float> data = OurRankingData.getBallsScoredOnInnerAve();
        String selectedEvent = settings.getString(getString(R.string.pref_SelectedEvent), "");
        Map<String, BlueAllianceTeam> teams = BlueAllianceTeam.getTeams(selectedEvent);
        View linearlayout = root.findViewById(R.id.linearLayout);

        if (data.size() == 0) {
            Log.e(TAG, HEADER + "No Rankings Data");
            Toast.makeText(getActivity(), "No Rankings Data", Toast.LENGTH_LONG).show();
            getFragmentManager().popBackStack();
        } else {
            Map<Integer, Float> tmpdata = new HashMap<>();
            tmpdata.putAll(data);
            Log.d(TAG, HEADER + "DATA SIZE " + data.size());
            for (int i = 0; i < data.size(); i++) {
                Pair<Integer, Float> highest = getHighest(tmpdata);
                tmpdata.remove(highest.first);
                Log.d(TAG, HEADER + "for loop " + highest.first);
                View segment = inflater.inflate(R.layout.rank_item_layout, container, false);
                TextView team = segment.findViewById(R.id.teamNumber);
                TextView rank = segment.findViewById(R.id.rank);
                TextView record = segment.findViewById(R.id.record);
                TextView details = segment.findViewById(R.id.details);
                TextView teamName = segment.findViewById(R.id.teamName);

                team.setText(String.valueOf(highest.first));
                rank.setText(String.valueOf(i + 1));
                record.setText(String.valueOf(highest.second));
//                    details.setText(String.valueOf(data.get(i).getDetails()));
                teamName.setText(String.valueOf(teams.get(String.valueOf(highest.first)).getTeam_name()));
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
                ((LinearLayout) linearlayout).addView(segment);
            }
        }
        return root;
    }

    private Pair<Integer, Float> getHighest(Map<Integer, Float> tmpdata) {
        Pair<Integer, Float> rtn = new Pair<>(-1, -1f);
        for (Map.Entry<Integer, Float> tmp : tmpdata.entrySet()) {
            Log.d(TAG, HEADER + "hightes  " + tmp.getKey());
            if (rtn.second < tmp.getValue()) {
                Pair<Integer, Float> tmprtn = new Pair<>(tmp.getKey(), tmp.getValue());
                rtn = tmprtn;
            }
        }
        return rtn;
    }
}
