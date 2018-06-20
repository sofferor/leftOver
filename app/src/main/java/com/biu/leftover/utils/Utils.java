package com.biu.leftover.utils;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public abstract class Utils {

    private static final List<Integer> imagesList = new ArrayList<>(13);

    public static String getUID(){
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference(Constants.EVENTS).push();
        String[] urlAraay = databaseReference.toString().split("/");
        return urlAraay[urlAraay.length - 1];
    }

    private static void initialImages() {
        imagesList.add(Constants.EMOJI1);
        imagesList.add(Constants.EMOJI2);
        imagesList.add(Constants.EMOJI3);
        imagesList.add(Constants.EMOJI4);
        imagesList.add(Constants.EMOJI5);
        imagesList.add(Constants.EMOJI6);
        imagesList.add(Constants.EMOJI7);
        imagesList.add(Constants.EMOJI8);
        imagesList.add(Constants.EMOJI9);
        imagesList.add(Constants.EMOJI10);
        imagesList.add(Constants.EMOJI11);
        imagesList.add(Constants.EMOJI12);
        imagesList.add(Constants.EMOJI13);
    }

    public static int getImage(int index) {
        if (imagesList.isEmpty()) {
            initialImages();
        }
        return imagesList.get(index);
    }
}
