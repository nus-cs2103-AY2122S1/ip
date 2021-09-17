import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import pats.task.Deadline;
import pats.task.Task;

public class DeadlineTest {
    @Test
    public void constructor_validDate_dateReturned() {
        LocalDate date = LocalDate.now();
        Deadline ddl = Task.getDeadline("des", date.toString());
        assertEquals(ddl.toString(), "[D][ ] des (by: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")");
        assertEquals(ddl.populateSaveData(), "D | 0 | des | " + date.toString());

        ddl = Task.getDeadline("des", date.toString(), true);
        assertEquals(ddl.toString(), "[D][X] des (by: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")");
        assertEquals(ddl.populateSaveData(), "D | 1 | des | " + date.toString());

        ddl = Task.getDeadline("des", date.toString(), false);
        assertEquals(ddl.toString(), "[D][ ] des (by: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")");
        assertEquals(ddl.populateSaveData(), "D | 0 | des | " + date.toString());
    }

    @Test
    public void equals_validDate_test() {
        LocalDate date = LocalDate.now();
        assertEquals(Task.getDeadline("des", date.toString()), Task.getDeadline("des", date.toString()));
        assertEquals(Task.getDeadline("des", date.toString(), true), Task.getDeadline("des", date.toString(), true));

        assertNotEquals(Task.getDeadline("des", date.toString()), Task.getDeadline("de", date.toString()));
        assertNotEquals(Task.getDeadline("des", date.toString()),
                Task.getDeadline("des", date.minusDays(1L).toString()));

        assertNotEquals(Task.getDeadline("des", date.toString(), true), Task.getDeadline("de", date.toString(), true));
        assertNotEquals(Task.getDeadline("des", date.toString(), true),
                Task.getDeadline("des", date.toString(), false));
        assertNotEquals(Task.getDeadline("des", date.toString(), false),
                Task.getDeadline("des", date.minusDays(1L).toString(), false));
    }
}
