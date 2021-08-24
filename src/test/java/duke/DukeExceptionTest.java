package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeExceptionTest {
    @Test
    public void exceptionTest() {
        DukeException e = new DukeException("Test exception");
        assertEquals("Test exception", e.getMessage());
    }
}
