package exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.exception.EmptyCommandException;

public class EmptyCommandExceptionTest {
    @Test
    public void testExceptionMessage() {
        assertEquals("You left the field blank!", new EmptyCommandException().getMessage());
    }
}
