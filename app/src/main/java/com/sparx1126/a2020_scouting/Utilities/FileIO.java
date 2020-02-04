package com.sparx1126.a2020_scouting.Utilities;
import android.content.Context;
import android.os.Environment;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

//Singleton design, one instance per instance of the app
public class FileIO {
    //Strings for file Path names
    private static final String FOLDER_NAME = "infiniteRecharge";
    //{key} is intended to be replaced when creating the file path
    private static final String EVENT_MATCHES_FILE_PATH = "eventMatches{key}.json";

    private File directory;
    private File externalDirectory;
    private static FileIO instance;

    //synchronized so 2 threads cannot run at the same time
    public static synchronized FileIO getInstance(){
        if(instance==null){
            instance = new FileIO();
        }
        return instance;
    }
    //Creates directory in harddrive for sto intialilize storage
    public void intializeStorage(Context _context){
        directory = new File(_context.getFilesDir(), FOLDER_NAME);
        if (!directory.exists()) {
            if (!directory.mkdir()) throw new AssertionError("Could not make directory!" + this);
        }
        if(Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())){
            externalDirectory = new File(Environment.getExternalStorageDirectory(), FOLDER_NAME);
            if(!externalDirectory.exists()) {
                externalDirectory.mkdir();
            }
        }
        Toast.makeText(_context, "Storage Path:" + directory.getPath(), Toast.LENGTH_LONG).show();

    }


    public void storeEventMatches(String _data, String eventKey){ storeData(EVENT_MATCHES_FILE_PATH, _data, eventKey); }
    public String fetchEventMatches(String eventKey){ return fetchData(EVENT_MATCHES_FILE_PATH,eventKey); }




    //Less specific method for storing general data when handed a json String
    private void storeData(String _path, String _data, String key){
        if (directory == null) throw new AssertionError("Not Initialize" + this);
        String filePath = directory.getPath() + "/" + (_path.replace("{key}",key));
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(filePath));
            writer.write(_data);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
    //key will be respective extra key of each of the fetch methods, for example for fetchEventKeys, the key is the evnt key
    private String fetchData(String _path, String key){
        if (directory == null) throw new AssertionError("Not Initialize" + this);

        String filePath = directory.getPath() + "/" + (_path.replace("{key}",key));
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

