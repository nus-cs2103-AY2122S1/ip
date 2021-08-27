import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import task.Deadline;
import task.Task;
import task.TaskList;
import task.Todo;

public class TaskListTest {
    /**
     * Test whether addTask method successfully adds a task to the TaskList.
     */
    @Test
    public void addTaskTest() {
        Task task1 = new Todo("run");

        TaskList tasks = new TaskList(new ArrayList<>());
        tasks.addTask(task1);
        assertEquals("[T][ ] run", tasks.getTask(0).toString());
    }

    /**
     * Test whether removeTask method successfully removes a task to the TaskList.
     */
    @Test
    public void removeTaskTest() {
        Task task1 = new Todo("run");
        Task task2 = new Deadline("sleep", LocalDate.parse("2021-06-29"), LocalTime.parse("12:00"));

        TaskList tasks = new TaskList(new ArrayList<>());
        tasks.addTask(task1);
        tasks.addTask(task2);
        tasks.removeTask(0);
        assertEquals("[D][ ] sleep (by: Jun 29 2021 12:00 PM)", tasks.getTask(0).toString());

    }
}
