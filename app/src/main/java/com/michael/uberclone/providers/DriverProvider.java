package com.michael.uberclone.providers;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.michael.uberclone.models.Driver;

public class DriverProvider {

    public DatabaseReference mDatabase;

    public DriverProvider() {
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child("Drivers");
    }
    public Task<Void> create(Driver driver) {
        return  mDatabase.child(driver.getId()).setValue(driver);
    }
}
