package duke.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ToDoTest {
    @Test
    public void testToString() {
        assertEquals("[T][ ] task 1", new ToDo("task 1").toString());
    }

    @Test
    public void testToSaveString() {
        assertEquals("| T | 0 | task 1", new ToDo("task 1").toSaveString());
    }
}
