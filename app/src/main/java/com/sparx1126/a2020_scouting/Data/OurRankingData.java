package com.sparx1126.a2020_scouting.Data;

import android.content.SharedPreferences;
import android.util.Log;
import android.util.Pair;

import com.sparx1126.a2020_scouting.BlueAllianceData.JsonData;
import com.sparx1126.a2020_scouting.R;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class OurRankingData extends JsonData {
    private SharedPreferences settings;

    //Keys
    private static final String bottom_port_tele = "bottom_port_tele";
    private static final String outer_port_tele = "outer_port_tele";
    private static final String inner_port_tele = "inner_port_tele";
    //Log
    static private String TAG = "Sparx: ";
    static private String HEADER = "OurRankingData: ";
    //just have dont use
    private static Map<String, ArrayList<Integer>> ballsScoredOnBottomTele = new HashMap<>();
    private static Map<String, ArrayList<Integer>> ballsScoredOnOuterTele = new HashMap<>();
    private static Map<String, ArrayList<Integer>> ballsScoredOnInnerTele = new HashMap<>();
    // use these they are the averaged ones
    private static  Map<String,Float > ballsScoredOnBottomTeleAve = new HashMap<>();
    private static Map<String, Float > ballsScoredOnOuterTeleAve = new HashMap<>();
    private static Map<String, Float > ballsScoredOnInnerTeleAve= new HashMap<>();

    public static Map<String, ArrayList<Integer>> getBallsScoredOnBottomTele() {
        return ballsScoredOnBottomTele;
    }

    public static Map<String, ArrayList<Integer>> getBallsScoredOnOuterTele() {
        return ballsScoredOnOuterTele;
    }

    public static Map<String, ArrayList<Integer>> getBallsScoredOnInnerTele() {
        return ballsScoredOnInnerTele;
    }

    public static Map<String, Float> getBallsScoredOnBottomTeleAve() {
        return ballsScoredOnBottomTeleAve;
    }

    public static Map<String, Float> getBallsScoredOnOuterTeleAve() {
        return ballsScoredOnOuterTeleAve;
    }

    public static Map<String, Float> getBallsScoredOnInnerTeleAve() {
        return ballsScoredOnInnerTeleAve;
    }

    public static  void calculate(){
        for(Map.Entry<String, ArrayList<Integer>> arr : ballsScoredOnBottomTele.entrySet()){
            float average = 0f;
            for(Integer ar: arr.getValue()){
                average += ar;
            }
            average = average/arr.getValue().size();
            ballsScoredOnBottomTeleAve.put(arr.getKey(), average);
        }
        for(Map.Entry<String, ArrayList<Integer>> arr : ballsScoredOnOuterTele.entrySet()){
            float average = 0f;
            for(Integer ar: arr.getValue()){
                average += ar;
            }
            average = average/arr.getValue().size();
            ballsScoredOnOuterTeleAve.put(arr.getKey(), average);
        }
        for(Map.Entry<String, ArrayList<Integer>> arr : ballsScoredOnInnerTele.entrySet()){
            float average = 0f;
            for(Integer ar: arr.getValue()){
                average += ar;
            }
            average = average/arr.getValue().size();
            ballsScoredOnInnerTeleAve.put(arr.getKey(), average);
        }
    }
    public static void parseJson(String team, String str) {
        try {
            JSONObject obj = new JSONObject(str);
            JSONObject tele = getJsonObject(obj, "Teleop");
            JSONObject ballsScored = getJsonObject(tele, "power_cells_scored_tele");
            int ballsScoredOnouterTele = Integer.valueOf(getString(ballsScored, outer_port_tele));
            if(ballsScoredOnOuterTele.containsKey(team)){
                ballsScoredOnOuterTele.get(team).add(ballsScoredOnouterTele);
            }else{
                ArrayList<Integer> arr = new ArrayList<>();
                arr.add(ballsScoredOnouterTele);
                ballsScoredOnOuterTele.put(team,arr);
            }



            int ballsScoredInnerTele = Integer.valueOf(getString(ballsScored, inner_port_tele));
            if(ballsScoredOnInnerTele.containsKey(team)){
                ballsScoredOnInnerTele.get(team).add(ballsScoredInnerTele);
            }else{
                ArrayList<Integer> arr = new ArrayList<>();
                arr.add(ballsScoredInnerTele);
                ballsScoredOnInnerTele.put(team,arr);
            }
            int ballsScoredBottomTele = Integer.valueOf(getString(ballsScored, bottom_port_tele));
            if(ballsScoredOnBottomTele.containsKey(team)){
                ballsScoredOnBottomTele.get(team).add(ballsScoredBottomTele);
            }else{
                ArrayList<Integer> arr = new ArrayList<>();
                arr.add(ballsScoredBottomTele);
                ballsScoredOnBottomTele.put(team,arr);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Map<String, Integer> ourRank(){
        Map<String, Integer> ourRankng = new HashMap<>();
        for(Map.Entry<String, Float> i : ballsScoredOnInnerTeleAve.entrySet()){

        }
        return null;
    }
}
