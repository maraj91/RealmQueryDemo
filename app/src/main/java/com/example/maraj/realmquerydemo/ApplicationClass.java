package com.example.maraj.realmquerydemo;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by maraj on 24-08-2017.
 */

public class ApplicationClass extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        initRealm();
    }
    private void initRealm() {
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder(this)
                .name(Realm.DEFAULT_REALM_NAME)
                .schemaVersion(0)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);
    }
}
