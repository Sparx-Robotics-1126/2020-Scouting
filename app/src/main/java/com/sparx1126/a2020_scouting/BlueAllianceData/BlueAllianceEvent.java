package com.sparx1126.a2020_scouting.BlueAllianceData;

import android.util.Log;

import androidx.annotation.NonNull;

import com.sparx1126.a2020_scouting.Utilities.FileIO;
import com.sparx1126.a2020_scouting.Utilities.JsonData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class BlueAllianceEvent extends JsonData {
    private static final String TAG = "Sparx: ";
    private static final String HEADER = "BlueAllianceEvent: ";

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
    private String location;
    private String startDate;
    private String endDate;

    private static Map<String, BlueAllianceEvent> events = new HashMap<>();
    private static FileIO fileIO = FileIO.getInstance();

    private BlueAllianceEvent(String _key, String _name, String _week, String _location, String _startDate, String _endDate) {
        key = _key;
        name = _name;
        week = _week;
        location = _location;
        startDate = _startDate;
        endDate = _endDate;
    }

    public static void parseJson(String _data, String _team) {
        events.clear();
        try {
            JSONArray arr = new JSONArray(_data);
            for (int i = 0; i < arr.length(); i++) {
                JSONObject obj = arr.getJSONObject(i);
                String k = getString(obj, KEY);
                String n = getString(obj, NAME);
                String w = getString(obj, WEEK);
                String l = getString(obj, LOCATION);
                String s = getString(obj, START_DATE);
                String e = getString(obj, END_DATE);

                Log.d(TAG, HEADER + "parseJson for event " + k);
                events.put(k, new BlueAllianceEvent(k, n, w, l, s, e));
            }
            fileIO.storeTeamEvents(_data, _team);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getKey() {
        return key;
    }

    public String getName() {
        return name;
    }

    public String getWeek() {
        return week;
    }

    public String getLocation() {
        return location;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public static Map<String, BlueAllianceEvent> getEvents(String _team) {
        if (events.isEmpty()) {
            String data = fileIO.fetchTeamEvents(_team);
            if (!data.isEmpty()) {
                parseJson(data, _team);
            }
        }
        return events;
    }

    @Override
    @NonNull
    public String toString() {
        return ("\n" + "Event Name: " + name + "\n" + "Event Week: " + week + "\n" + "Event Location: " + location + "\n" + "Event Start Date " + startDate + "\n"
                + "Event End Date " + endDate);
    }
}

