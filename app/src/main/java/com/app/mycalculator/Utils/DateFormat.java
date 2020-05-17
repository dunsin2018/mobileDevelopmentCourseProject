package com.app.mycalculator.Utils;

import java.util.Date;

public class DateFormat
{


    public  static  String getDate(Date date)
    {
        android.text.format.DateFormat df = new android.text.format.DateFormat();
        return String.valueOf(df.format("MMMM dd,yyyy hh:mm",date));

    }

}
