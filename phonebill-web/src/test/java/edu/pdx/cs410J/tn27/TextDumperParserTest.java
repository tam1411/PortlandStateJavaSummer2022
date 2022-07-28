package edu.pdx.cs410J.tn27;

import edu.pdx.cs410J.ParserException;
import org.junit.jupiter.api.Test;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.Collections;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class TextDumperParserTest {

 /* @Test
  void emptyMapCanBeDumpedAndParsed() throws ParserException {
    //Map<String, String> map = Collections.emptyMap();
    //Map<String, String> read = dumpAndParse(map);
    PhoneBill bill1 = dumpAndParse(null);
    assertThat(new PhoneBill(null), equalTo(bill1));
  }*/

  private PhoneBill dumpAndParse(PhoneBill bill) throws ParserException {
    StringWriter sw = new StringWriter();
    TextDumper dumper = new TextDumper(sw);
    dumper.dump(bill);

    String text = sw.toString();

    TextParser parser = new TextParser(new StringReader(text));
    return parser.parse();
  }

  @Test
  void dumpedTextCanBeParsed() throws ParserException {
    PhoneBill bill = new PhoneBill ("Tam");
    PhoneCall call = new PhoneCall("123-456-7890", "345-678-9078", "01/12/2022", "1:20",
            "am","01/12/2022", "1:30","am");
    bill.addPhoneCall(call);
    PhoneBill read = dumpAndParse(bill);
    assertThat(read.toString(), equalTo(bill.toString()));
  }
}
