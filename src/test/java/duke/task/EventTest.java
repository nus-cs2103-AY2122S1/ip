package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void createEventTest() {
        Task t = new Event("event eat /at 2021-12-06");
        assertEquals("[E][ ] eat (at: Dec 6 2021)", t.toString());
    }

    @Test
    public void doneEventTest() {
        Task t = new Event("event eat /at 2021-12-06");
        t.setDone();
        assertEquals("[E][X] eat (at: Dec 6 2021)", t.toString());
    }
}