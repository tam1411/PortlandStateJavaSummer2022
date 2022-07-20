package edu.pdx.cs410J.tn27;

import com.google.common.annotations.VisibleForTesting;
import edu.pdx.cs410J.ParserException;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static edu.pdx.cs410J.tn27.validateinfo.*;

/**
 * The main class for the CS410J Phone Bill Project
 *
 */
public class Project3 {



  public static void main(String[] args)  {

      if (args.length == 0) {
          System.err.println( "Missing command line arguments\n");
          System.err.println("usage: java -jar target/phonebill-2022.0.0.jar [options] <args>\n" +
                  "args are (in this order):\n" +
                  "customer: Person whose phone bill we arere modeling\n" +
                  "callerNumber: Phone number of caller\n" +
                  "calleeNumber: Phone number of person who was called\n" +
                  "begin Date and time call began (12-hour time)\n" +
                  "end Date and time call ended (12-hour time)\n" +
                  "options are (options may appear in any order):\n" +
                  "-textFile file : Where to read/write the phone bill\n" +
                  "-print : Prints a description of the new phone call\n" +
                  "-README : Prints a README for this project and exits");
      }

      else {

          //Validate argument first
          int result_argument = ValidArgument(args);

          //If the argument number is valid
          if (result_argument == 1) {
              switch(args.length) {
                  //-print option
                  case (10): {
                      if (args[0].equals("-print")) {
                          PhoneBill phonebill = new PhoneBill(args[1]);
                          PhoneCall call = new PhoneCall(args[2], args[3], args[4], args[5], args[6], args[7],args[8],args[9]);  // Refer to one of Dave's classes so that we can be sure it is on the classpath
                          phonebill.addPhoneCall(call);
                          System.out.println(call.toString());
                      } else System.err.println("Invalid option");
                      break;
                  }
                  //-README option
                  case (1): {
                      if (args[0].equals("-README")) {

                          try {
                              InputStream readme = Project3.class.getResourceAsStream("README.txt");
                              BufferedReader reader = new BufferedReader(new InputStreamReader(readme));
                              String line = null;
                              while ((line = reader.readLine()) != null) {
                                  System.out.println(line);
                              }
                          } catch (IOException e) {
                              System.err.println("Error with README.txt");
                          }
                      }
                      break;
                  }
                  //Text File option
                  case (11): {
                      if (args[0].equals("-textFile")) {
                          if (ExistFile(args[1])) { //If file exists
                              try {
                                  File text_file = new File(args[1]);
                                  //Read the text file and create a new phone bill
                                  PhoneBill bill = ReadFile(text_file);
                                  if (!bill.getCustomer().equalsIgnoreCase(args[2])) {
                                      throw new InvalidPhoneCallArgument("NotFoundCustomer");
                                  }
                                  PhoneCall call = CreatePhoneCall(args);//validate phone call info before create it.
                                  bill.addPhoneCall(call); //Add the phone call on the command line to the phone bill
                                  WritePhoneBillToTextFile(text_file, bill); //Write the new added phone bill to text file
                              } catch (ParserException | IOException | InvalidPhoneCallArgument e) {
                                  System.err.println(e.getMessage());
                              }
                          } else {  //If not exist
                              try {
                                  File text_file = new File(args[1]);
                                  if (text_file.createNewFile()) {
                                      PhoneBill bill = new PhoneBill(args[2]); //Create a new phone bill
                                      PhoneCall call = CreatePhoneCall(args); //Add the new phone call
                                      bill.addPhoneCall(call);
                                      WritePhoneBillToTextFile(text_file, bill);
                                  }
                              } catch (IOException | InvalidPhoneCallArgument e) {
                                  System.err.println(e.getMessage());
                              }
                          }


                      }

                      //-pretty file option


                          if (args[0].equals("-pretty"))
                          {
                              if (ExistFile(args[1])) {
                                  PhoneBill bill = new PhoneBill(args[2]);
                                  try {
                                      File text_file = new File(args[1]);
                                      //Read the text file and create a new phone bill
                                        try {
                                             bill = ReadFile(text_file);
                                            }catch(ParserException ex){
                                                ex.getMessage();
                                            }
                                      if (!bill.getCustomer().equalsIgnoreCase(args[2])) {
                                          throw new InvalidPhoneCallArgument("NotFoundCustomer");
                                      }

                                      PhoneCall call = CreatePhoneCall(args);//validate phone call info before create it.
                                      bill.addPhoneCall(call); //Add the phone call on the command line to the phone bill
                                      PrettyPrint dumper = new PrettyPrint(new FileWriter(text_file));
                                      dumper.dump(bill);
                                  } catch (InvalidPhoneCallArgument  | IOException e) {
                                      System.err.println(e.getMessage());
                                  }
                              } else if(args[1].equals("-")){
                                  try {
                                      PhoneBill bill = new PhoneBill(args[2]);
                                      PhoneCall call = CreatePhoneCall(args);
                                      bill.addPhoneCall(call);
                                      System.out.println(bill.PrintStandardOut());
                                  }
                                  catch(InvalidPhoneCallArgument | ParserException e){
                                      System.err.println(e.getMessage());
                                  }

                              }

                              else  {
                                  try {
                                      File text_file = new File(args[1]);
                                      PhoneCall call = CreatePhoneCall(args); //Add the new phone call
                                      if (text_file.createNewFile()) {
                                          PhoneBill bill = new PhoneBill(args[2]); //Create a new phone bill
                                          bill.addPhoneCall(call);
                                          PrettyPrint dumper = new PrettyPrint(new FileWriter(text_file));
                                          dumper.dump(bill);
                                      }
                                  } catch (IOException | InvalidPhoneCallArgument e) {
                                      System.err.println(e.getMessage());
                                  }

                              }
                          }
                         //else{
                            //System.err.println("Invalid option");
                             //}
                      break;
                      }




                  //Normal command line arguments
                  case (9): {     /*Validate phone number*/

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
                      if ((!isValidTime(args[6],args[7])) || (!isValidTime(args[9],args[10]))) {
                          System.err.println("Invalid time");
                      }

                      PhoneBill phonebill = new PhoneBill(args[0]);
                      PhoneCall call = new PhoneCall(args[1], args[2], args[3], args[4], args[5], args[6],args[7],args[8]);  // Refer to one of Dave's classes so that we can be sure it is on the classpath

                      phonebill.addPhoneCall(call);
                      break;
                      /*for (String arg : args) {
                   System.out.println(arg);*/
                  }


              }


          } else {
              //Other case will default to fail to meet the valid argument
              System.err.println("Please check if your command line has the invalid: \n");
              System.err.println("Not enough argument or too many arguments.\n");
          }
      }
   }
 //Function to read and create the new phone bill with phone call
 //When the file exists
 @VisibleForTesting
 static PhoneBill ReadFile(File file_name) throws ParserException {
     try{
         TextParser parser = new TextParser(new FileReader(file_name));
         //Create a new phone bill with customer name.
         //Need the AddPhoneCallFromText function here
         return parser.parse();
     }
     catch (FileNotFoundException e){
         throw new ParserException("While parsing the phone bill test", e);
     }


 }
 //Function to write the new added phone call of phone bill to the text file
 @VisibleForTesting
 static void WritePhoneBillToTextFile(File file_name, PhoneBill bill) throws IOException {

          TextDumper dumper = new TextDumper(new FileWriter(file_name));
          dumper.dump(bill);

 }
//Function to validate the format of file name
//Make sure it ends with .txt
@VisibleForTesting
static boolean ValidateFileName(String file){
    String regex = ".*\\.txt";
    Pattern P = Pattern.compile(regex);
    if(file == null) return false;
    Matcher match = P.matcher(file);

    return match.matches();
}
//Function to check if the file exist.
@VisibleForTesting
static boolean ExistFile(String file_name){
      File file = new File(file_name);
      if (file.isFile()) return true;
      return false;

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

@VisibleForTesting
    //Extend from exception
    static class InvalidPhoneCallArgument extends Exception{
      public InvalidPhoneCallArgument(String message){
          super(message);
      }

}
//Function to validate each arg before create the phone call for text file purpose
@VisibleForTesting
    static PhoneCall CreatePhoneCall (String...args) throws InvalidPhoneCallArgument{
      if (!isValidPhoneNumber(args[3]) || !isValidPhoneNumber(args[4])){
          throw new InvalidPhoneCallArgument("Invalid phone number.");
      }
      else if (!isValidDate(args[5]) || !isValidDate(args[8])){
          throw new InvalidPhoneCallArgument("Invalid date.");
      }
      else if (!isValidTime(args[6],args[7]) || !isValidTime(args[9],args[10])){
          throw new InvalidPhoneCallArgument("Invalid time.");
      }
      else{
          return new PhoneCall(args[3],args[4],args[5],args[6],args[7], args[8],args[9],args[10]);
      }

}

}
