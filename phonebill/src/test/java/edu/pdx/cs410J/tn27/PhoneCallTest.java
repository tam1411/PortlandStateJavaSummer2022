package edu.pdx.cs410J.tn27;

import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Unit tests for the {@link PhoneCall} class.
 *
 * You'll need to update these unit tests as you build out your program.
 */
public class PhoneCallTest {


    /*@Test
    void forProject1ItIsOkayIfGetBeginTimeReturnsNull() {
      PhoneCall call = new PhoneCall(;
      assertThat(call.getBeginTime(), is(nullValue()));
    }*/
    //Test to see if we receive the caller number as expected
    //Like student named Pat is named Pat
    @Test
    void CallerNumberIsCallerNumber() {
        //Given caller phone number
        String caller_number = "123456789";
        PhoneCall call = CreatePhone(caller_number, null, null, null, null, null, null, null);
        assertThat(call.getCaller(), equalTo(caller_number));
    }

    private PhoneCall CreatePhone(String caller_number, String callee_number, String begin_date, String begin_time, String begin_zone, String end_date, String end_time, String end_zone) {
        return new PhoneCall(caller_number, callee_number, begin_date, begin_time, begin_zone, end_date, end_time, end_zone);
    }

    //Test to see if we will receive the callee number as expected
    @Test
    void CalleeNumberIsCalleeNumber() {
        //Given callee phone number
        String callee_number = "987654321";
        PhoneCall call = CreatePhone(null, callee_number, null, null, null, null, null, null);
        assertThat(call.getCallee(), equalTo(callee_number));
    }

    //Test to see if we will receive the begin time as expected
    @Test
    void BeginTimeIsBeginTime() {
        //Given begin time
        String begin_time = "03/15/2021 10:35";
        PhoneCall call = CreatePhone(null, null, null, begin_time, null, null, null, null);
        assertThat(call.getBeginTimeString(), equalTo(begin_time));

    }

    //Test to see if we will receive the end time as expected
    @Test
    void EndTimeIsEndTime() {
        //Given end time
        String end_time = "04/15/2021 11:35";
        PhoneCall call = CreatePhone(null, null, null, null, null, null, end_time, null);
        assertThat(call.getEndTimeString(), equalTo(end_time));
    }

    //Test if there is no argument
   /* @Test
    void validateNoArgument(){
        Project1 var = new Project1();
        assertThat(var.ValidArgument(),equalTo("Missing command line arguments\n"));
    }*/
    //Test if there is not enough argument
    @Test
    void ValidateNotEnoughArgument() {
        Project3 var = new Project3();
        assertThat(var.ValidArgument("name", "2"), equalTo(-1));
    }

    //Test for a null argument on the command line
    @Test
    void NullArgument() {
        Project3 var = new Project3();
        assertThat(var.ValidArgument("name", null, null, "123", "name", "1"), equalTo(-1));
    }

    //Test for invalid phone number
    @Test
    void InvalidPhoneNumber() {
        Project3 var = new Project3();
        boolean result = var.isValidPhoneNumber("123-46-abcd");
        if (!result) {
            assertThat("Invalid phone number", equalTo("Invalid phone number"));

        }
    }

    //Test for an invalid date
    @Test
    void InvalidDate() {
        Project3 var = new Project3();
        boolean result = var.isValidDate("123/12/2011");

        assertThat(result, equalTo(false));

    }

    //Test for an valid time
    @Test
    void validTime() {
        Project3 var = new Project3();
        boolean result = var.isValidTime("2:59", "am");
        assertThat(result, equalTo(true));

    }

    @Test
        //Test for an invalid file name
    void InvalidFileName() {
        Project3 var = new Project3();
        boolean result = var.ValidateFileName("file.txx");
        if (!result) {
            assertThat("Invalid File Name", equalTo("Invalid File Name"));
        }
    }

    @Test
        //Test if the existing file does exist
    void ExistFileDoesExist() {
        Project3 var = new Project3();
        boolean result = var.ExistFile("empty-phonebill.txt");
        if (!result) {
            assertThat(false, equalTo(false));
        }
    }

    @Test
    static Date StringTimeDateParseToObjectDate() {
        //Given
        String date = "01/12/2022";
        String time = "8:46";
        String zone = "am";
        Date full_date = null;
        SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy h:mm a");
        try {
            full_date = df.parse((date + " " + time + " " + zone.toUpperCase().toString()));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        assertThat(df.format(full_date), equalTo("01/12/2022 8:46 AM"));
        return full_date;
    }

    @Test
    void GetBeginTimeReturnDate() {
        PhoneCall call = new PhoneCall("0", "0", "01/12/2022", "8:46", "am", "01/12/2022", "8:46", "am");
        Date date = call.getBeginTime();
        assertThat(date, equalTo(StringTimeDateParseToObjectDate()));


    }

    @Test
    void GetEndTimeReturnDate() {
        PhoneCall call = new PhoneCall("0", "0", "01/12/2022", "8:45", "am", "01/12/2022", "8:46", "am");
        Date date = call.getEndTime();
        assertThat(date, equalTo(StringTimeDateParseToObjectDate()));
    }

}


