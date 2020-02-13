package com.sparx1126.a2020_scouting;

import android.service.autofill.FieldClassification;
import android.util.Log;

public class ScoutingData {

    private String scouterName;
    private String MatchNumber;
    private String TeamScouted;
    private String startingPos;
    private String PowerCellAtStart;
    private String ballsScoredOnBottomAuto;
    private String ballsScoredOnOuterAuto;
    private String ballsScoredOnInnerAuto;
    private String ballsAcquiredFloorAuto;
    private boolean crossesInitiatoinLine;
    private String ballsScoredOnBottomTele;
    private String ballsScoredOnOuterTele;
    private String ballsScoredOnInnerTele;
    private String ballsAcquiredFloorTele;
    private String ballsAcquiredLowChuteTele;
    private String ballsAcquiredHighChuteTele;
    private boolean preformedRotationControl;
    private boolean performedPositionControl;
    private boolean hanging;
    private boolean parked;
    private String leveling;

    public ScoutingData(String scouterName,
             String MatchNumber,
             String TeamScouted,
             String startingPos,
             String PowerCellAtStart,
             String ballsScoredOnBottomAuto,
             String ballsScoredOnOuterAuto,
             String ballsScoredOnInnerAuto,
             String ballsAcquiredFloorAuto,
             boolean crossesInitiatoinLine,
             String ballsScoredOnBottomTele,
             String ballsScoredOnOuterTele,
             String ballsScoredOnInnerTele,
             String ballsAcquiredFloorTele,
             String ballsAcquiredLowChuteTele,
             String ballsAcquiredHighChuteTele,
             boolean preformedRotationControl,
             boolean performedPositionControl,
             boolean hanging,
             boolean parked,
             String leveling){

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

    public void setCrossesInitiatoinLine(boolean crossesInitiatoinLine) {
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

    public void setPreformedRotationControl(boolean preformedRotationControl) {
        this.preformedRotationControl = preformedRotationControl;
    }

    public void setPerformedPositionControl(boolean performedPositionControl) {
        this.performedPositionControl = performedPositionControl;
    }

    public void setHanging(boolean hanging) {
        this.hanging = hanging;
    }

    public void setParked(boolean parked) {
        this.parked = parked;
    }

    public void setLeveling(String leveling) {
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
        String ScouterName = "scouter_name:"+ scouterName;
        String MatchNum = "match_num:"+ MatchNumber;
        String teamNum = "team_num:" + TeamScouted;
        String startingPos = "starting_pos:"+ this.startingPos;
        String bottomPort =  "bottom_port:" + ballsScoredOnBottomAuto + ",";
        String OuterPort = "outer_port:" + ballsScoredOnOuterAuto + ",";
        String InnerPort = "inner_port:" + ballsScoredOnBottomAuto;
        String PowerCellsScored =  "Power_Cells_Scored:{" + bottomPort + OuterPort + InnerPort+ "},";
        String powerCellsStart = "power_cell_start:" + PowerCellAtStart;
        String PowerCellsAcqFloor =  "power_cells_acq_floor:" + ballsAcquiredFloorAuto;
        String crosses = "crosses_initiation_line:"+ crossesInitiatoinLine;
        String bottomPortTele = "bottom_port:"+ ballsScoredOnBottomTele + ",";
        String outerPortTele = "outer_port:" + ballsScoredOnOuterTele + ",";
        String innerPortTele = "inner_port:" + ballsScoredOnInnerTele;
        String PowerCellsScoredTele = "power_cells_scored:{" + bottomPortTele + outerPortTele + innerPortTele + "},";
        String floorTele = "acq_floor:" + ballsAcquiredFloorTele;
        String lowChuteTele = "low_chute:" + ballsAcquiredLowChuteTele;
        String highChuteTele = "high_chute" + ballsAcquiredHighChuteTele;
        String PowerCellsAcqTele = "power_cell_acq:{" + floorTele + lowChuteTele + highChuteTele + "},";
        String performedRot = "performed_rotation_control:" + preformedRotationControl + ",";
        String performtPos = "performed_position_control:" + performedPositionControl+ ",";
        String ControlPanel = "control_panel:{"+ performedRot + performtPos + "},";
        String hanging = "hanging:" + isHanging()+",";
        String parked = "parked:" + isParked()+",";
        String rendezoas = "rendzoas:{" + hanging + parked + "},";
        String leveling = "leveling:" + this.leveling;

        return ("{" + ScouterName +","+ MatchNum + ","+ teamNum + "," + "Auto:{"+ startingPos + ","+
                PowerCellsScored + powerCellsStart + "," + PowerCellsAcqFloor + "," + crosses + "},"
                + "Teleop:{" +  PowerCellsScoredTele + PowerCellsAcqTele + ControlPanel + "}," +
                "EndGame:{" + rendezoas + leveling+ "}," + "}");
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

    public String getPowerCellAtStart() {
        return PowerCellAtStart;
    }

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

    public boolean isCrossesInitiatoinLine() {
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

    public boolean isPreformedRotationControl() {
        return preformedRotationControl;
    }

    public boolean isPerformedPositionControl() {
        return performedPositionControl;
    }

    public boolean isHanging() {
        return hanging;
    }

    public boolean isParked() {
        return parked;
    }

    public String getLeveling() {
        return leveling;
    }
}
