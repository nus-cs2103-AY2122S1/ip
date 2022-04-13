package exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.exception.DataFileChangedException;

public class DataFileChangedExceptionTest {
    @Test
    public void testExceptionMessage() {
        assertEquals("Contents of file have been changed! Resetting the list!",
                new DataFileChangedException().getMessage());
    }
}
