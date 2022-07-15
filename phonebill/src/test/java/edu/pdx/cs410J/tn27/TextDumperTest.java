package edu.pdx.cs410J.tn27;

import edu.pdx.cs410J.ParserException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.*;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

public class TextDumperTest {

  @Test
  void appointmentBookOwnerIsDumpedInTextFormat() {
    //Given a complete phone bill with customer name
    //and one phone call.
    String customer = "Test Phone Bill";
    PhoneBill bill = new PhoneBill(customer);
    PhoneCall new_call = new PhoneCall("123-456-7890", "123-456-7890", "12/30", "12:30", "12/31", "12:31");
    bill.addPhoneCall(new_call);


    StringWriter sw = new StringWriter();
    TextDumper dumper = new TextDumper(sw);
    dumper.dump(bill);  //Dump customer


    String text = sw.toString();
    assertThat(text, containsString(customer));
    //assertThat(text,containsString(bill.toString()));

  }

  //Test if the new added phone call was dumped to file correctly.
 /* @Test
  void PhoneCallDumpedInTextFormat(){
    String customer = "Test Phone Bill";
    PhoneBill bill = new PhoneBill(customer);
    PhoneCall new_call = new PhoneCall("123-456-7890", "123-456-7890","12/30", "12:30","12/31","12:31");
    bill.addPhoneCall(new_call);

    StringWriter sw = new StringWriter();
    TextDumper dumper = new TextDumper(sw);
    dumper.dumpPhoneCall(bill);

    String text = sw.toString();
    assertThat(text,containsString(new_call.toString()));
  }*/

  @Test
  void canParseTextWrittenByTextDumper(@TempDir File tempDir) throws IOException, ParserException {
    //Given a collection of phone calls in a phone bill.
    String customer = "Test Phone Bill";
    PhoneBill bill = new PhoneBill(customer);
    PhoneCall new_call = new PhoneCall("123-456-7890", "123-456-7890","12/30", "12:30","12/31","12:31");
    PhoneCall new_call1 = new PhoneCall("503-123-4561", "123-456-7890","12/30", "12:30","12/31","12:31");
    bill.addPhoneCall(new_call);
    bill.addPhoneCall(new_call1);


    File textFile = new File(tempDir, "apptbook.txt");
    TextDumper dumper = new TextDumper(new FileWriter(textFile));
    dumper.dump(bill);

    TextParser parser = new TextParser(new FileReader(textFile));
    PhoneBill read = parser.parse();
    List<PhoneCall> call = (List<PhoneCall>) read.getPhoneCalls();
    PhoneCall call1 = call.get(0);
    PhoneCall call2 = call.get(1);
    assertThat(read.getCustomer(), equalTo(customer));
    assertThat(call1.toString(),equalTo(new_call.toString()));
    assertThat(call2.toString(),equalTo(new_call1.toString()));
  }
}
