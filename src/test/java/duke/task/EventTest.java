package duke.task;

import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void testEvent() {
        LocalDateTime at = LocalDateTime.parse("2021-08-30T13:55");
        Event event = new Event("meeting", at);
        assertEquals("[E][ ] meeting (at: 08 30 2021 at 01:55)", event.toString());
    }
}
