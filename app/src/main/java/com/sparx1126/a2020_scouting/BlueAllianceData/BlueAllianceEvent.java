package com.sparx1126.a2020_scouting.BlueAllianceData;

import android.util.Log;

import com.sparx1126.a2020_scouting.Utilities.FileIO;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.security.Key;
import java.util.HashMap;
import java.util.Map;
import java.util.jar.JarEntry;

//Old class from 2019
//Must be updated to new 2020 data format (Static maps in each Data class)

public class BlueAllianceEvent extends JsonData {
    // keys from thebluealliance.com API
    private static final String KEY = "key";
    private static final String NAME = "name";
    private static final String WEEK = "week";
    private static final String LOCATION = "location_name";
    private static final String START_DATE = "start_date";
    private static final String END_DATE = "end_date";

    private String key;
    private String name;
    private String week;
    private String Location;
    private String startDate;
    private String endDate;

    private static Map<String, BlueAllianceEvent> events = new HashMap<>();

    public BlueAllianceEvent(String key, String name, String week, String Location, String startDate, String endDate) {
       this.key = key;
       this.name = name;
       this.week = week;
       this.Location = Location;
       this.startDate = startDate;
       this.endDate = endDate;
    }

    public static void parseJson(String _data, String _team){
        events.clear();
        try{
           JSONArray arr = new JSONArray(_data);
           for(int i = 0; i < arr.length(); i++){
               JSONObject obj = arr.getJSONObject(i);
               String k = getString(obj, KEY);
               String n =  getString(obj,NAME);
               String w = getString(obj,WEEK);
               String l = getString(obj,LOCATION);
               String s = getString(obj, START_DATE);
               String e = getString(obj, END_DATE);

               events.put(k ,new BlueAllianceEvent(k, n, w, l ,s ,e));
           }
           FileIO fileIO = FileIO.getInstance();
           fileIO.storeTeamEvents(_data, _team);
        }catch(JSONException e) {
            e.printStackTrace();
        }
    }

    public String getKey() {return key;}
    public String getName() {return name;}
    public String getWeek() {return week;}
    public String getLocation() {return Location;}
    public String getStartDate() {return startDate;}
    public String getEndDate() {return endDate;}
    public static Map<String, BlueAllianceEvent> getEvents(String _team) {
        if(events.isEmpty()) {
            FileIO fileIO = FileIO.getInstance();
            String data = fileIO.fetchTeamEvents(_team);
            if(!data.isEmpty()) {
                parseJson(data, _team);
            }
        }

        return events;
    }

    @Override
    public String toString(){
        return ("\n" + "Event Name: " + name + "\n" + "Event Week: " + week + "\n" + "Event Location: " + Location + "\n" +  "Event Start Date " + startDate + "\n"
        + "Event End Date " + endDate);
    }
}

