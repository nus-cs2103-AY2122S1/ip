package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    @Test
    public void testToString() {
        ToDo task = new ToDo("read book");
        assertEquals("[T][ ] read book", task.toString());
    }

    @Test
    public void testSaveAsString() {
        ToDo task = new ToDo("1", "read book");
        assertEquals("T | 1 | read book", task.saveAsString());
    }

    @Test
    public void testGetStatusIcon() {
        ToDo task = new ToDo("1", "read book");
        assertEquals("X", task.getStatusIcon());
    }

    @Test
    public void testMarkAsDone() {
        ToDo task = new ToDo("1", "read book");
        task.markTaskAsDone();
        assertEquals("[T][X] read book", task.toString());
    }

    @Test
    public void testOnDate() {
        ToDo task = new ToDo("read book");
        assertEquals(false, task.isOnDate("2021-08-23"));
    }
}
