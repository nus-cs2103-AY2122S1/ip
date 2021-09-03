package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    @Test
    public void toStringTest() {
        Deadline deadline = new Deadline("deadline description", LocalDate.parse("2021-12-12"));
        assertEquals("[D][ ] deadline description (by: Dec 12 2021)", deadline.toString());
    }

    @Test
    public void toStringData() {
        Deadline deadline = new Deadline("deadline description", LocalDate.parse("2021-12-12"));
        assertEquals("D| |deadline description|2021-12-12", deadline.toStringData());
    }

    @Test
    public void getDueDate() {
        Deadline deadline = new Deadline("deadline description", LocalDate.parse("2021-12-12"));
        assertEquals(LocalDate.parse("2021-12-12"), deadline.getDueDate());
    }
}
