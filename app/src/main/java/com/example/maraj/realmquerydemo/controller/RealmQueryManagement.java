package com.example.maraj.realmquerydemo.controller;

import android.app.Activity;
import android.app.Application;
import android.support.v4.app.Fragment;

import com.example.maraj.realmquerydemo.model.MyData;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by maraj on 24-08-2017.
 */

public class RealmQueryManagement {
    private static RealmQueryManagement instance;
    private final Realm realm;

    public RealmQueryManagement(Application application) {
        realm = Realm.getDefaultInstance();
    }

    public static RealmQueryManagement with(Fragment fragment) {

        if (instance == null) {
            instance = new RealmQueryManagement(fragment.getActivity().getApplication());
        }
        return instance;
    }

    public static RealmQueryManagement with(Activity activity) {

        if (instance == null) {
            instance = new RealmQueryManagement(activity.getApplication());
        }
        return instance;
    }

    public static RealmQueryManagement with(Application application) {

        if (instance == null) {
            instance = new RealmQueryManagement(application);
        }
        return instance;
    }

    public static RealmQueryManagement getInstance() {

        return instance;
    }

    public Realm getRealm() {

        return realm;
    }

    //Refresh the realm istance
    public void refresh() {

        realm.refresh();
    }

    //clear all objects from MyData.class
    public void clearAll() {
        realm.beginTransaction();
        realm.clear(MyData.class);
        realm.commitTransaction();
    }

    //check if MyData.class is empty
    public boolean hasMyData() {
        return !realm.allObjects(MyData.class).isEmpty();
    }

    //find all objects in the Book.class
    public RealmResults<MyData> getAllRecords() {
        return realm.where(MyData.class).findAll();
    }

    //Insert Data In Realm DB
    public void insertData(MyData data) {
        // Persist your data easily
        realm.beginTransaction();
        realm.copyToRealm(data);
        realm.commitTransaction();
    }

    //Delete Record From table using number
    public void delete(int id) {
        RealmResults<MyData> datas = realm.where(MyData.class).findAll();
        MyData data = datas.where().equalTo("id", id).findFirst();
        if (data != null) {
            if (!realm.isInTransaction()) {
                realm.beginTransaction();
            }
            data.removeFromRealm();
            realm.commitTransaction();
        }
    }

    //Update Record From table using number
    public void update(MyData data) {
        if (data != null) {
            realm.beginTransaction();
            realm.copyToRealmOrUpdate(data);
            realm.commitTransaction();
        }
    }

    //Search Record From table using number
    public MyData search(long number) {
        return realm.where(MyData.class).equalTo("id", number).findFirst();
    }
}
