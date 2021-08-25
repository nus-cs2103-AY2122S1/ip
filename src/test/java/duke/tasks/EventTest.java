package duke.tasks;

import duke.DukeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class EventTest {
    @Test
    public void invalidAt() {
        assertThrows(DukeException.class, () ->
                new Event("event", "9:14"));
    }
}
