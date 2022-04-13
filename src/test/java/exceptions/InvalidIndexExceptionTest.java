package exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.exception.InvalidIndexException;

public class InvalidIndexExceptionTest {
    @Test
    public void testExceptionMessage() {
        assertEquals("You entered an invalid task number of 7. "
                        + "Please enter a task number from 1 to 6.",
                new InvalidIndexException(1, 6, 7).getMessage());
    }
}
