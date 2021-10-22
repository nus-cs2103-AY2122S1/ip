import org.junit.jupiter.api.Test;
import duke.task.Event;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void addEventTest() {
        assertEquals("[E][ ] eventOne (at: Jan 12 2020)",
                new Event("eventOne", "2020-01-12").toString());
    }
}
