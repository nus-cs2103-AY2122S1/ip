package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    @Test
    public void toDataString() {
        Deadline deadline = new Deadline("do homework", LocalDate.parse("2021-08-21"), false);
        assertEquals("D | 0 | do homework | 2021-08-21", deadline.toDataString());
        deadline.markAsDone();
        assertEquals("D | 1 | do homework | 2021-08-21", deadline.toDataString());
    }
}
