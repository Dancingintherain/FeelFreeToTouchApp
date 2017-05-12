package de.htw_berlin.katharinapapke.feelfreetotouchapp.models;

import java.io.Serializable;

/**
 * Created by Berlina on 07.05.17.
 */

public class Comments implements Serializable{
    private int id;
    private String comment;
    private String age;

    public Comments (String comment, String age){
        this.comment = comment;
        this.age = age;
    }

    public Comments(int id, String comment, String age) {
        this.id = id;
        this.comment = comment;
        this.age = age;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Comments{" +
                "id=" + id +
                ", comment='" + comment + '\'' +
                ", age=" + age +
                '}';
    }
}
