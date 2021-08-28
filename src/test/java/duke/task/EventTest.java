package duke.task;

import duke.DukeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class EventTest {
    @Test
    public void Event_missingDateTime_dukeExceptionThrown(){
        try {
            assertEquals(0, new Event(new String[]{"test"}));
            fail(); // the test should not reach this line
        } catch (DukeException e) {
            assertEquals("Usage of event does not match 'description' /at 'timeframe'", e.toString());
        }
    }
}
