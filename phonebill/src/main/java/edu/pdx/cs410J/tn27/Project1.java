package edu.pdx.cs410J.tn27;

import com.google.common.annotations.VisibleForTesting;

/**
 * The main class for the CS410J Phone Bill Project
 */
public class Project1 {

  @VisibleForTesting
  static boolean isValidPhoneNumber(String phoneNumber) {
    return true;
  }

  public static void main(String [] args) {

    if (args.length == 0) {
      System.err.println("Missing command line arguments");

    }
    if (args.length == 1){
      System.err.println("Not enough argument");
    }

    for (String arg : args) {
      System.out.println(arg);
    }

    PhoneCall call = new PhoneCall(args[1], args[2], args[3], args[4]);  // Refer to one of Dave's classes so that we can be sure it is on the classpath
  }


}