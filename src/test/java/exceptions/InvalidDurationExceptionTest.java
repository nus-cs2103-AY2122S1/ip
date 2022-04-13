package exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.exception.InvalidDurationException;

public class InvalidDurationExceptionTest {
    @Test
    public void testExceptionMessage() {
        assertEquals("Please define the duration of your event in the following format: "
                        + "YYYY/MM/DD HHMM - HHMM in the 24 hours format.",
                new InvalidDurationException().getMessage());
    }
}
