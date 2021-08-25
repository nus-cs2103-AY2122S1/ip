import duke.task.Event;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {

    @Test
    public void testStringConversion() {
        Event event = new Event("orientation", LocalDate.of(2021, 9, 1));
        assertEquals("[E][ ] orientation (at: Sep 1 2021)", event.toString());
    }

    @Test
    public void stringConversion_doneTask_indicated() {
        Event event = new Event("orientation", LocalDate.of(2021, 9, 1));
        event.markAsDone();
        assertEquals("[E][X] orientation (at: Sep 1 2021)", event.toString());
    }

    @Test
    public void testFileFormatConversion() {
        Event event = new Event("orientation", LocalDate.of(2021, 9, 1));
        assertEquals("E / 0 / orientation / 2021-09-01", event.toFileFormat());
    }

    @Test
    public void fileFormatConversion_doneTask_indicated() {
        Event event = new Event("orientation", LocalDate.of(2021, 9, 1));
        event.markAsDone();
        assertEquals("E / 1 / orientation / 2021-09-01", event.toFileFormat());
    }
}
