package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


public class TaskListTest {
    @Test
    public void test() {
        TaskList taskListInstance = new TaskList();
        Task task = new Todo("House Chores");
        taskListInstance.add(task);
        assertEquals("T | | House Chores", taskListInstance.get(0).toString());
        
        taskListInstance.remove(0);
        assertEquals(0, taskListInstance.size());

    }
}
