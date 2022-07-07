package edu.pdx.cs410J.tn27;

import edu.pdx.cs410J.InvokeMainTestCase;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

/**
 * Tests the functionality in the {@link Project1} main class.
 */
class Project1IT extends InvokeMainTestCase {

    /**
     * Invokes the main method of {@link Project1} with the given arguments.
     */
    private MainMethodResult invokeMain(String... args) {
        return invokeMain( Project1.class, args );
    }

  /**
   * Tests that invoking the main method with no arguments issues an error
   */
  @Test
  void testNoCommandLineArguments() {
    InvokeMainTestCase.MainMethodResult result = invokeMain(Project1.class);
    assertThat(result.getTextWrittenToStandardError(),containsString("Missing command line arguments\n"));
  }
    @Test
    void NotEnoughArguments(){
        MainMethodResult result = invokeMain(Project1.class, "Name");
        assertThat(result.getTextWrittenToStandardError(),equalTo("Not enough arguments.\n"));
    }
}