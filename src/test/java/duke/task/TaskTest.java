package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
}
