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
