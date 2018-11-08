package com.example.lmandrew.nonprofit_application;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.IOException;
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
            OutputStreamWriter out = new OutputStreamWriter(openFileOutput(filename, 0));
            out.write(notes.getText().toString());
            out.close();
            Toast.makeText(getApplicationContext(), "Note was saved!", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Exception" + e.toString(), Toast.LENGTH_SHORT).show();
        }
    }
}
