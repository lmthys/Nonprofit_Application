package com.example.lmandrew.nonprofit_application;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

public class MainNoteMenu extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_taking_menu);

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
}
