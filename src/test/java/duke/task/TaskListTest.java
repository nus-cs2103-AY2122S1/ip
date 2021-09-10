package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

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

    @Test
    public void prioritizeTask() {
        TaskList taskList = new TaskList();

        taskList.addTask(new Todo("todo-1"));
        taskList.addTask(new Deadline("deadline-1", "2021-12-12"));
        taskList.addTask(new Event("event-1", "2021-11-30"));

        taskList.prioritizeTask(0, Priority.HIGH);
        taskList.prioritizeTask(2, Priority.LOW);

        assertEquals(new Todo("todo-1", Priority.HIGH, false).toString(), taskList.getTask(0).toString());
        assertEquals(new Deadline("deadline-1", Priority.MEDIUM, "2021-12-12", false).toString(),
                taskList.getTask(1).toString());
        assertEquals(new Event("event-1", Priority.LOW, "2021-11-30", false).toString(),
                taskList.getTask(2).toString());
    }
}
