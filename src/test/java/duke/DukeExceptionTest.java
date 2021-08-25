package duke;

import duke.exception.DukeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeExceptionTest {

    @Test
    public void exception_message_correct() {
        DukeException e = new DukeException("Test Test");
        assertEquals("Test Test", e.getMessage());
    }
}
