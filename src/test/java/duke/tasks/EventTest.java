package duke.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import duke.DukeException;



public class EventTest {
    @Test
    public void invalidAt() {
        assertThrows(DukeException.class, () ->
                new Event("event", "9:14"));
    }

    @Test
    public void createEvent() throws DukeException {
        assertEquals("[E][ ] movie night (at: 18 Sep 2021)", new Event("movie night", "2021-09-18").toString());
    }
}
