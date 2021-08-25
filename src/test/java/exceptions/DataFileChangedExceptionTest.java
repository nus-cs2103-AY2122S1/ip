package exceptions;

import duke.exception.DataFileChangedException;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DataFileChangedExceptionTest {
    @Test
    public void testExceptionMessage() {
        assertEquals("Contents of file have been changed! Resetting the list!", new DataFileChangedException().getMessage());
    }
}
