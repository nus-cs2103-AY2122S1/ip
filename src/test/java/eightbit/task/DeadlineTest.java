package eightbit.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DeadlineTest {

    private Deadline deadline;

    @BeforeEach
    void setUp() {
        String description = "NewDeadline";
        LocalDateTime dateTime = LocalDateTime.of(2021, 9, 15, 10, 30);
        deadline = new Deadline(description, dateTime);
    }

    @Test
    void testToString() {
        String expected = "[D][ ] NewDeadline (by: 15 Sep 2021 10:30)";
        assertEquals(expected, deadline.toString());
    }

    @AfterEach
    void tearDown() {
        deadline = null;
    }
}
