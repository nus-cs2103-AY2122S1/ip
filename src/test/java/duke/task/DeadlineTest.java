package duke.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {

    @Test
    public void build_validDescriptionAndTime_returnDeadline() {
        Deadline dl = Deadline.build("test /by 2000-02-02");
        assertEquals(dl.description, "test");
        assertEquals(dl.dt, LocalDate.parse("2000-02-02"));
    }

}
