package kermit.tasks;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void testGetShortForm() {
        assertEquals("D",
                new Deadline("Test deadline", LocalDate.parse("2021-04-05")).getShortForm());
    }

    @Test
    public void testToString() {
        Deadline deadline = new Deadline("Test deadline", LocalDate.parse("2021-04-05"));
        assertEquals("[D] [] Test deadline (by: Apr 5 2021)",
                deadline.toString());
        deadline.markAsComplete();
        assertEquals("[D] [X] Test deadline (by: Apr 5 2021)",
                deadline.toString());
    }
}
