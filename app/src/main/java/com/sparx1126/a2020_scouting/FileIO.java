package com.sparx1126.a2020_scouting;

import android.content.Context;
import android.os.Environment;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileIO {
    private static final String FOLDER_NAME = "deepspace";
    private static final String TEAM_EVENTS_FILE_NAME = "teamEvents.json";
    private static final String EVENT_MATCHES_FILE_NAME = "eventMatches.json";
    private static final String EVENT_TEAMS_FILE_NAME = "eventTeams.json";
    public static final String SCOUTING_DATA_HEADER = "scoutingData";
    public static final String BENCHMARK_DATA_HEADER = "benchmarkData";
    public static final String BUG_REPORT_HEADER = "bugReport";
    public static final String TEAM = "Team";
    public static final String MATCH = "Match";

    private static FileIO instance;
    private File dir;
    private File externalDir;

    // synchronized means that the method cannot be executed by two threads at the same time
    // hence protected so that it always returns the same instance
    public static synchronized FileIO getInstance() {
        if (instance == null) {
            instance = new FileIO();
        }
        return instance;
    }

    // To be called once by MainActivity
    public void InitializeStorage(Context _context) {
        dir = new File(_context.getFilesDir(), FOLDER_NAME);
        if (!dir.exists()) {
            if (!dir.mkdir()) throw new AssertionError("Could not make directory!" + this);
        }
        if(Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())){
            externalDir = new File(Environment.getExternalStorageDirectory(), FOLDER_NAME);
            if(!externalDir.exists()) {
                externalDir.mkdir();
            }
        }
        Toast.makeText(_context, "Storage Path:" + dir.getPath(), Toast.LENGTH_LONG).show();
    }

    public void storeTeamEvents(String _input) {
        storeData(TEAM_EVENTS_FILE_NAME, _input);
    }

    public String fetchTeamEvents() {
        return fetchData(TEAM_EVENTS_FILE_NAME);
    }

    public void storeEventMatches(String _input) {
        storeData(EVENT_MATCHES_FILE_NAME, _input);
    }

    public String fetchEventMatches() {
        return fetchData(EVENT_MATCHES_FILE_NAME);
    }

    public void storeEventTeams(String _input) {
        storeData(EVENT_TEAMS_FILE_NAME, _input);
    }

    public String fetchEventTeams() {
        return fetchData(EVENT_TEAMS_FILE_NAME);
    }

    public void storeScoutingData(String _input, String _teamNumber, String _match) {
        String fileName = SCOUTING_DATA_HEADER + "_" + TEAM + _teamNumber + "_" + MATCH + _match + ".json";
        storeData(fileName, _input);
    }
    public Map<Integer, Map<Integer, String>> fetchScoutingDatas() {
        if (dir == null) throw new AssertionError("Not Initialize" + this);

        Map<Integer, Map<Integer, String>> rtnObj = new HashMap<>();
        File[] listOfFiles = dir.listFiles();

        for (File listOfFile : listOfFiles) {
            String fileName = listOfFile.getName();
            if (listOfFile.isFile() && fileName.contains(SCOUTING_DATA_HEADER)) {
                String[] fileNameParts = fileName.split("[_.]");
                Integer team = Integer.parseInt(fileNameParts[1].replace(TEAM, ""));
                Integer match = Integer.parseInt(fileNameParts[2].replace(MATCH, ""));

                Map<Integer, String> matchMap;
                if (rtnObj.get(team) != null) {
                    matchMap = rtnObj.get(team);
                } else {
                    matchMap = new HashMap<>();
                }

                String data = fetchData(fileName);
                matchMap.put(match, data);
                rtnObj.put(team, matchMap);
            }
        }
        return rtnObj;
    }

    public void storeBenchmarkData(String _input, String _teamNumber) {
        String fileName = BENCHMARK_DATA_HEADER + "_" + TEAM + _teamNumber + ".txt";
        storeData(fileName, _input);
    }

    public Map<Integer, String> fetchBenchmarkDatas() {
        if (dir == null) throw new AssertionError("Not Initialize" + this);

        Map<Integer, String> rtnObj = new HashMap<>();
        File[] listOfFiles = dir.listFiles();

        for (File listOfFile : listOfFiles) {
            String fileName = listOfFile.getName();
            if (listOfFile.isFile() && fileName.contains(BENCHMARK_DATA_HEADER)) {
                String[] fileNameParts = fileName.split("[_.]");
                Integer team = Integer.parseInt(fileNameParts[1].replace(TEAM, ""));

                String data = fetchData(fileName);
                rtnObj.put(team, data);
            }
        }
        return rtnObj;
    }

    public void storeBugReport(String _input, String _studentName) {
        Long tsLong = System.currentTimeMillis()/1000;
        String fileName = BUG_REPORT_HEADER + "_" + _studentName + "_" + tsLong.toString() + ".txt";
        storeData(fileName, _input);
    }

    public Map<String, List<String>> fetchBugReports() {
        if (dir == null) throw new AssertionError("Not Initialize" + this);

        Map<String, List<String>> rtnObj = new HashMap<>();
        File[] listOfFiles = dir.listFiles();

        for (File listOfFile : listOfFiles) {
            String fileName = listOfFile.getName();
            if (listOfFile.isFile() && fileName.contains(BUG_REPORT_HEADER)) {
                String data = fetchData(fileName);

                String[] fileNameParts = fileName.split("[_]");
                String studentName = fileNameParts[1];

                if (rtnObj.containsKey(studentName)) {
                    rtnObj.get(studentName).add(data);
                }
                else {
                    //creates new map
                    List<String> bugReports = new ArrayList<>();
                    bugReports.add(data);

                    rtnObj.put(studentName, bugReports);
                }
            }
        }
        return rtnObj;
    }

    private void storeData(String _fileName, String _input) {
        if (dir == null) throw new AssertionError("Not Initialize" + this);

        String filePath = dir.getPath() + "/" + _fileName;
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(filePath));
            writer.write(_input);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void cloneToExternalStorage() {
        if (dir == null) throw new AssertionError("Not Initialized " + this);
        if(externalDir != null){
            File[] data = dir.listFiles();
            File newFile;
            FileInputStream is = null;
            FileOutputStream os = null;
            byte[] buffer = new byte[1024];
            int length;;
            for(File file: data){
                if(!file.isDirectory()){
                    try {
                        newFile = new File(externalDir.getAbsolutePath() + "/" + file.getName());
                        if(newFile.exists()){
                            newFile.delete();
                        }
                        is = new FileInputStream(file);
                        os = new FileOutputStream(newFile);
                        while((length = is.read(buffer)) > 0) {
                            os.write(buffer, 0, length);
                        }
                        is.close();
                        os.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }
        }

    }

    private String fetchData(String _fileName) {
        if (dir == null) throw new AssertionError("Not Initialize" + this);

        String filePath = dir.getPath() + "/" + _fileName;
        StringBuilder contentBuilder = new StringBuilder();
        File tmpFileHandle = new File(filePath);
        if(tmpFileHandle.exists()) {

            try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
                String sCurrentLine;
                while ((sCurrentLine = br.readLine()) != null) {
                    contentBuilder.append(sCurrentLine).append("\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return contentBuilder.toString();
    }
}
