package com.sparx1126.a2020_scouting.Data;

import android.util.Log;

import com.sparx1126.a2020_scouting.Utilities.JsonData;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class OurRankingData extends JsonData {
    private static final String TAG = "Sparx: ";
    private static final String HEADER = "OurRankingData: ";

    private static final double autoStartingPositionClosestWeight = -1.0;
    private static final double autoStartingPositionMiddletWeight = 0;
    private static final double autoStartingPositionFarthestWeight = 1.0;
    private static final double autoPowerCellsInRobotWeight = 2.0;
    private static final double autoPowerCellsScoredBottomPortWeight = 2.0;
    private static final double autoPowerCellsScoredInnerPortWeight = 6.0;
    private static final double autoPowerCellsScoredOuterPortWeight = 4.0;
    private static final double autoPowerCellsAcquiredFloorWeight = 3.0;
    private static final double autoCrossedInitiationLineWeightFailed = 0;
    private static final double autoCrossedInitiationLineWeightCrossed = 5.0;
    private static final double telePowerCellsScoredBottomPortWeight = 1.0;
    private static final double telePowerCellsScoredInnerPortWeight = 3.0;
    private static final double telePowerCellsScoredOuterPortWeight = 2.0;
    private static final double telePowerCellsAcquiredFloorWeight = 3.0;
    private static final double telePowerCellsAcquiredHighChuteWeight = 1.0;
    private static final double telePowerCellsAcquiredLowChuteWeight = 1.0;
    private static final double telePerformedPositionControlWeightCompleted = 20.0;
    private static final double telePerformedPositionControlWeightNotTried = 0;
    private static final double telePerformedRotationControlWeightCompleted = 10.0;
    private static final double telePerformedRotationControlWeightNotTried = 0;
    private static final double endRendezvousWeightNoPark = 0;
    private static final double endRendezvousWeightParked = 5.0;
    private static final double endRendezvousWeightHanged = 25.0;
    private static final double endActiveLevelingWeightNoLeveling = 0;
    private static final double endActiveLevelingWeightTriedLeveling = 5.0;
    private static final double endActiveLevelingWeightLeveled = 15.0;
    private static final double otherPlayedExcellentDefenseWeightDid = 10.0;
    private static final double otherPlayedExcellentDefenseWeightNo = 0;
    private static final double otherCausedFoulWeightDid = -10.0;
    private static final double otherCausedFoulWeightNo = 0;
    private static final double otherBrokeOrGotDisabledWeightWas = -10.0;
    private static final double otherBrokeOrGotDisabledWeightNo = 0;

    private static Map<Integer, Double> autoStartingPositionAve = new HashMap<>();
    private static Map<Integer, Double> autoPowerCellsInRobotAve = new HashMap<>();
    private static Map<Integer, Double> autoPowerCellsScoredBottomPortAve = new HashMap<>();
    private static Map<Integer, Double> autoPowerCellsScoredInnerPortAve = new HashMap<>();
    private static Map<Integer, Double> autoPowerCellsScoredOuterPortAve = new HashMap<>();
    private static Map<Integer, Double> autoPowerCellsAcquiredFloorAve = new HashMap<>();
    private static Map<Integer, Double> autoCrossedInitiationLineAve = new HashMap<>();
    private static Map<Integer, Double> telePowerCellsScoredBottomPortAve = new HashMap<>();
    private static Map<Integer, Double> telePowerCellsScoredInnerPortAve = new HashMap<>();
    private static Map<Integer, Double> telePowerCellsScoredOuterPortAve = new HashMap<>();
    private static Map<Integer, Double> telePowerCellsAcquiredFloorAve = new HashMap<>();
    private static Map<Integer, Double> telePowerCellsAcquiredHighChuteAve = new HashMap<>();
    private static Map<Integer, Double> telePowerCellsAcquiredLowChuteAve = new HashMap<>();
    private static Map<Integer, Double> telePerformedPositionControlAve = new HashMap<>();
    private static Map<Integer, Double> telePerformedRotationControlAve = new HashMap<>();
    private static Map<Integer, Double> endRendezvousAve = new HashMap<>();
    private static Map<Integer, Double> endActiveLevelingAve = new HashMap<>();
    private static Map<Integer, Double> otherPlayedExcellentDefenseAve = new HashMap<>();
    private static Map<Integer, Double> otherCausedFoulAve = new HashMap<>();
    private static Map<Integer, Double> otherBrokeOrGotDisabledAve = new HashMap<>();

    public static Map<Integer, Double> getAutoStartingPositionAve() {
        return autoStartingPositionAve;
    }

    public static Map<Integer, Double> getAutoPowerCellsInRobotAve() {
        return autoPowerCellsInRobotAve;
    }

    public static Map<Integer, Double> getAutoPowerCellsScoredBottomPortAve() {
        return autoPowerCellsScoredBottomPortAve;
    }

    public static Map<Integer, Double> getAutoPowerCellsScoredInnerPortAve() {
        return autoPowerCellsScoredInnerPortAve;
    }

    public static Map<Integer, Double> getAutoPowerCellsScoredOuterPortAve() {
        return autoPowerCellsScoredOuterPortAve;
    }

    public static Map<Integer, Double> getAutoPowerCellsAcquiredFloorAve() {
        return autoPowerCellsAcquiredFloorAve;
    }

    public static Map<Integer, Double> getAutoCrossedInitiationLineAve() {
        return autoCrossedInitiationLineAve;
    }

    public static Map<Integer, Double> getTelePowerCellsScoredBottomPortAve() {
        return telePowerCellsScoredBottomPortAve;
    }

    public static Map<Integer, Double> getTelePowerCellsScoredInnerPortAve() {
        return telePowerCellsScoredInnerPortAve;
    }

    public static Map<Integer, Double> getTelePowerCellsScoredOuterPortAve() {
        return telePowerCellsScoredOuterPortAve;
    }

    public static Map<Integer, Double> getTelePowerCellsAcquiredFloorAve() {
        return telePowerCellsAcquiredFloorAve;
    }

    public static Map<Integer, Double> getTelePowerCellsAcquiredHighChuteAve() {
        return telePowerCellsAcquiredHighChuteAve;
    }

    public static Map<Integer, Double> getTelePowerCellsAcquiredLowChuteAve() {
        return telePowerCellsAcquiredLowChuteAve;
    }

    public static Map<Integer, Double> getTelePerformedPositionControlAve() {
        return telePerformedPositionControlAve;
    }

    public static Map<Integer, Double> getTelePerformedRotationControlAve() {
        return telePerformedRotationControlAve;
    }

    public static Map<Integer, Double> getEndRendezvousAve() {
        return endRendezvousAve;
    }

    public static Map<Integer, Double> getEndActiveLevelingAve() {
        return endActiveLevelingAve;
    }

    public static Map<Integer, Double> getOtherPlayedExcellentDefenseAve() {
        return otherPlayedExcellentDefenseAve;
    }

    public static Map<Integer, Double> getOtherCausedFoulAve() {
        return otherCausedFoulAve;
    }

    public static Map<Integer, Double> getOtherBrokeOrGotDisabledAve() {
        return otherBrokeOrGotDisabledAve;
    }

    private static Set<Integer> teams = new HashSet<>();
    private static Map<Integer, Double> totals = new HashMap<>();

    public static Map<Integer, Double> getTotals() {
        return totals;
    }

    public static void parseJsons(String _prefEvent, Map<String, JSONObject> _data) {
        teams.clear();
        totals.clear();
        autoStartingPositionAve.clear();
        autoPowerCellsInRobotAve.clear();
        autoPowerCellsScoredBottomPortAve.clear();
        autoPowerCellsScoredInnerPortAve.clear();
        autoPowerCellsScoredOuterPortAve.clear();
        autoPowerCellsAcquiredFloorAve.clear();
        autoCrossedInitiationLineAve.clear();
        telePowerCellsScoredBottomPortAve.clear();
        telePowerCellsScoredInnerPortAve.clear();
        telePowerCellsScoredOuterPortAve.clear();
        telePowerCellsAcquiredFloorAve.clear();
        telePowerCellsAcquiredHighChuteAve.clear();
        telePowerCellsAcquiredLowChuteAve.clear();
        telePerformedPositionControlAve.clear();
        telePerformedRotationControlAve.clear();
        endRendezvousAve.clear();
        endActiveLevelingAve.clear();
        otherPlayedExcellentDefenseAve.clear();
        otherCausedFoulAve.clear();
        otherBrokeOrGotDisabledAve.clear();
        Map<Integer, ArrayList<Double>> autoStartingPosition = new HashMap<>();
        Map<Integer, ArrayList<Double>> autoPowerCellsInRobot = new HashMap<>();
        Map<Integer, ArrayList<Double>> autoPowerCellsScoredBottomPort = new HashMap<>();
        Map<Integer, ArrayList<Double>> autoPowerCellsScoredInnerPort = new HashMap<>();
        Map<Integer, ArrayList<Double>> autoPowerCellsScoredOuterPort = new HashMap<>();
        Map<Integer, ArrayList<Double>> autoPowerCellsAcquiredFloor = new HashMap<>();
        Map<Integer, ArrayList<Double>> autoCrossedInitiationLine = new HashMap<>();
        Map<Integer, ArrayList<Double>> telePowerCellsScoredBottomPort = new HashMap<>();
        Map<Integer, ArrayList<Double>> telePowerCellsScoredInnerPort = new HashMap<>();
        Map<Integer, ArrayList<Double>> telePowerCellsScoredOuterPort = new HashMap<>();
        Map<Integer, ArrayList<Double>> telePowerCellsAcquiredFloor = new HashMap<>();
        Map<Integer, ArrayList<Double>> telePowerCellsAcquiredHighChute = new HashMap<>();
        Map<Integer, ArrayList<Double>> telePowerCellsAcquiredLowChute = new HashMap<>();
        Map<Integer, ArrayList<Double>> telePerformedPositionControl = new HashMap<>();
        Map<Integer, ArrayList<Double>> telePerformedRotationControl = new HashMap<>();
        Map<Integer, ArrayList<Double>> endRendezvous = new HashMap<>();
        Map<Integer, ArrayList<Double>> endActiveLeveling = new HashMap<>();
        Map<Integer, ArrayList<Double>> otherPlayedExcellentDefense = new HashMap<>();
        Map<Integer, ArrayList<Double>> otherCausedFoul = new HashMap<>();
        Map<Integer, ArrayList<Double>> otherBrokeOrGotDisabled = new HashMap<>();
        try {
            Log.d(TAG, HEADER + "parseJsons for number rank " + _data.size());
            for (Map.Entry<String, JSONObject> mail : _data.entrySet()) {
                // example 2020ndgf.frc3871.BlueAlliance.Position3.2.json
                String subject = mail.getKey();
                if (subject.contains(_prefEvent)) {
                    int indexStart = subject.indexOf("frc") + 3; // find string start of "frc", add string lenght.
                    String team = subject.substring(indexStart);
                    team = team.substring(0, team.indexOf("."));
                    parseJson(Integer.parseInt(team), autoStartingPosition, autoPowerCellsInRobot,
                            autoPowerCellsScoredBottomPort, autoPowerCellsScoredInnerPort,
                            autoPowerCellsScoredOuterPort, autoPowerCellsAcquiredFloor,
                            autoCrossedInitiationLine, telePowerCellsScoredBottomPort,
                            telePowerCellsScoredInnerPort, telePowerCellsScoredOuterPort,
                            telePowerCellsAcquiredFloor, telePowerCellsAcquiredHighChute,
                            telePowerCellsAcquiredLowChute, telePerformedPositionControl,
                            telePerformedRotationControl, endRendezvous, endActiveLeveling,
                            otherPlayedExcellentDefense, otherCausedFoul, otherBrokeOrGotDisabled,
                            mail.getValue());
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        calculate(autoStartingPosition.entrySet(), autoStartingPositionAve);
        calculate(autoPowerCellsInRobot.entrySet(), autoPowerCellsInRobotAve);
        calculate(autoPowerCellsScoredBottomPort.entrySet(), autoPowerCellsScoredBottomPortAve);
        calculate(autoPowerCellsScoredInnerPort.entrySet(), autoPowerCellsScoredInnerPortAve);
        calculate(autoPowerCellsScoredOuterPort.entrySet(), autoPowerCellsScoredOuterPortAve);
        calculate(autoPowerCellsAcquiredFloor.entrySet(), autoPowerCellsAcquiredFloorAve);
        calculate(autoCrossedInitiationLine.entrySet(), autoCrossedInitiationLineAve);
        calculate(telePowerCellsScoredBottomPort.entrySet(), telePowerCellsScoredBottomPortAve);
        calculate(telePowerCellsScoredInnerPort.entrySet(), telePowerCellsScoredInnerPortAve);
        calculate(telePowerCellsScoredOuterPort.entrySet(), telePowerCellsScoredOuterPortAve);
        calculate(telePowerCellsAcquiredFloor.entrySet(), telePowerCellsAcquiredFloorAve);
        calculate(telePowerCellsAcquiredHighChute.entrySet(), telePowerCellsAcquiredHighChuteAve);
        calculate(telePowerCellsAcquiredLowChute.entrySet(), telePowerCellsAcquiredLowChuteAve);
        calculate(telePerformedPositionControl.entrySet(), telePerformedPositionControlAve);
        calculate(telePerformedRotationControl.entrySet(), telePerformedRotationControlAve);
        calculate(endRendezvous.entrySet(), endRendezvousAve);
        calculate(endActiveLeveling.entrySet(), endActiveLevelingAve);
        calculate(otherPlayedExcellentDefense.entrySet(), otherPlayedExcellentDefenseAve);
        calculate(otherCausedFoul.entrySet(), otherCausedFoulAve);
        calculate(otherBrokeOrGotDisabled.entrySet(), otherBrokeOrGotDisabledAve);
        for (Integer team : teams) {
            totals.put(team, autoStartingPositionAve.get(team)
                    + autoPowerCellsInRobotAve.get(team)
                    + autoPowerCellsScoredBottomPortAve.get(team)
                    + autoPowerCellsScoredInnerPortAve.get(team)
                    + autoPowerCellsScoredOuterPortAve.get(team)
                    + autoPowerCellsAcquiredFloorAve.get(team)
                    + autoCrossedInitiationLineAve.get(team)
                    + telePowerCellsScoredBottomPortAve.get(team)
                    + telePowerCellsScoredInnerPortAve.get(team)
                    + telePowerCellsScoredOuterPortAve.get(team)
                    + telePowerCellsAcquiredFloorAve.get(team)
                    + telePowerCellsAcquiredHighChuteAve.get(team)
                    + telePowerCellsAcquiredLowChuteAve.get(team)
                    + telePerformedPositionControlAve.get(team)
                    + telePerformedRotationControlAve.get(team)
                    + endRendezvousAve.get(team)
                    + endActiveLevelingAve.get(team)
                    + otherPlayedExcellentDefenseAve.get(team)
                    + otherCausedFoulAve.get(team)
                    + otherBrokeOrGotDisabledAve.get(team));
        }
    }

    private static void calculate(Set<Map.Entry<Integer, ArrayList<Double>>> _entrySet,
                                  Map<Integer, Double> _container) {
        for (Map.Entry<Integer, ArrayList<Double>> entry : _entrySet) {
            double average = 0;
            for (Double ar : entry.getValue()) {
                average += ar;
            }
            average = average / entry.getValue().size();
            _container.put(entry.getKey(), average);
        }
    }

    private static void parseJson(Integer _team,
                                  Map<Integer, ArrayList<Double>> autoStartingPosition,
                                  Map<Integer, ArrayList<Double>> autoPowerCellsInRobot,
                                  Map<Integer, ArrayList<Double>> autoPowerCellsScoredBottomPort,
                                  Map<Integer, ArrayList<Double>> autoPowerCellsScoredInnerPort,
                                  Map<Integer, ArrayList<Double>> autoPowerCellsScoredOuterPort,
                                  Map<Integer, ArrayList<Double>> autoPowerCellsAcquiredFloor,
                                  Map<Integer, ArrayList<Double>> autoCrossedInitiationLine,
                                  Map<Integer, ArrayList<Double>> telePowerCellsScoredBottomPort,
                                  Map<Integer, ArrayList<Double>> telePowerCellsScoredInnerPort,
                                  Map<Integer, ArrayList<Double>> telePowerCellsScoredOuterPort,
                                  Map<Integer, ArrayList<Double>> telePowerCellsAcquiredFloor,
                                  Map<Integer, ArrayList<Double>> telePowerCellsAcquiredHighChute,
                                  Map<Integer, ArrayList<Double>> telePowerCellsAcquiredLowChute,
                                  Map<Integer, ArrayList<Double>> telePerformedPositionControl,
                                  Map<Integer, ArrayList<Double>> telePerformedRotationControl,
                                  Map<Integer, ArrayList<Double>> endRendezvous,
                                  Map<Integer, ArrayList<Double>> endActiveLeveling,
                                  Map<Integer, ArrayList<Double>> otherPlayedExcellentDefense,
                                  Map<Integer, ArrayList<Double>> otherCausedFoul,
                                  Map<Integer, ArrayList<Double>> otherBrokeOrGotDisabled,
                                  JSONObject _data) {
        try {
            JSONObject auto = getJsonObject(_data, ScoutingData.AUTO);
            String autoStartingPositionStr = getString(auto, ScoutingData.STARTING_POSITION);
            if (autoStartingPositionStr.contentEquals(ScoutingData.STARTING_POSITION_CLOSEST)) {
                addValue(autoStartingPosition, _team, autoStartingPositionClosestWeight);
            } else if (autoStartingPositionStr.contentEquals(ScoutingData.STARTING_POSITION_MIDDLE)) {
                addValue(autoStartingPosition, _team, autoStartingPositionMiddletWeight);
            } else if (autoStartingPositionStr.contentEquals(ScoutingData.STARTING_POSITION_FARTHEST)) {
                addValue(autoStartingPosition, _team, autoStartingPositionFarthestWeight);
            } else {
                assert false;
            }
            addValue(autoPowerCellsInRobot, _team,
                    getInt(auto, ScoutingData.POWER_CELLS_IN_ROBOT) * autoPowerCellsInRobotWeight);

            JSONObject autoScored = getJsonObject(auto, ScoutingData.POWER_CELLS_SCORED);
            addValue(autoPowerCellsScoredBottomPort, _team,
                    getInt(autoScored, ScoutingData.BOTTOM_PORT) * autoPowerCellsScoredBottomPortWeight);
            addValue(autoPowerCellsScoredInnerPort, _team,
                    getInt(autoScored, ScoutingData.INNER_PORT) * autoPowerCellsScoredInnerPortWeight);
            addValue(autoPowerCellsScoredOuterPort, _team,
                    getInt(autoScored, ScoutingData.OUTER_PORT) * autoPowerCellsScoredOuterPortWeight);
            JSONObject autoAcquired = getJsonObject(auto, ScoutingData.POWER_CELLS_ACQUIRED);
            addValue(autoPowerCellsAcquiredFloor, _team,
                    getInt(autoAcquired, ScoutingData.FLOOR) * autoPowerCellsAcquiredFloorWeight);
            if (getBoolean(auto, ScoutingData.CROSSED_INITIATION_LINE)) {
                addValue(autoCrossedInitiationLine, _team, autoCrossedInitiationLineWeightCrossed);
            } else {
                addValue(autoCrossedInitiationLine, _team, autoCrossedInitiationLineWeightFailed);
            }
            JSONObject tele = getJsonObject(_data, ScoutingData.TELE);
            JSONObject teleScored = getJsonObject(tele, ScoutingData.POWER_CELLS_SCORED);
            addValue(telePowerCellsScoredBottomPort, _team,
                    getInt(teleScored, ScoutingData.BOTTOM_PORT) * telePowerCellsScoredBottomPortWeight);
            addValue(telePowerCellsScoredInnerPort, _team,
                    getInt(teleScored, ScoutingData.INNER_PORT) * telePowerCellsScoredInnerPortWeight);
            addValue(telePowerCellsScoredOuterPort, _team,
                    getInt(teleScored, ScoutingData.OUTER_PORT) * telePowerCellsScoredOuterPortWeight);
            JSONObject teleAcquired = getJsonObject(tele, ScoutingData.POWER_CELLS_ACQUIRED);
            addValue(telePowerCellsAcquiredFloor, _team,
                    getInt(teleAcquired, ScoutingData.FLOOR) * telePowerCellsAcquiredFloorWeight);
            addValue(telePowerCellsAcquiredHighChute, _team,
                    getInt(teleAcquired, ScoutingData.FLOOR) * telePowerCellsAcquiredHighChuteWeight);
            addValue(telePowerCellsAcquiredLowChute, _team,
                    getInt(teleAcquired, ScoutingData.FLOOR) * telePowerCellsAcquiredLowChuteWeight);
            JSONObject teleControlPanel = getJsonObject(tele, ScoutingData.CONTROL_PANEL);
            if (getBoolean(teleControlPanel, ScoutingData.PERFORMED_POSITION_CONTROL)) {
                addValue(telePerformedPositionControl, _team, telePerformedPositionControlWeightCompleted);
            } else {
                addValue(telePerformedPositionControl, _team, telePerformedPositionControlWeightNotTried);
            }
            if (getBoolean(teleControlPanel, ScoutingData.PERFORMED_ROTATION_CONTROL)) {
                addValue(telePerformedRotationControl, _team, telePerformedRotationControlWeightCompleted);
            } else {
                addValue(telePerformedRotationControl, _team, telePerformedRotationControlWeightNotTried);
            }
            JSONObject end = getJsonObject(_data, ScoutingData.END);
            String endRendezvousStr = getString(end, ScoutingData.RENDEZVOUS);
            if (endRendezvousStr.contentEquals(ScoutingData.RENDEZVOUS_NO_PARK)) {
                addValue(endRendezvous, _team, endRendezvousWeightNoPark);
            } else if (endRendezvousStr.contentEquals(ScoutingData.RENDEZVOUS_PARKED)) {
                addValue(endRendezvous, _team, endRendezvousWeightParked);
            } else if (endRendezvousStr.contentEquals(ScoutingData.RENDEZVOUS_HANGED)) {
                addValue(endRendezvous, _team, endRendezvousWeightHanged);
            } else {
                assert false;
            }
            String endActiveLevelingStr = getString(end, ScoutingData.ACTIVE_LEVELING);
            if (endActiveLevelingStr.contentEquals(ScoutingData.ACTIVE_LEVELING_NO_LEVEL)) {
                addValue(endActiveLeveling, _team, endActiveLevelingWeightNoLeveling);
            } else if (endActiveLevelingStr.contentEquals(ScoutingData.ACTIVE_LEVELING_TRIED_TO_LEVEL)) {
                addValue(endActiveLeveling, _team, endActiveLevelingWeightTriedLeveling);
            } else if (endActiveLevelingStr.contentEquals(ScoutingData.ACTIVE_LEVELING_LEVELED)) {
                addValue(endActiveLeveling, _team, endActiveLevelingWeightLeveled);
            } else {
                assert false;
            }
            JSONObject other = getJsonObject(_data, ScoutingData.OTHER);
            if (getBoolean(other, ScoutingData.PLAYED_EXCELLENT_DEFENSE)) {
                addValue(otherPlayedExcellentDefense, _team, otherPlayedExcellentDefenseWeightDid);
            } else {
                addValue(otherPlayedExcellentDefense, _team, otherPlayedExcellentDefenseWeightNo);
            }
            if (getBoolean(other, ScoutingData.CAUSED_FOUL)) {
                addValue(otherCausedFoul, _team, otherCausedFoulWeightDid);
            } else {
                addValue(otherCausedFoul, _team, otherCausedFoulWeightNo);
            }
            if (getBoolean(other, ScoutingData.BROKE_OR_GOT_DISABLED)) {
                addValue(otherBrokeOrGotDisabled, _team, otherBrokeOrGotDisabledWeightWas);
            } else {
                addValue(otherBrokeOrGotDisabled, _team, otherBrokeOrGotDisabledWeightNo);
            }
            teams.add(_team);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void addValue(Map<Integer, ArrayList<Double>> _map, Integer _team, double _value) {
        if (_map.containsKey(_team)) {
            _map.get(_team).add(_value);
        } else {
            ArrayList<Double> values = new ArrayList<>();
            values.add(_value);
            _map.put(_team, values);
        }
    }
}
