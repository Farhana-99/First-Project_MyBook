package com.example.mybook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

public class NoteEditor extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_editor);

        EditText editText = (EditText) findViewById(R.id.editDescription);
        Intent intent = getIntent();
        int noteId = intent.getIntExtra("noteId", -1);

        if (noteId != -1){
            editText.setText(Notes.notes.get(noteId));

        }
    }
}
