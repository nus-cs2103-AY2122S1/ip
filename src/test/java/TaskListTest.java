import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import task.Deadline;
import java.time.LocalDate;
import java.time.LocalTime;
import task.TaskList;
import task.Task;
import task.Todo;

import java.util.ArrayList;

public class TaskListTest {
    @Test
    public void addTaskTest() {
        Task task1 = new Todo("run");

        TaskList tasks = new TaskList(new ArrayList<>());
        tasks.addTask(task1);
        assertEquals("[T][ ] run", tasks.getTask(0).toString());
    }

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
