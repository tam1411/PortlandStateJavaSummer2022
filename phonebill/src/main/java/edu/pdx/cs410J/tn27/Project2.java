package edu.pdx.cs410J.tn27;

import com.google.common.annotations.VisibleForTesting;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The main class for the CS410J Phone Bill Project
 */
public class Project2 {



  public static void main(String[] args) {

      if (args.length == 0) {
          System.err.println( "Missing command line arguments\n");
      }

      else {

          //Validate argument first
          int result_argument = ValidArgument(args);

          //If the argument number is valid
          if (result_argument == 1) {

              //-print option
              if (args.length == 8) {
                  if (args[0].equals("-print")) {
                      PhoneBill phonebill = new PhoneBill(args[1]);
                      PhoneCall call = new PhoneCall(args[2], args[3], args[4], args[5], args[6], args[7]);  // Refer to one of Dave's classes so that we can be sure it is on the classpath
                      phonebill.addPhoneCall(call);
                      System.out.println(call.toString());
                  } else System.err.println("Invalid option");

              }
              //-README option
              if (args.length == 1 && args[0].equals("-README")) {
              }
              //Normal command line arguments
              if (args.length == 7) {
                  /*Validate phone number*/
                  if ((!isValidPhoneNumber(args[1])) || (!isValidPhoneNumber(args[2]))) {
                      System.err.println("Invalid phone number");
                  }
                  //Validate date
                  if (!isValidDate(args[3]) || !isValidDate(args[5])) {
                      boolean result_date = isValidDate(args[3]);
                      //System.out.println(result_date + "result date\n");
                      System.err.println("Invalid date");
                  }
                  //Validate time
                  if ((!isValidTime(args[4])) || (!isValidTime(args[6]))) {
                      boolean result_time = isValidTime(args[4]);
                      //System.out.println(result_time + "result time\n");
                      System.err.println("Invalid time");
                  }

                  PhoneBill phonebill = new PhoneBill(args[0]);
                  PhoneCall call = new PhoneCall(args[1], args[2], args[3], args[4], args[5], args[6]);  // Refer to one of Dave's classes so that we can be sure it is on the classpath

                  phonebill.addPhoneCall(call);
               /*for (String arg : args) {
                   System.out.println(arg);*/
              }


          } else {
              //Other case will default to fail to meet the valid argument
              System.err.println("Please check if your command line has the invalid: \n");
              System.err.println("Not enough argument or too many arguments.\n");
          }
      }
   }


//function to test the number of arguments on the command line
@VisibleForTesting
  static int ValidArgument(String... args) {
      if (args.length == 0) return -1;
    if(args.length > 1 &&args.length < 7){
        return -1;
    }
    if (args.length >= 7) {
        for (int i = 0; i <= 4; ++i) {
            if (args[i] == null) {
                return -1;
            }

        }
    }
    return 1;
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

  String regex = "[0-2][0-9]:[0-6][0-9]";
  Pattern P = Pattern.compile(regex);
  if (time == null) return false;
  Matcher match = P.matcher(time);

  return match.matches();

}

}