package com.example.lmandrew.nonprofit_application;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

public class MainNoteMenu extends Activity {

    private SimpleAdapter adapter;

    private ListView listView;

    private ArrayList<HashMap<String, String>> files;
    private ArrayList<String> keep;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_taking_menu);

        //get list view
        listView = findViewById(R.id.listOfNotes);

        prepareNotes();

        // Create from an tos
        String[] from = {"fileName"};
        int[] to = {R.id.file_name};
        adapter = new SimpleAdapter(this, files, R.layout.row, from, to);
        //set the list view adpater
        listView.setAdapter(adapter);

        //send a note that is clicked to the newnoteactivity to display
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), NewNoteActivity.class);
                intent.putExtra("File", keep.get(position));
                startActivity(intent);
            }
        });





    }

    @Override
    public void onResume() {
        super.onResume();
        prepareNotes();
        adapter.notifyDataSetChanged();


    }

    /**
     * On click method for adding a note
     * @param v
     */
    public void OnClickNewNote(View v){
        // Start new note
        Intent intent = new Intent(getApplicationContext(), NewNoteActivity.class);
        startActivity(intent);
    }


    /**
     * Method to find all previously saved text files adapted from https://www.androidauthority.com/lets-build-a-simple-text-editor-for-android-773774/
     */
    private void prepareNotes() {
        File directory;
        directory = getFilesDir();
        File[] dir_files = directory.listFiles();

        files = new ArrayList<>();
        keep = new ArrayList<>();
        for(int i = 1; i < dir_files.length; i++) {
            if(dir_files[i].getName().contains(".txt")){
                String file_name = dir_files[i].getName();
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put("fileName", file_name);
                files.add(hashMap);
                keep.add(file_name);
            }
        }
    }

}
