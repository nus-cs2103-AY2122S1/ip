package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.format.DateTimeParseException;

import org.junit.jupiter.api.Test;

public class DeadlineTest {

    @Test
    public void toString_correctFormat_success() {
        Deadline deadline = new Deadline("assignment /by 15-10-2021 2359");
        assertEquals(deadline.toString(), "[D][ ] assignment (by: Oct 15 2021 23:59)");
    }

    @Test
    public void toString_incorrectFormat_exceptionThrown() {
        try {
            Deadline deadline = new Deadline("assignment /by 15102021 2359");
        } catch (DateTimeParseException e) {
            assertEquals(e.getMessage(),
                    "Text '15102021 2359' could not be parsed at index 2");
        }
    }

    @Test
    public void toString_incorrectFormat2_exceptionThrown() {
        try {
            Deadline deadline = new Deadline("assignment /b 15102021 2359");
        } catch (DukeException e) {
            assertEquals(e.getMessage(),
                    "Please use the correct format! deadline <name> /by <date-time>");
        }
    }
}
