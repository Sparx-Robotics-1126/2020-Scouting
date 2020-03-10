package com.sparx1126.a2020_scouting.Data;

import android.content.SharedPreferences;
import android.util.Log;

import com.sparx1126.a2020_scouting.R;
import com.sparx1126.a2020_scouting.Utilities.JsonData;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class OurRankingData extends JsonData {
    private static final String TAG = "Sparx: ";
    private static final String HEADER = "OurRankingData: ";

    private static Map<Integer, Float> ballsScoredOnBottomAve = new HashMap<>();
    private static Map<Integer, Float> ballsScoredOnOuterAve = new HashMap<>();
    private static Map<Integer, Float> ballsScoredOnInnerAve= new HashMap<>();

    public static Map<Integer, Float> getBallsScoredOnBottomAve() {
        return ballsScoredOnBottomAve;
    }
    public static Map<Integer, Float> getBallsScoredOnOuterAve() {
        return ballsScoredOnOuterAve;
    }
    public static Map<Integer, Float> getBallsScoredOnInnerAve() {
        return ballsScoredOnInnerAve;
    }

    public static void parseJsons(String _prefEvent, Map<String, JSONObject> _data) {
        ballsScoredOnBottomAve.clear();
        ballsScoredOnOuterAve.clear();
        ballsScoredOnInnerAve.clear();
        Map<Integer, ArrayList<Integer>> ballsScoredOnBottom = new HashMap<>();
        Map<Integer, ArrayList<Integer>> ballsScoredOnInner = new HashMap<>();
        Map<Integer, ArrayList<Integer>> ballsScoredOnOuter = new HashMap<>();
        try {
            for(Map.Entry<String, JSONObject> mail :  _data.entrySet()){
                // example 2020ndgf.frc3871.BlueAlliance.Position3.2.json
                String subject = mail.getKey();
                if(subject.contains(_prefEvent)) {
                    int indexStart = subject.indexOf("frc") + 3; // find string start of "frc", add string lenght.
                    String team = subject.substring(indexStart);
                    team = team.substring(0, team.indexOf("."));
                    Log.d(TAG, HEADER + "parseJson " + team);
                    parseJson(Integer.parseInt(team), ballsScoredOnBottom,
                            ballsScoredOnInner, ballsScoredOnOuter, mail.getValue());
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        calculate(ballsScoredOnBottom.entrySet(), ballsScoredOnBottomAve);
        calculate(ballsScoredOnOuter.entrySet(), ballsScoredOnOuterAve);
        calculate(ballsScoredOnInner.entrySet(), ballsScoredOnInnerAve);
    }

    private static void calculate(Set<Map.Entry<Integer, ArrayList<Integer>>> _entrySet,
                           Map<Integer, Float> _container) {
        for(Map.Entry<Integer, ArrayList<Integer>> entry : _entrySet){
            float average = 0f;
            for(Integer ar: entry.getValue()){
                average += ar;
            }
            average = average/entry.getValue().size();
            _container.put(entry.getKey(), average);
        }
    }

    private static int ourRank(SharedPreferences settings){
        Integer teamWithOfLargest = 1126;
        for(Map.Entry<Integer, Float> r : ballsScoredOnInnerAve.entrySet()){
            if(r.getValue() > ballsScoredOnInnerAve.get(teamWithOfLargest)){
                teamWithOfLargest = r.getKey();
            }
        }
        return teamWithOfLargest;
    }

    private static void parseJson(Integer _team, Map<Integer, ArrayList<Integer>> _ballsScoredOnBottom,
                           Map<Integer, ArrayList<Integer>> _ballsScoredOnInner,
                           Map<Integer, ArrayList<Integer>> _ballsScoredOnOuter,
                           JSONObject _data) {
        try {
            JSONObject auto = getJsonObject(_data, ScoutingData.AUTO);
            JSONObject autoScored = getJsonObject(auto, ScoutingData.POWER_CELLS_SCORED);
            addValue(_ballsScoredOnBottom, _team, getInt(autoScored,  ScoutingData.BOTTOM_PORT));
            addValue(_ballsScoredOnInner, _team, getInt(autoScored,  ScoutingData.INNER_PORT));
            addValue(_ballsScoredOnOuter, _team, getInt(autoScored,  ScoutingData.OUTER_PORT));
            JSONObject tele = getJsonObject(_data, ScoutingData.TELE);
            JSONObject teleScored = getJsonObject(tele, ScoutingData.POWER_CELLS_SCORED);
            addValue(_ballsScoredOnBottom, _team, getInt(teleScored,  ScoutingData.BOTTOM_PORT));
            addValue(_ballsScoredOnInner, _team, getInt(teleScored,  ScoutingData.INNER_PORT));
            addValue(_ballsScoredOnOuter, _team, getInt(teleScored,  ScoutingData.OUTER_PORT));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void addValue(Map<Integer, ArrayList<Integer>> _map, Integer _team, int _value) {
        if(_map.containsKey(_team)){
            _map.get(_team).add(_value);
        } else {
            ArrayList<Integer> values = new ArrayList<>();
            values.add(_value);
            _map.put(_team, values);
        }
    }
}
