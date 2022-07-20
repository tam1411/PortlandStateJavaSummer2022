package edu.pdx.cs410J.tn27;

import edu.pdx.cs410J.AbstractPhoneCall;
import edu.pdx.cs410J.ParserException;
//import edu.pdx.cs410J.ParserException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**  Create a new Phone call object
 *   include all the valid information on the command line.
 *
 */
public class PhoneCall extends AbstractPhoneCall implements Comparable<PhoneCall>{
  private final String caller_number;
  private final String callee_number;
  private final String begin_time;
  private final String end_time;
  private final String begin_date;
  private final String end_date;
  private final String begin_zone;
  private final String end_zone;

  public PhoneCall(String caller_number, String callee_number, String begin_date, String begin_time, String begin_zone, String end_date, String end_time, String end_zone) {
    this.caller_number = caller_number;
    this.callee_number = callee_number;
    this.begin_time = begin_time;
    this.end_time = end_time;
    this.begin_date = begin_date;
    this.end_date = end_date;
    this.begin_zone = begin_zone;
    this.end_zone = end_zone;

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

    Date begin = getBeginTime();
    DateFormat df = DateFormat.getDateTimeInstance(DateFormat.SHORT,DateFormat.SHORT);
    String result = df.format(begin);
    return result;
  }

  @Override
  public String getEndTimeString() {

    Date end = getEndTime();
    DateFormat df = DateFormat.getDateTimeInstance(DateFormat.SHORT,DateFormat.SHORT);
    return df.format(end);
  }

  @Override
  public Date getBeginTime() {
    StringBuilder sb = new StringBuilder();
    sb.append(this.begin_date + " " + this.begin_time + " " + this.begin_zone);
    Date date = null;
    SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy h:mm a");
    try {
      date = df.parse(sb.toString());
    } catch (ParseException e) {
      throw new RuntimeException(e);
    }
    return date;
  }

  @Override
  public Date getEndTime() {
    StringBuilder sb = new StringBuilder();
    sb.append(this.end_date + " " + this.end_time + " " + this.end_zone);
    Date date = null;
    SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy h:mm a");
    try {
      date = df.parse(sb.toString());
    } catch (ParseException e) {
      throw new RuntimeException(e);
    }
    return date;


  }


  @Override
  public int compareTo(PhoneCall new_call) {
    int i = this.getBeginTime().compareTo(new_call.getBeginTime());
    if (i > 0)
      return 1;
    else if (i == 0){
      if (this.caller_number.compareTo(new_call.getCaller())> 0)
        return 0;
    }
    return -1;

  }
  public long CalculateDurationMins() throws ParserException {
     long var = this.getEndTime().getTime() - this.getBeginTime().getTime();
     long result = var/(1000*60);
     if (result < 0) {
       throw new ParserException("End time is less than begin time");
     }
     else return (int)result;

  }
  //Return the normal date and time wihout DateFormat.
  public String NormalBegin(){
    return this.begin_date + " "+ this.begin_time +" "+ this.begin_zone;
  }
  public String NormalEnd(){
    return this.end_date + " " + this.end_time + " "+ this.end_zone;
  }


}





