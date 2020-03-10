package com.sparx1126.a2020_scouting.BlueAllianceData;

import android.util.Log;

import androidx.annotation.NonNull;

import com.sparx1126.a2020_scouting.Utilities.FileIO;
import com.sparx1126.a2020_scouting.Utilities.JsonData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BlueAllianceMatch extends JsonData {
    private static final String TAG = "Sparx: ";
    private static final String HEADER = "BlueAllianceMatch: ";

    //keys to access the JSON Object
    private static final String EPOCH_TIME = "predicted_time";
    private static final String MATCH_NUMBER = "match_number";
    private static final String ALLIANCES = "alliances";
    private static final String TEAM_KEYS = "team_keys";
    private static final String BLUE = "blue";
    private static final String RED = "red";
    private static final String COMP_LEVEL = "comp_level";

    private static FileIO fileIO = FileIO.getInstance();

    //Static Maps to hold all the BAM Objects
    private static HashMap<String, BlueAllianceMatch> matches = new HashMap<>();

    //Getters
    public static HashMap<String, BlueAllianceMatch> getMatches(String _event) {
        if (matches.isEmpty()) {
            FileIO fileIO = FileIO.getInstance();
            String data = fileIO.fetchEventMatches(_event);
            if (!data.isEmpty()) {
                parseDataToBAMMap(data, _event);
            }
        }
        return matches;
    }

    public ArrayList<String> getBlueTeamKeys() {
        return blueTeamKeys;
    }

    public ArrayList<String> getRedTeamKeys() {
        return redTeamKeys;
    }

    public String getEpochTime() {
        return epochTime;
    }

    public String getMatchNum() {
        return matchNum;
    }

    //Actual non-static object data
    private ArrayList<String> blueTeamKeys = new ArrayList<>();
    private ArrayList<String> redTeamKeys = new ArrayList<>();
    //Predicted start time of the match(epochTime
    private String epochTime;
    private String matchNum;

    //Initialize
    private BlueAllianceMatch(ArrayList<String> _blueKeys, ArrayList<String> _redKeys, String _time, String _matchNum) {
        blueTeamKeys = _blueKeys;
        redTeamKeys = _redKeys;
        epochTime = _time;
        matchNum = _matchNum;
    }

    //Parses all the matches in the JSON Object in String form(_data), to populate the static map(matches)
    public static void parseDataToBAMMap(String _data, String _event) {
        matches.clear();
        try {
            JSONArray array = new JSONArray(_data);
            for (int j = 0; j < array.length(); j++) {
                JSONObject obj = array.getJSONObject(j);
                // We onluy scout during qualifying matches...
                String compLevel = getString(obj, COMP_LEVEL);
                if (compLevel.equals("qm")) {
                    String epoch = getString(obj, EPOCH_TIME);
                    String matchNum = getString(obj, MATCH_NUMBER);
                    ArrayList<String> redKeys = new ArrayList<>();
                    ArrayList<String> blueKeys = new ArrayList<>();
                    JSONObject allianceObj = getJsonObject(obj, ALLIANCES);
                    JSONObject redObj = getJsonObject(allianceObj, RED);
                    JSONArray redKeyArray = getJsonArray(redObj, TEAM_KEYS);
                    JSONObject blueObj = getJsonObject(allianceObj, BLUE);
                    JSONArray blueKeyArray = getJsonArray(blueObj, TEAM_KEYS);
                    for (int i = 0; i < redKeyArray.length(); i++) {
                        redKeys.add(redKeyArray.getString(i));
                    }
                    for (int i = 0; i < blueKeyArray.length(); i++) {
                        blueKeys.add(blueKeyArray.getString(i));
                    }
                    matches.put(matchNum, new BlueAllianceMatch(blueKeys, redKeys, epoch, matchNum));
                }
            }
            Log.d(TAG, HEADER + "parsed for matches " + matches.size());
            fileIO.storeEventMatches(_data, _event);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static HashMap<String, BlueAllianceMatch> getTeamMatches(String _teamkey) {
        HashMap<String, BlueAllianceMatch> rtrn = new HashMap<>();
        for (Map.Entry<String, BlueAllianceMatch> matchEntry : matches.entrySet()) {
            if (matchEntry.getValue().getBlueTeamKeys().contains(_teamkey) || matchEntry.getValue().getRedTeamKeys().contains(_teamkey)) {
                rtrn.put(String.valueOf(matchEntry.getKey()), matchEntry.getValue());
            }
        }
        return rtrn;
    }

    @Override
    @NonNull
    //Print out the state of the BAM OBJect
    public String toString() {
        return "\n" + "MATCH NUMBER: " + matchNum + "\n" + "TIME: " + epochTime + "\n" + "RED KEYS: " + redTeamKeys.toString() + "\n" + "BLUE KEYS: " + blueTeamKeys.toString();
    }
}
