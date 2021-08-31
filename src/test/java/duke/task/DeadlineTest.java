package duke.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

public class DeadlineTest {

    @Test
    public void build_validDescriptionAndTime_returnDeadline() {
        Deadline dl = Deadline.build("test (by: Feb 02 2000)");
        assertEquals(dl.description, "test");
        assertEquals(dl.date, LocalDate.parse("2000-02-02"));
    }

}
