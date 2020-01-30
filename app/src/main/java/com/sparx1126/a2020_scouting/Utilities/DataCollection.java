package com.sparx1126.a2020_scouting.Utilities;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.sparx1126.a2020_scouting.BlueAllianceData.*;

import java.util.HashMap;
import java.util.Map;

public class DataCollection {
    private static DataCollection dataCollection;
    private Map<String, BlueAllianceEvent> events;

    private DataCollection() {
        events =new HashMap<>();
    }

    public static synchronized DataCollection getInstance(){
        if(dataCollection == null ) {
            dataCollection = new DataCollection();
        }
        return dataCollection;
    }

    public Map<String, BlueAllianceEvent> getEvents(){
        return events;
    }

    public void setTeamEvents(String _data){
        events.clear();
        try {
            JSONArray array = new JSONArray(_data);
            for (int i = 0; i < array.length(); i++) {
                JSONObject obj = array.getJSONObject(i);
                BlueAllianceEvent item = new BlueAllianceEvent(obj);
                events.put(item.getKey(), item);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
