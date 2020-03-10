package com.sparx1126.a2020_scouting.ui.Benchmarking;

import android.text.BoringLayout;

public class BenchmarkingData {
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

    private final static String TEAM_NUMBER = "Team Number: ";
    private final static String END_TAG = ",\n";

    private final static String DRIVE_TYPE = "Drive Type: ";
    private final static String WHEEL_TYPE = "Wheel Type: ";
    private final static String NUM_WHEELS = "Number of Wheels: ";
    private final static String MAX_SPEED = "Max Speed (ft/s): ";
    private final static String HEIGHT = "Normal Height (ft): ";
    private final static String WEIGHT = "Weight (lbs): ";
    private final static String VISION_TYPE = "Vision Type: ";

    private final static String START_POS = "Start Position: ";
    private final static String STARTING_CELLS = "Starting Cells: ";
    private final static String AUTO_SCORE_BOTTOM = "Can Score Bottom in Auto: ";
    private final static String AUTO_SCORE_TOP = "Can Score Top in Auto: ";
    private final static String AUTO_ACQUIRE_FLOOR = "Can Acquire From Floor in Auto: ";

    private final static String TELE_SCORE_BOTTOM = "Can Score Bottom in Teleop: ";
    private final static String TELE_SCORE_TOP = "Can Score Top in Teleop: ";
    private final static String TELE_ACQUIRE_FLOOR = "Can Acquire From Floor in Teleop: ";

    private final static String CAN_CLIMB = "Can Climb: ";
    private final static String CAN_LEVEL = "Can Actively Level: ";

    private final static String COMMENTS = "Comments: ";

    BenchmarkingData(int teamNumber){
        this.teamNumber = teamNumber;
    }


    public String getData(){
        String data = "";

        data += TEAM_NUMBER + teamNumber + END_TAG;

        data += DRIVE_TYPE + driveType + END_TAG;
        data += WHEEL_TYPE + wheelType + END_TAG;
        data += NUM_WHEELS + numWheels + END_TAG;
        data += MAX_SPEED + maxSpeed + END_TAG;
        data += HEIGHT + height + END_TAG;
        data += WEIGHT + weight + END_TAG;
        data += VISION_TYPE + visionType + END_TAG;

        data += START_POS + startPos + END_TAG;
        data += STARTING_CELLS + startingCells + END_TAG;
        data += AUTO_SCORE_BOTTOM + autoScoreBottom + END_TAG;
        data += AUTO_SCORE_TOP + autoScoreTop + END_TAG;
        data += AUTO_ACQUIRE_FLOOR + autoAcquireFloor + END_TAG;

        data += TELE_SCORE_BOTTOM + teleScoreBottom + END_TAG;
        data += TELE_SCORE_TOP + teleScoreTop + END_TAG;
        data += TELE_ACQUIRE_FLOOR + teleAcquireFloor + END_TAG;

        data += CAN_CLIMB + canClimb + END_TAG;
        data += CAN_LEVEL + canLevel + END_TAG;

        data += COMMENTS + comments + END_TAG;

        return data;
    }

    public void setData(String data){
        teamNumber = Integer.parseInt(restoreData(data, TEAM_NUMBER));

        driveType = restoreData(data, DRIVE_TYPE);
        wheelType = restoreData(data, WHEEL_TYPE);
        numWheels = Integer.parseInt(restoreData(data, WHEEL_TYPE));
        maxSpeed = Double.parseDouble(restoreData(data, MAX_SPEED));
        height = Double.parseDouble(restoreData(data, HEIGHT));
        weight = Double.parseDouble(restoreData(data, WEIGHT));
        visionType = restoreData(data,VISION_TYPE);

        startPos = restoreData(data, START_POS);
        startingCells = Integer.parseInt(restoreData(data, STARTING_CELLS));
        autoScoreBottom = Boolean.parseBoolean(restoreData(data, AUTO_SCORE_BOTTOM));
        autoScoreTop = Boolean.parseBoolean(restoreData(data, AUTO_SCORE_TOP));
        autoAcquireFloor =  Boolean.parseBoolean(restoreData(data, AUTO_ACQUIRE_FLOOR));

        teleScoreBottom = Boolean.parseBoolean(restoreData(data, TELE_SCORE_BOTTOM));
        teleScoreTop = Boolean.parseBoolean(restoreData(data, TELE_SCORE_TOP));
        teleAcquireFloor = Boolean.parseBoolean(restoreData(data, TELE_ACQUIRE_FLOOR));

        canClimb = Boolean.parseBoolean(restoreData(data, CAN_CLIMB));
        canLevel = Boolean.parseBoolean(restoreData(data, CAN_LEVEL));

        comments = restoreData(data, COMMENTS);
    }


    private static String restoreData(String data, String key){
        int begin = data.indexOf(key) + key.length();
        int end = data.indexOf(END_TAG, begin);
        return data.substring(begin, end);
    }

}
