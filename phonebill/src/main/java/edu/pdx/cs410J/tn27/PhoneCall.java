package edu.pdx.cs410J.tn27;

import edu.pdx.cs410J.AbstractPhoneCall;

public class PhoneCall extends AbstractPhoneCall {
  private final String caller_number;
  private final String callee_number;
  private final String begin_time;
  private final String end_time;
  private final String begin_date;
  private final String end_date;
  public PhoneCall(String caller_number, String callee_number, String begin_date,String begin_time, String end_date,String end_time){
    this.caller_number = caller_number;
    this.callee_number = callee_number;
    this.begin_time = begin_time;
    this.end_time = end_time;
    this.begin_date = begin_date;
    this.end_date = end_date;
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
    return this.begin_time;
  }

  @Override
  public String getEndTimeString() {
    return this.end_time;
  }

  public String getBeginDate(){
    return this.begin_date;
  }
  public String getEndDate(){
    return this.end_date;
  }




}





