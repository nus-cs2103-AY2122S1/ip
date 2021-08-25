package jared.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {

    @Test
    public void testStringConversion() {
        Task t =  new Task("exercise");
        assertEquals("[ ] exercise", t.toString());
        t.markDone();
        assertEquals("[X] exercise", t.toString());
    }
}
