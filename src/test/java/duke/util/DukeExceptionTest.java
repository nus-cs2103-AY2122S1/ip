package duke.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DukeExceptionTest {
    @Test
    public void testExceptionMessage() {
        assertEquals("Hello world", new DukeException("Hello world").getMessage());
    }
}
