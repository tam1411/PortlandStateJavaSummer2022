package edu.pdx.cs410J.tn27;

import edu.pdx.cs410J.ParserException;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TextParserTest {

  @Test
  void validTextFileCanBeParsed() throws ParserException {
    InputStream resource = getClass().getResourceAsStream("valid-phonebill.txt");
    assertThat(resource, notNullValue());


    TextParser parser = new TextParser(new InputStreamReader(resource));
    //Parse bill 1 from the text file.
    PhoneBill bill1 = parser.parse();
    List<PhoneCall> call = (List<PhoneCall>) bill1.getPhoneCalls();
    PhoneCall call1 = call.get(0);
    assertThat(bill1.getCustomer(), equalTo("Test Phone Bill"));


    //Given a collection of 1 phone call
    PhoneBill bill3 = new PhoneBill("Test Phone Bill");
    PhoneCall new_call = new PhoneCall("123-456-7890", "123-456-7890","12/30/2011", "12:30","AM","12/31/2012","12:31","AM");

    bill3.addPhoneCall(new_call);
    assertThat(call1.toString(),equalTo(new_call.toString()));
  }

  @Test
  void invalidTextFileThrowsParserException() {
    InputStream resource = getClass().getResourceAsStream("empty-phonebill.txt");
    assertThat(resource, notNullValue());

    TextParser parser = new TextParser(new InputStreamReader(resource));
    assertThrows(ParserException.class, parser::parse);
  }


}

