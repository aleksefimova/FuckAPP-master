package com.example.a111.fuckapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    String DicName = "SessionDictionary"; // the Dictionary File name, to find the other files of the Sessions. If this is not preimplementet here it also doesn't find the Dictionary
    private ListView lv; //The ListView is the list of Sessions that is shown in the activity
    public static final String EXTRA_MESSAGE = "com.example.a111.fuckapp.MESSAGE";
    Context context = this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SessionDictionary sessionDictionary = new SessionDictionary(DicName, this); //load the Dictionary form shardpref

        lv = (ListView) findViewById(R.id._listview);

        ArrayList<String> Stringlist = new ArrayList<>();
        Stringlist.add("New Session");
        Stringlist.addAll(sessionDictionary.DictionaryToText());

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, Stringlist);
        lv.setAdapter(arrayAdapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            // argument position gives the index of item which is clicked
            public void onItemClick(AdapterView<?> arg0, View v, int position, long arg3)
            {
                Intent intent = new Intent(context, MapsActivity.class);
                intent.putExtra(EXTRA_MESSAGE, (String)arg0.getItemAtPosition(position));
                startActivity(intent);
                finish();
            }
        });
    }

}
