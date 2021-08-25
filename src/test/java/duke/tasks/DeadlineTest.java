package duke.tasks;

import duke.DukeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class DeadlineTest {
    @Test
    public void invalidBy() {
        assertThrows(DukeException.class, () ->
                new Deadline("task", "23 aug 2021"));
    }
}
