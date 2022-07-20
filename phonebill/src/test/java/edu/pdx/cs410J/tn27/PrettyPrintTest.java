package edu.pdx.cs410J.tn27;
import edu.pdx.cs410J.ParserException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

public class PrettyPrintTest {
  /*  @Test
    void CanPrettyPrintDumpIntoFile() throws Exception {

        String customer = "Tam";
        PhoneBill bill = new PhoneBill(customer);
        PhoneCall call1 = new PhoneCall("789", "0", "01/12/2022", "8:46", "am", "01/12/2022", "8:50", "am");
        PhoneCall call2 = new PhoneCall("456", "0", "01/12/2022", "8:50", "am", "01/12/2022", "8:50", "pm");
        PhoneCall call3 = new PhoneCall("712", "0", "01/12/2022", "8:50", "am", "01/12/2022", "8:50", "am");
        bill.addPhoneCall(call3);
        bill.addPhoneCall(call2);
        bill.addPhoneCall(call1);
        bill.SortCollectionPhoneCalls();

        StringWriter sw = new StringWriter();
        PrettyPrint dumper = new PrettyPrint(sw);
        dumper.dump(bill);

        String text = sw.toString();
        String text_string = "Tam\r\n" + "712 0 1/12/22, 8:50 AM 1/12/22, 8:50 AM 0minute(s)\r\n" +
          "456 0 1/12/22, 8:50 AM 1/12/22, 8:50 PM 720minute(s)\r\n" +"789 0 1/12/22, 8:46 AM 1/12/22, 8:50 AM 4minute(s)\r\n"  ;
        assertThat(text,equalTo(text_string));



    }*/

}
