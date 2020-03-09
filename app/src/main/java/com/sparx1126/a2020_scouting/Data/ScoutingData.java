package com.sparx1126.a2020_scouting.Data;

import android.util.Log;

import com.google.gson.JsonObject;
import com.sparx1126.a2020_scouting.BlueAllianceData.BlueAllianceMatch;
import com.sparx1126.a2020_scouting.Utilities.JsonData;

import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;

public class ScoutingData extends JsonData {
    private static final String TAG = "Sparx: ";
    private static final String HEADER = "ScoutingData: ";

    //KEYS for the JSON
    public static final String SCOUTER_NAME =  "scouter_name";
    public static final String MATCH_NUMBER = "match_number";
    public static final String TEAM_NUMBER = "team_number";
    public static final String ALLIANCE_COLOR = "alliance_color";
    public static final String AUTO = "auto";
    public static final String TELE = "tele";
    public static final String END = "end";
    public static final String OTHER = "other";
    public static final String POWER_CELLS_SCORED = "power_cells_scored";
    public static final String POWER_CELLS_ACQUIRED = "power_cells_acquired";
    public static final String STARTING_POSITION = "starting_position";
    public static final String POWER_CELLS_IN_ROBOT = "power_cells_in_robot";
    public static final String BOTTOM_PORT = "bottom_port";
    public static final String INNER_PORT = "inner_port";
    public static final String OUTER_PORT = "outer_port";
    public static final String FLOOR = "floor";
    public static final String HIGH_CHUTE = "high_chute";
    public static final String LOW_CHUTE = "low_chute";
    public static final String CROSSED_INITIATION_LINE = "crossed_initiation_line";
    public static final String CONTROL_PANEL = "control_panel";
    public static final String PERFORMED_POSITION_CONTROL = "performed_position_control";
    public static final String PERFORMED_ROTATION_CONTROL = "performed_rotation_control";
    public static final String RENDEZVOUS = "rendezvous";
    public static final String ACTIVE_LEVELING = "active_leveling";
    public static final String PLAYED_EXCELLENT_DEFENSE = "played_excellent_defense";
    public static final String CAUSED_FOUL = "caused_foul";
    public static final String BROKE_OR_GOT_DISABLED = "broke_or_got_disabled";

    // STARTING_POSITION valid values
    public static final String STARTING_POSITION_CLOSEST = "Closest to Power Port";
    public static final String STARTING_POSITION_MIDDLE = "Middle";
    public static final String STARTING_POSITION_FARTHEST = "Farthest to Power Port";
    public static final String RENDEZVOUS_NO_PARK = "No Park";
    public static final String RENDEZVOUS_PARKED = "Parked";
    public static final String RENDEZVOUS_HANGED = "Hanged";
    public static final String ACTIVE_LEVELING_NO_LEVEL = "No Leveling";
    public static final String ACTIVE_LEVELING_TRIED_TO_LEVEL = "Tried Leveling";
    public static final String ACTIVE_LEVELING_LEVELED = "Leveled";

    private static Map<Integer, ScoutingData> data = new HashMap<>();
    public static Map<Integer, ScoutingData> getData() {
        return data;
    }

    private static String prefAllianceColor;
    private static String prefTeamPosition;
    public static String getPrefAllianceColor() {
        return prefAllianceColor;
    }
    public static String getPrefTeamPosition() {
        return prefTeamPosition;
    }


    private String scouterName = "";
    private int matchNumber;
    private int teamNumber;
    private String allianceColor = "";
    private String autoStartingPosition = "";
    private int autoPowerCellsInRobot;
    private int autoPowerCellsScoredBottomPort;
    private int autoPowerCellsScoredInnerPort;
    private int autoPowerCellsScoredOuterPort;
    private int autoPowerCellsAcquiredFloor;
    private boolean autoCrossedInitiationLine;
    private int telePowerCellsScoredBottomPort;
    private int telePowerCellsScoredInnerPort;
    private int telePowerCellsScoredOuterPort;
    private int telePowerCellsAcquiredFloor;
    private int telePowerCellsAcquiredHighChute;
    private int telePowerCellsAcquiredLowChute;
    private boolean telePerformedPositionControl;
    private boolean telePerformedRotationControl;
    private String endRendezvous = "";
    private String endActiveLeveling = "";
    private boolean otherPlayedExcellentDefense;
    private boolean otherCausedFoul;
    private boolean otherBrokeOrGotDisabled;

    public void setScouterName(String scouterName) {
        this.scouterName = scouterName;
    }

    public void setTeamNumber(int teamNumber) {
        this.teamNumber = teamNumber;
    }

    public void setAllianceColor(String allianceColor) {
        this.allianceColor = allianceColor;
    }

    public void setAutoStartingPosition(String autoStartingPosition) {
        this.autoStartingPosition = autoStartingPosition;
    }

