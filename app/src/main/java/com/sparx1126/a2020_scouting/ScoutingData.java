package com.sparx1126.a2020_scouting;

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

    public ScoutingData( String scouterName,
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
}
