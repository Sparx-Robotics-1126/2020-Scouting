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

    private ListView listView;
    private RankArrayAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_rankings, container, false);


        listView = (ListView) root.findViewById(R.id.rankList);
        ArrayList<RankListItem> rankList = new ArrayList<>();
        //multiple of these:
        rankList.add(new RankListItem());
        rankList.add(new RankListItem());
        rankList.add(new RankListItem());
        rankList.add(new RankListItem());
        RankListItem hasData = new RankListItem();
        hasData.setDetails("I live in a boat with a bunch of Justins");
        rankList.add(hasData);

        adapter = new RankArrayAdapter(getActivity(), rankList);
        listView.setAdapter(adapter);

        return root;

    }
}