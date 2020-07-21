package com.michael.uberclone.activities.driver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.michael.uberclone.R;
import com.michael.uberclone.activities.MainActivity;
import com.michael.uberclone.activities.client.MapClientActivity;
import com.michael.uberclone.providers.AuthProvider;

public class MapDriverActivity extends AppCompatActivity {

    private Button mButtonLogout;
    public AuthProvider mAuthProvider;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_driver);

        mButtonLogout = findViewById(R.id.btnLogout);
        mAuthProvider = new AuthProvider();
        mButtonLogout .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuthProvider.logout();
                Intent intent = new Intent(MapDriverActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}