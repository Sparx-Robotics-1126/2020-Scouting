package com.sparx1126.a2020_scouting.BlueAllianceData;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BlueAllianceMatch extends JsonData {
    private static final String HARD_CODED_FRC_TEAM = "frc1126";

    //keys to access the JSON Object
    private static final String EPOCH_TIME = "predicted_time";
    private static final String MATCH_NUMBER = "match_number";
    private static final String ALLIANCES = "alliances";
    private static final String TEAM_KEYS = "team_keys";
    private static final String BLUE = "blue";
    private static final String RED = "red";
    private static final String COMP_LEVEL ="comp_level";

    //Static Maps to hold all the BAM Objects
    private static HashMap<String,BlueAllianceMatch> matches = new HashMap<>();
    private static Map<String, JSONObject> jsonMathes = new HashMap<>();
    private static Map<String, JSONObject> jsonteamMathes = new HashMap<>();
    private static Map<String, BlueAllianceMatch> teamMatches = new HashMap<>();
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
            for(int j=0; j<array.length();j++){
                JSONObject obj = array.getJSONObject(j);
               // Log.e("BAM OBJ", obj.toString());
                String epoch =  getString(obj,EPOCH_TIME);
                String compLevel = getString(obj,COMP_LEVEL);
                String matchNum = getString(obj,MATCH_NUMBER);
                ArrayList<String> redKeys = new ArrayList<>();
                ArrayList<String>  blueKeys = new ArrayList<>();
                JSONObject allianceObj = getJsonObject(obj, ALLIANCES);
                JSONObject redObj = getJsonObject(allianceObj,RED);
                JSONArray redKeyArray = getJsonArray(redObj,TEAM_KEYS);
                JSONObject blueObj = getJsonObject(allianceObj,BLUE);
                JSONArray blueKeyArray = getJsonArray(blueObj,TEAM_KEYS);
                for( int i=0;i<redKeyArray.length();i++){
                    //Log.e("BAM", redKeyArray.getString(i));
                    redKeys.add(redKeyArray.getString(i));
                }
                for( int i=0;i<blueKeyArray.length();i++){
                    blueKeys.add(blueKeyArray.getString(i));
                }
                if(compLevel.equals("qm")){
                    matches.put(matchNum,new BlueAllianceMatch(blueKeys,redKeys,epoch,matchNum));
                }
             //  Log.e("BAM2", matches.get("1").getEpochTime());
            }
        }
        catch(JSONException e) {
            e.printStackTrace();
        }

    }
    public static HashMap<String,BlueAllianceMatch> getTeamMatches() {
        HashMap<String, BlueAllianceMatch> rtrn = new HashMap<>();
        for (int i = 1; i < matches.size(); i++) {
            //System.out.println("TESTING "+ i);
            BlueAllianceMatch match = BlueAllianceMatch.getMatches().get(String.valueOf(i));
            //Log.e("TEST KEYS NULL", match.toString());

            if (match.getBlueTeamKeys().contains(HARD_CODED_FRC_TEAM) || match.getRedTeamKeys().contains(HARD_CODED_FRC_TEAM)) {
                //Log.e("TEAM TEST SAT",match.getEpochTime());
                rtrn.put(String.valueOf(i), match);
            }
        }
        return rtrn;
    }


    @Override
    //Print out the state of the BAM OBJect
    public String toString(){
      return  "\n"+"MATCH NUMBER: " + matchNum +"\n"+ "TIME: "+ epochTime + "\n" + "RED KEYS: " + redTeamKeys.toString() + "\n" + "BLUE KEYS: " + blueTeamKeys.toString();
    }




}
