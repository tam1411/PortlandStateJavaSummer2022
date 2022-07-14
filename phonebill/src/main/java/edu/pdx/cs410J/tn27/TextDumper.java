package edu.pdx.cs410J.tn27;

import edu.pdx.cs410J.AppointmentBookDumper;
import edu.pdx.cs410J.PhoneBillDumper;

import java.io.*;
import java.util.List;

public class TextDumper implements PhoneBillDumper<PhoneBill> {
  private final Writer writer;

  public TextDumper(Writer writer) {
    this.writer = writer;
  }

  @Override
  public void dump(PhoneBill bill) {
    try (
      PrintWriter pw = new PrintWriter(this.writer)
    ) {
      pw.println(bill.getCustomer());
      //pw.println(bill.getPhoneCalls());
      //pw.println("");//Add a new line;
      List<PhoneCall> call = (List<PhoneCall>) bill.getPhoneCalls();
      for (int i = 0; i < call.size(); ++i) {
        PhoneCall new_call = call.get(0);
        String line = new_call.getCaller() + " " +new_call.getCallee()+ " "+ new_call.getBeginDate()+ " "+ new_call.getBeginTimeString()+ " "+ new_call.getEndDate()+" "+ new_call.getEndTimeString();
        pw.println(line);

      pw.flush();
    }
  }
  //Function to write new added phone call to phone bill to the text file.
  /*public void dumpPhoneCall(PhoneBill bill){
    try (
            PrintWriter pw = new PrintWriter(this.writer)
    ) {
      //w.println(bill.getPhoneCalls());
      List<PhoneCall> call = (List<PhoneCall>) bill.getPhoneCalls();
      for (PhoneCall new_call : call) {
        pw.println(new_call);

      }



      pw.flush();
    }*/
  }



}
