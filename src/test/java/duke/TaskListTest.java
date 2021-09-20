package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import duke.tasks.Task;
import duke.tasks.Todo;

public class TaskListTest {
    @Test
    public void addNewTask() {
        TaskList tasks = new TaskList();
        ArrayList<Task> taskArr = new ArrayList<>();
        taskArr.add(new Todo("test"));
        TaskList otherTasks = new TaskList(taskArr);
        tasks.addNewTask(new Todo("test"));
        assertEquals(tasks, otherTasks);
    }

    @Test
    public void markTaskDone() throws DukeException {
        ArrayList<Task> taskArr = new ArrayList<>();
        taskArr.add(new Todo("test"));
        Todo doneTodo = new Todo("test", true);
        TaskList tasks = new TaskList(taskArr);
        ArrayList<Task> doneArr = new ArrayList<>();
        doneArr.add(doneTodo);
        TaskList otherTasks = new TaskList(doneArr);
        tasks.markTaskDone(0);
        assertEquals(otherTasks, tasks);
    }

    @Test
    public void markTaskDone_alreadyDoneTask_exceptionThrown() throws DukeException {
        ArrayList<Task> taskArr = new ArrayList<>();
        taskArr.add(new Todo("test", true));
        TaskList tasks = new TaskList(taskArr);
        try {
            tasks.markTaskDone(0);
            fail();
        } catch (DukeException e) {
            assertEquals("Task 1 is already marked as done.", e.getMessage());
        }

    }

    @Test
    public void deleteTask() throws DukeException {
        ArrayList<Task> taskArr = new ArrayList<>();
        taskArr.add(new Todo("test"));
        TaskList tasks = new TaskList(taskArr);
        tasks.deleteTask(0);
        assertEquals(new TaskList(), tasks);
    }

    @Test
    public void getTaskStrings() throws DukeException {
        ArrayList<Task> taskArr = new ArrayList<>();
        taskArr.add(new Todo("test", true));
        TaskList tasks = new TaskList(taskArr);
        ArrayList<String> expected = new ArrayList<>();
        expected.add("T | 1 | test");
        assertEquals(expected, tasks.getTaskStrings());
    }
}
