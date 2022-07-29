package edu.pdx.cs410J.tn27;

import edu.pdx.cs410J.InvokeMainTestCase;
import edu.pdx.cs410J.ParserException;
import edu.pdx.cs410J.UncaughtExceptionInMain;
import edu.pdx.cs410J.web.HttpRequestHelper.RestException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static edu.pdx.cs410J.tn27.validateinfo.isValidDate;
import static edu.pdx.cs410J.tn27.validateinfo.isValidTime;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.MethodOrderer.MethodName;

/**
 * Tests the {@link Project4} class by invoking its main method with various arguments
 */

@TestMethodOrder(MethodName.class)
class Project4IT extends InvokeMainTestCase {
    private static final String HOSTNAME = "localhost";
    private static final String PORT = System.getProperty("http.port", "8080");

    @Test
    void test0RemoveAllMappings() throws IOException {
      PhoneBillRestClient client = new PhoneBillRestClient(HOSTNAME, Integer.parseInt(PORT));
      client.removeAllDictionaryEntries();
    }

    @Test
    void test1NoCommandLineArguments() {
        MainMethodResult result = invokeMain( Project4.class );
        assertThat(result.getTextWrittenToStandardError(), containsString(Project4.MISSING_ARGS));
    }
  /*  @Test
    void test2SearchForBeginEnd() throws InvalidPhoneCallArgument, IOException, ParserException {
        PhoneBillRestClient client = new PhoneBillRestClient(HOSTNAME, Integer.parseInt(PORT));


            Date begin = getBegin("02/27/2022", "12:00", "am");
            Date end = getBegin("02/26/2022", "12:59", "am");
            client.SearchPhoneCallsBeginEnd("Tam",begin,end);
    }
    public static Date getBegin(String begin_date, String begin_time, String begin_zone) throws InvalidPhoneCallArgument {
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

   /* @Test
    void test2EmptyServer() {
        MainMethodResult result = invokeMain( Project4.class, HOSTNAME, PORT );
        String out = result.getTextWrittenToStandardOut();
        assertThat(out, out, containsString(PrettyPrinter.formatWordCount(0)));


    @Test
    void test3NoDefinitionsThrowsAppointmentBookRestException() {
        String word = "WORD";
        try {
            invokeMain(Project4.class, "-host",HOSTNAME,"-port" ,PORT, word);
            fail("Expected a RestException to be thrown");

        } catch (UncaughtExceptionInMain ex) {
            RestException cause = (RestException) ex.getCause();
            assertThat(cause.getHttpStatusCode(), equalTo(HttpURLConnection.HTTP_NOT_FOUND));
        }
    }

    @Test
    void test4AddDefinition() {
        String word = "WORD";
        String definition = "DEFINITION";

        MainMethodResult result = invokeMain( Project4.class, HOSTNAME, PORT, word, definition );
        String out = result.getTextWrittenToStandardOut();
        assertThat(out, out, containsString(Messages.definedWordAs(word, definition)));

        result = invokeMain( Project4.class, HOSTNAME, PORT, word );
        out = result.getTextWrittenToStandardOut();
        assertThat(out, out, containsString(PrettyPrinter.formatDictionaryEntry(word, definition)));

        result = invokeMain( Project4.class, HOSTNAME, PORT );
        out = result.getTextWrittenToStandardOut();
        assertThat(out, out, containsString(PrettyPrinter.formatDictionaryEntry(word, definition)));
    }*/
}

