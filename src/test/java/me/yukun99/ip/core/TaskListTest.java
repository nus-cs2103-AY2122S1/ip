package me.yukun99.ip.core;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import me.yukun99.ip.exceptions.HelpBotDateTimeFormatException;
import me.yukun99.ip.exceptions.HelpBotInvalidTaskException;
import me.yukun99.ip.exceptions.HelpBotInvalidTaskTypeException;
import me.yukun99.ip.tasks.Deadline;
import me.yukun99.ip.tasks.Event;
import me.yukun99.ip.tasks.Task;
import me.yukun99.ip.tasks.ToDo;

public class TaskListTest {
    private TaskFinder taskFinder = new TaskFinder();
    private TaskList taskList = new TaskList(taskFinder);
    private Task testToDoTask = new ToDo("Test ToDo Task.");
    private Task testDeadlineTask = new Deadline("Test Deadline Task.", "2021-04-26 12:00:00");
    private Task testEventTask = new Event("Test Event Task.", "2021-04-26 12:00:00");

    public TaskListTest() throws HelpBotDateTimeFormatException {}

    /**
     * Test for adding a ToDo task to the task list.
     */
    @Test
    public void addTask_todoTask_taskAdded() throws HelpBotInvalidTaskException {
        taskList.addTask(testToDoTask, null);
        assertEquals(testToDoTask.toString(), taskList.getTask("1").toString());
    }

    /**
     * Test for adding a Deadline task to the task list.
     */
    @Test
    public void addTask_deadlineTask_taskAdded() throws HelpBotInvalidTaskException, HelpBotInvalidTaskTypeException {
        taskList.addTask(testDeadlineTask, testDeadlineTask.getDate());
        assertEquals(testDeadlineTask.toString(), taskList.getTask("1").toString());
    }

    /**
     * Test for adding an Event task to the task list.
     */
    @Test
    public void addTask_eventTask_taskAdded() throws HelpBotInvalidTaskException, HelpBotInvalidTaskTypeException {
        taskList.addTask(testEventTask, testEventTask.getDate());
        assertEquals(testEventTask.toString(), taskList.getTask("1").toString());
    }
}
