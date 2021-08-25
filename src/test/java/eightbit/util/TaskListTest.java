package eightbit.util;

import eightbit.task.Task;
import eightbit.task.TaskStub;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TaskListTest {

    private TaskList taskList;

    @BeforeEach
    void setUp() {
        List<Task> list = new ArrayList<>();
        TaskStub t0 = new TaskStub("Task0", false);
        TaskStub t1 = new TaskStub("Task1", false);
        TaskStub t2 = new TaskStub("Task2", true);
        list.add(t0);
        list.add(t1);
        list.add(t2);
        taskList = new TaskList(list);
    }

    @Test
    void size() {
        assertEquals(3, taskList.size());
    }

    @Test
    void size_emptyTaskList() {
        TaskList emptyList = new TaskList(new ArrayList<>());
        assertEquals(0, emptyList.size());
    }

    @Test
    void get_validIndex_correctTask() {
        TaskStub t = (TaskStub) taskList.get(1);
        assertEquals("Task1", t.description);
    }

    @Test
    void get_invalidIndex_exceptionThrown() {
        assertThrows(IndexOutOfBoundsException.class, () -> taskList.get(50));
    }

    @Test
    void add() {
        taskList.add(new TaskStub("Added", false));
        assertEquals(4, taskList.size());
        assertEquals("Added", taskList.get(3).getDescription());
    }

    @Test
    void remove_validIndex_taskRemoved() {
        taskList.remove(0);
        assertEquals(2, taskList.size());
        TaskStub t = (TaskStub) taskList.get(0);
        assertEquals("Task1", t.description);
    }

    @Test
    void remove_invalidIndex_exceptionThrown() {
        assertThrows(IndexOutOfBoundsException.class, () -> taskList.remove(50));
    }

    @AfterEach
    void tearDown() {
        taskList = new TaskList(new ArrayList<>());
    }
}
