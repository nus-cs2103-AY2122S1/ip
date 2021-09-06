import org.junit.jupiter.api.Test;
import duke.tasks.Event;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EventTest {
    protected static final DateTimeFormatter formatted = DateTimeFormatter.ofPattern("MMM d yyyy HHmm");

    @Test
    public void createEventTest(){
        LocalDateTime startTime = LocalDateTime.parse("10-10-1010 1010", formatted);
        Event event = new Event("event hello/at 10-10-1010 1010", startTime);
        assertEquals("[E][ ] hello (at: Oct 10 1010 1010)", event.toString());
    }

    @Test
    public void completeEventTest() {
        LocalDateTime startTime = LocalDateTime.parse("10-10-1010 1010", formatted);
        Event event = new Event("event hello/at 10-10-1010 1010", startTime);
        event.markAsDone();
        assertEquals("[E][X] hello (at: Oct 10 1010 1010)", event.toString());
    }
}