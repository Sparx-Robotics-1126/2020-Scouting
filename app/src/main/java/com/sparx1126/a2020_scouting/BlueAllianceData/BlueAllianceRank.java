package com.sparx1126.a2020_scouting.BlueAllianceData;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class BlueAllianceRank extends JsonData {
    // keys from thebluealliance.com API
    private static final String RANK_ARRAY = "rankings";
    private static final String RANK = "rank";
    private static final String RECORD_OBJ = "record";
    private static final String LOSSES = "losses";
    private static final String TIES = "ties";
    private static final String WINS = "wins";
    private static final String DETAILS_ARRAY = "sort_orders";
    private static final int RANKING_SCORE_POS = 0;
    private static final int AUTO_POS = 1;
    private static final int END_GAME_POS = 2;
    private static final int TELEOP_CELL_CPANEL_POS = 3;
    private static final String TEAM_KEY = "team_key";

    private String teamNum;
    private String rank;
    private String record;   // wins-ties-losses
    private String teamName;
    private String details;

    public String getTeamNum() {
        return teamNum;
    }

    public String getRank() {
        return rank;
    }

    public String getRecord() {
        return record;
    }

    public String getTeamName() {
        return teamName;
    }

    public String getDetails() {
        return details;
    }

    private static Map<Integer, BlueAllianceRank> ranks = new HashMap<>();

    public BlueAllianceRank(String teamNum, String rank, String record, String teamName, String details) {
        this.teamNum = teamNum;
        this.rank = rank;
        this.record = record;
        this.teamName = teamName;
        this.details = details;
    }

    public static void parseJson(String _data){
        ranks.clear();
        try{
            JSONObject entireObj = new JSONObject(_data);
            JSONArray rankingsArr = entireObj.getJSONArray(RANK_ARRAY);
            for(int i = 0; i < rankingsArr.length(); i++) {
                JSONObject currentObj = rankingsArr.getJSONObject(i);
                String rank = getString(currentObj, RANK);
                JSONObject recordObj = getJsonObject(currentObj, RECORD_OBJ);
                String record = getString(recordObj, WINS) + "-";
                record += getString(recordObj, TIES) + "-";
                record += getString(recordObj, LOSSES);
                JSONArray detailsArr = currentObj.getJSONArray(DETAILS_ARRAY);
                String details = "Ranking Score: " + detailsArr.getString(RANKING_SCORE_POS) + ", ";
                details += "Auto: " + detailsArr.getString(AUTO_POS) + ", ";
                details += "End Game: " + detailsArr.getString(END_GAME_POS) + ", ";
                details += "Teleop Cell + CPanel: " + detailsArr.getString(TELEOP_CELL_CPANEL_POS);
                String teamNum = getString(currentObj, TEAM_KEY).substring(3);
                String teamName = getString(currentObj, TEAM_KEY); // broken for now
                ranks.put(Integer.parseInt(rank), new BlueAllianceRank(teamNum, rank, record, teamName, details));
            }
        }catch(JSONException e) {
            e.printStackTrace();
        }
    }

    public static Map<Integer, BlueAllianceRank> getRanks() {
        return ranks;
    }
}

