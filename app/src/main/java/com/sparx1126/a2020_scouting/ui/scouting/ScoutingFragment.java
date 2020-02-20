package com.sparx1126.a2020_scouting.ui.scouting;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.sparx1126.a2020_scouting.R;
import com.sparx1126.a2020_scouting.scouting;

public class ScoutingFragment extends Fragment {
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_settings, container, false);
        Intent intent = new Intent(getActivity(), scouting.class);
        startActivity(intent);
        return root;
    }

}
