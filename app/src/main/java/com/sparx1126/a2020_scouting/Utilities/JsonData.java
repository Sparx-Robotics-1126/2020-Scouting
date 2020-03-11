package com.sparx1126.a2020_scouting.Utilities;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;

public class JsonData {
    protected static int getInt(JSONObject _jsonObj, String _key) {
        int rtnData = 0;

        try {
            rtnData = _jsonObj.getInt(_key);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return rtnData;
    }

    protected static float getFloat(JSONObject _jsonObj, String _key) {
        float rtnData = (float) 0.0;

        try {
            //https://ourcodeworld.com/articles/read/367/how-to-get-a-float-value-from-a-json-object-in-java
            rtnData = BigDecimal.valueOf(_jsonObj.getDouble(_key)).floatValue();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return rtnData;
    }

    protected static boolean getBoolean(JSONObject _jsonObj, String _key) {
        boolean rtnData = false;

        try {
            rtnData = _jsonObj.getBoolean(_key);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return rtnData;
    }

    protected static String getString(JSONObject _jsonObj, String _key) {
        String rtnData = "";

        try {
            rtnData = _jsonObj.get(_key).toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return rtnData;
    }

    protected static JSONObject getJsonObject(JSONObject _jsonObj, String _key) {
        JSONObject rtnData = null;

        try {
            rtnData = _jsonObj.getJSONObject(_key);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return rtnData;
    }

    protected static JSONArray getJsonArray(JSONObject _jsonObj, String _key) {
        JSONArray rtnData = null;

        try {
            rtnData = _jsonObj.getJSONArray(_key);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return rtnData;
    }

    public static boolean isValidJsonArray(String _data) {
        try {
            new JSONArray(_data);
        } catch (JSONException jsExcp) {
            return false;
        }
        return true;
    }

    public static boolean isValidJsonObject(String _data) {
        try {
            new JSONObject(_data);
        } catch (JSONException jsExcp) {
            return false;
        }
        return true;
    }

    public static boolean isValidJsonObjectOrArray(String _data) {
        try {
            new JSONObject(_data);
        } catch (JSONException ex) {
            try {
                new JSONArray(_data);
            } catch (JSONException ex1) {
                return false;
            }
        }
        return true;
    }
}
