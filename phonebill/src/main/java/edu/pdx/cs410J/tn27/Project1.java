package edu.pdx.cs410J.tn27;

import com.google.common.annotations.VisibleForTesting;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The main class for the CS410J Phone Bill Project
 */
public class Project1 {



  public static void main(String[] args) {

    /*Test the number of argument on the command line*/
    String result = ValidArgument(args);
    System.err.println(result);

    if (result == null) {
      /*Validate phone number*/
      if (!isValidPhoneNumber(args[1]) || !isValidPhoneNumber(args[2])) {
        System.err.println("Invalid phone number");
      }
      /*Validate Name*/
      if (!isValidName(args[0])){
        System.err.println("Invalid name");
      }

      if (!isValidDate(args[3])|| !isValidDate(args[5])){
        System.err.println("Invalid date");
      }
      if (!isValidTime(args[4])|| !isValidDate(args[6])){
        System.err.println("Invalid time");
      }
    }

 /*for (String arg : args) {
      System.out.println(arg);
    }*/

      PhoneCall call = new PhoneCall(args[1], args[2], args[3], args[4],args[5],args[6]);  // Refer to one of Dave's classes so that we can be sure it is on the classpath
      PhoneBill phonebill = new PhoneBill(args[0]);
      phonebill.addPhoneCall(call);

  }

//function to test the number of arguments on the command line
@VisibleForTesting
  static String ValidArgument(String... args) {

    if (args.length == 0) {
      return "Missing command line arguments\n";

    }
    if (args.length < 7) {
      return"Not enough arguments";
    }
    else if (args.length == 7)
    {
      for (int i = 0; i <= 4; ++i){
        if (args[i] == null) {
         return "Containing an empty argument";

        }
      }
    }
    return null;
  }
 //Function to test if user enter valid phone number
  @VisibleForTesting
  static boolean isValidPhoneNumber(String phoneNumber) {

    String regex = "[0-9]{3}-[0-9]{3}-[0-9]{4}";
    Pattern P = Pattern.compile(regex);
    if(phoneNumber == null) return false;
    Matcher match = P.matcher(phoneNumber);

    return match.matches();
  }
//Function to test for valid name
  @VisibleForTesting
static boolean isValidName(String name) {

  String regex = ".*";
  Pattern P = Pattern.compile(regex);
  if(name == null) return false;
  Matcher match = P.matcher(name);

  return match.matches();
}
@VisibleForTesting
//Function to test for valid date
static boolean isValidDate(String date) {

  String regex = "[0-9]{1,2}/[0-9]{1,2}/[0-9]{4}";
  Pattern P = Pattern.compile(regex);
  if(date == null) return false;
  Matcher match = P.matcher(date);

  return match.matches();
}
@VisibleForTesting
  static boolean isValidTime(String time) {

  String regex = "[0-4]{1,2}:[0-6][0-9]";
  Pattern P = Pattern.compile(regex);
  if (time == null) return false;
  Matcher match = P.matcher(time);

  return match.matches();

}
}