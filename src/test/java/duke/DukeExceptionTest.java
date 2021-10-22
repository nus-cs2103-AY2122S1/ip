package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DukeExceptionTest {
    @Test
    public void exceptionTest() {
        DukeException e = new DukeException("Test exception");
        assertEquals("Test exception", e.getMessage());
    }
}
