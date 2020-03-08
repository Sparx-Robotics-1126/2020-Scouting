package com.sparx1126.a2020_scouting.Data;

import com.sparx1126.a2020_scouting.BlueAllianceData.JsonData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class OurRankingData extends JsonData {
    private static final String bottom_port_tele = "bottom_port_tele";
    private static final String outer_port_tele = "outer_port_tele";
    private static final String inner_port_tele = "inner_port_tele";

    private ArrayList<Integer> ballsScoredOnBottomTele;
    private ArrayList<Integer> ballsScoredOnOuterTele;
    private ArrayList<Integer> ballsScoredOnInnerTele;

    private static Map<String, OurRankingData> data = new HashMap<>();

    public OurRankingData(){
        ballsScoredOnBottomTele = new ArrayList<>();
        ballsScoredOnInnerTele = new ArrayList<>();
        ballsScoredOnOuterTele = new ArrayList<>();
    }


//    public static void parseJson(String str){
//        data.clear();
//        try{
//            JSONObject obj = new JSONObject(str);
//            String bottomPortTele = getString(obj, bottom_port_tele);
//            String outerPortTele = getString(obj, outer_port_tele);
//            String innerPortTele = getString(obj, inner_port_tele);
//
//            Set<String> teamNames = BlueAllianceTeam.getTeams().keySet();
//            for(int i = 0; i < teamNames.size(); i++){
//                data.put(BlueAllianceTeam.getTeams().get(), new OurRankingData(bottomPortTele, outerPortTele, innerPortTele));
//            }
//        }catch (JSONException e){
//            e.printStackTrace();
//        }
//    }
}
