package exceptions;

import duke.exception.IncorrectFormatException;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class IncorrectFormatExceptionTest {
    @Test
    public void testExceptionMessage() {
        assertEquals("Please use /at when you want to call the event command and" +
                        " include the date and time as YYYY/MM/DD and HHMM in 24 hour format.",
                new IncorrectFormatException("event", "/at").getMessage());
    }
}