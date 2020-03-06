package com.sparx1126.a2020_scouting;

import android.service.autofill.FieldClassification;
import android.util.Log;


import com.sparx1126.a2020_scouting.BlueAllianceData.BlueAllianceMatch;
import com.sparx1126.a2020_scouting.BlueAllianceData.JsonData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ScoutingData extends JsonData {
    //KEYS for the JSON
    private static final String  scouter_name =  "scouter_name";
    private static final String match_num = "match_num";
    private static final String team_scouted = "team_num";
    private static final String starting_pos = "starting_pos";
    private static final String bottom_port = "bottom_port";
    private static final String outer_port = "outer_port";
    private static final String inner_port = "inner_port";
    private static final String Power_Cells_Scored = "Power_Cells_Scored";
    private static final String Auto = "Auto";
    private static final String power_cell_start = "power_cell_start";
    private static final String power_cells_acq_floor = "power_cells_acq_floor";
    private static final String crosses_initiation_line = "crosses_initiation_line";
    private static final String bottom_port_tele = "bottom_port_tele";
    private static final String outer_port_tele = "outer_port_tele";
    private static final String inner_port_tele = "inner_port_tele";
    private static final String power_cells_scored_tele = "power_cells_scored_tele";
    private static final String acq_floor = "acq_floor";
    private static final String low_chute = "low_chute";
    private static final String high_chute = "high_chute";
    private static final String power_cell_acq = "power_cell_acq";
    private static final String performed_rotation_control = "performed_rotation_control";
    private static final String performed_position_control = "performed_position_control";
    private static final String control_panel = "control_panel";
    private static final String hangingEnd = "hanging";
    private static final String parkedEnd = "parked";
    private static final String  rendzoas = "rendzoas";
    private static final String levelingEnd = "leveling";

    //class variables
    private String scouterName;
    private String MatchNumber;
    private String TeamScouted;
    private String startingPos;
    private String PowerCellAtStart;
    private String ballsScoredOnBottomAuto;
    private String ballsScoredOnOuterAuto;
    private String ballsScoredOnInnerAuto;
    private String ballsAcquiredFloorAuto;
    private String crossesInitiatoinLine;
    private String ballsScoredOnBottomTele;
    private String ballsScoredOnOuterTele;
    private String ballsScoredOnInnerTele;
    private String ballsAcquiredFloorTele;
    private String ballsAcquiredLowChuteTele;
    private String ballsAcquiredHighChuteTele;
    private String preformedRotationControl;
    private String performedPositionControl;
    private String hanging;
    private String parked;
    private String leveling;

    private static Map<String, ScoutingData> data = new HashMap<>();

    public ScoutingData(String match_num){
        this.scouterName = "NO SCOUTER";
        this.MatchNumber = match_num;
        this.TeamScouted = "0000";
        this.startingPos = "-1";
        this.PowerCellAtStart = "-1";
        this.ballsScoredOnBottomAuto = "0";
        this.ballsScoredOnOuterAuto = "0";
        this.ballsScoredOnInnerAuto = "0";
        this.ballsAcquiredFloorAuto = "0";
        this.crossesInitiatoinLine = "false";
        this.ballsScoredOnBottomTele = "0";
        this.ballsScoredOnOuterTele = "0";
        this.ballsScoredOnInnerTele = "0";
        this.ballsAcquiredFloorTele = "0";
        this.ballsAcquiredLowChuteTele = "0";
        this.ballsAcquiredHighChuteTele = "0";
        this.preformedRotationControl = "";
        this.performedPositionControl = "";
        this.hanging = "";
        this.parked = "";
        this.leveling = "";
    }

    public ScoutingData(String scouterName,
             String MatchNumber,
             String TeamScouted,
             String startingPos,
             String PowerCellAtStart,
             String ballsScoredOnBottomAuto,
             String ballsScoredOnOuterAuto,
             String ballsScoredOnInnerAuto,
             String ballsAcquiredFloorAuto,
             String crossesInitiatoinLine,
             String ballsScoredOnBottomTele,
             String ballsScoredOnOuterTele,
             String ballsScoredOnInnerTele,
             String ballsAcquiredFloorTele,
             String ballsAcquiredLowChuteTele,
             String ballsAcquiredHighChuteTele,
             String preformedRotationControl,
             String performedPositionControl,
             String hanging,
             String parked,
             String leveling) {

        this.scouterName = scouterName;
        this.MatchNumber = MatchNumber;
        this.TeamScouted = TeamScouted;
        this.startingPos = startingPos;
        this.PowerCellAtStart = PowerCellAtStart;
        this.ballsScoredOnBottomAuto = ballsScoredOnBottomAuto;
        this.ballsScoredOnOuterAuto = ballsScoredOnOuterAuto;
        this.ballsScoredOnInnerAuto = ballsScoredOnInnerAuto;
        this.ballsAcquiredFloorAuto = ballsAcquiredFloorAuto;
        this.crossesInitiatoinLine = crossesInitiatoinLine;
        this.ballsScoredOnBottomTele = ballsScoredOnBottomTele;
        this.ballsScoredOnOuterTele = ballsScoredOnOuterTele;
        this.ballsScoredOnInnerTele = ballsScoredOnInnerTele;
        this.ballsAcquiredFloorTele = ballsAcquiredFloorTele;
        this.ballsAcquiredLowChuteTele = ballsAcquiredLowChuteTele;
        this.ballsAcquiredHighChuteTele = ballsAcquiredHighChuteTele;
        this.preformedRotationControl = preformedRotationControl;
        this.performedPositionControl = performedPositionControl;
        this.hanging = hanging;
        this.parked = parked;
        this.leveling = leveling;

    }



    public String toJson(){
        /**
         * {
         * Scouter Name:
         * Match Num:
         * Team Num:
         * Alliance color
         * Auto{
         * Starting Position
         * powercells scored
         * {
         * Bottem Port,
         * Inner Port,
         * Outer Port
         * },
         * Power cells at start
         * power cells acquierd
         * Crossed initiation line
         * },
         * Teleop{
         * powercellsScored{
         * Bottom Port,
         * Inner Port,
         * Outer Port
         * }
         * power Chells Acq{
         * floor,
         * low Chute,
         * high Chute
         * }
         * ControlPannel{
         * performed Rotation control,
         * peroment Position control
         * }
         * },
         * End Game{
         * Redeuoas{
         * hanging,
         * parked
         * }
         * Leveling{
         * No leveling,
         * Tried,
         * Saccessful
         * }
         * }
         * }
         */
        String ScouterName = "\"scouter_name\":" +"\""+ scouterName+ "\"";
        String MatchNum = "\"match_num\":"+ "\""+MatchNumber +"\"";
        String teamNum = "\"team_num\":" + "\""+TeamScouted+"\"";
        String startingPos = "\"starting_pos\":"+ "\""+this.startingPos+"\"";
        String bottomPort =  "\"bottom_port\":" + "\""+ballsScoredOnBottomAuto + "\""+ ",";
        String OuterPort = "\"outer_port\":" + "\""+ballsScoredOnOuterAuto + "\""+ ",";
        String InnerPort = "\"inner_port\":" + "\""+ballsScoredOnBottomAuto+ "\"";
        String PowerCellsScored =  "\"Power_Cells_Scored\":{" + bottomPort + OuterPort + InnerPort+ "},";
        String powerCellsStart = "\"power_cell_start\":" + "\""+PowerCellAtStart+ "\"";
        String PowerCellsAcqFloor =  "\"power_cells_acq_floor\":" +"\""+ ballsAcquiredFloorAuto+ "\"";
        String crosses = "\"crosses_initiation_line\":"+ "\""+crossesInitiatoinLine+ "\"";
        String bottomPortTele = "\"bottom_port_tele\":"+"\""+ ballsScoredOnBottomTele+ "\"" + ",";
        String outerPortTele = "\"outer_port_tele\":" + "\""+ballsScoredOnOuterTele+ "\"" + ",";
        String innerPortTele = "\"inner_port_tele\":" + "\""+ballsScoredOnInnerTele+ "\"";
        String PowerCellsScoredTele = "\"power_cells_scored_tele\":{" + bottomPortTele + outerPortTele + innerPortTele + "},";
        String floorTele = "\"acq_floor\":" +"\""+ ballsAcquiredFloorTele+ "\""+ ",";
        String lowChuteTele = "\"low_chute\":" + "\""+ballsAcquiredLowChuteTele+ "\""+ ",";
        String highChuteTele = "\"high_chute\":" + "\""+ballsAcquiredHighChuteTele+ "\"";
        String PowerCellsAcqTele = "\"power_cell_acq\":{" +floorTele + lowChuteTele + highChuteTele + "},";
        String performedRot = "\"performed_rotation_control\":" + "\""+ preformedRotationControl + "\""+ ",";
        String performtPos = "\"performed_position_control\":" + "\""+ performedPositionControl+ "\"";
        String ControlPanel = "\"control_panel\":{"+ performedRot + performtPos + "}";
        String hanging = "\"hanging\":" + "\""+ isHanging()+ "\""+",";
        String parked = "\"parked\":" + "\""+isParked()+ "\"";
        String rendezoas = "\"rendzoas\":{" + hanging + parked + "},";
        String leveling = "\"leveling\":" +"\""+ this.leveling+ "\"";

        return ("{" + ScouterName +","+ MatchNum + ","+ teamNum + "," + "\"Auto\":{"+ startingPos + ","+
                PowerCellsScored + powerCellsStart + "," + PowerCellsAcqFloor + "," + crosses + "},"
                + "\"Teleop\":{" +  PowerCellsScoredTele + PowerCellsAcqTele + ControlPanel + "}," +
                "\"EndGame\":{" + rendezoas + leveling+ "}" + "}");
    }

    public static void setJson(Map<String, BlueAllianceMatch> bam){

    }

    public static void parseJson(String str){
        data.clear();
        try{
            JSONObject obj = new JSONObject(str);
                String name = getString(obj, scouter_name);
                String matchNum = getString(obj, match_num);
                String teamNum = getString(obj, team_scouted);
                String startingPos = getString(obj, starting_pos);
                String bottomPort = getString(obj, bottom_port);
                String outerPort = getString(obj, outer_port);
                String innerPort = getString(obj, inner_port);
                String ballsStart = getString(obj, power_cell_start);
                String ballsAcq = getString(obj, power_cells_acq_floor);
                String crosses = getString(obj, crosses_initiation_line);
                String bottomPortTele = getString(obj, bottom_port_tele);
                String outerPortTele = getString(obj, outer_port_tele);
                String innerPortTele = getString(obj, inner_port_tele);
                String acqFloor = getString(obj, acq_floor);
                String bottomChute = getString(obj, low_chute);
                String highChute = getString(obj, high_chute);
                String performedRot = getString(obj, performed_rotation_control);
                String performedPos = getString(obj, performed_position_control);
                String hagingEnd = getString(obj, hangingEnd);
                String parkedend = getString(obj, parkedEnd);
                String leveling = getString(obj, levelingEnd);

                data.put(matchNum, new ScoutingData(name, matchNum, teamNum, startingPos,bottomPort, outerPort, innerPort, ballsStart, ballsAcq, crosses,
                        bottomPortTele, outerPortTele, innerPortTele, acqFloor, bottomChute, highChute,performedRot, performedPos, hangingEnd ,parkedend,leveling));
        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "ScoutingData{" +
                "scouterName='" + scouterName + '\'' +
                ", MatchNumber='" + MatchNumber + '\'' +
                ", TeamScouted='" + TeamScouted + '\'' +
                ", startingPos='" + startingPos + '\'' +
                ", PowerCellAtStart='" + PowerCellAtStart + '\'' +
                ", ballsScoredOnBottomAuto='" + ballsScoredOnBottomAuto + '\'' +
                ", ballsScoredOnOuterAuto='" + ballsScoredOnOuterAuto + '\'' +
                ", ballsScoredOnInnerAuto='" + ballsScoredOnInnerAuto + '\'' +
                ", ballsAcquiredFloorAuto='" + ballsAcquiredFloorAuto + '\'' +
                ", crossesInitiatoinLine='" + crossesInitiatoinLine + '\'' +
                ", ballsScoredOnBottomTele='" + ballsScoredOnBottomTele + '\'' +
                ", ballsScoredOnOuterTele='" + ballsScoredOnOuterTele + '\'' +
                ", ballsScoredOnInnerTele='" + ballsScoredOnInnerTele + '\'' +
                ", ballsAcquiredFloorTele='" + ballsAcquiredFloorTele + '\'' +
                ", ballsAcquiredLowChuteTele='" + ballsAcquiredLowChuteTele + '\'' +
                ", ballsAcquiredHighChuteTele='" + ballsAcquiredHighChuteTele + '\'' +
                ", preformedRotationControl='" + preformedRotationControl + '\'' +
                ", performedPositionControl='" + performedPositionControl + '\'' +
                ", hanging='" + hanging + '\'' +
                ", parked='" + parked + '\'' +
                ", leveling='" + leveling + '\'' +
                '}';
    }

    public static Map<String, ScoutingData> getData(){
        return data;
    }

    public void setScouterName(String scouterName) {
        this.scouterName = scouterName;
    }

    public void setMatchNumber(String matchNumber) {
        MatchNumber = matchNumber;
    }

    public void setTeamScouted(String teamScouted) {
        TeamScouted = teamScouted;
    }

    public void setStartingPos(String startingPos) {
        this.startingPos = startingPos;
    }

    public void setPowerCellAtStart(String powerCellAtStart) {
        PowerCellAtStart = powerCellAtStart;
    }

    public void setBallsScoredOnBottomAuto(String ballsScoredOnBottomAuto) {
        this.ballsScoredOnBottomAuto = ballsScoredOnBottomAuto;
    }

    public void setBallsScoredOnOuterAuto(String ballsScoredOnOuterAuto) {
        this.ballsScoredOnOuterAuto = ballsScoredOnOuterAuto;
    }

    public void setBallsScoredOnInnerAuto(String ballsScoredOnInnerAuto) {
        this.ballsScoredOnInnerAuto = ballsScoredOnInnerAuto;
    }

    public void setBallsAcquiredFloorAuto(String ballsAcquiredFloorAuto) {
        this.ballsAcquiredFloorAuto = ballsAcquiredFloorAuto;
    }

    public void setCrossesInitiatoinLine(String crossesInitiatoinLine) {
        this.crossesInitiatoinLine = crossesInitiatoinLine;
    }

    public void setBallsScoredOnBottomTele(String ballsScoredOnBottomTele) {
        this.ballsScoredOnBottomTele = ballsScoredOnBottomTele;
    }

    public void setBallsScoredOnOuterTele(String ballsScoredOnOuterTele) {
        this.ballsScoredOnOuterTele = ballsScoredOnOuterTele;
    }

    public void setBallsScoredOnInnerTele(String ballsScoredOnInnerTele) {
        this.ballsScoredOnInnerTele = ballsScoredOnInnerTele;
    }

    public void setBallsAcquiredFloorTele(String ballsAcquiredFloorTele) {
        this.ballsAcquiredFloorTele = ballsAcquiredFloorTele;
    }

    public void setBallsAcquiredLowChuteTele(String ballsAcquiredLowChuteTele) {
        this.ballsAcquiredLowChuteTele = ballsAcquiredLowChuteTele;
    }

    public void setBallsAcquiredHighChuteTele(String ballsAcquiredHighChuteTele) {
        this.ballsAcquiredHighChuteTele = ballsAcquiredHighChuteTele;
    }

    public void setPreformedRotationControl(String preformedRotationControl) {
        this.preformedRotationControl = preformedRotationControl;
    }

    public void setPerformedPositionControl(String performedPositionControl) {
        this.performedPositionControl = performedPositionControl;
    }

    public void setHanging(String hanging) {
        this.hanging = hanging;
    }

    public void setParked(String parked) {
        this.parked = parked;
    }

    public void setLeveling(String leveling) {
        this.leveling = leveling;
    }
    public String getScouterName() {
        return scouterName;
    }

    public String getMatchNumber() {
        return MatchNumber;
    }

    public String getTeamScouted() {
        return TeamScouted;
    }

    public String getStartingPos() {
        return startingPos;
    }

    public String getPowerCellAtStart() {return PowerCellAtStart; }

    public String getBallsScoredOnBottomAuto() {
        return ballsScoredOnBottomAuto;
    }

    public String getBallsScoredOnOuterAuto() {
        return ballsScoredOnOuterAuto;
    }

    public String getBallsScoredOnInnerAuto() {
        return ballsScoredOnInnerAuto;
    }

    public String getBallsAcquiredFloorAuto() {
        return ballsAcquiredFloorAuto;
    }

    public String isCrossesInitiatoinLine() {
        return crossesInitiatoinLine;
    }

    public String getBallsScoredOnBottomTele() {
        return ballsScoredOnBottomTele;
    }

    public String getBallsScoredOnOuterTele() {
        return ballsScoredOnOuterTele;
    }

    public String getBallsScoredOnInnerTele() {
        return ballsScoredOnInnerTele;
    }

    public String getBallsAcquiredFloorTele() {
        return ballsAcquiredFloorTele;
    }

    public String getBallsAcquiredLowChuteTele() {
        return ballsAcquiredLowChuteTele;
    }

    public String getBallsAcquiredHighChuteTele() {
        return ballsAcquiredHighChuteTele;
    }

    public String isPreformedRotationControl() {
        return preformedRotationControl;
    }

    public String isPerformedPositionControl() {
        return performedPositionControl;
    }

    public String isHanging() {
        return hanging;
    }

    public String isParked() {
        return parked;
    }

    public String getLeveling() {
        return leveling;
    }
}
