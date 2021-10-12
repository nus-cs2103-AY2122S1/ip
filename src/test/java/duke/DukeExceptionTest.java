package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.exception.DukeException;

public class DukeExceptionTest {

    @Test
    public void exception_message_correct() {
        DukeException e = new DukeException("Test Test");
        assertEquals("Test Test", e.getMessage());
    }
}
