package duke.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import duke.DukeException;

public class DeadlineTest {
    @Test
    public void invalidBy() {
        assertThrows(DukeException.class, () ->
                new Deadline("task", "23 aug 2021"));
    }

    @Test
    public void createDeadline() throws DukeException {
        assertEquals("[D][ ] iP (by: 23:59)", new Deadline("iP", "23:59").toString());
    }
}
