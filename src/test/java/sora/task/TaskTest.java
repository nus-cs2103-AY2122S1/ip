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

        assertTrue(task.matchesKeyword("test"));
        assertTrue(task.matchesKeyword("task"));
        assertTrue(task.matchesKeyword("t"));

        assertFalse(task.matchesKeyword("apple"));
        assertFalse(task.matchesKeyword("banana"));
        assertFalse(task.matchesKeyword("tet"));

        assertFalse(task.matchesKeyword("todo"));
        assertFalse(task.matchesKeyword("deadline"));
        assertTrue(task.matchesKeyword("event"));
    }
}
