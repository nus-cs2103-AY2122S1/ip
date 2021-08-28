package iris;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import iris.task.Event;

public class EventTest {
    @Test
    public void constructor_validArguments_success() throws IrisException {
        Event event = new Event("test event", "2021-08-23");
        assertEquals("[E][ ] test event (at: Aug 23 2021)", event.toString());
    }

    @Test
    public void constructor_invalidDate_exceptionThrown() {
        try {
            new Event("test event", "Tuesday");
            fail();
        } catch (IrisException exception) {
            assertEquals("Invalid date provided.", exception.getMessage());
        }
    }

    @Test
    public void markComplete_success() throws IrisException {
        Event event = new Event("test event", "2021-08-23");
        event.markDone();
        assertEquals("[E][X] test event (at: Aug 23 2021)", event.toString());
    }

    @Test
    public void toCommand_incomplete_success() throws IrisException {
        Event event = new Event("test event", "2021-08-23");
        assertEquals("event test event /at 2021-08-23\n", event.toCommand(1));
    }

    @Test
    public void toCommand_completed_success() throws IrisException {
        Event event = new Event("test event", "2021-08-23");
        event.markDone();
        assertEquals("event test event /at 2021-08-23\ndone 1\n", event.toCommand(1));
    }
}
