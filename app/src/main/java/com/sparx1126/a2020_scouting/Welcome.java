package com.sparx1126.a2020_scouting;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.sparx1126.a2020_scouting.BlueAllianceData.BlueAllianceMatch;
import com.sparx1126.a2020_scouting.Utilities.BlueAllianceNetwork;


import java.util.Properties;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;

public class Welcome extends AppCompatActivity {

    private EditText emailInput,  passwordInput, teamInput;
    private SharedPreferences loginData;
    private BlueAllianceNetwork network;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        network=BlueAllianceNetwork.getInstance();
        //BAM POPULATE
        network.downloadEventMatches("2019ohcl", new BlueAllianceNetwork.Callback() {
            @Override
            public void handleFinishDownload(final String _data) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //Log.i("TAG", _data);
                        //System.out.println(_data);
                        BlueAllianceMatch.parseDataToBAMMap(_data);

                    }
                });

            }
        });


        loginData = getSharedPreferences("Sparx_prefs", 0);
        checkPreferences();

        emailInput = findViewById(R.id.emailInput);
        passwordInput = findViewById(R.id.passwordInput);
        teamInput = findViewById(R.id.teamInput);

        Button login = findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });

        //test code
        //Set mail properties and configure accordingly
        String hostval = "pop.gmail.com";
        String mailStrProt = "pop3";
        String uname = "sparxsscouts1126@gmail.com";
        String pwd = "sparx";
        // Calling checkMail method to check received emails
        checkMail(hostval, mailStrProt, uname, pwd);

    }

    public void checkPreferences(){
        if(!loginData.getString("password", "").isEmpty())
            switchScreen();
    }

    public void login() {
        String email, password, team;
        email = emailInput.getText().toString();
        password = passwordInput.getText().toString();
        team = teamInput.getText().toString();

        SharedPreferences.Editor editor;
        editor = loginData.edit();
        editor.putString("email", email);
        editor.putString("password", password);
        editor.putString("team", team);
        editor.apply();

        Log.i("loginSave", "email: " + loginData.getString("email", "email not found"));
        Log.i("loginSave", "password: " + loginData.getString("password", "password not found"));
        Log.i("loginSave", "team: " + loginData.getString("team", "team number not found"));

        switchScreen();


    }

    public void switchScreen(){
        Log.e("switchScreen", "unknown");
        startActivity(new Intent(Welcome.this, MatchViewer.class));
    }

    public static void checkMail(String hostval, String mailStrProt, String uname,String pwd)
    {
        try {
            //Set property values
            Properties propvals = new Properties();
            propvals.put("mail.pop3.host", hostval);
            propvals.put("mail.pop3.port", "995");
            propvals.put("mail.pop3.starttls.enable", "true");
            Session emailSessionObj = Session.getDefaultInstance(propvals);
            //Create POP3 store object and connect with the server
            Store storeObj = emailSessionObj.getStore("pop3s");
            storeObj.connect(hostval, uname, pwd);
            //Create folder object and open it in read-only mode
            Folder emailFolderObj = storeObj.getFolder("INBOX");
            emailFolderObj.open(Folder.READ_ONLY);
            //Fetch messages from the folder and print in a loop
            Message[] messageobjs = emailFolderObj.getMessages();

            for (int i = 0, n = messageobjs.length; i < n; i++) {
                Message indvidualmsg = messageobjs[i];
                System.out.println("Printing individual messages");
                System.out.println("No# " + (i + 1));
                System.out.println("Email Subject: " + indvidualmsg.getSubject());
                System.out.println("Sender: " + indvidualmsg.getFrom()[0]);
                System.out.println("Content: " + indvidualmsg.getContent().toString());

            }
            //Now close all the objects
            emailFolderObj.close(false);
            storeObj.close();
        } catch (NoSuchProviderException exp) {
            exp.printStackTrace();
        } catch (MessagingException exp) {
            exp.printStackTrace();
        } catch (Exception exp) {
            exp.printStackTrace();
        }
    }


}