    public void setAutoPowerCellsInRobot(int autoPowerCellsInRobot) {
        this.autoPowerCellsInRobot = autoPowerCellsInRobot;
    }

    public void setAutoPowerCellsScoredBottomPort(int autoPowerCellsScoredBottomPort) {
        this.autoPowerCellsScoredBottomPort = autoPowerCellsScoredBottomPort;
    }

    public void setAutoPowerCellsScoredInnerPort(int autoPowerCellsScoredInnerPort) {
        this.autoPowerCellsScoredInnerPort = autoPowerCellsScoredInnerPort;
    }

    public void setAutoPowerCellsScoredOuterPort(int autoPowerCellsScoredOuterPort) {
        this.autoPowerCellsScoredOuterPort = autoPowerCellsScoredOuterPort;
    }

    public void setAutoPowerCellsAcquiredFloor(int autoPowerCellsAcquiredFloor) {
        this.autoPowerCellsAcquiredFloor = autoPowerCellsAcquiredFloor;
    }

    public void setAutoCrossedInitiationLine(boolean autoCrossedInitiationLine) {
        this.autoCrossedInitiationLine = autoCrossedInitiationLine;
    }

    public void setTelePowerCellsScoredBottomPort(int telePowerCellsScoredBottomPort) {
        this.telePowerCellsScoredBottomPort = telePowerCellsScoredBottomPort;
    }

    public void setTelePowerCellsScoredInnerPort(int telePowerCellsScoredInnerPort) {
        this.telePowerCellsScoredInnerPort = telePowerCellsScoredInnerPort;
    }

    public void setTelePowerCellsScoredOuterPort(int telePowerCellsScoredOuterPort) {
        this.telePowerCellsScoredOuterPort = telePowerCellsScoredOuterPort;
    }

    public void setTelePowerCellsAcquiredFloor(int telePowerCellsAcquiredFloor) {
        this.telePowerCellsAcquiredFloor = telePowerCellsAcquiredFloor;
    }

    public void setTelePowerCellsAcquiredHighChute(int telePowerCellsAcquiredHighChute) {
        this.telePowerCellsAcquiredHighChute = telePowerCellsAcquiredHighChute;
    }

    public void setTelePowerCellsAcquiredLowChute(int telePowerCellsAcquiredLowChute) {
        this.telePowerCellsAcquiredLowChute = telePowerCellsAcquiredLowChute;
    }

    public void setTelePerformedPositionControl(boolean telePerformedPositionControl) {
        this.telePerformedPositionControl = telePerformedPositionControl;
    }

    public void setTelePerformedRotationControl(boolean telePerformedRotationControl) {
        this.telePerformedRotationControl = telePerformedRotationControl;
    }

    public void setEndRendezvous(String endRendezvous) {
        this.endRendezvous = endRendezvous;
    }

    public void setEndActiveLeveling(String endActiveLeveling) {
        this.endActiveLeveling = endActiveLeveling;
    }

    public void setOtherPlayedExcellentDefense(boolean otherPlayedExcellentDefense) {
        this.otherPlayedExcellentDefense = otherPlayedExcellentDefense;
    }

    public void setOtherCausedFoul(boolean otherCausedFoul) {
        this.otherCausedFoul = otherCausedFoul;
    }

    public void setOtherBrokeOrGotDisabled(boolean otherBrokeOrGotDisabled) {
        this.otherBrokeOrGotDisabled = otherBrokeOrGotDisabled;
    }

    public String getScouterName() {
        return scouterName;
    }

    public int getMatchNumber() {
        return matchNumber;
    }

    public int getTeamNumber() {
        return teamNumber;
    }

    public String getAllianceColor() {
        return allianceColor;
    }

    public String getAutoStartingPosition() {
        return autoStartingPosition;
    }

    public int getAutoPowerCellsInRobot() {
        return autoPowerCellsInRobot;
    }

    public int getAutoPowerCellsScoredBottomPort() {
        return autoPowerCellsScoredBottomPort;
    }

    public int getAutoPowerCellsScoredInnerPort() {
        return autoPowerCellsScoredInnerPort;
    }

    public int getAutoPowerCellsScoredOuterPort() {
        return autoPowerCellsScoredOuterPort;
    }

    public int getAutoPowerCellsAcquiredFloor() {
        return autoPowerCellsAcquiredFloor;
    }

    public boolean isAutoCrossedInitiationLine() {
        return autoCrossedInitiationLine;
    }

    public int getTelePowerCellsScoredBottomPort() {
        return telePowerCellsScoredBottomPort;
    }

    public int getTelePowerCellsScoredInnerPort() {
        return telePowerCellsScoredInnerPort;
    }

    public int getTelePowerCellsScoredOuterPort() {
        return telePowerCellsScoredOuterPort;
    }

