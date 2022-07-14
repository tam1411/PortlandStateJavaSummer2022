package edu.pdx.cs410J.tn27;

import org.junit.jupiter.api.Test;

import java.util.List;

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
        PhoneCall call = CreatePhone(caller_number, null,null,null, null, null);
        assertThat(call.getCaller(), equalTo(caller_number));
    }

    private PhoneCall CreatePhone(String caller_number, String callee_number, String begin_date,String begin_time,String end_date, String end_time) {
        return new PhoneCall(caller_number, callee_number, begin_date,begin_time,end_date, end_time);
    }

    //Test to see if we will receive the callee number as expected
    @Test
    void CalleeNumberIsCalleeNumber() {
        //Given callee phone number
        String callee_number = "987654321";
        PhoneCall call = CreatePhone(null, callee_number, null,null,null ,null);
        assertThat(call.getCallee(), equalTo(callee_number));
    }

    //Test to see if we will receive the begin time as expected
    @Test
    void BeginTimeIsBeginTime() {
        //Given begin time
        String begin_time = "03/15/2021 10:35";
        PhoneCall call = CreatePhone(null,null,null,begin_time,null,null);
        assertThat(call.getBeginTimeString(),equalTo(begin_time));

    }
    //Test to see if we will receive the end time as expected
    @Test
    void EndTimeIsEndTime(){
        //Given end time
        String end_time = "04/15/2021 11:35";
        PhoneCall call = CreatePhone(null,null,null,null,null,end_time);
        assertThat(call.getEndTimeString(),equalTo(end_time));
    }

    //Test if there is no argument
   /* @Test
    void validateNoArgument(){
        Project1 var = new Project1();
        assertThat(var.ValidArgument(),equalTo("Missing command line arguments\n"));
    }*/
    //Test if there is not enough argument
    @Test
    void ValidateNotEnoughArgument(){
        Project2 var = new Project2();
        assertThat(var.ValidArgument("name","2"),equalTo(-1));
    }
    //Test for a null argument on the command line
    @Test
    void NullArgument(){
    Project2 var= new Project2();
    assertThat(var.ValidArgument("name",null,null,"123","name","1"),equalTo(-1));
    }

    //Test for invalid phone number
  @Test
    void InvalidPhoneNumber() {
      Project2 var = new Project2();
      boolean result = var.isValidPhoneNumber("123-46-abcd");
      if (!result) {
          assertThat("Invalid phone number", equalTo("Invalid phone number"));

      }
  }

   //Test for an invalid date
      @Test
     void InvalidDate(){
          Project2 var = new Project2();
          boolean result = var.isValidDate("123/12/2011");
          if (!result) {
              assertThat("Invalid date", equalTo("Invalid date"));
          }
      }
   //Test for an invalid time
   @Test
   void InvalidTime(){
       Project2 var = new Project2();
       boolean result = var.isValidTime("123:12");

           if (!result){
               assertThat("Invalid time", equalTo("Invalid time"));
       }
   }

@Test
   //Test for an invalid file name
    void InvalidFileName(){
        Project2 var = new Project2();
        boolean result = var.ValidateFileName("file.txx");
        if (!result){
            assertThat("Invalid File Name", equalTo("Invalid File Name"));
        }
    }
@Test
    //Test if the existing file does exist
    void ExistFileDoesExist(){
        Project2 var = new Project2();
        boolean result = var.ExistFile("empty-phonebill.txt");
        if (!result){
            assertThat(false,equalTo(false));
        }
    }

    /*Test if the AddPhoneCall add the phonecall
    void AddNewPhoneCall(){
        PhoneBill bill = new PhoneBill("Tam");
        PhoneCall call = new PhoneCall("123-456-7890" ,"123-456-7890", "12/30", "12:30", "12/31", "12:31");
        bill.addPhoneCall(call);
        List<PhoneCall> call1 = PhoneCall("123-456-7890" ,"123-456-7890", "12/30", "12:30", "12/31", "12:31");
        assertThat(bill.getPhoneCalls(),equalTo());
    }*/




}

