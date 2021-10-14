package pib.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import pib.pibexception.PibException;

public class DeadlineTest {
    @Test
    public void createDeadline_nonBlankDescription_success() {
        Deadline deadline = Deadline.createDeadline("Quiz", 0, "25 Dec 2020", "11:59 pm", true);
        assertEquals("Quiz", deadline.getDescription());
        assertEquals(0, deadline.getIsDone());
    }

    @Test
    public void createDeadline_blankDescription_exceptionThrown() {
        try {
            Deadline deadline = Deadline.createDeadline("/by 2020-12-12 2300", true);
            fail();
        } catch (PibException e) {
            assertEquals("empty-task-description", e.getMessage());
        }
    }

    @Test
    public void createDeadline_wrongDateTimeFormat_exceptionThrown() {
        try {
            Deadline deadline = Deadline.createDeadline("Quiz /by 20201212 2300", true);
            fail();
        } catch (PibException e) {
            assertEquals("wrong-datetime-format", e.getMessage());
        }
    }
}
