package com.sparx1126.a2020_scouting.BlueAllianceData;

import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BlueAllianceRanking extends JsonData{
    // the keys to get the data
    public static final String Rank = "rank";
    public static final String Losses = "losses";
    public static final String Ties = "ties";
    public static final String Wins = "wins";
    public static final String Sort_orders = "sort_orders";
    public static final String Team_key = "team_key";

    public static String rank;
    public static String losses;
    public static String ties;
    public static String wins;
    public static String team_key;

    private static ArrayList<String> sort_orders = new ArrayList<>();
    private static String Ranking_score;
    private static String Auto;
    private static String EndGame;
    private static String Teleop_Cell_CPanel;

    public static Map<String, BlueAllianceRanking> ranks = new HashMap<>();

    public BlueAllianceRanking(String rank, String losses, String ties, String wins, String team_key, String Ranking_score,
                               String Auto, String EndGame, String teleop_Cell_CPanel){
        this.rank = rank;
        this.losses = losses;
        this.ties = ties;
        this.wins = wins;
        this.team_key = team_key;
        this.Ranking_score = Ranking_score;
        this.Auto = Auto;
        this.EndGame = EndGame;
        this.Teleop_Cell_CPanel = teleop_Cell_CPanel;
    }

    public static void parseJson(String _data){
        ranks.clear();
        try{
            JSONObject o = new JSONObject(_data);
            JSONArray arr = getJsonArray(o,"rankings");
            for(int i = 0; i < arr.length(); i++){
                JSONObject obj = arr.getJSONObject(i);
                String r = getString(obj, Rank);
                JSONObject record = getJsonObject(obj, "record");
                String l = getString(record, Losses);
                String t = getString(record, Ties);
                String w = getString(record, Wins);
                String team = getString(obj, Team_key);
                JSONArray sortedOrders = getJsonArray(obj, Sort_orders);
                for(int j = 0; j < sortedOrders.length(); j++){
                    sort_orders.add(String.valueOf(sortedOrders.getInt(j)));
                }
                Ranking_score = sort_orders.get(0);
                Auto = sort_orders.get(1);
                EndGame = sort_orders.get(2);
                Teleop_Cell_CPanel = sort_orders.get(3);

                ranks.put(r, new BlueAllianceRanking(r, l, t, w, team, Ranking_score, Auto, EndGame, Teleop_Cell_CPanel));
            }
        }catch (JSONException e){
            e.printStackTrace();
        }
    }
}
