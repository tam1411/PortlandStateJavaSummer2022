package edu.pdx.cs410J.tn27;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import edu.pdx.cs410J.AbstractPhoneCall;

public class PhoneCall extends AbstractPhoneCall {
    private final String caller_number;
    private final String callee_number;
    private final String begin_time;
    private final String end_time;
    private final String begin_date;
    private final String end_date;
    //private final String begin_zone;
    //private final String end_zone;

    public PhoneCall(String caller_number, String callee_number, String begin_date, String begin_time, String end_date, String end_time) {
        this.caller_number = caller_number;
        this.callee_number = callee_number;
        this.begin_time = begin_time;
        this.end_time = end_time;
        this.begin_date = begin_date;
        this.end_date = end_date;
        //this.begin_zone = begin_zone;
        //this.end_zone = end_zone;

    }

    @Override
    public String getCaller() {
        return this.caller_number;
    }

    @Override
    public String getCallee() {
        return this.callee_number;
    }

    @Override
    public String getBeginTimeString() {

        /*Date begin = getBeginTime();
        DateFormat df = DateFormat.getDateTimeInstance(DateFormat.SHORT,DateFormat.SHORT);
        return df.format(begin);*/
        return begin_date;
    }

    @Override
    public String getEndTimeString() {

       /* Date end = getEndTime();
        DateFormat df = DateFormat.getDateTimeInstance(DateFormat.SHORT,DateFormat.SHORT);
        return df.format(end);*/
        return end_date;
    }

  /*  @Override
    public String getBeginTime() {
        /*StringBuilder sb = new StringBuilder();
        //sb.append(this.begin_date).append(" ").append(this.begin_time).append(" ").append(this.begin_zone);
        sb.append(this.begin_date).append(" ").append(this.begin_time).append(" ");
        Date date = null;
        SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy h:mm a");
        try {
            date = df.parse(sb.toString());
        } catch (ParseException e) {
            throw new RuntimeException("This is the Error" + e);
        }
        return date;
        return begin_time;
    }

    @Override
    public String getEndTime() {
        /*StringBuilder sb = new StringBuilder();
        //sb.append(this.end_date).append(" ").append(this.end_time).append(" ").append(this.end_zone);
        sb.append(this.end_date).append(" ").append(this.end_time).append(" ");
        Date date = null;
        SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy h:mm a");
        try {
            date = df.parse(sb.toString());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return date;
        return end_time;

    }*/
    //Return the normal date and time without DateFormat.
    public String NormalBegin(){
        //return this.begin_date + " "+ this.begin_time +" "+ this.begin_zone;
        return this.begin_date + " "+ this.begin_time;
    }
    public String NormalEnd(){
        //return this.end_date + " " + this.end_time + " "+ this.end_zone;
        return this.end_date + " " + this.end_time;
    }

}
