package duke.task;

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
        Task task = new Task("test task");
    
        assertTrue(task.matchDescription("test"));
        assertTrue(task.matchDescription("task"));
        assertTrue(task.matchDescription("t"));
    
        assertFalse(task.matchDescription("apple"));
        assertFalse(task.matchDescription("banana"));
        assertFalse(task.matchDescription("tet"));
    }
}
