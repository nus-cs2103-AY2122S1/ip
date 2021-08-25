package exceptions;

import duke.exception.InvalidCommandException;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class InvalidCommandExceptionTest {
    @Test
    public void testExceptionMessage() {
        assertEquals("Sorry, I don't understand that command.",
                new InvalidCommandException().getMessage());
    }
}