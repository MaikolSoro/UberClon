package com.michael.uberclone.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.michael.uberclone.R;
import com.michael.uberclone.activities.client.MapClientActivity;
import com.michael.uberclone.activities.driver.MapDriverActivity;

public class MainActivity extends AppCompatActivity {

   public Button mButtonIAmClient;
   public Button mButtonIAmDrive;

   public SharedPreferences mPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // instancia
        mPref =  getApplicationContext().getSharedPreferences("typeUser", MODE_PRIVATE);
        final SharedPreferences.Editor editor = mPref.edit();

        mButtonIAmClient = findViewById(R.id.btnIAmClient);
        mButtonIAmDrive = findViewById(R.id.btnIAmDrive);

        mButtonIAmClient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.putString("user", "client");
                editor.apply();
                gotToSelectAuth();
            }
        });

        mButtonIAmDrive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.putString("user", "driver");
                editor.apply();
                gotToSelectAuth();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            String user = mPref.getString("user", "");
            if(user.equals("client")) {

                Intent intent = new Intent(MainActivity.this, MapClientActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            } else {
                Intent intent = new Intent(MainActivity.this, MapDriverActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        }
    }

    private void gotToSelectAuth() {
        Intent intent = new Intent(MainActivity.this, SelectOptionAuthActivity.class);
        startActivity(intent);
    }
}