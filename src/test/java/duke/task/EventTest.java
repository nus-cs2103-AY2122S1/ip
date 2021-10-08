package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class EventTest {
    @Test
    public void toDataString() {
        Event event = new Event("project meeting", LocalDate.parse("2021-08-21"), false);
        assertEquals("E | 0 | project meeting | 2021-08-21", event.toDataString());
        event.markAsDone();
        assertEquals("E | 1 | project meeting | 2021-08-21", event.toDataString());
    }
}
