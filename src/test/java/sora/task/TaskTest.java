package sora.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class TaskTest {
    @Test
    public void constructor_success() {
        Task task = new Task("test task");

        String expected = "[ ] test task";
        assertEquals(expected, task.toString());
    }

    @Test
    public void markAsDone_success() {
        Task task = new Task("test task");
        task.markAsDone();

        String expected = "[X] test task";
        assertEquals(expected, task.toString());
    }

    @Test
    public void matchDescription_success() {
        Task task = new Event("test task", null, null, null);

        assertTrue(task.match("test"));
        assertTrue(task.match("task"));
        assertTrue(task.match("t"));

        assertFalse(task.match("apple"));
        assertFalse(task.match("banana"));
        assertFalse(task.match("tet"));

        assertFalse(task.match("todo"));
        assertFalse(task.match("deadline"));
        assertTrue(task.match("event"));
    }
}
