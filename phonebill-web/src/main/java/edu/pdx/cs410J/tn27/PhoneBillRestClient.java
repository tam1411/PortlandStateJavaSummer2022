package edu.pdx.cs410J.tn27;

import com.google.common.annotations.VisibleForTesting;
import edu.pdx.cs410J.ParserException;
import edu.pdx.cs410J.web.HttpRequestHelper;

import java.io.IOException;
import java.io.StringReader;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static edu.pdx.cs410J.web.HttpRequestHelper.Response;
import static edu.pdx.cs410J.web.HttpRequestHelper.RestException;
import static java.net.HttpURLConnection.HTTP_OK;

/**
 * A helper class for accessing the rest client.  Note that this class provides
 * an example of how to make gets and posts to a URL.  You'll need to change it
 * to do something other than just send dictionary entries.
 */
public class PhoneBillRestClient {

    private static final String WEB_APP = "phonebill";
    private static final String SERVLET = "calls";

  private final HttpRequestHelper http;


    /**
     * Creates a client to the Phone Bil REST service running on the given host and port
     * @param hostName The name of the host
     * @param port The port
     */
    public PhoneBillRestClient( String hostName, int port )
    {
      this(new HttpRequestHelper(String.format("http://%s:%d/%s/%s", hostName, port, WEB_APP, SERVLET)));
    }

  @VisibleForTesting
  PhoneBillRestClient(HttpRequestHelper http) {
    this.http = http;
  }

  /**
   * Returns all phone bills from the server
   */
  /*public Map<String, PhoneBill> getAllPhoneBillEntries() throws IOException, ParserException {
    Response response = http.get(Map.of());

    TextParser parser = new TextParser(new StringReader(response.getContent()));
    return parser.parse();
  }*/

  /**
   * Returns the phone bill for the given customer
   * @param customer
   *        pass in to get the phone bill content
   */

  public PhoneBill getPhoneBill(String customer) throws IOException, ParserException {
    Response response = http.get(Map.of("customer", customer));
    throwExceptionIfNotOkayHttpStatus(response);
    String content = response.getContent();

    TextParser parser = new TextParser(new StringReader(content));
    return parser.parse();
  }

  public PhoneBill SearchPhoneCallsBeginEnd (String customer, Date Begin, Date End) throws IOException, ParserException {
    Response response = http.get(Map.of("customer", customer));
    throwExceptionIfNotOkayHttpStatus(response);
    String content = response.getContent();

    TextParser parser = new TextParser(new StringReader(content));
    return SearchBeginEnd(parser.parse(),Begin,End);

  }
  /**
   * Function to search phone calls that begin
   * between two times inside a phone bill.
   *
   * @param bill
   *        original phone bill to search.
   * @param Begin
   *        Begin Date object passed in from the command line.
   * @param End
   *        Second Begin Date object passed in from the command line.
   */
  private PhoneBill SearchBeginEnd(PhoneBill bill, Date Begin, Date End){
    PhoneBill search_bill = new PhoneBill(bill.getCustomer());
    List<PhoneCall> call = (List<PhoneCall>) bill.getPhoneCalls();
    for (int i = 0; i < call.size(); ++i){
         if (call.get(i).getBeginTime().compareTo(Begin) >= 0 || call.get(i).getBeginTime().compareTo(End) <= 0){
           search_bill.addPhoneCall(call.get(i));
         }
    }
    return search_bill;
  }

  /**
   * Add the phone call to a phone bill entry
   * @param customer
   *        from the command line
   * @param caller
   *        from the command line
   * @param callee
   *        from the command line
   * @param begin_date
   *        from the command line
   * @param begin_time
   *        from the command line
   * @param begin_zone
   *        from the command line
   * @param end_date
   *        from the command line
   * @param end_time
   *        from the command line
   * @param end_zone
   *        from the command line
   * @throws IOException
   */
    public void addPhoneCallToPhoneBillEntry(String customer, String caller,String callee,String begin_date,
                     String begin_time,String begin_zone,String end_date,String end_time,String end_zone)
            throws IOException {

      Response response = http.post(Map.of("customer", customer, "caller", caller,"callee",callee, "begin_date",begin_date,
                                           "begin_time",begin_time, "begin_zone",  begin_zone, "end_date",end_date,
                                            "end_time",end_time,"end_zone",end_zone));
      throwExceptionIfNotOkayHttpStatus(response);
    }

  public void removeAllDictionaryEntries() throws IOException {
      Response response = http.delete(Map.of());
      throwExceptionIfNotOkayHttpStatus(response);
    }

    private void throwExceptionIfNotOkayHttpStatus(Response response) {
      int code = response.getHttpStatusCode();
      if (code != HTTP_OK) {
        String message = response.getContent();
        throw new RestException(code, message);
      }
    }

}
