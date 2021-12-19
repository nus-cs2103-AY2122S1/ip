package data.task;

import jwbot.data.task.Deadline;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void getByTest() {
        Deadline deadline = new Deadline("Study", "2012-12-24");
        assertEquals(LocalDate.parse("2012-12-24"), deadline.getBy());
    }
}
