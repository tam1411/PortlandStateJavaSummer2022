package edu.pdx.cs410J.tn27;

import edu.pdx.cs410J.InvokeMainTestCase;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Tests the functionality in the {@link project2} main class.
 */
class project2IT extends InvokeMainTestCase {

    /**
     * Invokes the main method of {@link project2} with the given arguments.
     */
    private MainMethodResult invokeMain(String... args) {
        return invokeMain(project2.class, args);
    }

    /**
     * Tests that invoking the main method with no arguments issues an error
     */
    @Test
    void testNoCommandLineArguments() {
        InvokeMainTestCase.MainMethodResult result = invokeMain(project2.class);
        assertThat(result.getTextWrittenToStandardError(), containsString("Missing command line arguments\n"));
    }


}