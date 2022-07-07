package edu.pdx.cs410J.tn27;

import com.google.common.annotations.VisibleForTesting;

/**
 * The main class for the CS410J Phone Bill Project
 */
public class Project1 {



  public static void main(String[] args) {

    String result = ValidArgument(args);
    for (String arg : args) {
      System.out.println(arg);
    }

    PhoneCall call = new PhoneCall(args[1], args[2], args[3], args[4]);  // Refer to one of Dave's classes so that we can be sure it is on the classpath
  }
//function to test the number of arguments on the command line
@VisibleForTesting
  static String ValidArgument(String... args) {
    String ErrorMessage = null;

    if (args.length == 0) {
      ErrorMessage = "Missing command line arguments\n";

    }
    if (args.length == 1) {
      ErrorMessage = "Not enough argument.\n";
    }
    return ErrorMessage;
  }
 //Function to test if user enter valid phone number
  @VisibleForTesting
  static boolean isValidPhoneNumber(String phoneNumber) {
    return true;
  }



}