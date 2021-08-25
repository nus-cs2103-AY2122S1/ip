package duke.exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeExceptionTest {
    @Test
    public void constructor_success() {
        assertEquals("message", new DukeException("message").getMessage());
    }
}
