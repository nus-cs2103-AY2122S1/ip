import duke.task.Deadline;
import org.junit.jupiter.api.Test;

import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DeadlineTest {

    @Test
    public void dateOnlyTest() {
        Deadline deadline = new Deadline("work", "2021-08-23");
        assertEquals(deadline.toString(), "[D][ ] work (by: Aug 23 2021)");
    }

    @Test
    public void dateTimeTest() {
        Deadline deadline = new Deadline("work", "2021-08-23", "18:00");
        assertEquals(deadline.toString(), "[D][ ] work (by: Aug 23 2021 06:00pm)");
    }

    @Test
    public void dateOnlyMarkDoneTest() {
        Deadline deadline = new Deadline("work", "2021-08-23");
        deadline.markDone();
        assertEquals(deadline.toString(), "[D][X] work (by: Aug 23 2021)");
    }

    @Test
    public void dateTimeMarkDoneTest() {
        Deadline deadline = new Deadline("work", "2021-08-23", "18:00");
        deadline.markDone();
        assertEquals(deadline.toString(), "[D][X] work (by: Aug 23 2021 06:00pm)");
    }

    @Test
    public void invalidDateTest() {
        assertThrows(DateTimeParseException.class, () -> {
            Deadline deadline = new Deadline("work", "2021-20-23");
        });
    }

    @Test
    public void invalidTimeTest() {
        assertThrows(DateTimeParseException.class, () -> {
            Deadline deadline = new Deadline("work", "2021-08-23", "42:69");
        });
    }
}
