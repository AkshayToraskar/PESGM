package com.ak.pesgm.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by dg hdghfd on 12-04-2017.
 */

public class ImageData extends RealmObject {

    @PrimaryKey
    long id;
    byte[] byteArrayImage;
    String date;
    int path;
    String info;

    public byte[] getByteArrayImage() {
        return byteArrayImage;
    }

    public void setByteArrayImage(byte[] byteArrayImage) {
        this.byteArrayImage = byteArrayImage;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getPath() {
        return path;
    }

    public void setPath(int path) {
        this.path = path;
    }
}
