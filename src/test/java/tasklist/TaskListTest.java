package tasklist;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import exception.DukeException;
import models.Task;

public class TaskListTest {

    private final TaskList list = new TaskList();

    @Test
    public void addTask_validInput_addSuccessfully() {
        this.list.addTask(new Task(""));
        assertEquals(1, this.list.getSize());
    }

    @Test
    public void deleteTask_validInput_deleteSuccessfully() {
        try {
            this.list.addTask(new Task(""));
            this.list.deleteTask(0);
            assertEquals(0, this.list.getSize());
        } catch (DukeException error) {
            fail("Should not have thrown any exception");
        }
    }

    @Test
    public void deleteTask_invalidInput_throwError() {
        try {
            this.list.deleteTask(0);
            fail("Should have thrown  DukeException");
        } catch (DukeException error) {
            return;
        }
    }

    @Test
    public void getTask_validInput_getTaskSuccessfully() {
        Task task = new Task("");
        this.list.addTask(task);
        Task result = this.list.getTask(0);
        assertEquals(task, result);
    }

    @Test
    public void getSize_validInput_returnSizeOfTasklistSuccessfully() {
        this.list.addTask(new Task(""));
        this.list.addTask(new Task(""));
        assertEquals(2, this.list.getSize());
    }
}
