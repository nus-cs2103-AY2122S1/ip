package kermit.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    /**
     * Tests to get short form for Deadline.
     */
    @Test
    public void testGetShortForm() {
        assertEquals("D",
                new Deadline("Test deadline", LocalDate.parse("2021-04-05")).getShortForm());
    }

    /**
     * Tests to string representation of deadline.
     */
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
