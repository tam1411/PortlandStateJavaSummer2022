package edu.pdx.cs410J.tn27;

/**
 * Class for formatting messages on the server side.  This is mainly to enable
 * test methods that validate that the server returned expected strings.
 */
public class Messages
{
    public static String missingRequiredParameter( String parameterName )
    {
        return String.format("The required parameter \"%s\" is missing", parameterName);
    }

    public static String CustomerhasPhoneBill(String customer, PhoneBill new_bill )
    {
        return String.format( "%s : \n.%s", customer, new_bill.toString() );
    }

    public static String allDictionaryEntriesDeleted() {
        return "All dictionary entries have been deleted";
    }

}
