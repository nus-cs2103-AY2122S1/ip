package exceptions;

import duke.exception.EmptyCommandException;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmptyCommandExceptionTest {
    @Test
    public void testExceptionMessage() {
        assertEquals("You left the field blank!", new EmptyCommandException().getMessage());
    }
}