package lifeline.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;

public class DeadlineTest {

    @Test
    public void testNewDeadlineIsDoneIsFalse() {
        Deadline deadline = new Deadline("project", LocalDate.now(), LocalTime.now());
        assertFalse(deadline.isDone());
    }

    @Test
    public void testDeadlineGetName() {
        Deadline deadline = new Deadline("project", LocalDate.now(), LocalTime.now());
        assertEquals("project", deadline.getName());
    }

    @Test
    public void testDeadlineToString() {
        Deadline deadline = new Deadline("project", LocalDate.of(2021, 3, 4),
                LocalTime.of(18, 30));
        assertEquals("[D][ ] project (by: Mar 4 2021 6:30 PM)", deadline.toString());
    }

    @Test
    public void testDeadlineToggleIsDone() {
        Deadline deadline = new Deadline("project", LocalDate.now(), LocalTime.now());
        assertFalse(deadline.isDone());
        deadline.setDone(true);
        assertTrue(deadline.isDone());
    }
}
