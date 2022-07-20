This is a README file!

Tam Nguyen

Content of the program
1. The program is about pretty print to file with new concept of phone bill.
2.The program also focuses on print the information provided
on the command line argument nicely.
3.In order to add a phone call to the phone bill, user has to
put valid information in the correct format and order.
4.If there is any error in the input, the program will displace
an error message and exit.
5. This is how the command line arguments will need to look like.
"usage: java -jar target/phonebill-2022.0.0.jar [options] <args>\n" +
                  "args are (in this order):\n" +
                  "customer: Person whose phone bill we arere modeling\n" +
                  "callerNumber: Phone number of caller\n" +
                  "calleeNumber: Phone number of person who was called\n" +
                  "begin Date and time call began (12-hour time "am/pm")\n" +
                  "end Date and time call ended (12-hour time " am/pm")\n" +
                  "options are (options may appear in any order):\n" +
                  "-textFile file : Where to read/write the phone bill\n" +
                  "-print : Prints a description of the new phone call\n" +
                  "-README : Prints a README for this project and exits"