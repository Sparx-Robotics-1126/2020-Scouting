package com.sparx1126.a2020_scouting.BlueAllianceData;


import android.util.SparseArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BlueAllianceMatch extends JsonData {
    //keys to access the JSON Object
    private static final String EPOCH_TIME = "predicted_time";
    private static final String MATCH_NUMBER = "match_number";
    private static final String ALLIANCES = "alliances";
    private static final String TEAM_KEYS = "team_keys";
    private static final String BLUE = "blue";
    private static final String RED = "red";

    //Static Maps to hold all the BAM Objects
    private static HashMap<String,BlueAllianceMatch> matches = new HashMap<String,BlueAllianceMatch>();
    private static Map<String, JSONObject> jsonMathes = new HashMap<String, JSONObject>();
    private static Map<String, JSONObject> jsonteamMathes = new HashMap<String, JSONObject>();
    private static Map<String, BlueAllianceMatch> teamMatches = new HashMap<String, BlueAllianceMatch>();
    //Getters
    public static  HashMap<String,BlueAllianceMatch> getMatches(){return matches;}
    public ArrayList<String> getBlueTeamKeys() { return blueTeamKeys; }
    public ArrayList<String> getRedTeamKeys() { return redTeamKeys; }
    public String getEpochTime() { return epochTime; }
    public String getMatchNum() { return matchNum; }

    //Actual non-static object data
    private ArrayList<String> blueTeamKeys;
    private ArrayList<String> redTeamKeys;
    //Predicted start time of the match(epochTime
    private String epochTime;
    private String matchNum;

    //Initialize
    private BlueAllianceMatch(ArrayList<String> blueKeys,ArrayList<String> redKeys,String time, String matchNum){
        blueTeamKeys=blueKeys;
        redTeamKeys=redKeys;
        epochTime=time;
        this.matchNum=matchNum;


    }
    //Parses all the matches in the JSON Object in String form(_data), to populate the static map(matches)
    public static void parseDataToBAMMap(String _data){
        matches.clear();
        try{
            JSONArray array = new JSONArray(_data);
            for(int j=1; j<array.length();j++){
                JSONObject obj = array.getJSONObject(j);
                String epoch =  getString(obj,EPOCH_TIME);
                String mathNum = getString(obj,MATCH_NUMBER);
                ArrayList<String> redKeys = new ArrayList<String>();
                ArrayList<String>  blueKeys = new ArrayList<String>();

                JSONObject allianceObj = getJsonObject(obj, ALLIANCES);
                JSONObject redObj = getJsonObject(allianceObj,RED);
                JSONArray redKeyArray = getJsonArray(redObj,TEAM_KEYS);
                JSONObject blueObj = getJsonObject(allianceObj,BLUE);
                JSONArray blueKeyArray = getJsonArray(blueObj,TEAM_KEYS);
                for( int i=0;i<redKeyArray.length();i++){
                    redKeys.add(redKeyArray.getString(i));
                }
                for( int i=0;i<blueKeyArray.length();i++){
                    blueKeys.add(blueKeyArray.getString(i));
                }
                matches.put(String.valueOf(j),new BlueAllianceMatch(blueKeys,redKeys,epoch,mathNum));


            }
        }
        catch(JSONException e) {
            e.printStackTrace();
        }

    }

    public static void parseDataToJSonMap(String _data){



    }




}
