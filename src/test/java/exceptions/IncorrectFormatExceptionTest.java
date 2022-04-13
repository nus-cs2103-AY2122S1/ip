package exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.exception.IncorrectFormatException;

public class IncorrectFormatExceptionTest {
    @Test
    public void testExceptionMessage() {
        assertEquals("Please use /at when you want to call the event command and"
                        + " include the date and time as YYYY/MM/DD and HHMM in 24 hour format.",
                new IncorrectFormatException("event", "/at").getMessage());
    }
}
