package com.sparx1126.a2020_scouting.Fragments;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.sparx1126.a2020_scouting.BlueAllianceData.BlueAllianceEvent;
import com.sparx1126.a2020_scouting.BlueAllianceData.BlueAllianceRank;
import com.sparx1126.a2020_scouting.Data.OurRankingData;
import com.sparx1126.a2020_scouting.R;
import com.sparx1126.a2020_scouting.Utilities.BlueAllianceNetwork;
import com.sparx1126.a2020_scouting.Utilities.GetMail;
import com.sparx1126.a2020_scouting.Utilities.JsonData;
import com.sparx1126.a2020_scouting.ViewData;

import org.json.JSONObject;

import java.util.Map;

public class ViewHomeFragment extends Fragment {
    private static final String TAG = "Sparx: ";
    private static final String HEADER = "ViewHomeFragment: ";

    private SharedPreferences settings;
    private BlueAllianceEvent event;
    private static BlueAllianceNetwork blueAlliance;

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
    private ImageButton goBackHome;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, HEADER + "onCreateView");

        View root = inflater.inflate(R.layout.fragment_view_home, container, false);
        settings = getActivity().getSharedPreferences(getString(R.string.SPARX_PREFS), 0);
        blueAlliance = BlueAllianceNetwork.getInstance();

        backgroundLayout = root.findViewById(R.id.background);
        regionalNameInput = root.findViewById(R.id.regionalName);
        regionLocationInput = root.findViewById(R.id.regionLocation);
        datesTextInput = root.findViewById(R.id.dates);
        configurationTextView = root.findViewById(R.id.configuration);
        teamTextView = root.findViewById(R.id.teamTxt);
        teamInput = root.findViewById(R.id.teamInput);
        ifScoutingTabletLayout = root.findViewById(R.id.ifScoutingTablet);
        if (!settings.getBoolean(getString(R.string.SCOUT), false)) {
            ifScoutingTabletLayout.setVisibility(View.INVISIBLE);
        }
        scouterTextView = root.findViewById(R.id.scouterTxt);
        scouterInput = root.findViewById(R.id.scouterInput);
        posTextView = root.findViewById(R.id.posTxt);
        posInput = root.findViewById(R.id.posInput);
        allianceColorTextView = root.findViewById(R.id.allianceColorTxt);
        allianceColorInput = root.findViewById(R.id.allianceColorInput);

        goBackHome = root.findViewById(R.id.goBackHome);
        goBackHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, HEADER + "going back Home");
                getActivity().finish();
            }
        });

        return root;
    }

    @Override
    public void onStart() {
        super.onStart();

        Log.d(TAG, HEADER + "onStart");
        String selectedEvent = settings.getString(getString(R.string.pref_SelectedEvent), "");
        if(selectedEvent.length() != 0) {
            String selectedTeam = settings.getString(getString(R.string.TEAM), "");
            if(selectedTeam.length() != 0) {
                Log.d(TAG, HEADER + "event " + selectedEvent + ", team " + selectedTeam);
                event = BlueAllianceEvent.getEvents(selectedTeam).get(selectedEvent);
                if (event != null) {
                    regionalNameInput.setText(event.getName());
                    regionLocationInput.setText(event.getLocation());
                    String dateRange = event.getStartDate() + " to " + event.getEndDate();
                    datesTextInput.setText(dateRange);
                }
                teamInput.setText(selectedTeam);
                if (settings.getBoolean(getString(R.string.SCOUT), false)) {
                    ifScoutingTabletLayout.setVisibility(View.VISIBLE);
                    scouterInput.setText(settings.getString(getString(R.string.scouter), "No one has Scouted"));
                    posInput.setText(String.valueOf(settings.getInt(getString(R.string.pref_TeamPosition), 0)));
                } else {
                    ifScoutingTabletLayout.setVisibility(View.INVISIBLE);
                }

                if (settings.getBoolean(getString(R.string.pref_BlueAlliance), false)) {
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
                } else {
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
            blueAlliance.downloadEventRanks(getActivity(), selectedEvent, new BlueAllianceNetwork.Callback() {
                @Override
                public void handleFinishDownload(final String _data) {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (JsonData.isValidJsonObject(_data)) {
                                Log.d(TAG, HEADER + "downLoaded event ranks");
                                BlueAllianceRank.parseJson(_data);
                                new GetMail(getActivity(), settings.getString(getString(R.string.EMAIL), ""),
                                        settings.getString(getString(R.string.PASSWORD), ""),
                                        new GetMail.Callback() {
                                            @Override
                                            public void handleFinishDownload(Map<String, JSONObject> mails) {
                                                Log.d(TAG, HEADER + "email handleFinishDownload");
                                                String pref_SelectedEvent = settings.getString(getResources().getString(R.string.pref_SelectedEvent), "");
                                                OurRankingData.parseJsons(pref_SelectedEvent, mails);
                                            }
                                        });
                            } else {
                                Log.e(TAG, HEADER + "Internet returned BAD DATA for EventRanks, try another wifi!");
                                Toast.makeText(getActivity(), "Internet returned BAD DATA for EventRanks, try another wifi!", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
            });
        }
    }
}