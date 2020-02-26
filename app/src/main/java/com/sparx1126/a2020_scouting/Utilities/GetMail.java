package com.sparx1126.a2020_scouting.Utilities;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Folder;
import javax.mail.NoSuchProviderException;

import okhttp3.Call;
import okhttp3.Response;

//Class is extending AsyncTask because this class is going to perform a networking operation
public class GetMail extends AsyncTask<Void,Void,Void> {

    public interface Callback {
        void handleFinishDownload(Map<String, JSONObject> mails);
    }

    //Declaring Variables
    private static Context context;
    private static GetMail instance;
    private Callback cb;

    private static  Map<String, JSONObject> JSONMails = new HashMap<>();

    //Progressdialog to show while sending email
    private ProgressDialog progressDialog;

    public static synchronized GetMail getInstance(Context _context){
        if (instance==null){
            instance=new GetMail();
        }
        context = _context;
        return instance;
    }

    //Class Constructor
    private GetMail(){
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        //Showing progress dialog while sending email
        progressDialog = ProgressDialog.show(context,"Sending message","Please wait...",false,false);
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        //Dismissing the progress dialog
        progressDialog.dismiss();
        //Showing a success message
        Toast.makeText(context,"Got Email",Toast.LENGTH_LONG).show();
    }

    @Override
    protected Void doInBackground(Void... params) {
        //Creating properties
        Properties props = new Properties();

        //Configuring properties for gmail
        //If you are not using gmail you may need to change the values
        props.put("mail.pop3.host", "pop.gmail.com");
        props.put("mail.pop3.port", "995");
        props.put("mail.pop3.starttls.enable", "true");

        try {
            Session emailSessionObj = Session.getDefaultInstance(props);
            //Create POP3 store object and connect with the server
            Store storeObj = emailSessionObj.getStore("pop3s");
            storeObj.connect("pop.gmail.com", "sparx1126scouts@gmail.com", "gosparx!");
            //Create folder object and open it in read-only mode
            Folder emailFolderObj = storeObj.getFolder("INBOX");
            emailFolderObj.open(Folder.READ_ONLY);
            //Fetch messages from the folder and print in a loop
            Message[] messageobjs = emailFolderObj.getMessages();

            JSONMails.clear();
            for (int i = 0, n = messageobjs.length; i < n; i++) {
                Message indvidualmsg = messageobjs[i];
                System.out.println("Printing individual messages");
                System.out.println("No# " + (i + 1));
                System.out.println("Email Subject: " + indvidualmsg.getSubject());
                System.out.println("Sender: " + indvidualmsg.getFrom()[0]);
                System.out.println("Content: " + indvidualmsg.getContent().toString());

                if(isValidJsonArray(indvidualmsg.getContent().toString())){
                    JSONMails.put(indvidualmsg.getSubject(), new JSONObject(indvidualmsg.getContent().toString()));
                }
                cb.handleFinishDownload(JSONMails);

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
        return null;
    }

    public void downloadMail(final GetMail.Callback callback){
        cb = callback;
        this.execute();
    }

    private boolean isValidJsonArray(String _data) {
        try{
            new JSONObject(_data);
        }catch(JSONException ex){
            try{
                new JSONArray(_data);
            } catch (JSONException ex1){
                return false;
            }
        }
        return true;
    }
}
