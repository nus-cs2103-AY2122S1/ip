package botto.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import botto.BottoException;
import botto.task.Task;
import botto.task.TaskStub;

public class TaskListTest {
    private TaskList taskList;
    private TaskStub t1 = new TaskStub("Collect Money");
    private TaskStub t2 = new TaskStub("Spend Money");
    private TaskStub t3 = new TaskStub("Earn Money");
    private List<Task> tasks = new LinkedList<>();

    @BeforeEach
    public void setUp() {
        tasks.add(t1);
        tasks.add(t2);
        tasks.add(t3);
        this.taskList = new TaskList(tasks);
    }

    @Test
    public void getTasks_noInput_success() {
        assertEquals(tasks, taskList.getTasks());
        List<Task> emptyList = new LinkedList<>();
        taskList = new TaskList(emptyList);
        assertEquals(emptyList, taskList.getTasks());
    }

    @Test
    public void getSize_noInput_success() {
        assertEquals(3, taskList.getSize());
    }

    @Test
    public void markAsDone_allTasks_success() throws BottoException {
        assertFalse(t1.isDone());
        assertEquals(t1, taskList.markAsDone(0));
        assertTrue(t1.isDone());

        assertEquals(t2, taskList.markAsDone(1));
        assertEquals(t3, taskList.markAsDone(2));
    }

    @Test
    public void markAsDone_notElement_exceptionThrown() {
        try {
            taskList.markAsDone(4);
        } catch (BottoException e) {
            assertEquals("☹ OOPS!!! The task does not exist.", e.getMessage());
        }
    }

    @Test
    public void addTask_randomTask_success() {
        Task task1 = new Task("Hello World");
        taskList.addTask(task1);
        assertTrue(taskList.getTasks().contains(task1));

        Task task2 = new Task("Hello CS2103T");
        taskList.addTask(task2);
        assertTrue(taskList.getTasks().contains(task2));

        assertEquals(5, taskList.getSize());
    }

    @Test
    public void deleteTask_allTasks_success() throws BottoException {
        assertTrue(taskList.getTasks().contains(t3));
        assertEquals(t3, taskList.deleteTask(2));
        assertFalse(taskList.getTasks().contains(t3));

        assertEquals(t2, taskList.deleteTask(1));
        assertEquals(t1, taskList.deleteTask(0));
    }

    @Test
    public void deleteTask_notElement_exceptionThrown() {
        try {
            taskList.deleteTask(4);
        } catch (BottoException e) {
            assertEquals("☹ OOPS!!! The task does not exist.", e.getMessage());
        }

        try {
            taskList.deleteTask(5);
        } catch (BottoException e) {
            assertEquals("☹ OOPS!!! The task does not exist.", e.getMessage());
        }
    }

    @AfterEach
    public void tearDown() {
        this.taskList = new TaskList(new LinkedList<>());
    }

}
