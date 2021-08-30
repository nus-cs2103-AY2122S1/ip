package exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.exception.InvalidCommandException;

public class InvalidCommandExceptionTest {
    @Test
    public void testExceptionMessage() {
        assertEquals("Sorry, I don't understand that command.",
                new InvalidCommandException().getMessage());
    }
}
