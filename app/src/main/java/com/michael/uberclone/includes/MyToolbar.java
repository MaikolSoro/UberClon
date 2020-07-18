package com.michael.uberclone.includes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.michael.uberclone.R;

public class MyToolbar {

    public static void show(AppCompatActivity activity, String title, boolean upButton) {

        // toolbar
      Toolbar toolbar = activity.findViewById(R.id.toolbar);
        activity.setSupportActionBar(toolbar);
        activity.getSupportActionBar().setTitle(title);
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);
    }
}
