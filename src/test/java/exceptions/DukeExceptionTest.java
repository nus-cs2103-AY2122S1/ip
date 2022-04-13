package exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.exception.DukeException;

public class DukeExceptionTest {
    @Test
    public void testExceptionMessage() {
        assertEquals("Hello world!", new DukeException("Hello world!").getMessage());
    }
}
