package exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.exception.EmptyListException;

public class EmptyListExceptionTest {
    @Test
    public void testExceptionMessage() {
        assertEquals("The list is currently empty!", new EmptyListException().getMessage());
    }
}
