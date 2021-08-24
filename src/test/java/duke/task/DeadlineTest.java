package duke.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void testToString() {
        Deadline deadline = new Deadline("do homework", LocalDate.parse("2021-08-21"), false);
        assertEquals("[D][ ] do homework (by: AUGUST 21 2021)", deadline.toString());
        deadline.markAsDone();
        assertEquals("[D][X] do homework (by: AUGUST 21 2021)", deadline.toString());
    }

    @Test
    public void testToDataString() {
        Deadline deadline = new Deadline("do homework", LocalDate.parse("2021-08-21"), false);
        assertEquals("D | 0 | do homework | 2021-08-21", deadline.toDataString());
        deadline.markAsDone();
        assertEquals("D | 1 | do homework | 2021-08-21", deadline.toDataString());
    }
}
