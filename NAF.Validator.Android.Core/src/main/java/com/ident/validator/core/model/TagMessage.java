package com.ident.validator.core.model;

import org.litepal.crud.DataSupport;

/**
 * Created by pengllrn on 2018/7/30.
 */

public class TagMessage extends DataSupport{
    private int id;
    private String uid;
    private String statenum;

    public TagMessage() {
    }

    public TagMessage(String uid, String statenum) {
        this.uid = uid;
        this.statenum = statenum;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getStatenum() {
        return statenum;
    }

    public void setStatenum(String statenum) {
        this.statenum = statenum;
    }
}
