package com.sparx1126.a2020_scouting.BlueAllianceData;

import android.util.Log;

import com.sparx1126.a2020_scouting.Utilities.JsonData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class BlueAllianceTeam extends JsonData {
    private static final String TAG = "Sparx: ";
    private static final String HEADER = "BlueAllianceTeam: ";

    private static final String key = "key";
    private static final String teamName = "nickname";

    private String team_name;
    private String team_num;

    public String getTeam_name() {
        return team_name;
    }

    public String getTeam_num() {
        return team_num;
    }

    public static Map<String, BlueAllianceTeam> getTeams() {
        return teams;
    }

    private static Map<String, BlueAllianceTeam> teams = new HashMap<>();

    private BlueAllianceTeam(String team_num, String team_name){
        this.team_name = team_name;
        this.team_num = team_num;
    }

    public static void parseJson(String _data){
        teams.clear();
        try{
            JSONArray arr = new JSONArray(_data);
            for(int i = 0; i < arr.length(); i++){
                JSONObject obj = arr.getJSONObject(i);
                String teamNum = getString(obj, key).substring(3);
                String teamname = getString(obj, teamName);

                Log.d(TAG, HEADER + "parseJson " + teamNum);

                teams.put(teamNum, new BlueAllianceTeam(teamNum, teamname));
            }
        }catch (JSONException e){
            e.printStackTrace();
        }
    }
}
