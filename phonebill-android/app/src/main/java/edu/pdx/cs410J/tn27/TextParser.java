package edu.pdx.cs410J.tn27;

import static edu.pdx.cs410J.tn27.validateinfo.isValidDate;
import static edu.pdx.cs410J.tn27.validateinfo.isValidTime;

import android.annotation.SuppressLint;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

import edu.pdx.cs410J.ParserException;
import edu.pdx.cs410J.PhoneBillParser;

public class TextParser implements PhoneBillParser<PhoneBill> {
    private final Reader reader;

    public TextParser(Reader reader) {
        this.reader = reader;
    }

    @SuppressLint("VisibleForTests")
    @Override
    public PhoneBill parse() throws ParserException {
        try (
                BufferedReader br = new BufferedReader(this.reader)
        ) {

            String customer = br.readLine();
            PhoneBill bill =  new PhoneBill(customer);


            if (customer == null) {
                throw new ParserException("Missing customer");
            }
            String line = br.readLine(); //Read the second line

            while (line != null) {
                //Function to split the String line into array
                String[] array = SplitStringLine(line);
                /* Add each phone call to phone bill */
                for (String s : array) {
                    if (s == null) {
                        throw new ParserException("Missing phone call information");
                    }
                }

                if (isValidDate(array[2])|| isValidDate(array[5])){
                    throw new ParserException("can't parse the file with invalid date");
                }
                if (isValidTime(array[3],array[4]) || isValidTime(array[6],array[7])){
                    throw new ParserException(("can't parse the file with invalid time"));
                }
                PhoneCall call = new PhoneCall(array[0], array[1], array[2], array[3], array[4],
                        array[5],array[6],array[7]);
                bill.addPhoneCall(call);

                //Read the next line until it's the end of file
                line = br.readLine();
            }
            return bill ;

        } catch (IOException e) {
            throw new ParserException("While parsing phone bill text", e);
        }
    }


    //Function to split the string array by space delimiter and
//return a string array
    public String[] SplitStringLine(String line){

        return line.split(" ");
    }
}
