package com.app.mycalculator.Model;

import java.sql.Timestamp;
import java.util.Date;

public class FireBaseRecordModel
{



    public   FireBaseRecordModel()
    {

        setTimeStamp(new com.google.firebase.Timestamp(new Date()));
    }
    public String getEquation() {
        return equation;
    }

    public void setEquation(String equation) {
        this.equation = equation;
    }

    private  String equation;



    public com.google.firebase.Timestamp getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(com.google.firebase.Timestamp timeStamp) {
        this.timeStamp = timeStamp;
    }

    private  com.google.firebase.Timestamp timeStamp;

}
