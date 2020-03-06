package com.sparx1126.a2020_scouting.ui.home;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.sparx1126.a2020_scouting.R;

public class HomeFragment extends Fragment {
    private SharedPreferences settings;
    private TextView emailtxt;
    private TextView emailInput;
    private TextView teamtxt;
    private TextView teamInput;
    private TextView eventtxt;
    private TextView eventInput;
    private TextView scoutertxt;
    private TextView scouterInput;
    private TextView posTxt;
    private TextView posInput;
    private TextView txtColor;
    private TextView colorInput;
    private LinearLayout ifScoutingTablet;
    private View root;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.fragment_home, container, false);
        settings = getActivity().getSharedPreferences(getString(R.string.SPARX_PREFS), 0);

        //email
        emailtxt = root.findViewById(R.id.emailTxt);
        emailInput = root.findViewById(R.id.emailInput);

        //team
        teamtxt = root.findViewById(R.id.teamTxt);
        teamInput = root.findViewById(R.id.teamInput);

        //event
        eventtxt = root.findViewById(R.id.eventTxt);
        eventInput = root.findViewById(R.id.eventInput);

        scoutertxt = root.findViewById(R.id.scouterTxt);
        scouterInput = root.findViewById(R.id.scouterInput);

        posTxt = root.findViewById(R.id.txtPos);
        posInput = root.findViewById(R.id.PosInput);

        txtColor = root.findViewById(R.id.txtColor);
        colorInput = root.findViewById(R.id.colorInput);

        ifScoutingTablet = root.findViewById(R.id.ifScoutingTablet);
        if(!settings.getBoolean(getString(R.string.SCOUT), false)) {
            ifScoutingTablet.setVisibility(View.INVISIBLE);
        }

        return root;
    }

    @Override
    public void onStart(){
        super.onStart();
        emailInput.setText(settings.getString(getString(R.string.EMAIL), "no"));
        teamInput.setText(settings.getString(getString(R.string.TEAM), "no"));
        eventInput.setText(settings.getString(getString(R.string.pref_SelectedEvent), "no"));
        if(settings.getBoolean(getString(R.string.SCOUT), false)){
            ifScoutingTablet.setVisibility(View.VISIBLE);
            scouterInput.setText(settings.getString(getString(R.string.scouter), "No one has Scouted"));
            posInput.setText(String.valueOf(settings.getInt(getString(R.string.pref_TeamPosition), 0)));
        }
        else {
            ifScoutingTablet.setVisibility(View.INVISIBLE);
        }

        if(settings.getBoolean(getString(R.string.pref_BlueAlliance), false)){
            txtColor.setTextColor(getResources().getColor(R.color.BText));
            colorInput.setBackgroundColor(getResources().getColor(R.color.BLUE));
            emailtxt.setTextColor(getResources().getColor(R.color.BText));
            emailInput.setTextColor(getResources().getColor(R.color.BText));
            teamtxt.setTextColor(getResources().getColor(R.color.BText));
            teamInput.setTextColor(getResources().getColor(R.color.BText));
            eventtxt.setTextColor(getResources().getColor(R.color.BText));
            eventInput.setTextColor(getResources().getColor(R.color.BText));
            scoutertxt.setTextColor(getResources().getColor(R.color.BText));
            scouterInput.setTextColor(getResources().getColor(R.color.BText));
            posTxt.setTextColor(getResources().getColor(R.color.BText));
            posInput.setTextColor(getResources().getColor(R.color.BText));
            View background = root.findViewById(R.id.background);
            background.setBackgroundColor(getResources().getColor(R.color.BBackground));
        }else{
            txtColor.setTextColor(getResources().getColor(R.color.RText));
            colorInput.setBackgroundColor(getResources().getColor(R.color.RED));
            emailtxt.setTextColor(getResources().getColor(R.color.RText));
            emailInput.setTextColor(getResources().getColor(R.color.RText));
            teamtxt.setTextColor(getResources().getColor(R.color.RText));
            teamInput.setTextColor(getResources().getColor(R.color.RText));
            eventtxt.setTextColor(getResources().getColor(R.color.RText));
            eventInput.setTextColor(getResources().getColor(R.color.RText));
            scoutertxt.setTextColor(getResources().getColor(R.color.RText));
            scouterInput.setTextColor(getResources().getColor(R.color.RText));
            posTxt.setTextColor(getResources().getColor(R.color.RText));
            posInput.setTextColor(getResources().getColor(R.color.RText));
            View background = root.findViewById(R.id.background);
            background.setBackgroundColor(getResources().getColor(R.color.RBackground));
        }
    }
}