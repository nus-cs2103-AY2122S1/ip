package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    @Test
    public void testToDoCreation() {
        assertEquals("[T] [ ] read book", (new ToDo("read book")).toString());
    }

    @Test
    public void testDeadlineCreation() {
        assertEquals("[D] [ ] read book (by: Sunday)", (new Deadline("read book", "Sunday")).toString());
    }

    @Test
    public void testEventCreation() {
        assertEquals("[E] [ ] Dance class (at: Sunday)", (new Event("Dance class", "Sunday")).toString());
    }
}
