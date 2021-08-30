import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.format.DateTimeParseException;

import org.junit.jupiter.api.Test;

import duke.task.Deadline;


/**
 * Test cases for Deadline.
 */
public class DeadlineTest {

    /**
     * Test constructing a Deadline object with only a date.
     */
    @Test
    public void deadline_dateOnly_correctStringRepresentation() {
        Deadline deadline = new Deadline("work", "2021-08-23");
        assertEquals("[D][ ] work (by: Aug 23 2021)", deadline.toString());
    }

    /**
     * Test constructing a Deadline object with both date and time.
     */
    @Test
    public void deadline_dateAndTime_correctStringRepresentation() {
        Deadline deadline = new Deadline("work", "2021-08-23", "18:00");
        assertEquals("[D][ ] work (by: Aug 23 2021 06:00PM)", deadline.toString());
    }

    /**
     * Test marking a Deadline object with only date as done.
     */
    @Test
    public void markDone_dateOnlyDeadline_correctStringRepresentation() {
        Deadline deadline = new Deadline("work", "2021-08-23");
        deadline.markDone();
        assertEquals("[D][X] work (by: Aug 23 2021)", deadline.toString());
    }

    /**
     * Test marking a Deadline object with both date and time as done.
     */
    @Test
    public void markDone_dateAndTimeDeadline_correctStringRepresentation() {
        Deadline deadline = new Deadline("work", "2021-08-23", "18:00");
        deadline.markDone();
        assertEquals("[D][X] work (by: Aug 23 2021 06:00PM)", deadline.toString());
    }

    /**
     * Invalid date test.
     */
    @Test
    public void deadline_invalidDate_dateTimeParseExceptionThrown() {
        assertThrows(DateTimeParseException.class, () -> {
            new Deadline("work", "2021-20-23");
        });
    }

    /**
     * Invalid time test.
     */
    @Test
    public void deadline_invalidTime_dateTimeParseExceptionThrown() {
        assertThrows(DateTimeParseException.class, () -> {
            new Deadline("work", "2021-08-23", "42:69");
        });
    }
}