    public int getTelePowerCellsAcquiredFloor() {
        return telePowerCellsAcquiredFloor;
    }

    public int getTelePowerCellsAcquiredHighChute() {
        return telePowerCellsAcquiredHighChute;
    }

    public int getTelePowerCellsAcquiredLowChute() {
        return telePowerCellsAcquiredLowChute;
    }

    public boolean isTelePerformedPositionControl() {
        return telePerformedPositionControl;
    }

    public boolean isTelePerformedRotationControl() {
        return telePerformedRotationControl;
    }

    public String getEndRendezvous() {
        return endRendezvous;
    }

    public String getEndActiveLeveling() {
        return endActiveLeveling;
    }

    public boolean isOtherPlayedExcellentDefense() {
        return otherPlayedExcellentDefense;
    }

    public boolean isOtherCausedFoul() {
        return otherCausedFoul;
    }

    public boolean isOtherBrokeOrGotDisabled() {
        return otherBrokeOrGotDisabled;
    }

    public static void initializeData(String _prefAllianceColor, String _prefTeamPosition) {
        prefAllianceColor = _prefAllianceColor;
        prefTeamPosition = _prefTeamPosition;
        Map<String, BlueAllianceMatch> matches = BlueAllianceMatch.getMatches();
        for(BlueAllianceMatch entry : matches.values()){
            int matchNum = Integer.parseInt(entry.getMatchNum());
            data.put(matchNum, new ScoutingData(matchNum));
        }
    }

    private ScoutingData(int _matchNumber) {
        matchNumber = _matchNumber;
    }

    public JsonObject toJson(){
        /**
         * {
         *   scouter_name:
         *   match_num:
         *   team_num:
         *   alliance_color:
         *   auto:{
         *     starting_pos:
         *     power_cells_in_robot:
         *     power_cells_scored:{
         *       bottom_port:
         *       inner_port:
         *       outer_port:
         *     }
         *     power_cells_acquired:{
         *       floor:
         *     }
         *     crossed_initiation_line:
         *   }
         *   tele:{
         *     power_cells_scored:{
         *       bottom_port:
         *       inner_port:
         *       outer_port:
         *     }
         *     power_cells_acquired:{
         *       floor:
         *       high_chute:
         *       low_chute:
         *     }
         *     control_panel:{
         *       performed_position_control:
         *       performed_rotation_control:
         *     }
         *   }
         *   end{
         *     rendezvous:
         *     active_leveling:
         *   }
         *   other{
         *     played_excellent_defense:
         *     caused_foul:
         *     broke_or_got_disabled:
         *   }
         * }
         **/
        JsonObject object = new JsonObject();
        object.addProperty(SCOUTER_NAME, scouterName);
        object.addProperty(MATCH_NUMBER, matchNumber);
        object.addProperty(TEAM_NUMBER, teamNumber);
        object.addProperty(ALLIANCE_COLOR, allianceColor);
        
        JsonObject autoObject = new JsonObject();
        {
            autoObject.addProperty(STARTING_POSITION, autoStartingPosition);
            autoObject.addProperty(POWER_CELLS_IN_ROBOT, autoPowerCellsInRobot);
            JsonObject autoScoredObject = new JsonObject();
            {
                autoScoredObject.addProperty(BOTTOM_PORT, autoPowerCellsScoredBottomPort);
                autoScoredObject.addProperty(INNER_PORT, autoPowerCellsScoredInnerPort);
                autoScoredObject.addProperty(OUTER_PORT, autoPowerCellsScoredOuterPort);
            }
            autoObject.add(POWER_CELLS_SCORED, autoScoredObject);
            JsonObject autoAcquiredObject = new JsonObject();
            {
                autoAcquiredObject.addProperty(FLOOR, autoPowerCellsAcquiredFloor);
            }
            autoObject.add(POWER_CELLS_ACQUIRED, autoAcquiredObject);
            autoObject.addProperty(CROSSED_INITIATION_LINE, autoCrossedInitiationLine);
        }
        object.add(AUTO, autoObject);

        JsonObject teleObject = new JsonObject();
        {
            JsonObject teleScoredObject = new JsonObject();
            {
                teleScoredObject.addProperty(BOTTOM_PORT, telePowerCellsScoredBottomPort);
                teleScoredObject.addProperty(INNER_PORT, telePowerCellsScoredInnerPort);
                teleScoredObject.addProperty(OUTER_PORT, telePowerCellsScoredOuterPort);
            }
            teleObject.add(POWER_CELLS_SCORED, teleScoredObject);
            
            JsonObject teleAcquiredObject = new JsonObject();
            {
                teleAcquiredObject.addProperty(FLOOR, telePowerCellsAcquiredFloor);
                teleAcquiredObject.addProperty(HIGH_CHUTE, telePowerCellsAcquiredHighChute);
                teleAcquiredObject.addProperty(LOW_CHUTE, telePowerCellsAcquiredLowChute);
            }
            teleObject.add(POWER_CELLS_ACQUIRED, teleAcquiredObject);
            
            JsonObject teleControlPanelObject = new JsonObject();
            {
                teleControlPanelObject.addProperty(PERFORMED_POSITION_CONTROL, telePerformedPositionControl);
                teleControlPanelObject.addProperty(PERFORMED_ROTATION_CONTROL, telePerformedRotationControl);
            }
            teleObject.add(CONTROL_PANEL, teleControlPanelObject);
            
        }
        object.add(TELE, teleObject);

        JsonObject endObject = new JsonObject();
        {
            endObject.addProperty(RENDEZVOUS, endRendezvous);
            endObject.addProperty(ACTIVE_LEVELING, endActiveLeveling);
        }
        object.add(END, endObject);

        JsonObject otherObject = new JsonObject();
        {
            otherObject.addProperty(PLAYED_EXCELLENT_DEFENSE, otherPlayedExcellentDefense);
            otherObject.addProperty(CAUSED_FOUL, otherCausedFoul);
            otherObject.addProperty(BROKE_OR_GOT_DISABLED, otherBrokeOrGotDisabled);
        }
        object.add(OTHER, otherObject);

        return object;
    }

