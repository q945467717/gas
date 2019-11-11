package com.wis.pojo.po;

import java.io.Serializable;

public class Roles implements Serializable {

    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
