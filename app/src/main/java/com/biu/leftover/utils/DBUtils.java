package com.biu.leftover.utils;

import com.biu.leftover.model.DbObject;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;
import java.util.Map;

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

    public static Task<Void> addObject(String tableName, DbObject dbObject) {
        if (dbObject != null) {
            String dbId = databaseReference.child(tableName).push().getKey();
            dbObject.setDbId(dbId);
            return databaseReference.child(tableName).child(dbId).setValue(dbObject);
        }
        return null;
    }

    public static Task<Void> updateObject(String tableName, String dbId, DbObject o, Map<String, Object> params) {
        DatabaseReference objRef = databaseReference.child(tableName).child(dbId);
        if (o != null) {
            o.setUpdate_time(new Date());
            return objRef.setValue(o);
        } else if (params != null) {
            params.put("update_time", new Date());
            return objRef.updateChildren(params);
        }
        return null;
    }
}
