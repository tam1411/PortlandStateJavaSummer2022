package edu.pdx.cs410J.tn27;

import edu.pdx.cs410J.PhoneBillDumper;
import java.io.*;
import java.util.List;

public class PrettyPrint implements PhoneBillDumper<PhoneBill> {
    private final Writer writer;

    public PrettyPrint(Writer writer) {
        this.writer = writer;
    }

    @Override
    public void dump(PhoneBill bill) {
        try (PrintWriter pw = new PrintWriter(this.writer)) {
            pw.println(bill.getCustomer());

            bill.SortCollectionPhoneCalls();

            List<PhoneCall> calls = (List<PhoneCall>) bill.getPhoneCalls();

            for (PhoneCall new_call : calls) {
                String line = new_call.getCaller() + " " + new_call.getCallee() + " "
                        + new_call.getBeginTimeString() + " " + new_call.getEndTimeString() + " " + new_call.CalculateDurationMins() + "minute(s)";
                pw.println(line);

            }

            pw.flush();

        }
    }

}