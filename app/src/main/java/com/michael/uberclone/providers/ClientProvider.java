package com.michael.uberclone.providers;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.michael.uberclone.models.Client;

import java.util.HashMap;
import java.util.Map;

public class ClientProvider {

    public DatabaseReference mDatabase;

    public ClientProvider() {
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child("Clients");
    }
    public Task<Void> create(Client client) {
        Map<String, Object> map = new HashMap<>();
        map.put("name", client.getName());
        map.put("email", client.getEmail());
        return  mDatabase.child(client.getId()).setValue(map);
    }
}
