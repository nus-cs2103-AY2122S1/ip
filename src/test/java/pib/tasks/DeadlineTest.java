package pib.tasks;

import org.junit.jupiter.api.Test;
import pib.pibexception.PibException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class DeadlineTest {
    @Test
    public void createDeadline_nonBlankDescription_success() {
        Deadline deadline = Deadline.createDeadline("Quiz", 0, "25 Dec 2020", "11:59 pm");
        assertEquals("Quiz", deadline.getDescription());
        assertEquals(0, deadline.getIsDone());
    }

    @Test
    public void createDeadline_blankDescription_exceptionThrown() {
        try {
            Deadline deadline = Deadline.createDeadline("/by 2020-12-12 2300");
            fail();
        } catch (PibException e) {
            assertEquals("empty-task-description", e.getMessage());
        }
    }

    @Test
    public void createDeadline_wrongDateTimeFormat_exceptionThrown() {
        try {
            Deadline deadline = Deadline.createDeadline("Quiz /by 20201212 2300");
            fail();
        } catch (PibException e) {
            assertEquals("wrong-datetime-format", e.getMessage());
        }
    }
}
