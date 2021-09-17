package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TaskTest {
    @Test
    public void testTaskToString() {
        assertEquals("[ ] placeholder",
                new Task("placeholder").toString());
    }

    @Test
    public void testTaskMarkAsDone() {
        Task temp = new Task("placeholder");
        temp.markAsDone();
        assertEquals("[X] placeholder", temp.toString());
    }
}
