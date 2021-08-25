package exceptions;

import duke.exception.EmptyListException;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmptyListExceptionTest {
    @Test
    public void testExceptionMessage() {
        assertEquals("The list is currently empty!", new EmptyListException().getMessage());
    }
}