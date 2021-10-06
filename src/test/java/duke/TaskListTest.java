package duke;

import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

public class TaskListTest {
    @Test
    public void testGetTasks() {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new Todo("return book"));
        tasks.add(new Deadline("return book", "06-06-2021 18:00"));
        tasks.add(new Event("return book", "2-4pm"));
        TaskList taskList = new TaskList(tasks);
        Assertions.assertEquals(tasks, taskList.getTasks());
    }

    @Test
    public void testLastTask() {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new Todo("return book"));
        tasks.add(new Deadline("return book", "06-06-2021 18:00"));
        tasks.add(new Event("return book", "2-4pm"));
        TaskList taskList = new TaskList(tasks);
        Assertions.assertEquals(taskList.lastTask(), new Event("return book", "2-4pm"));
    }

    @Test
    public void testAddTodoTask() {
        TaskList taskList = new TaskList(new ArrayList<>());
        taskList.addTodoTask("return book");

        ArrayList<Task> expectedTasks = new ArrayList<>();
        expectedTasks.add(new Todo("return book"));

        Assertions.assertEquals(taskList, new TaskList(expectedTasks));
    }

    @Test
    public void testAddDeadlineTask() {
        TaskList taskList = new TaskList(new ArrayList<>());
        taskList.addDeadlineTask("return book", "06-06-2021 18:00");

        ArrayList<Task> expectedTasks = new ArrayList<>();
        expectedTasks.add(new Deadline("return book", "06-06-2021 18:00"));

        Assertions.assertEquals(taskList, new TaskList(expectedTasks));
    }

    @Test
    public void testAddEventTask() {
        TaskList taskList = new TaskList(new ArrayList<>());
        taskList.addEventTask("return book", "06-06-2021 18:00");

        ArrayList<Task> expectedTasks = new ArrayList<>();
        expectedTasks.add(new Event("return book", "06-06-2021 18:00"));

        Assertions.assertEquals(taskList, new TaskList(expectedTasks));
    }

    @Test
    public void testValidDeleteTask() {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new Todo("return book"));
        tasks.add(new Deadline("return book", "06-06-2021 18:00"));
        tasks.add(new Event("return book", "2-4pm"));
        TaskList taskList = new TaskList(tasks);
        taskList.deleteTask(2);

        ArrayList<Task> expectedTasks = new ArrayList<>();
        expectedTasks.add(new Todo("return book"));
        expectedTasks.add(new Deadline("return book", "06-06-2021 18:00"));

        Assertions.assertEquals(taskList, new TaskList(expectedTasks));
    }

    @Test
    public void testInvalidDeleteTask() {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new Todo("return book"));
        TaskList taskList = new TaskList(tasks);
        Assertions.assertThrows(DukeException.class, () -> taskList.deleteTask(2));
    }

    @Test
    public void testMarkTaskDone() {
        Task task = new Todo("return book", false);
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(task);
        TaskList taskList = new TaskList(tasks);
        Assertions.assertEquals(taskList.markTaskDone(0), new Pair<Boolean, Task>(false, task));
    }

    @Test
    public void testMarkAlreadyDoneTask() {
        Task task = new Todo("return book", true);
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(task);
        TaskList taskList = new TaskList(tasks);
        Assertions.assertEquals(taskList.markTaskDone(0), new Pair<Boolean, Task>(true, task));
    }

    @Test
    public void testInvalidMarkTaskDone() {
        TaskList taskList = new TaskList(new ArrayList<Task>());
        Assertions.assertThrows(DukeException.class, () -> taskList.markTaskDone(2));
    }
}
