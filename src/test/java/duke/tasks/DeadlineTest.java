package duke.tasks;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import duke.DukeException;

public class DeadlineTest {
    @Test
    public void invalidBy() {
        assertThrows(DukeException.class, () ->
                new Deadline("task", "23 aug 2021"));
    }
}
