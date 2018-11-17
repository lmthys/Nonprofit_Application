package com.example.lmandrew.nonprofit_application;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class NewNoteActivity extends Activity {

    private EditText notes;

    private String filename;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_note);

        //Set the new note text
        notes = findViewById(R.id.taking_notes);

        //Check if there file name from intent
        Intent intent = getIntent();
        if(intent.getStringExtra("File") != null){
           String content = Open(intent.getStringExtra("File"));
           notes.setText(content);

        }
    }

    public void OnClickSave(View v){
        // Make a alert to get the user to give you a name for the file
        // Adapted from https://stackoverflow.com/questions/10903754/input-text-dialog-android
        AlertDialog.Builder builder = new AlertDialog.Builder(NewNoteActivity.this);
        builder.setTitle("Please enter filename for this note.");

        //Set up the input
        final EditText input = new EditText(this);
        // We need to specify the type that will be given
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);

        // set up the buttons
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                filename = input.getText().toString();
                save(filename);
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();


    }

    private void save(String filename){
        // Now save the actual text
        try {
            filename += ".txt";
            OutputStreamWriter out = new OutputStreamWriter(openFileOutput(filename, 0));
            out.write(notes.getText().toString());
            out.close();
            Toast.makeText(getApplicationContext(), "Note was saved!", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Exception" + e.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Method to check if a file exists, adapted from https://www.androidauthority.com/lets-build-a-simple-text-editor-for-android-773774/
     * @param fname the current filename
     */
    public boolean FileExists(String fname){
        File file = getBaseContext().getFileStreamPath(fname);
        return file.exists();
    }

    /**
     * Method to open a file that exists adapted from https://www.androidauthority.com/lets-build-a-simple-text-editor-for-android-773774/
     *
     */
    public String Open(String fileName) {
        String content = "";
        if(FileExists(fileName)) {
            try {
                InputStream in = openFileInput(fileName);
                if(in != null) {
                    InputStreamReader inputStreamReader = new InputStreamReader(in);
                    BufferedReader reader = new BufferedReader(inputStreamReader);
                    String str = "";
                    StringBuilder buf = new StringBuilder();

                    while ((str = reader.readLine()) != null) {
                        buf.append(str + "\n");
                    } in.close();
                    content = buf.toString();
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return content;
    }
}
