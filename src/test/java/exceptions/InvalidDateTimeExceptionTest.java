package exceptions;

import duke.exception.InvalidDateTimeException;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class InvalidDateTimeExceptionTest {
    @Test
    public void testExceptionMessage() {
        assertEquals("Your date or time format is wrong! " +
                "Please use the format YYYY/MM/DD HHMM where the time is in 24 hours.",
                new InvalidDateTimeException().getMessage());
    }
}