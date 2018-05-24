package com.example.a111.fuckapp;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Array;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MappingSession {

    public String SessionTitle;
    public ArrayList Markers;
    private Context context; //needed to save it with shared preferences
    private SharedPreferences sharedPref;


    //the Constructor to make a MappingSession Object from session Title, markers Context. When calling it from the Activity put "this" for the context value
    public MappingSession(String sessionTitle, ArrayList markers, Context context){
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
       // this.Markers = new Gson().fromJson(json, new ListParameterizedType(MarkerOptions));
    }

    //Save the Session to shared preferences
    public void SaveSession(){
        String json = new Gson().toJson(Markers); //needs to be a string to save it, converts it to a JSON
        SharedPreferences.Editor editor = sharedPref.edit(); //Creates an Editor to write to shared preferences
        editor.putString(SessionTitle,json); //Pass the Session as a key value pair
        editor.apply(); //Saves the changes, commit() instead apply() would return if it was successful as boolean
    }

    static class ListParameterizedType<X> implements ParameterizedType { //this one is that the fromJSON works, but is not fine yet, just ignore

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
