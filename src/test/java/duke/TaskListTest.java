package duke;

import duke.tasks.Task;
import duke.tasks.Todo;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    public void testAddNewTask() {
        TaskList tasks = new TaskList();
        ArrayList<Task> taskArr = new ArrayList<>();
        taskArr.add(new Todo("test"));
        TaskList otherTasks = new TaskList(taskArr);
        tasks.addNewTask(new Todo("test"));
        assertEquals(tasks, otherTasks);
    }

    @Test
    public void testMarkTaskDone() throws DukeException {
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
    public void testDeleteTask() throws DukeException {
        ArrayList<Task> taskArr = new ArrayList<>();
        taskArr.add(new Todo("test"));
        TaskList tasks = new TaskList(taskArr);
        tasks.deleteTask(0);
        assertEquals(new TaskList(), tasks);
    }
    
    @Test
    public void testGetTaskStrings() throws DukeException {
        ArrayList<Task> taskArr = new ArrayList<>();
        taskArr.add(new Todo("test", true));
        TaskList tasks = new TaskList(taskArr);
        ArrayList<String> expected = new ArrayList<>();
        expected.add("T | 1 | test");
        assertEquals(expected, tasks.getTaskStrings());
    }
}
