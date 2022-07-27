package edu.pdx.cs410J.tn27;

import java.io.PrintWriter;
import java.io.Writer;
import java.util.List;
import java.util.Map;

public class TextDumper {
  private final Writer writer;

  public TextDumper(Writer writer) {
    this.writer = writer;
  }

  /*public void dump(Map<String, String> dictionary) {
    try (
      PrintWriter pw = new PrintWriter(this.writer)
    ){
      for (Map.Entry<String, String> entry : dictionary.entrySet()) {
        pw.println(entry.getKey() + " : " + entry.getValue());
      }

      pw.flush();
    }
  }*/


  public void dump(PhoneBill bill) {
    try (
            PrintWriter pw = new PrintWriter(this.writer)
    ) {
      pw.println(bill.getCustomer());
      //pw.println(bill.getPhoneCalls());
      //pw.println("");//Add a new line;
      List<PhoneCall> call = (List<PhoneCall>) bill.getPhoneCalls();
      for (int i = 0; i < call.size(); ++i) {
        PhoneCall new_call = call.get(i);
        String line = new_call.getCaller() + " " +new_call.getCallee()+" "+ new_call.NormalBegin()+ " "+ new_call.NormalEnd();
        pw.println(line);

        pw.flush();
      }
    }

  }
}
