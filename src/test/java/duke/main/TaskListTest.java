package duke.main;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import duke.exceptions.DucException;
import duke.exceptions.DucOutOfBoundException;
import duke.exceptions.DucWrongCommandException;
import duke.task.Task;

public class TaskListTest {

    private TaskList taskList;

    @BeforeEach
    public void setup() {
        this.taskList = new TaskList();
        Storage.loadData(new File("taskFile/testFile1.txt"), taskList);
        taskList.addTask("play the piano", Task.Type.TODO);
        taskList.addTask("CS2103T IP submission /by 2021-09-17", Task.Type.DEADLINE);
        taskList.addTask("CS2103T Lecture /at 2021-09-17", Task.Type.EVENT);
    }

    /**
     * Tests the size command of DuC task list
     */
    @Test
    public void testSize() {
        assertEquals(3, taskList.size());
        taskList.addTask("play the piano", Task.Type.TODO);
        assertEquals(4, taskList.size());
        taskList.delete(4);
        assertEquals(3, taskList.size());
    }

    /**
     * Tests the completion making of DuC task objects
     */
    @Test
    public void testDone() {
        assertThrows(DucException.class, () -> taskList.done(10));
        assertThrows(DucOutOfBoundException.class, () -> taskList.done(-1));
        taskList.done(1);
        assertTrue(taskList.get(1).isCompleted());
        taskList.doneAll();
        assertTrue(taskList.get(2).isCompleted());
        assertTrue(taskList.get(3).isCompleted());
    }

    /**
     * Tests the deletion of a task from the list
     */
    @Test
    public void testDelete() {
        assertThrows(DucException.class, () -> taskList.delete(10));
        assertThrows(DucOutOfBoundException.class, () -> taskList.delete(-1));
        taskList.delete(1);
        assertEquals(2, taskList.size());
        taskList.deleteAll();
        assertEquals(0, taskList.size());
    }

    /**
     * Tests the addition of a task to the list
     */
    @Test
    public void testAddTask() {
        taskList.addTask("walk 10000 steps", Task.Type.TODO);
        assertEquals(4, taskList.size());
        assertFalse(taskList.get(4).isCompleted());
        taskList.addTask("cs2100 tutorial /by 2021-08-29", Task.Type.DEADLINE);
        assertFalse(taskList.get(5).isCompleted());
        assertEquals(5, taskList.size());
        taskList.addTask("cs2101 OP 1 /at 2021-08-30", Task.Type.EVENT);
        assertEquals(6, taskList.size());
        assertThrows(DucWrongCommandException.class, () ->
                taskList.addTask("play Liszt", Task.Type.DEADLINE));
        assertThrows(DucWrongCommandException.class, () ->
                taskList.addTask("play Liszt", Task.Type.EVENT));
    }

    @AfterEach
    public void finish() {
        this.taskList.deleteAll();
    }
}
