package task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.task.Event;

public class EventTest {
    @Test
    public void toDataFormat() {
        Event event = new Event("Finish iP", "22 Aug 2021");
        assertEquals("E | 0 | Finish iP |  | 22 Aug 2021", event.toDataFormat());
    }

    @Test
    public void stringRepresentation() {
        Event event = new Event("Finish iP", "22 Aug 2021");
        assertEquals("[E][ ] Finish iP (at: 22 Aug 2021)", event.toString());
    }

    @Test
    public void dateParse() {
        Event event = new Event("Finish iP", "2021-11-12");
        assertEquals("E | 0 | Finish iP |  | Nov 12 2021", event.toDataFormat());
    }
}
