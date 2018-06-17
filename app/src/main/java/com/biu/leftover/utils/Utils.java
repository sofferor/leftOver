package com.biu.leftover.utils;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Utils {

    public static String getUID(){
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference(Constants.EVENTS).push();
        String[] urlAraay = databaseReference.toString().split("/");
        return urlAraay[urlAraay.length - 1];
    }
}
