package lifeline.task;

import static lifeline.util.ErrorString.ERROR_TASK_ALREADY_DONE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import lifeline.exception.LifelineException;

public class TaskListTest {

    @Test
    public void testAddTask() {
        TaskList taskList = new TaskList(new ArrayList<Task>());
        ToDo todo = new ToDo("read book");
        assertEquals(0, taskList.getSize());
        taskList.add(todo);
        assertEquals(1, taskList.getSize());
    }

    @Test
    public void testDeleteTask() {
        ArrayList<Task> tasks = new ArrayList<>();
        ToDo todo = new ToDo("read book");
        tasks.add(todo);
        TaskList taskList = new TaskList(tasks);
        assertEquals(1, taskList.getSize());
        taskList.deleteTask(0);
        assertEquals(0, taskList.getSize());
    }

    @Test
    public void completeTask_uncompletedTask_success() throws LifelineException {
        TaskList taskList = new TaskList(new ArrayList<Task>());
        ToDo todo = new ToDo("read book");
        taskList.add(todo);
        assertFalse(taskList.getTask(0).isDone());
        taskList.completeTask(0);
        assertTrue(taskList.getTask(0).isDone());
    }

    @Test
    public void completeTask_completedTask_exceptionThrown() {
        try {
            TaskList taskList = new TaskList(new ArrayList<Task>());
            ToDo todo = new ToDo("read book", true);
            taskList.add(todo);
            taskList.completeTask(0);
            fail();
        } catch (LifelineException e) {
            assertEquals(ERROR_TASK_ALREADY_DONE, e.getMessage());
        }
    }

    @Test
    public void testGetTaskList() {
        ArrayList<Task> tasks = new ArrayList<>();
        TaskList taskList = new TaskList(tasks);
        assertEquals(tasks, taskList.getTaskList());
    }

    @Test
    public void testGetTask() {
        TaskList taskList = new TaskList(new ArrayList<Task>());
        ToDo todo = new ToDo("read book");
        taskList.add(todo);
        assertEquals(todo, taskList.getTask(0));
    }
}
