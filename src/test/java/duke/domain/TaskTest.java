package duke.domain;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {
    Task task;
    static final String TASK_NAME = "Task One";

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
        task = null;
    }

    @Test
    void task_nameOnly_taskStateNew() {
        task = new Task(TASK_NAME);
        assertEquals(task.isDone(), false);
    }

    @Test
    void task_nameAndState() {
        task = new Task(TASK_NAME, false);
        assertEquals(task.isDone(), false);
        task = new Task(TASK_NAME, true);
        assertEquals(task.isDone(), true);
    }

    @Test
    void finish_setFinish_taskStateDone() {
        task = new Task(TASK_NAME);
        task.finish();
        assertEquals(task.isDone(), true);
    }

    @Test
    void storageFields_getListOfFields_correctOrderAndValues() {
        task = new Task(TASK_NAME);
        List<String> list = task.getStorageFields();
        assertEquals(list.get(0), null);
        assertEquals(list.get(1), 0);
        assertEquals(list.get(2), TASK_NAME);
        task.finish();
        assertEquals(list.get(0), null);
        assertEquals(list.get(1), 1);
        assertEquals(list.get(2), TASK_NAME);
    }
}
