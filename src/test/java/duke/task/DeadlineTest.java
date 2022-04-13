package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.DukeException;

public class DeadlineTest {
    private final String errorHeader = "(O_O;) Oh no!! ";

    @Test
    public void init_emptyDescription_throwException() {
        try {
            new Deadline("", "25-08-2021 14:30");
            fail();
        } catch (DukeException e) {
            assertEquals(errorHeader + "Looks like you forgot to include a description of the deadline.", e.toString());
        }
    }

    @Test
    public void init_emptyBy_throwException() {
        try {
            new Deadline("task", "");
            fail();
        } catch (DukeException e) {
            assertEquals("(O_O;) Oh no!! Looks like you forgot to include a deadline for the task.", e.toString());
        }
    }

    @Test
    public void init_invalidDateTime_throwException() {
        try {
            new Deadline("task", "2pm");
            fail();
        } catch (DukeException e) {
            assertEquals(errorHeader + "The deadline date is invalid. Please follow this format: dd-MM-yyyy HH:mm",
                e.toString());
        }
    }

    @Test
    public void testToString() {
        try {
            Deadline deadline = new Deadline("task", "25-08-2021 14:30");

            assertEquals("[D][ ] task (by: Aug 25 2021, 02:30 pm)", deadline.toString());
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void testDataString() {
        try {
            Deadline deadline = new Deadline("task", "25-08-2021 14:30");

            assertEquals("D|0|task|25-08-2021 14:30", deadline.toDataString("|"));
        } catch (DukeException e) {
            fail();
        }
    }
}
