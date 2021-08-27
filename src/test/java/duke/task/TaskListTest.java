package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TaskListTest {
    @Test
    public void addTask() {
        TaskList taskList = new TaskList();

        taskList.addTask(new Todo("todo-1"));
        taskList.addTask(new Deadline("deadline-1", "2021-12-12"));
        taskList.addTask(new Event("event-1", "2021-11-30"));

        assertEquals(new Todo("todo-1").toString(), taskList.getTask(0).toString());
        assertEquals(new Deadline("deadline-1", "2021-12-12").toString(),
                taskList.getTask(1).toString());
        assertEquals(new Event("event-1", "2021-11-30").toString(), taskList.getTask(2).toString());
    }

    @Test
    public void deleteTask() {
        TaskList taskList = new TaskList();

        taskList.addTask(new Todo("todo-1"));
        taskList.addTask(new Deadline("deadline-1", "2021-12-12"));
        taskList.addTask(new Event("event-1", "2021-11-30"));

        assertThrows(IndexOutOfBoundsException.class, () -> taskList.deleteTask(3));
        taskList.deleteTask(1);

        assertEquals(new Todo("todo-1").toString(), taskList.getTask(0).toString());
        assertEquals(new Event("event-1", "2021-11-30").toString(), taskList.getTask(1).toString());
    }

    @Test
    public void markDone() {
        TaskList taskList = new TaskList();

        taskList.addTask(new Todo("todo-1"));
        taskList.addTask(new Deadline("deadline-1", "2021-12-12"));
        taskList.addTask(new Event("event-1", "2021-11-30"));

        taskList.markDone(1);

        assertEquals(new Todo("todo-1").toString(), taskList.getTask(0).toString());
        assertEquals(new Deadline("deadline-1", "2021-12-12", true).toString(), taskList.getTask(1).toString());
        assertEquals(new Event("event-1", "2021-11-30").toString(), taskList.getTask(2).toString());
    }
}
