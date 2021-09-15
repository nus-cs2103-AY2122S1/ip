package duke.helpers;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

public class HelpersTest {

    @Test
    public void isInteger_validIntegerStrings_trueReturned() {
        String[] validIntegerStrings = { "1", "2", "3", "4", "5", "6", "7", "8", "100", "19101011" };

        for (String validIntegerString : validIntegerStrings) {
            assertTrue(Helpers.isInteger(validIntegerString));
        }
    }

    @Test
    public void isInteger_invalidIntegerStrings_falseReturned() {
        String[] invalidIntegerStrings = { "", "todo", "123@#", "Er5", "1!" };

        for (String invalidIntegerString : invalidIntegerStrings) {
            assertFalse(Helpers.isInteger(invalidIntegerString));
        }
    }
}