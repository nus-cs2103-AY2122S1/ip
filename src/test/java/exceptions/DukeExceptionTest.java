package exceptions;

import duke.exception.DukeException;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeExceptionTest {
    @Test
    public void testExceptionMessage() {
        assertEquals("Hello world!", new DukeException("Hello world!").getMessage());
    }
}