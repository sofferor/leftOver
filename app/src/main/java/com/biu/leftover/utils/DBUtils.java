package com.biu.leftover.utils;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DBUtils {
    private static final FirebaseDatabase database = FirebaseDatabase.getInstance();
    private static final DatabaseReference databaseReference = database.getReference();

    public static FirebaseDatabase getDB() {
        return database;
    }

    public static DatabaseReference getDBRef() {
        return databaseReference;
    }

    public static DatabaseReference getDBRefOf(String tablePath) {
        return databaseReference.child(tablePath);
    }

    public static Task<Void> addObject(String tableName, Object o) {
        if (o != null) {
            return databaseReference.child(tableName).push().setValue(o);
        }
        return null;
    }
}
