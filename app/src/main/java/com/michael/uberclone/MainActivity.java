package com.michael.uberclone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

   public Button mButtonIAmClient;
   public Button mButtonIAmDrive;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mButtonIAmClient = findViewById(R.id.btnIAmClient);
        mButtonIAmDrive = findViewById(R.id.btnIAmDrive);

        mButtonIAmClient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotToSelectAuth();
            }
        });

        mButtonIAmDrive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotToSelectAuth();
            }
        });
    }

    private void gotToSelectAuth() {
        Intent intent = new Intent(MainActivity.this, SelectOptionAuthActivity.class);
        startActivity(intent);
    }
}