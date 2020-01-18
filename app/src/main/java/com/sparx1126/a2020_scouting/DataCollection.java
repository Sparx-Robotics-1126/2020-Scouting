package com.sparx1126.a2020_scouting;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DataCollection {
    private Map<String, BlueAllianceMatch> matches;
    private Map<String, BlueAllianceTeam> teams;

    private DataCollection() {
        teams = new HashMap<>();
        matches = new HashMap<>();

    }

    public void setEventTeams(String _data){
        teams.clear();
        try {
            JSONArray array = new JSONArray(_data);
            for (int i = 0; i < array.length(); i++) {
                JSONObject obj = array.getJSONObject(i);
                BlueAllianceTeam item = new BlueAllianceTeam(obj);
                teams.put(item.getKey(), item);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}

