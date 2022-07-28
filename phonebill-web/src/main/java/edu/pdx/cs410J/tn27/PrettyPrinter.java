package edu.pdx.cs410J.tn27;

import com.google.common.annotations.VisibleForTesting;
import edu.pdx.cs410J.ParserException;

import java.io.PrintWriter;
import java.io.Writer;
import java.util.List;
import java.util.Map;

public class PrettyPrinter {
  private final Writer writer;

  public PrettyPrinter(Writer writer) {
    this.writer = writer;
  }
  public void dump(PhoneBill bill) {
    try (PrintWriter pw = new PrintWriter(this.writer)) {
      pw.println(bill.getCustomer());

      bill.SortCollectionPhoneCalls();

      List<PhoneCall> calls = (List<PhoneCall>) bill.getPhoneCalls();

      for (PhoneCall new_call : calls) {

        String line = new_call.getCaller() + " " + new_call.getCallee() + " "
                + new_call.getBeginTimeString() + " " + new_call.getEndTimeString();
        pw.println(line);
      }

      pw.flush();
    }
  }
}
/*
   @VisibleForTesting
   static String formatWordCount(int count )
   {
     return String.format( "Dictionary on server contains %d words", count );
   }

   @VisibleForTesting
   static String formatDictionaryEntry(String customer, PhoneBill bill )
   {
     return String.format("  %s -> %s", word, definition);
   }


   public PrettyPrinter(Writer writer) {
     this.writer = writer;
   }

   public void dump(Map<String, String> dictionary) {
     try (
       PrintWriter pw = new PrintWriter(this.writer)
     ) {

       pw.println(formatWordCount(dictionary.size()));

       for (Map.Entry<String, String> entry : dictionary.entrySet()) {
         String word = entry.getKey();
         String definition = entry.getValue();
         pw.println(formatDictionaryEntry(word, definition));
       }

       pw.flush();
     }

   }*/