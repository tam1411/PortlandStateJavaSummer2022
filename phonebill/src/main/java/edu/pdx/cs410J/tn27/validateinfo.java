package edu.pdx.cs410J.tn27;

import com.google.common.annotations.VisibleForTesting;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Validate the argument on the command line
 */
public class validateinfo {
    //Function to test if user enter valid phone number
    @VisibleForTesting
    static boolean isValidPhoneNumber(String phoneNumber) {

        String regex = "[0-9]{3}-[0-9]{3}-[0-9]{4}";
        Pattern P = Pattern.compile(regex);
        if (phoneNumber == null) return false;
        Matcher match = P.matcher(phoneNumber);

        return match.matches();
    }


    @VisibleForTesting
//Function to test for valid date
    static boolean isValidDate(String date) {

        String regex = "[0-9]{1,2}/[0-9]{1,2}/[0-9]{4}";
        Pattern P = Pattern.compile(regex);
        if (date == null) return false;
        Matcher match = P.matcher(date);

        return match.matches();
    }

    @VisibleForTesting
    static boolean isValidTime(String number, String zone) {
        String regex = "(1[012]|[1-9]):[0-5][0-9]" + "(am|pm)";
        Pattern P = Pattern.compile(regex);
        Matcher match = P.matcher(number + zone);

        return match.matches();

    }
}
@VisibleForTesting
//Extend from exception
static class InvalidPhoneCallArgument extends Exception{
    public InvalidPhoneCallArgument(String message){
        super(message);
    }


    //Function to validate each arg before create the phone call for text file purpose
    @VisibleForTesting
    static PhoneCall CreatePhoneCall (String caller, String callee, String begin_date, String begin_time, String begin_zone,
                                      String end_date, String end_time,String end_zone) throws InvalidPhoneCallArgument {
        if (!isValidPhoneNumber(caller) || !isValidPhoneNumber(callee)) {
            throw new InvalidPhoneCallArgument("Invalid phone number.");
        } else if (!isValidDate(begin_date) || !isValidDate(end_date)) {
            throw new InvalidPhoneCallArgument("Invalid date.");
        } else if (!isValidTime(begin_time, begin_zone) || !isValidTime(end_time, end_zone)) {
            throw new InvalidPhoneCallArgument("Invalid time.");
        } else {
            return new PhoneCall(caller, callee, begin_date, begin_time, begin_zone, end_date, end_time, end_zone);
        }
    }