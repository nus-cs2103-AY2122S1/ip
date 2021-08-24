package duke.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class DukeExceptionTest {
    @Test
    public void constructor_success() {
        assertEquals("test message", new DukeException("test message").getMessage());
    }
}
