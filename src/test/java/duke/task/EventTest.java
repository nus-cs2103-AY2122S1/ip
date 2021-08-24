package duke.task;

import duke.task.Event;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class EventTest {

    @Test
    void testToString() {
        Event event = new Event("Project Meeting", LocalDate.parse("2013-12-04"));
        String actual = event.toString();

        assertEquals("[E][ ] Project Meeting (at: Dec 4 2013)", actual);
    }

    @Test
    void toSavedFormat() {
        Event event = new Event("Project Meeting", LocalDate.parse("2017-12-04"));
        String actual = event.toSavedFormat();

        assertEquals("Project Meeting/~/2017-12-04", actual);
    }
}