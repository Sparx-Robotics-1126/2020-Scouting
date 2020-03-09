package com.sparx1126.a2020_scouting.Fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.sparx1126.a2020_scouting.R;

public class OurRankingsFragment extends Fragment {
    static String TAG = "Sparx: ";
    static String HEADER = "OurRankingsFragment: ";

    private SharedPreferences settings;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, HEADER + "onCreateView");
        View root = inflater.inflate(R.layout.fragment_settings, container, false);
        settings = getActivity().getSharedPreferences(getString(R.string.SPARX_PREFS), 0);

        return root;
    }
}
