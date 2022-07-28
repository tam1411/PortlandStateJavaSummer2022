package edu.pdx.cs410J.tn27;

import com.google.common.annotations.VisibleForTesting;
import com.google.inject.internal.InternalFlags;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/**
 * This servlet ultimately provides a REST API for working with an
 * <code>PhoneBill</code>.  However, in its current state, it is an example
 * of how to use HTTP and Java servlets to store simple dictionary of words
 * and their definitions.
 */
public class PhoneBillServlet extends HttpServlet
{
    //static final String WORD_PARAMETER = "word";
    //static final String DEFINITION_PARAMETER = "definition";

    //private final Map<String, String> dictionary = new HashMap<>();
      static final String CUSTOMER = "customer";
      static final String CALLER = "caller";
      static final String CALLEE = "callee";
      static final String BEGIN_DATE = "begin_date";
      static final String BEGIN_TIME = "begin_time";
      static final String BEGIN_ZONE = "begin_zone";
      static final String END_DATE = "end_date";
      static final String END_TIME = "end_time";
      static final String END_ZONE = "end_zone";


      //private final List<PhoneBill> bill = new ArrayList<>();
      private final Map <String,PhoneBill> Phonebill_List = new HashMap<>();
    /**
     * Handles an HTTP GET request from a client by writing all the phone calls of the
     * customer specified in "customer" HTTP parameter to the HTTP response.
     */
    @Override
    protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws IOException
    {
        response.setContentType( "text/plain" );

        String customer = getParameter( CUSTOMER, request );
        if (customer != null) {
            WritePhoneCallsOfEachPhoneBill(customer, response);

        } else {
            writeAllPhoneBills(response);
        }
    }

    /**
     * Handles an HTTP POST request by storing the dictionary entry for the
     * "word" and "definition" request parameters.  It writes the dictionary
     * entry to the HTTP response.
     */
    @Override
    protected void doPost( HttpServletRequest request, HttpServletResponse response ) throws IOException
    {
        response.setContentType( "text/plain" );

        String customer = getParameter(CUSTOMER, request );
        if (customer == null) {
            missingRequiredParameter(response, CUSTOMER);
            return;
        }
        String caller = getParameter(CALLER, request);
        String callee = getParameter(CALLEE,request);
        String begin_date = getParameter(BEGIN_DATE, request );
        String begin_time = getParameter(BEGIN_TIME, request );
        String begin_zone = getParameter(BEGIN_ZONE, request );
        String end_date = getParameter(END_DATE, request );
        String end_time = getParameter(END_TIME, request );
        String end_zone = getParameter(END_ZONE, request );
        if ( begin_date == null){
            missingRequiredParameter( response, BEGIN_DATE );
            return;
        }
        if ( begin_time == null){
            missingRequiredParameter( response, BEGIN_TIME );
            return;
        }
        if ( begin_zone == null){
            missingRequiredParameter( response, BEGIN_ZONE );
            return;
        }
        if ( end_date == null){
            missingRequiredParameter( response, END_DATE );
            return;
        }
        if ( end_time == null){
            missingRequiredParameter( response, END_TIME);
            return;
        }
        if ( end_zone == null){
            missingRequiredParameter( response, END_ZONE );
            return;
        }
        PhoneBill new_bill = getContent(customer);
        if (new_bill == null){
            new_bill = new PhoneBill(customer);
        }
        PhoneCall call = new PhoneCall(caller,callee,begin_date,begin_time,begin_zone,end_date,end_time,end_zone);
        new_bill.addPhoneCall(call);
        //this.dictionary.put(word, definition);
        this.Phonebill_List.put(customer,new_bill);
        PrintWriter pw = response.getWriter();

        pw.println(new_bill.getCustomer());
        List<PhoneCall> call1 = (List<PhoneCall>) new_bill.getPhoneCalls();
        for (int i = 0; i < call1.size(); ++i) {
            PhoneCall new_call = call1.get(i);
            //String line = new_call.getCaller() + " " + new_call.getCallee() + " " + new_call.NormalBegin() + " " + new_call.NormalEnd();
            pw.println(new_call.toString());
            //pw.flush();
        }
        pw.flush();

        response.setStatus( HttpServletResponse.SC_OK);
    }

    /**
     * Handles an HTTP DELETE request by removing all dictionary entries.  This
     * behavior is exposed for testing purposes only.  It's probably not
     * something that you'd want a real application to expose.
     */
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/plain");

        this.Phonebill_List.clear();

        PrintWriter pw = response.getWriter();
        pw.println(Messages.allDictionaryEntriesDeleted());
        pw.flush();

        response.setStatus(HttpServletResponse.SC_OK);

    }

    /**
     * Writes an error message about a missing parameter to the HTTP response.
     *
     * The text of the error message is created by {@link Messages#missingRequiredParameter(String)}
     */
    private void missingRequiredParameter( HttpServletResponse response, String parameterName )
        throws IOException
    {
        String message = Messages.missingRequiredParameter(parameterName);
        response.sendError(HttpServletResponse.SC_PRECONDITION_FAILED, message);
    }

    /**
     * Write the content of the phone bill associated with a customer to the response
     *
     * The text of the message is formatted with {@link TextDumper}
     */
    private void WritePhoneCallsOfEachPhoneBill(String customer, HttpServletResponse response) throws IOException {
        PhoneBill content = getContent(customer);

       /* if (definition == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);*/

        if (content == null){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }

        else {
            PrintWriter pw = response.getWriter();
           // Map<String, String> wordDefinition = Map.of(word, definition);
            TextDumper dumper = new TextDumper(pw);
            dumper.dump(content);

            response.setStatus(HttpServletResponse.SC_OK);
        }
    }

    /**
     * Function to search a phone bill in a list of phone bills by a customer name
     * @param customer
     *        passes in to create a new phone bill.
     * @return
     *       PhoneBill object.
     */
    private PhoneBill getContent(String customer) {
        /*PhoneBill Customer = new PhoneBill(customer);
        PhoneBill content = null;
        if (this.bill.contains(Customer)){
            int i = bill.indexOf(Customer);
            content = this.bill.get(i);
        }*/
        return this.Phonebill_List.get(customer);
    }

    /**
     * Writes all of the dictionary entries to the HTTP response.
     *
     * The text of the message is formatted with {@link TextDumper}
     */
    private void writeAllPhoneBills(HttpServletResponse response ) throws IOException
    {
        PrintWriter pw = response.getWriter();
        TextDumper dumper = new TextDumper(pw);

        for (String key : this.Phonebill_List.keySet()) {
            dumper.dump(this.Phonebill_List.get(key));
        }
        response.setStatus( HttpServletResponse.SC_OK );
    }

    /**
     * Returns the value of the HTTP request parameter with the given name.
     *
     * @return <code>null</code> if the value of the parameter is
     *         <code>null</code> or is the empty string
     */
    private String getParameter(String name, HttpServletRequest request) {
      String value = request.getParameter(name);
      if (value == null || "".equals(value)) {
        return null;

      } else {
        return value;
      }
    }

    @VisibleForTesting
    PhoneBill getAPhoneBill(String customer) {
        return this.Phonebill_List.get(customer);
    }

}
