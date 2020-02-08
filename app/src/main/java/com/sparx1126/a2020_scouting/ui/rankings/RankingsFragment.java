package com.sparx1126.a2020_scouting.ui.rankings;

import android.os.Bundle;
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

import com.sparx1126.a2020_scouting.MainActivity;
import com.sparx1126.a2020_scouting.R;

import java.util.ArrayList;

public class RankingsFragment extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_rankings, container, false);

        //list
        final ListView rankList = root.findViewById(R.id.rankList);
        String[] values = new String[]{"Hi", "bye"};

        final ArrayAdapter adapter = new ArrayAdapter(getActivity(),
                android.R.layout.simple_list_item_1, values);
        rankList.setAdapter(adapter);


        //ArrayAdapter adapter = new ArrayAdapter<RankCustomLayout>(this, R.layout.rank_custom_layout, );

        /*
        RankCustomLayout testRank = root.findViewById(R.id.testRank);

        testRank.setRank(5);
        testRank.setTeamNumber(1126);
        testRank.setRecord(1,2,3);
        testRank.setTeamName("Sprax");
        testRank.setDetails("Jaren was here");
         */

        return root;

    }
}