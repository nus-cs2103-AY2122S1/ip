import duke.task.Deadline;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class DeadlineTest {
    @Test
    public void constructor_validDate_dateReturned() {
        LocalDate date = LocalDate.now();
        Deadline ddl = new Deadline("des", date.toString());
        assertEquals(ddl.toString(), "[D][ ] des (by: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")");
        assertEquals(ddl.populateSaveData(), "D | 0 | des | " + date.toString());

        ddl = new Deadline("des", date.toString(), true);
        assertEquals(ddl.toString(), "[D][X] des (by: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")");
        assertEquals(ddl.populateSaveData(), "D | 1 | des | " + date.toString());

        ddl = new Deadline("des", date.toString(), false);
        assertEquals(ddl.toString(), "[D][ ] des (by: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")");
        assertEquals(ddl.populateSaveData(), "D | 0 | des | " + date.toString());
    }

    @Test
    public void equals_validDate_test() {
        LocalDate date = LocalDate.now();
        assertEquals(new Deadline("des", date.toString()), new Deadline("des", date.toString()));
        assertEquals(new Deadline("des", date.toString(), true), new Deadline("des", date.toString(), true));

        assertNotEquals(new Deadline("des", date.toString()), new Deadline("de", date.toString()));
        assertNotEquals(new Deadline("des", date.toString()), new Deadline("des", date.minusDays(1L).toString()));

        assertNotEquals(new Deadline("des", date.toString(), true), new Deadline("de", date.toString(), true));
        assertNotEquals(new Deadline("des", date.toString(), true), new Deadline("des", date.toString(), false));
        assertNotEquals(new Deadline("des", date.toString(), false), new Deadline("des", date.minusDays(1L).toString(), false));
    }
}
