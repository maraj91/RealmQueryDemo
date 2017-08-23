package com.example.maraj.realmquerydemo.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by maraj on 24-08-2017.
 */

public class MyData extends RealmObject {
    @PrimaryKey
    private int id;
    private long number;
    private String email;
    private String address;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
