package com.sparx1126.a2020_scouting.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.sparx1126.a2020_scouting.BlueAllianceData.BlueAllianceMatch;
import com.sparx1126.a2020_scouting.R;
import com.sparx1126.a2020_scouting.Scouting;

public class ScoutingFragment extends Fragment {
    private static final String TAG = "Sparx: ";
    private static final String HEADER = "ScoutingFragment: ";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, HEADER + "onCreateView");

        View root = inflater.inflate(R.layout.fragment_settings, container, false);


        if(BlueAllianceMatch.getMatches().size() == 0){
            Log.e(TAG, HEADER + "No Matches to Scout");
            Toast.makeText(getActivity(), "No Matches to Scout", Toast.LENGTH_LONG).show();
            getFragmentManager().popBackStack();
        }else{
            Intent intent = new Intent(getActivity(), Scouting.class);
            startActivity(intent);
            getFragmentManager().popBackStack();
        }
        return root;
    }

}
