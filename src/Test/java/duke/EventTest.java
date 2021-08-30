package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class EventTest {
    @Test
    public void toString_correctFormat_success() {
        Event event = new Event("attend wedding /at some day to specify");
        assertEquals(event.toString(), "[E][ ] attend wedding (at: some day to specify)");
    }

    @Test
    public void toString_incorrectFormat_exceptionThrown() {
        try {
            Event event = new Event("attend wedding /ad some day to specify");
        } catch (DukeException e) {
            assertEquals(e.getMessage(),
                    "Please use the correct format! event <name> /at <text>");
        }
    }
}
