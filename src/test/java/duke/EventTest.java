package duke;

import duke.tasks.Event;
import duke.tasks.Todo;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {

    @Test
    public void testEvent() {
        Event event = new Event("Exam", "30 Aug 2021 09:00");
        assertEquals(event.toString(),"[E] [ ] Exam (at: 30 Aug 2021 09:00)");
    }
}
