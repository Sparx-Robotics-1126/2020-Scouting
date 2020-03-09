package com.sparx1126.a2020_scouting.Fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.sparx1126.a2020_scouting.BlueAllianceData.BlueAllianceEvent;
import com.sparx1126.a2020_scouting.R;

public class HomeFragment extends Fragment {
    private static String TAG = "Sparx: ";
    private static String HEADER = "HomeFragment: ";

    private SharedPreferences settings;
    private BlueAllianceEvent event;
    private LinearLayout backgroundLayout;
    private TextView regionalNameInput;
    private TextView regionLocationInput;
    private TextView datesTextInput;
    private TextView configurationTextView;
    private TextView teamTextView;
    private TextView teamInput;
    private LinearLayout ifScoutingTabletLayout;
    private TextView scouterTextView;
    private TextView scouterInput;
    private TextView posTextView;
    private TextView posInput;
    private TextView allianceColorTextView;
    private TextView allianceColorInput;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, HEADER + "onCreateView");

        View root = inflater.inflate(R.layout.fragment_home, container, false);
        settings = getActivity().getSharedPreferences(getString(R.string.SPARX_PREFS), 0);

        backgroundLayout = root.findViewById(R.id.background);
        regionalNameInput = root.findViewById(R.id.regionalName);
        regionLocationInput = root.findViewById(R.id.regionLocation);
        datesTextInput = root.findViewById(R.id.dates);
        configurationTextView = root.findViewById(R.id.configuration);
        teamTextView = root.findViewById(R.id.teamTxt);
        teamInput = root.findViewById(R.id.teamInput);
        ifScoutingTabletLayout = root.findViewById(R.id.ifScoutingTablet);
        if(!settings.getBoolean(getString(R.string.SCOUT), false)) {
            ifScoutingTabletLayout.setVisibility(View.INVISIBLE);
        }
        scouterTextView = root.findViewById(R.id.scouterTxt);
        scouterInput = root.findViewById(R.id.scouterInput);
        posTextView = root.findViewById(R.id.posTxt);
        posInput = root.findViewById(R.id.posInput);
        allianceColorTextView = root.findViewById(R.id.allianceColorTxt);
        allianceColorInput = root.findViewById(R.id.allianceColorInput);

        return root;
    }

    @Override
    public void onStart(){
        super.onStart();

        Log.d(TAG, HEADER + "onStart");

        event = BlueAllianceEvent.getEvents(settings.getString(getString(R.string.TEAM), "")).get(settings.getString(getString(R.string.pref_SelectedEvent), ""));
        if(event != null) {
            regionalNameInput.setText(event.getName());
            regionLocationInput.setText(event.getLocation());
            String dateRange = event.getStartDate() + " to " + event.getEndDate();
            datesTextInput.setText(dateRange);
        }
        teamInput.setText(settings.getString(getString(R.string.TEAM), ""));
        if(settings.getBoolean(getString(R.string.SCOUT), false)){
            ifScoutingTabletLayout.setVisibility(View.VISIBLE);
            scouterInput.setText(settings.getString(getString(R.string.scouter), "No one has Scouted"));
            posInput.setText(String.valueOf(settings.getInt(getString(R.string.pref_TeamPosition), 0)));
        }
        else {
            ifScoutingTabletLayout.setVisibility(View.INVISIBLE);
        }

        if(settings.getBoolean(getString(R.string.pref_BlueAlliance), false)){
            backgroundLayout.setBackgroundColor(getResources().getColor(R.color.BBackground));
            regionalNameInput.setTextColor(getResources().getColor(R.color.BText));
            regionLocationInput.setTextColor(getResources().getColor(R.color.BText));
            datesTextInput.setTextColor(getResources().getColor(R.color.BText));
            configurationTextView.setTextColor(getResources().getColor(R.color.BText));
            teamTextView.setTextColor(getResources().getColor(R.color.BText));
            teamInput.setTextColor(getResources().getColor(R.color.BText));
            scouterTextView.setTextColor(getResources().getColor(R.color.BText));
            scouterInput.setTextColor(getResources().getColor(R.color.BText));
            posTextView.setTextColor(getResources().getColor(R.color.BText));
            posInput.setTextColor(getResources().getColor(R.color.BText));
            allianceColorTextView.setTextColor(getResources().getColor(R.color.BText));
            allianceColorInput.setBackgroundColor(getResources().getColor(R.color.BLUE));
        }else{
            backgroundLayout.setBackgroundColor(getResources().getColor(R.color.RBackground));
            regionalNameInput.setTextColor(getResources().getColor(R.color.RText));
            regionLocationInput.setTextColor(getResources().getColor(R.color.RText));
            datesTextInput.setTextColor(getResources().getColor(R.color.RText));
            configurationTextView.setTextColor(getResources().getColor(R.color.RText));
            teamTextView.setTextColor(getResources().getColor(R.color.RText));
            teamInput.setTextColor(getResources().getColor(R.color.RText));
            scouterTextView.setTextColor(getResources().getColor(R.color.RText));
            scouterInput.setTextColor(getResources().getColor(R.color.RText));
            posTextView.setTextColor(getResources().getColor(R.color.RText));
            posInput.setTextColor(getResources().getColor(R.color.RText));
            allianceColorTextView.setTextColor(getResources().getColor(R.color.RText));
            allianceColorInput.setBackgroundColor(getResources().getColor(R.color.RED));
        }
    }
}