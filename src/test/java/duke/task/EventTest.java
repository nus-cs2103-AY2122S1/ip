package duke.task;

import duke.util.DukeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void createEventTest() throws DukeException {
        Task t = new Event("event eat /at 2021-12-06");
        assertEquals("[E][ ] eat (at: Dec 6 2021)", t.toString());
    }

    @Test
    public void doneEventTest() throws DukeException {
        Task t = new Event("event eat /at 2021-12-06");
        t.setDone();
        assertEquals("[E][X] eat (at: Dec 6 2021)", t.toString());
    }
}