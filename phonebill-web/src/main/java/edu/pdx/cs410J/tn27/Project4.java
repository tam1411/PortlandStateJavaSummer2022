package edu.pdx.cs410J.tn27;

import edu.pdx.cs410J.ParserException;
import edu.pdx.cs410J.web.HttpRequestHelper;

import java.io.*;
import java.util.Map;

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

        for (String arg : args) {
            if (host == null){
                host = arg;
            } else if (hostName == null && host.equals("-host")) {
                hostName = arg;

            } else if (port_num == null) {
                  port_num = arg;
            } else if (portString == null && port_num.equals("-port")) {
                portString = arg;

            } else if (option == null) {
                      option  = arg;
                      if ( !option.equals("-search") || !option.equals("-print")){
                           customer = arg;
                           option = "empty";
                      }
            } else if (customer == null) {
                customer = arg;
            } else if (caller == null) {
                caller = arg;
            } else if (callee == null){
                callee = arg;
            } else if (begin_date == null){
                begin_date = arg;
            } else if (begin_time == null){
                begin_time = arg;
            } else if (begin_zone == null){
                begin_zone = arg;
            } else if (end_date == null){
                end_date = arg;
            } else if (end_time == null){
                end_time = arg;
            } else if (end_zone == null){
                end_zone = arg;
            }
            else {
                usage("Extraneous command line argument: " + arg);
            }
        }

        if (hostName == null) {
            usage( MISSING_ARGS );

        }else if (hostName.equals("-README")){
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

        String message;
        try {
           /* if (word == null) {
                // Print all word/definition pairs
                Map<String, String> dictionary = client.getAllDictionaryEntries();
                StringWriter sw = new StringWriter();
                PrettyPrinter pretty = new PrettyPrinter(sw);
                pretty.dump(dictionary);
                message = sw.toString();*/

             if (args.length == 5 && customer != null) {
                // Print all dictionary entries
               // message = PrettyPrinter.formatDictionaryEntry(customer, client.getPhoneBill(customer));
                  bill = client.getPhoneBill(customer);
                 StringWriter sw = new StringWriter();
                 PrettyPrinter pretty = new PrettyPrinter(sw);
                 pretty.dump(bill);
                 message = sw.toString();
            } else {
                // Post the word/definition pair
                 PhoneCall call = new PhoneCall(caller,callee,begin_date,
                         begin_time,begin_zone,end_date,end_time,end_zone);
                client.addPhoneCallToPhoneBillEntry(customer,caller,callee,begin_date,
                        begin_time,begin_zone,end_date,end_time,end_zone);
                message = Messages.CustomerhasPhoneBill(customer,call);
             }
        } catch (IOException | ParserException ex ) {
            error("While contacting server: " + ex);
            return;
        }

        System.out.println(message);
        }

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
            String line = null;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

        } catch (IOException e) {
            System.err.println("Error with README.txt");
        }
    }

}