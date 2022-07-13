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
    PhoneBill bill1 = parser.parse();

    assertThat(bill1.getCustomer(), equalTo("Test Phone Bill"));
    PhoneBill bill2 = parser.AddPhoneCallFromText(bill1);


    //Given a collection of 1 phone call
    PhoneBill bill3 = new PhoneBill("Test Phone Bill");
    PhoneCall new_call = new PhoneCall("123-456-7890", "123-456-7890","12/30", "12:30","12/31","12:31");
     bill3.addPhoneCall(new_call);
    assertThat(bill2.getPhoneCalls(),equalTo(bill3.getPhoneCalls()));
  }

  @Test
  void invalidTextFileThrowsParserException() {
    InputStream resource = getClass().getResourceAsStream("empty-phonebill.txt");
    assertThat(resource, notNullValue());

    TextParser parser = new TextParser(new InputStreamReader(resource));
    assertThrows(ParserException.class, parser::parse);
  }

 /* @Test
  void StringSplitToStringArray() throws IOException {
    InputStream resource = getClass().getResourceAsStream("valid-phonebill.txt");
    assertThat(resource, notNullValue());

    BufferedReader reader = new BufferedReader(new InputStreamReader(resource));
    TextParser parser = new TextParser(reader);
    String line = reader.readLine();;
    String [] array = parser.SplitStringLine(line);
  }*/
}

