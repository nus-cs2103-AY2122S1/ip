package jared.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TaskTest {
    /**
     * Test string conversion of Task.
     */
    @Test
    public void testStringConversion() {
        Task t = new Task("exercise");
        assertEquals("[ ] exercise", t.toString());
        t.markDone();
        assertEquals("[X] exercise", t.toString());
    }
}
