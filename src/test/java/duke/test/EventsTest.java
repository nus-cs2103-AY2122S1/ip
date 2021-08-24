package duke.test;
import duke.Events;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * A class that test functionality of an event.
 *
 */
public class EventsTest {
    /**
     * Method that test writeTask() in Event class.
     *
     */
    @Test
    public void testWriteTask() {
        Events t = new Events("Task 1" , "12pm");
        assertEquals("E | 0 | Task 1 | 12pm", t.writeTask());
    }
}
