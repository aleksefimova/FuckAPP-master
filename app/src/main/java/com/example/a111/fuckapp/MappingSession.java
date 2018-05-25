package com.example.a111.fuckapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MappingSession {

    public String SessionTitle = Calendar.getInstance().getTime().toString(); //Default for the SessionTitle is the Date and Time
    public ArrayList<MappingPoint> Markers;
    private Context context; //needed to save it with shared preferences
    private SharedPreferences sharedPref;




    //Constructor to make an "empty" MappingSession with giving a context
    public MappingSession(Context context){
        this.context = context;
        this.Markers = new ArrayList<>();
    }

    //the Constructor to make a MappingSession Object from session Title, markers Context. When calling it from the Activity put "this" for the context value
    public MappingSession(String sessionTitle, ArrayList<MappingPoint> markers, Context context){
        this.SessionTitle = sessionTitle;
        this.Markers = markers;
        this.context = context;
        this.sharedPref = context.getSharedPreferences(this.SessionTitle, Context.MODE_PRIVATE);
    }

    //Constructor to make a Mapping Session Object from the shared preferences. When calling it from the Activity put "this" for the context value
    public MappingSession(String sessionTitle, Context context){
        this.context = context;
        this.SessionTitle = sessionTitle;
        this.sharedPref = context.getSharedPreferences(this.SessionTitle, Context.MODE_PRIVATE);
        String json = sharedPref.getString(SessionTitle, null);
        this.Markers = new Gson().fromJson(json, new ListParameterizedType<>(MappingPoint.class));
    }

    //Save the Session to shared preferences
    public void SaveSession(){
        String json = new Gson().toJson(Markers); //needs to be a string to save it, converts it to a JSON
        SharedPreferences.Editor editor = sharedPref.edit(); //Creates an Editor to write to shared preferences
        editor.putString(SessionTitle,json); //Pass the Session as a key value pair
        editor.apply(); //Saves the changes, commit() instead apply() would return if it was successful as boolean
    }

    //Exporting the session to external Storage
    public void ExportSession(){
        String LOG_TAG = "SessionExportError";
        String FileName = SessionTitle +".txt";
        String state = Environment.getExternalStorageState(); //get the state of the external storage
        if (!Environment.MEDIA_MOUNTED.equals(state)) { //check if it is not mounted
            Log.e(LOG_TAG, "No external storage mounted");
            int test = 5;
        }else {
            try {
                File root = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "Mapping Sessions"); //Get the Path of the Downloads-Dictionary and the folder  "Mapping Sessions"
                if (!root.exists()) { //if the folder does not exist...
                    boolean newfolder = root.mkdirs(); //... add it
                    int test2 = 6;
                }
                File file = new File(root, FileName); //get to our File
                boolean newfile = file.createNewFile(); //create it if it doesn't exist
                boolean test3 = false;
                FileWriter writer = new FileWriter(file); //Filewriter to write our new file
                writer.append(new Gson().toJson(Markers)); //Add a Json of your Markers to the File
                writer.flush(); //just send it!
                writer.close();
                Toast.makeText(context, "Saved", Toast.LENGTH_SHORT).show(); //shows a little "Saved" massage on the Display
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void addPoint(MappingPoint mappingPoint){
        this.Markers.add(mappingPoint);
    }

    // this class implements the Interface ParameterizedType so that Gson().fromJson can properly interprete the MarkerOptions class (and every other Class)
    private static class ListParameterizedType<X> implements ParameterizedType {

        private Class<?> wrapped;

        private ListParameterizedType(Class<X> wrapped){
            this.wrapped = wrapped;
        }

        @Override
        public Type[] getActualTypeArguments() {
            return new Type[] {wrapped};
        }

        @Override
        public Type getRawType(){
            return ArrayList.class;
        }

        @Override
        public Type getOwnerType(){
            return null;
        }
    }

}
