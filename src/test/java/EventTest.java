import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import iris.task.Event;

public class EventTest {

    @Test
    public void eventGetEventTime() {
        String commandDetails = "project meeting /at 2021-08-31 15:00";
        int atIndex = commandDetails.indexOf("/at") - 1;
        String eventDetails = commandDetails.substring(0, atIndex);
        LocalDateTime at = LocalDateTime.parse(commandDetails.substring(commandDetails.indexOf("at") + 3),
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        Event event = new Event(eventDetails, false, at);
        assertEquals("(at: 31-08-2021 15:00)", event.getEventTime());
    }

    @Test
    public void eventGetStatus() {
        String commandDetails = "project meeting /at 2021-08-31 15:00";
        int atIndex = commandDetails.indexOf("/at") - 1;
        String eventDetails = commandDetails.substring(0, atIndex);
        LocalDateTime at = LocalDateTime.parse(commandDetails.substring(commandDetails.indexOf("at") + 3),
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        Event event = new Event(eventDetails, false, at);
        assertEquals("[E][ ] project meeting (at: 31-08-2021 15:00)", event.getStatus());
        event.markDone();
        assertEquals("[E][X] project meeting (at: 31-08-2021 15:00)", event.getStatus());
    }

    @Test
    public void eventToString() {
        String commandDetails = "project meeting /at 2021-08-31 15:00";
        int atIndex = commandDetails.indexOf("/at") - 1;
        String eventDetails = commandDetails.substring(0, atIndex);
        LocalDateTime at = LocalDateTime.parse(commandDetails.substring(commandDetails.indexOf("at") + 3),
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        Event event = new Event(eventDetails, false, at);
        assertEquals("E | 0 | project meeting | 2021-08-31T15:00", event.toString());
        event.markDone();
        assertEquals("E | 1 | project meeting | 2021-08-31T15:00", event.toString());
    }
}
