package com.michael.uberclone.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.michael.uberclone.R;
import com.michael.uberclone.activities.client.RegisterActivity;
import com.michael.uberclone.activities.driver.RegisterDriverActivity;
import com.michael.uberclone.includes.MyToolbar;

public class SelectOptionAuthActivity extends AppCompatActivity {

    public SharedPreferences mPref;
    public Button  mButtonGoToLogin;
    public Button  mButtonGoToRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_option_auth);
        MyToolbar.show(this, "Selecciona una opción", true);


        mButtonGoToLogin = findViewById(R.id.btnGoToLogin);
        mButtonGoToRegister = findViewById(R.id.btnGoToRegister);
        mButtonGoToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToLogin();
            }
        });
        mButtonGoToRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToRegister();
            }
        });
        mPref =  getApplicationContext().getSharedPreferences("typeUser", MODE_PRIVATE);
    }

    private void goToLogin() {
        Intent intent =  new Intent(SelectOptionAuthActivity.this, LoginActivity.class);
        startActivity(intent);
    }
    private void goToRegister() {
        String typeUser = mPref.getString("user", "");
        if(typeUser.equals("client")) {
            Intent intent =  new Intent(SelectOptionAuthActivity.this, RegisterActivity.class);
            startActivity(intent);
        } else {
            Intent intent =  new Intent(SelectOptionAuthActivity.this, RegisterDriverActivity.class);
            startActivity(intent);
        }
     }
}