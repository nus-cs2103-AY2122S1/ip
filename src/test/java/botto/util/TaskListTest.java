package botto.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
    }

    @Test
    public void getSize_noInput_success() {
        assertEquals(3, taskList.getSize());
    }

    @Test
    public void markAsDone_task1_success() throws BottoException {
        assertEquals(false, t1.isDone());
        assertEquals(t1, taskList.markAsDone(0));
        assertEquals(true, t1.isDone());
    }

    @Test
    public void markAsDone_notElement_exceptionThrown() {
        try {
            assertEquals(new Task("Dummy"), taskList.markAsDone(4));
        } catch (BottoException e) {
            assertEquals("☹ OOPS!!! The task does not exist.", e.getMessage());
        }
    }

    @AfterEach
    public void tearDown() {
        this.taskList = new TaskList(new LinkedList<>());
    }

}
