package edu.pdx.cs410J.tn27;

import edu.pdx.cs410J.ParserException;
import edu.pdx.cs410J.PhoneBillParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

public class TextParser implements PhoneBillParser<PhoneBill> {
  private final Reader reader;

  public TextParser(Reader reader) {
    this.reader = reader;
  }

  @Override
  public PhoneBill parse() throws ParserException {
    try (
      BufferedReader br = new BufferedReader(this.reader)
    ) {

      String customer = br.readLine();

      if (customer == null) {
        throw new ParserException("Missing customer");
      }

      return new PhoneBill(customer);

    } catch (IOException e) {
      throw new ParserException("While parsing phone bill text", e);
    }
  }

  //Function to read in the text file line by line and add the phone calls to phone bill
  public PhoneBill AddPhoneCallFromText(PhoneBill bill) throws IOException {
    BufferedReader br = new BufferedReader(this.reader);
    br.readLine(); //Read the first line
    String line = br.readLine(); //Read the second line
    while (line != null){
      //Function to split the String line into array
      String [] array = SplitStringLine(line);
      //Add each phone call to phone bill

        PhoneCall call = new PhoneCall(array[0],array[1],array[2],array[3],array[4],array[5]);
        bill.addPhoneCall(call);

      //Read the next line until it's the end of file
      line = br.readLine();
    }

  return bill;

  }
//Function to split the string array by space delimiter and
//return a string array
  public String[] SplitStringLine(String line){
    String [] array = null;
    array = line.split(" ");
    return array;
  }
}
