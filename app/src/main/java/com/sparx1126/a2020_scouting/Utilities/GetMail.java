package com.sparx1126.a2020_scouting.Utilities;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Folder;
import javax.mail.NoSuchProviderException;
import javax.mail.internet.MimeMultipart;

//Class is extending AsyncTask because this class is going to perform a networking operation
public class GetMail extends AsyncTask<Void,Void,Void> {

    public interface Callback {
        void handleFinishDownload(Map<String, JSONObject> mails);
    }

    //Declaring Variables
    private static Context context;
    private static GetMail instance;
    private Callback cb;
    private Properties props;

    private static  Map<String, JSONObject> jsonMails = new HashMap<>();

    //Progressdialog to show while sending email
    private ProgressDialog progressDialog;

    public static synchronized GetMail getInstance(Context _context){
        if (instance == null){
            instance = new GetMail();
        }
        context = _context;
        return instance;
    }

    //Class Constructor
    private GetMail(){
        //Creating properties
        Properties props = new Properties();

        //Configuring properties for gmail
        //If you are not using gmail you may need to change the values
        props.put("mail.pop3.host", "pop.gmail.com");
        props.put("mail.pop3.port", "995");
        props.put("mail.pop3.starttls.enable", "true");
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
        cb.handleFinishDownload(jsonMails);
    }

    @Override
    protected Void doInBackground(Void... params) {
        try {
            Session emailSessionObj = Session.getDefaultInstance(props);
            //Create POP3 store object and connect with the server
            Store storeObj = emailSessionObj.getStore("pop3s");
            storeObj.connect("pop.gmail.com", "sparx1126scouts@gmail.com", "gosparx!");
            //Create folder object and open it in read-only mode
            Folder emailFolderObj = storeObj.getFolder("INBOX");
            //emailFolderObj.open(Folder.READ_ONLY);
            //Fetch messages from the folder and print in a loop
            Message[] messageobjs = emailFolderObj.getMessages();

            jsonMails.clear();
            for (int i = 0, n = messageobjs.length; i < n; i++) {
                Message indvidualmsg = messageobjs[i];
//                System.out.println("Printing individual messages");
//                System.out.println("No# " + (i + 1));
//                System.out.println("Email Subject: " + indvidualmsg.getSubject());
//                Log.e("Sender: " , indvidualmsg.getFrom()[0]);

                if(isValidJsonArray(getTextFromMessage(indvidualmsg))) {
                    Log.e("Content: ", getTextFromMessage(indvidualmsg));
                    jsonMails.put(indvidualmsg.getSubject(), new JSONObject(getTextFromMessage(indvidualmsg)));
                }
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

    private String getTextFromMessage(Message message) throws MessagingException, IOException {
        String result = "";
        if (message.isMimeType("text/plain")) {
            result = message.getContent().toString();
        } else if (message.isMimeType("multipart/*")) {
            MimeMultipart mimeMultipart = (MimeMultipart) message.getContent();
            result = getTextFromMimeMultipart(mimeMultipart);
        }
        return result;
    }

    private String getTextFromMimeMultipart(
            MimeMultipart mimeMultipart)  throws MessagingException, IOException{
        String result = "";
        int count = mimeMultipart.getCount();
        for (int i = 0; i < count; i++) {
            BodyPart bodyPart = mimeMultipart.getBodyPart(i);
            if (bodyPart.isMimeType("text/plain")) {
                result = result + "\n" + bodyPart.getContent();
                break; // without break same text appears twice in my tests
            } else if (bodyPart.getContent() instanceof MimeMultipart){
                result = result + getTextFromMimeMultipart((MimeMultipart)bodyPart.getContent());
            }
        }
        return result;
    }

}
