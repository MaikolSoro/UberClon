package com.michael.uberclone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.widget.Toolbar;

public class SelectOptionAuthActivity extends AppCompatActivity {

    public Toolbar mToolbar;
    public Button  mButtonGoToLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_option_auth);

        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Seleccionar opción");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mButtonGoToLogin = findViewById(R.id.btnGoToLogin);
        mButtonGoToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToLogin();
            }
        });
    }

    private void goToLogin() {
        Intent intent =  new Intent(SelectOptionAuthActivity.this, LoginActivity.class);
        startActivity(intent);
    }
}