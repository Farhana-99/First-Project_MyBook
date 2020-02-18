package com.example.mybook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button button;
    private Button button1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.library);
        button1 = (Button) findViewById(R.id.note);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openlibrary();
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opennotes();
            }
        });
    }

    public void openlibrary(){
        Intent intent = new Intent(this, Library.class);
        startActivity(intent);
    }

    public void opennotes(){
        Intent intent = new Intent(this, Notes.class);
        startActivity(intent);
    }
}



