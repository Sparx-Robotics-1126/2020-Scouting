package com.sparx1126.a2020_scouting;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.sparx1126.a2020_scouting.BlueAllianceData.BlueAllianceMatch;
import com.sparx1126.a2020_scouting.Utilities.BlueAllianceNetwork;
import com.sparx1126.a2020_scouting.Utilities.*;


import org.json.JSONArray;
import org.json.JSONException;

import java.util.Properties;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;

public class Welcome extends AppCompatActivity {

    private EditText emailInput, passwordInput, teamInput;
    private SharedPreferences loginData;
    private BlueAllianceNetwork network;
    public static boolean toggledBlue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        //get rid of this segment when finiseh with setting screen
        toggledBlue = false;
        changeUi();

        network = BlueAllianceNetwork.getInstance();
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

    private void downLoadEvents() {
        network.downloadEvents(new BlueAllianceNetwork.Callback() {
            @Override
            public void handleFinishDownload(final String _data) {
                // this needs to run on the ui thread because of ui components in it
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("JT " + _data);
                        if (isValidJsonArray(_data)) {
//                            dataCollection.setTeamEvents(_data);
//                            fileIO.storeTeamEvents(_data);
                            BlueAllianceMatch.parseDataToBAMMap(_data);

                        } else {
                            Toast.makeText(Welcome.this, "Internet returned BAD DATA for Events, try another wifi!", Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
    }

        public void changeUi () {
            if (toggledBlue) {
                LinearLayout li = (LinearLayout) findViewById(R.id.background);
                li.setBackgroundColor(getResources().getColor(R.color.BBackground));
                Button log = findViewById(R.id.login);
                log.setBackgroundColor(getResources().getColor(R.color.BButtonBackground));
                log.setTextColor(getResources().getColor(R.color.BText));
                TextView email = findViewById(R.id.email);
                email.setTextColor(getResources().getColor(R.color.BText));
                TextView password = findViewById(R.id.password);
                password.setTextColor(getResources().getColor(R.color.BText));
                TextView team = findViewById(R.id.team);
                team.setTextColor(getResources().getColor(R.color.BText));
                EditText emailInput = findViewById(R.id.emailInput);
                emailInput.setTextColor(getResources().getColor(R.color.BText));
                EditText passwordInput = findViewById(R.id.passwordInput);
                passwordInput.setTextColor(getResources().getColor(R.color.BText));
                EditText teamInput = findViewById(R.id.teamInput);
                teamInput.setTextColor(getResources().getColor(R.color.BText));
            } else {
                LinearLayout li = (LinearLayout) findViewById(R.id.background);
                li.setBackgroundColor(getResources().getColor(R.color.RBackground));
                Button log = findViewById(R.id.login);
                log.setBackgroundColor(getResources().getColor(R.color.RButtonBackground));
                log.setTextColor(getResources().getColor(R.color.RText));
                TextView email = findViewById(R.id.email);
                email.setTextColor(getResources().getColor(R.color.RText));
                TextView password = findViewById(R.id.password);
                password.setTextColor(getResources().getColor(R.color.RText));
                TextView team = findViewById(R.id.team);
                team.setTextColor(getResources().getColor(R.color.RText));
                EditText emailInput = findViewById(R.id.emailInput);
                emailInput.setTextColor(getResources().getColor(R.color.RText));
                EditText passwordInput = findViewById(R.id.passwordInput);
                passwordInput.setTextColor(getResources().getColor(R.color.RText));
                EditText teamInput = findViewById(R.id.teamInput);
                teamInput.setTextColor(getResources().getColor(R.color.RText));
            }
        }

        public void checkPreferences () {
            // Jaren: Please check all fields: email, password, team
            // I found that for email u can use -> test if not empty and
            // Patterns.EMAIL_ADDRESS.matcher(loginData.getString("email", ""))
            // For the team test -> not empty and Integer.parseInt(loginData.getString("team", ""))
            if (!loginData.getString("password", "").isEmpty()) {
                BlueAllianceNetwork.getInstance().seteamKey("frc" + loginData.getString("team", "team number not found"));
                switchScreen();
            }
            // Jaren: Please add an else with a Toast that says something is wrong...
            // Tell on a dialog exactly what went wrong
        }

        public void login() {
            String email, password, team;
            email = emailInput.getText().toString();
            password = passwordInput.getText().toString();
            team = teamInput.getText().toString();

            SharedPreferences.Editor editor;
            editor = loginData.edit();

            editor.putString(getString(R.string.EMAIL), email);
            editor.putString(getString(R.string.PASSWORD), password);
            editor.putString(getString(R.string.TEAM), team);
            editor.apply();

            Log.i("loginSave", "email: " + loginData.getString(getString(R.string.EMAIL), "email not found"));
            Log.i("loginSave", "password: " + loginData.getString(getString(R.string.PASSWORD), "password not found"));
            Log.i("loginSave", "team: " + loginData.getString(getString(R.string.TEAM), "team number not found"));

            // Jaren: I suggest that instead of switching you call checkPreferences
            switchScreen();
        }

        public void switchScreen () {
            Log.i("switchScreen", "unknown");
            startActivity(new Intent(Welcome.this, SettingsScreen.class));
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

    private boolean isValidJsonArray(String _data) {
        try {
            new JSONArray(_data);
        } catch (JSONException jsExcp) {
            return false;
        }
        return true;
    }
}
