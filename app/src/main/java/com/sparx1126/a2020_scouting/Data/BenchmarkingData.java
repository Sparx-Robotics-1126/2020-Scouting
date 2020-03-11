package com.sparx1126.a2020_scouting.Data;

import android.util.Log;

import com.google.gson.JsonObject;
import com.sparx1126.a2020_scouting.BlueAllianceData.BlueAllianceTeam;
import com.sparx1126.a2020_scouting.Utilities.JsonData;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class BenchmarkingData extends JsonData {
    private static final String TAG = "Sparx: ";
    private static final String HEADER = "BenchmarkingData: ";

    //KEYS for the JSON
    public static final String TEAM_NUMBER = "team_number";
    public static final String DRIVE_TYPE = "drive_type";
    public static final String WHEEL_TYPE = "wheel_type";
    public static final String NUM_WHEELS = "num_wheels";
    public static final String MAX_SPEED = "max_speed";
    public static final String HEIGHT = "height";
    public static final String WEIGHT = "weight";
    public static final String VISION_TYPE = "vision_type";
    public static final String START_POS = "start_position";
    public static final String START_CELLS = "start_cells";
    public static final String AUTO_SCORE_BOTTOM = "auto_score_bottom";
    public static final String AUTO_SCORE_TOP = "auto_score_top";
    public static final String AUTO_ACQUIRE_FLOOR = "auto_acquire_floor";
    public static final String TELE_SCORE_BOTTOM = "tele_score_bottom";
    public static final String TELE_SCORE_TOP = "tele_score_top";
    public static final String TELE_ACQUIRE_FLOOR = "tele_acquire_floor";
    public static final String CAN_CLIMB = "can_climb";
    public static final String CAN_LEVEL = "can_level";
    public static final String COMMENTS = "comments";

    private static Map<Integer, BenchmarkingData> data = new HashMap<>();
    public static Map<Integer, BenchmarkingData> getData() {
        return data;
    }

    private static String prefEvent;
    public static String getprefEvent() {
        return prefEvent;
    }

    private int teamNumber;
    //General
    private String driveType;
    private String wheelType;
    private int numWheels;
    private double maxSpeed;
    private double height;
    private double weight;
    private String visionType;
    //Auto
    private String startPos;
    private int startingCells;
    private boolean autoScoreBottom;
    private boolean autoScoreTop;
    private boolean autoAcquireFloor;
    //Teleop
    private boolean teleScoreBottom;
    private boolean teleScoreTop;
    private boolean teleAcquireFloor;
    //End Game
    private boolean canClimb;
    private boolean canLevel;
    //Comments
    private String comments;

    public void setTeamNumber(int teamNumber) {
        this.teamNumber = teamNumber;
    }

    public void setDriveType(String driveType) {
        this.driveType = driveType;
    }

    public void setWheelType(String wheelType) {
        this.wheelType = wheelType;
    }

    public void setNumWheels(int numWheels) {
        this.numWheels = numWheels;
    }

    public void setMaxSpeed(double maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setVisionType(String visionType) {
        this.visionType = visionType;
    }

    public void setStartPos(String startPos) {
        this.startPos = startPos;
    }

    public void setStartingCells(int startingCells) {
        this.startingCells = startingCells;
    }

    public void setAutoScoreBottom(boolean autoScoreBottom) {
        this.autoScoreBottom = autoScoreBottom;
    }

    public void setAutoScoreTop(boolean autoScoreTop) {
        this.autoScoreTop = autoScoreTop;
    }

    public void setAutoAcquireFloor(boolean autoAcquireFloor) {
        this.autoAcquireFloor = autoAcquireFloor;
    }

    public void setTeleScoreBottom(boolean teleScoreBottom) {
        this.teleScoreBottom = teleScoreBottom;
    }

    public void setTeleScoreTop(boolean teleScoreTop) {
        this.teleScoreTop = teleScoreTop;
    }

    public void setTeleAcquireFloor(boolean teleAcquireFloor) {
        this.teleAcquireFloor = teleAcquireFloor;
    }

    public void setCanClimb(boolean canClimb) {
        this.canClimb = canClimb;
    }

    public void setCanLevel(boolean canLevel) {
        this.canLevel = canLevel;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public int getTeamNumber() {
        return teamNumber;
    }

    public String getDriveType() {
        return driveType;
    }

    public String getWheelType() {
        return wheelType;
    }

    public int getNumWheels() {
        return numWheels;
    }

    public double getMaxSpeed() {
        return maxSpeed;
    }

    public double getHeight() {
        return height;
    }

    public double getWeight() {
        return weight;
    }

    public String getVisionType() {
        return visionType;
    }

    public String getStartPos() {
        return startPos;
    }

    public int getStartingCells() {
        return startingCells;
    }

    public boolean isAutoScoreBottom() {
        return autoScoreBottom;
    }

    public boolean isAutoScoreTop() {
        return autoScoreTop;
    }

    public boolean isAutoAcquireFloor() {
        return autoAcquireFloor;
    }

    public boolean isTeleScoreBottom() {
        return teleScoreBottom;
    }

    public boolean isTeleScoreTop() {
        return teleScoreTop;
    }

    public boolean isTeleAcquireFloor() {
        return teleAcquireFloor;
    }

    public boolean isCanClimb() {
        return canClimb;
    }

    public boolean isCanLevel() {
        return canLevel;
    }

    public String getComments() {
        return comments;
    }

    public static void initializeData(String _prefEvent, Map<String, BlueAllianceTeam> _teams) {
        data.clear();
        prefEvent = _prefEvent;

        for (String team : _teams.keySet()) {
            int teamNumber = Integer.parseInt(team);
            data.put(teamNumber, new BenchmarkingData(teamNumber));
        }
    }

    public BenchmarkingData(int teamNumber) {
        this.teamNumber = teamNumber;
    }

    public JsonObject toJson() {
        JsonObject object = new JsonObject();

        object.addProperty(TEAM_NUMBER, teamNumber);
        object.addProperty(DRIVE_TYPE, driveType);
        object.addProperty(WHEEL_TYPE, wheelType);
        object.addProperty(NUM_WHEELS, numWheels);
        object.addProperty(MAX_SPEED, maxSpeed);
        object.addProperty(HEIGHT, height);
        object.addProperty(WEIGHT, weight);
        object.addProperty(VISION_TYPE, visionType);
        object.addProperty(START_POS, startPos);
        object.addProperty(START_CELLS, startingCells);
        object.addProperty(AUTO_SCORE_BOTTOM, autoScoreBottom);
        object.addProperty(AUTO_SCORE_TOP, autoScoreTop);
        object.addProperty(AUTO_ACQUIRE_FLOOR, autoAcquireFloor);
        object.addProperty(TELE_SCORE_BOTTOM, teleScoreBottom);
        object.addProperty(TELE_SCORE_TOP, teleScoreTop);
        object.addProperty(TELE_ACQUIRE_FLOOR, teleAcquireFloor);
        object.addProperty(CAN_CLIMB, canClimb);
        object.addProperty(CAN_LEVEL, canLevel);
        object.addProperty(COMMENTS, comments);

        return object;
    }

    public static void parseJsons(Map<String, JSONObject> _data) {
        try {
            Log.d(TAG, HEADER + "parseJsons for number teams " + _data.size());
            for (Map.Entry<String, JSONObject> mail : _data.entrySet()) {
                String subject = mail.getKey();
                if (subject.contains(prefEvent)) {
                    // example 2020ndgf.frc3871.json
                    int indexStart = subject.indexOf("frc") + 3; // find string start of "frc", add string lenght.
                    String team = subject.substring(indexStart);
                    team = team.substring(0, team.indexOf("."));
                    data.get(Integer.parseInt(team)).parseJson(mail.getValue());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void parseJson(JSONObject _data) {
        try {
            teamNumber = getInt(_data, TEAM_NUMBER);
            driveType = getString(_data, DRIVE_TYPE);
            wheelType = getString(_data, WHEEL_TYPE);
            numWheels = getInt(_data, NUM_WHEELS);
            maxSpeed = getDouble(_data, MAX_SPEED);
            height = getDouble(_data, HEIGHT);
            weight = getDouble(_data, WEIGHT);
            visionType = getString(_data, VISION_TYPE);
            startPos = getString(_data, START_POS);
            startingCells = getInt(_data, START_CELLS);
            autoScoreBottom = getBoolean(_data, AUTO_SCORE_BOTTOM);
            autoScoreTop = getBoolean(_data, AUTO_SCORE_TOP);
            autoAcquireFloor = getBoolean(_data, AUTO_ACQUIRE_FLOOR);
            teleScoreBottom = getBoolean(_data, TELE_SCORE_BOTTOM);
            teleScoreTop = getBoolean(_data, TELE_SCORE_TOP);
            teleAcquireFloor = getBoolean(_data, TELE_ACQUIRE_FLOOR);
            canClimb = getBoolean(_data, CAN_CLIMB);
            canLevel = getBoolean(_data, CAN_LEVEL);
            comments = getString(_data, COMMENTS);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}