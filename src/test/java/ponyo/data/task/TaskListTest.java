package ponyo.data.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class TaskListTest {
    @Test
    public void taskListTest_correctElementUsingRetrieve() {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new Todo("task 1"));
        tasks.add(new Todo("task 2"));

        TaskList ponyo = new TaskList(tasks);
        assertEquals(tasks.get(1), ponyo.retrieveTask(1));
    }
}
