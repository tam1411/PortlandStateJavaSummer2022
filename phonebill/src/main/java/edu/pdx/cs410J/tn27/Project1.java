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
    /*for (String arg : args) {
      System.out.println(arg);
    }*/

    /*Validate phone number*/
      String error = "Invalid phone number";
      if (!isValidPhoneNumber(args[1]) || !isValidPhoneNumber(args[2])){
        System.err.println(error);
      }



    //PhoneCall call = new PhoneCall(args[1], args[2], args[3], args[4]);  // Refer to one of Dave's classes so that we can be sure it is on the classpath
  }

//function to test the number of arguments on the command line
@VisibleForTesting
  static String ValidArgument(String... args) {
    String ErrorMessage = null;

    if (args.length == 0) {
      ErrorMessage = "Missing command line arguments\n";

    }
    if (args.length == 1) {
      ErrorMessage = "Not enough arguments";
    }
    else if (args.length == 5)
    {
      for (int i = 0; i <= 4; ++i){
        if (args[i] == null) {
          ErrorMessage = "Containing an empty argument";
          break;
        }
      }
    }
    return ErrorMessage;
  }
 //Function to test if user enter valid phone number
  @VisibleForTesting
  static boolean isValidPhoneNumber(String phoneNumber) {
    /*char [] character = phoneNumber.toCharArray();
    for (char c : character){
      if (Character.isDigit(c)){
        return false;
      }
    }
    return true;*/


    String regex = "[0-9]{3}-[0-9]{3}-[0-9]{4}";
    Pattern P = Pattern.compile(regex);
    if(phoneNumber == null) return false;
    Matcher match = P.matcher(phoneNumber);

    return match.matches();
  }



}