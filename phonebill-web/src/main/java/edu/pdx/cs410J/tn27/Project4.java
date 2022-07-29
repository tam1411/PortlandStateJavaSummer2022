package edu.pdx.cs410J.tn27;

import edu.pdx.cs410J.ParserException;
import edu.pdx.cs410J.web.HttpRequestHelper;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import static edu.pdx.cs410J.tn27.validateinfo.*;

/**
 * The main class that parses the command line and communicates with the
 * Phone Bill server using REST.
 */
public class Project4 {

    public static final String MISSING_ARGS = "Missing command line arguments";

    public static void main(String... args) {
        String hostName = null, host = null;
        String portString = null,port_num = null, option = null;
        String customer = null,caller = null,callee = null;
        String begin_date = null, begin_time = null, begin_zone  = null;
        String end_date = null, end_time = null, end_zone = null;

        //for (String arg : args) {
        for (int i = 0; i < args.length; ++i){
            if (host == null){
                host = args[i];
            } else if (hostName == null && host.equals("-host")) {
                hostName = args[i];

            } else if (port_num == null) {
                  port_num = args[i];
            } else if (portString == null && port_num.equals("-port")) {
                portString = args[i];

            } else if (option == null) {
                      option  = args[i];
                      if ( option.equals("-search")) {
                          for (int j = i + 1; j < args.length; ++j){
                               if (customer == null) {
                                   customer = args[j];
                          } else if (begin_date == null){
                              begin_date = args[j];
                          } else if (begin_time == null){
                              begin_time = args[j];
                          } else if (begin_zone == null){
                              begin_zone = args[j];
                          } else if (end_date == null){
                              end_date = args[j];
                          } else if (end_time == null){
                              end_time = args[j];
                          } else if (end_zone == null){
                              end_zone = args[j];
                          }

                          }
                          break;
                      }
                      else{
                          option = "empty";
                          customer = args[i];
                      }
            } else if (customer == null) {
                customer = args[i];
                System.out.println(customer+ "\n");
            } else if (caller == null) {
                caller = args[i];
               // System.out.println(caller);
            } else if (callee == null){
                callee = args[i];
            } else if (begin_date == null){
                begin_date = args[i];
            } else if (begin_time == null){
                begin_time = args[i];
            } else if (begin_zone == null){
                begin_zone = args[i];
            } else if (end_date == null){
                end_date = args[i];
            } else if (end_time == null){
                end_time = args[i];
            } else if (end_zone == null){
                end_zone = args[i];
            }
            else {
                usage("Extraneous command line argument: " + args[i]);
            }
        }

        if (host == null) {
            usage( MISSING_ARGS );

        }else if (host.equals("-README")){
            README();

        }
        else if ( portString == null) {
            usage( "Missing port" );
        }

        int port;
        try {
            port = Integer.parseInt( portString );
            
        } catch (NumberFormatException ex) {
            usage("Port \"" + portString + "\" must be an integer");
            return;
        }

        PhoneBillRestClient client = new PhoneBillRestClient(hostName, port);
        PhoneBill bill = new PhoneBill(customer);

        String message = "message does not fall in any cases";
        try {
             if (args.length == 5 && customer != null) {
                  bill = client.getPhoneBill(customer);
                 StringWriter sw = new StringWriter();
                 PrettyPrinter pretty = new PrettyPrinter(sw);
                 pretty.dump(bill);
                 message = sw.toString();
            }else if (option.equals("-search") && customer != null){
                // Date Begin = getBegin(begin_date,begin_time, begin_zone);
                 //Date End = getBegin(end_date,end_time,end_zone);
                 if (!isValidTime(begin_time,begin_zone))
                     throw new InvalidPhoneCallArgument("Invalid time");
                 if (!isValidDate(begin_date))
                     throw new InvalidPhoneCallArgument("Invalid date");
                 String begin = begin_date + " " + begin_time + " " + begin_zone;
                 String end = end_date + " " + end_time + " " + end_zone;


                 bill = client.SearchPhoneCallsBeginEnd(customer,begin,end);


                     StringWriter sw = new StringWriter();
                     PrettyPrinter pretty = new PrettyPrinter(sw);
                     pretty.dump(bill);
                     String sw1 = sw.toString();
                     //System.out.println("This search case");
                     if (sw1.trim().equals(bill.getCustomer()))
                     {  message = "There is no calls";
                     }else{
                         message = sw1;
                     }

                 ;

            }
             else {
                // Post the word/definition pair
                 PhoneCall call = CreatePhoneCall(caller,callee,begin_date,
                         begin_time,begin_zone,end_date,end_time,end_zone);
                client.addPhoneCallToPhoneBillEntry(customer,caller,callee,begin_date,
                        begin_time,begin_zone,end_date,end_time,end_zone);
                System.out.println("Just added to " + customer);
                message = Messages.CustomerhasPhoneBill(customer,call);
             }
        } catch (IOException | ParserException | InvalidPhoneCallArgument ex ) {
            error("While contacting server: " + ex);
            return;
        }

        System.out.println(message);
        }

    /**
     * Function to parse the begin time into a Date object
     * to search for phone calls between two begin times
      * @param begin_date
     *         begin date from command line
     * @param begin_time
     *        begin time from command line
     * @param begin_zone
     *        begin_zone from command line
     * @return date
     */
   /*public static Date getBegin(String begin_date, String begin_time, String begin_zone) throws InvalidPhoneCallArgument {
        if (!isValidTime(begin_time,begin_zone))
            throw new InvalidPhoneCallArgument("Invalid time");
        if (!isValidDate(begin_date))
            throw new InvalidPhoneCallArgument("Invalid date");


        StringBuilder sb = new StringBuilder();
        sb.append(begin_date + " " + begin_time + " " + begin_zone);
        Date date = null;
        SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy h:mm a");
        try {
            date = df.parse(sb.toString());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return date;
    }*/

    /**
     * Makes sure that the give response has the expected HTTP status code
     * @param code The expected status code
     * @param response The response from the server
     */
    private static void checkResponseCode( int code, HttpRequestHelper.Response response )
    {
        if (response.getHttpStatusCode() != code) {
            error(String.format("Expected HTTP code %d, got code %d.\n\n%s", code,
                                response.getHttpStatusCode(), response.getContent()));
        }
    }

    private static void error( String message )
    {
        PrintStream err = System.err;
        err.println("** " + message);
    }

    /**
     * Prints usage information for this program and exits
     * @param message An error message to print
     */
    private static void usage( String message )
    {
        PrintStream err = System.err;
        err.println("** " + message);
        err.println();
        err.println("usage: java -jar target/phonebill-2022.0.0.jar [options] <args> ");
        err.println("args are (in this order)");
        err.println("  customer      Person whose phone bill we’re modeling");
        err.println("  callerNumber  Phone number of caller");
        err.println("  calleeNumber  Phone number of person who was called");
        err.println("  begin         Date and time call began");
        err.println("  end           Date and time call end");
        err.println();
        err.println("options are (options may appear in any order):\n");
        err.println("-host hostname  Host computer on which the server runs\n");
        err.println("-port port      Port on which the server is listening");
        err.println("-search         Phone calls should be searched for");
        err.println("-print          Prints a description of the new phone call");
        err.println("-README         Prints a README for this project and exits.");
        err.println();
    }
    private static void README(){

        try {
            //Might not work because of the class.
            //Check later

            InputStream readme = Project4.class.getResourceAsStream("README.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(readme));
            //FileReader reader = new FileReader(new File("README.txt"));
            String line = null;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

        } catch (IOException e) {
            System.err.println("Error with README.txt");
        }
    }

}