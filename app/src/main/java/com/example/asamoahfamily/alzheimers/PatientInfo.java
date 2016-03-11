package com.example.asamoahfamily.alzheimers;

/**
 * Created by Asamoah Family on 3/9/2016.
 */
public class PatientInfo{

    private int _id;
    private String _butname;

    public PatientInfo() {
    }

    public PatientInfo(String butname){
        this._butname = butname;
    }

    public int get_id() {
        return _id;
    }

    public String get_butname() {
        return _butname;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public void set_butname(String _butname) {
        this._butname = _butname;
    }
}
