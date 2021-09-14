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

    @Test
    public void taskListTest_correctElementDeleted() {
        ArrayList<Task> tasks = new ArrayList<>();
        TaskList ponyo = new TaskList();

        Task task1 = new Todo("task 1");
        Task task2 = new Todo("task 2");

        tasks.add(task1);
        tasks.add(task2);
        ponyo.add(task1);
        ponyo.add(task2);

        ponyo.remove(0);
        assertEquals(tasks.get(1), ponyo.retrieveTask(0));
    }
}
