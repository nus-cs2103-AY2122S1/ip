package duke;

import duke.task.Event;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {

    @Test
    public void testGetAt() {
        String input = "event project meeting /at Aug 20 2020 18:00";
        Parser parser = new Parser();
        String time = parser.getEventTime(input);
        String desc = parser.getEventDescription(input);
        Event event = new Event(desc, time);
        assertEquals("Aug 20 2020 18:00", event.getAt());
    }

    @Test
    public void testToString(){
        String input = "event project meeting /at Aug 20 2020 18:00";
        Parser parser = new Parser();
        String time = parser.getEventTime(input);
        String desc = parser.getEventDescription(input);
        Event event = new Event(desc, time);
        assertEquals("[E][ ] project meeting (at: Aug 20 2020 18:00)", event.toString());
    }

    @Test
    public void testIsDone(){
        String input = "event project meeting /at Aug 20 2020 18:00";
        Parser parser = new Parser();
        String time = parser.getEventTime(input);
        String desc = parser.getEventDescription(input);
        Event event = new Event(desc, time);
        assertEquals(false, event.isDone());
    }

}
