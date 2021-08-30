package exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.exception.InvalidDateTimeException;

public class InvalidDateTimeExceptionTest {
    @Test
    public void testExceptionMessage() {
        assertEquals("Your date or time format is wrong! "
                        + "Please use the format YYYY/MM/DD HHMM where the time is in 24 hours.",
                new InvalidDateTimeException().getMessage());
    }
}
