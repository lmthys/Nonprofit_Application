package com.example.lmandrew.nonprofit_application;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * On Click for the notes image asset. Will take user to the Note menu
     * @param v
     */
    public void OnClickNotes(View v){
        // Send the user to the note taking menu
        Intent intent = new Intent(getApplicationContext(), MainNoteMenu.class);
        startActivity(intent);

    }

    /**
     * On click
     * @param v
     */
    public void OnClickContact(View v){

    }
}
