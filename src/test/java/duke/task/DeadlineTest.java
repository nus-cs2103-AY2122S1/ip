package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.DateTimeException;

import org.junit.jupiter.api.Test;

public class DeadlineTest {

    @Test
    public void createDeadline() {
        String desc = "TEST DEADLINE";
        Task task = Deadline.of(false, desc, "2020-02-02");
        assertEquals(desc, task.getDescription());
        assertEquals(" ", task.getStatusIcon());
        task = Deadline.of(true, desc, "2020-02-02");
        assertEquals("X", task.getStatusIcon());
    }

    @Test
    public void completeDeadline() {
        String desc = "TEST DEADLINE";
        Task task = Deadline.of(false, desc, "2020-02-02");
        task.markDone();
        assertTrue(task.isDone());
    }

    @Test
    public void makeDeadlineStrings() {
        String desc = "TEST DEADLINE";
        Task task = Deadline.of(false, desc, "2020-02-02");

        assertEquals("[D][ ] TEST DEADLINE (by: Feb 2 2020)", task.toString());
        assertEquals("D|0|TEST DEADLINE|2020-02-02", task.toStorageString());
        task.markDone();
        assertEquals("[D][X] TEST DEADLINE (by: Feb 2 2020)", task.toString());
        assertEquals("D|1|TEST DEADLINE|2020-02-02", task.toStorageString());
    }

    @Test
    public void createDeadline_invalidDate_exceptionThrown() {
        String desc = "TEST DEADLINE";
        assertThrows(DateTimeException.class, () -> Deadline.of(false, desc, "NOT VALID DATE"));
    }

}
