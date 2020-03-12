package com.sparx1126.a2020_scouting.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.sparx1126.a2020_scouting.R;
import com.sparx1126.a2020_scouting.Benchmarking;

public class BenchmarkingFragment extends Fragment {
    private static final String TAG = "Sparx: ";
    private static final String HEADER = "BenchmarkingFragment: ";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, HEADER + "onCreateView");

        View root = inflater.inflate(R.layout.fragment_empty, container, false);

        Log.d(TAG, HEADER + "popping this fragment out");
        getFragmentManager().popBackStack();
        Log.d(TAG, HEADER + "restoring default fragment");
        Intent intent = new Intent(getActivity(), Benchmarking.class);
        startActivity(intent);

        return root;
    }

}
