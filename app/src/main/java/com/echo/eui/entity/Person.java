package com.echo.eui.entity;

/**
 * Person实体类
 * Created by Echo-z on 2016/5/13/0013.
 */
public class Person {
    private int id;
    private String name;
    private String pass;

    public Person(int id, String name, String pass) {
        this.id = id;
        this.name = name;
        this.pass = pass;
    }

    public int getId() {
        return id;
    }

    public String getPass() {
        return pass;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "{id=" + id + ", name='" + name + "', pass='" + pass + "'}";
    }
}
