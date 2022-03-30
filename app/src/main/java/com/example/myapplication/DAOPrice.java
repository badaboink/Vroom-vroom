package com.example.myapplication;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DAOPrice {
    private DatabaseReference databaseReference;
    public DAOPrice()
    {
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        databaseReference = db.getReference(Price.class.getSimpleName());
    }
    public Task<Void> add(Price price)
    {
        return databaseReference.push().setValue(price);
    }
}