    public static void parseJsons(Map<String, JSONObject> _data) {
        try {
            for(Map.Entry<String, JSONObject> mail :  _data.entrySet()){
                String subject = mail.getKey();
                if(subject.contains(prefAllianceColor) && subject.contains(prefTeamPosition)) {
                    int indexStart= subject.indexOf("frc");
                    String team = subject.substring(indexStart);
                    team = team.substring(0,team.indexOf("."));
                    data.get(Integer.parseInt(team)).parseJson(mail.getValue());
                    Log.d(TAG, HEADER + "parseJson " + team);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void parseJson(JSONObject _data) {
        try {
            scouterName = getString(_data, SCOUTER_NAME);
            matchNumber = getInt(_data, MATCH_NUMBER);
            teamNumber = getInt(_data, TEAM_NUMBER);
            allianceColor = getString(_data, ALLIANCE_COLOR);
            JSONObject auto = getJsonObject(_data, AUTO);
            autoStartingPosition = getString(auto, STARTING_POSITION);
            autoPowerCellsInRobot = getInt(auto, POWER_CELLS_IN_ROBOT);
            JSONObject autoScored = getJsonObject(auto, POWER_CELLS_SCORED);
            autoPowerCellsScoredBottomPort = getInt(autoScored, BOTTOM_PORT);
            autoPowerCellsScoredInnerPort = getInt(autoScored, INNER_PORT);
            autoPowerCellsScoredOuterPort = getInt(autoScored, OUTER_PORT);
            JSONObject autoAcquired = getJsonObject(auto, POWER_CELLS_ACQUIRED);
            autoPowerCellsAcquiredFloor = getInt(autoAcquired, FLOOR);
            autoCrossedInitiationLine = getBoolean(auto, CROSSED_INITIATION_LINE);
            JSONObject tele = getJsonObject(_data, TELE);
            JSONObject teleScored = getJsonObject(tele, POWER_CELLS_SCORED);
            telePowerCellsScoredBottomPort = getInt(teleScored, BOTTOM_PORT);
            telePowerCellsScoredInnerPort = getInt(teleScored, INNER_PORT);
            telePowerCellsScoredOuterPort = getInt(teleScored, OUTER_PORT);
            JSONObject teleAcquired = getJsonObject(tele, POWER_CELLS_ACQUIRED);
            telePowerCellsAcquiredFloor = getInt(teleAcquired, FLOOR);
            telePowerCellsAcquiredHighChute = getInt(teleAcquired, HIGH_CHUTE);
            telePowerCellsAcquiredLowChute = getInt(teleAcquired, LOW_CHUTE);
            JSONObject teleControlPanel = getJsonObject(tele, CONTROL_PANEL);
            telePerformedPositionControl = getBoolean(teleControlPanel, PERFORMED_POSITION_CONTROL);
            telePerformedRotationControl = getBoolean(teleControlPanel, PERFORMED_ROTATION_CONTROL);
            JSONObject end = getJsonObject(_data, END);
            endRendezvous = getString(end, RENDEZVOUS);
            endActiveLeveling = getString(end, ACTIVE_LEVELING);
            JSONObject other = getJsonObject(_data, OTHER);
            otherPlayedExcellentDefense = getBoolean(other, PLAYED_EXCELLENT_DEFENSE);
            otherCausedFoul = getBoolean(other, CAUSED_FOUL);
            otherBrokeOrGotDisabled = getBoolean(other, BROKE_OR_GOT_DISABLED);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
