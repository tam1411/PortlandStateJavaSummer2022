package edu.pdx.cs410J.tn27;

import edu.pdx.cs410J.ParserException;
import edu.pdx.cs410J.PhoneBillDumper;
import java.io.*;
import java.util.List;

/**
 * Pretty class to dump the nice format of phone bill to file
 */
public class PrettyPrint implements PhoneBillDumper<PhoneBill> {
    private final Writer writer;

    public PrettyPrint(Writer writer) {
        this.writer = writer;
    }
    //Dump to the file
    @Override
    public void dump(PhoneBill bill) {
        try (PrintWriter pw = new PrintWriter(this.writer)) {
            pw.println(bill.getCustomer());

            bill.SortCollectionPhoneCalls();

            List<PhoneCall> calls = (List<PhoneCall>) bill.getPhoneCalls();

            for (PhoneCall new_call : calls) {
                long i = 0;
                try {
                     i = new_call.CalculateDurationMins(); //Catch the negative value
                String line = new_call.getCaller() + " " + new_call.getCallee() + " "
                        + new_call.getBeginTimeString() + " " + new_call.getEndTimeString() + " " + i + "minute(s)";
                pw.println(line);
                } catch (ParserException e) {
                    System.err.println(e.getMessage());
                }
            }

            pw.flush();

        }// catch (Exception e) {
           //e.getMessage();
       // }
    }

}