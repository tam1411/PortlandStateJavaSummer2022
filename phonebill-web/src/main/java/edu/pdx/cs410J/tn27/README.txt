This is a README file!

Tam Nguyen

Content of the program
Program focuses on the interaction between Java program and web server.
When user type on the command line some valid information such as customer name, phone calls numbers,
date, time, the program will support web browser to get and post information.
Here is the usage on how user can type in.
usage: java -jar target/phonebill-2022.0.0.jar [options] <args>
args are (in this order):
customer         Person whose phone bill we’re modeling
callerNumber     Phone number of caller
calleeNumber     Phone number of person who was called
begin            Date and time call began
end              Date and time call ended
options are (options may appear in any order):
-host hostname   Host computer on which the server runs
-port port       Port on which the server is listening
-search          Phone calls should be searched for
-print           Prints a description of the new phone call
-README          Prints a README for this project and exits

Any errors in the arguments will lead to stop the program.