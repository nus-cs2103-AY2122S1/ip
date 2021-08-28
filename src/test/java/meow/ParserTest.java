package meow;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ParserTest {
    @Test
    public void convertToLocalDate_invalidDate_exceptionThrown() {
        try {
            assertEquals("2020-13-70",
                    new Parser().convertToLocalDate("70/13/2020").toString());
            fail();
            // the test should not reach this line
        } catch (InvalidDateTimeException e) {
            String errorMessage = "------------------------------------------------------------------------------\n"
                    +
                    "Meow: The format of your deadline is invalid, please try again~\n"
                    +
                    "------------------------------------------------------------------------------";
            assertEquals(errorMessage,
                    e.toString());
        }
    }

    private void fail() {
        System.out.println("Your test has failed.");
        Assertions.fail();
    }
}
