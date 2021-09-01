import duke.task.Deadline;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void constructor_validDate_dateReturned() {
        LocalDate date = LocalDate.now();
        Deadline ddl = new Deadline("des", date.toString());
        assertEquals(ddl.toString(), "[D][ ] des (by: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")");
        assertEquals(ddl.populateSaveData(), "D | 0 | des | 2021-09-01");

        ddl = new Deadline("des", date.toString(), true);
        assertEquals(ddl.toString(), "[D][X] des (by: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")");
        assertEquals(ddl.populateSaveData(), "D | 1 | des | 2021-09-01");

        ddl = new Deadline("des", date.toString(), false);
        assertEquals(ddl.toString(), "[D][ ] des (by: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")");
        assertEquals(ddl.populateSaveData(), "D | 0 | des | 2021-09-01");
    }
}
