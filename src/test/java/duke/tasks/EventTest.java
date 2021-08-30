package duke.tasks;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import duke.DukeException;



public class EventTest {
    @Test
    public void invalidAt() {
        assertThrows(DukeException.class, () ->
                new Event("event", "9:14"));
    }
}
