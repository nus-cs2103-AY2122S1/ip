package tasklist;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import exception.DukeException;
import models.Task;

public class TaskListTest {

    private TaskList list = new TaskList(new ArrayList<>());

    @Test
    public void addTask_validInput_addSuccessfully() {
        list.addTask(new Task(""));
        list = list.getNext();
        assertEquals(1, list.getSize());
    }

    @Test
    public void deleteTask_validInput_deleteSuccessfully() {
        try {
            list.addTask(new Task(""));
            list = list.getNext();
            list.deleteTask(0);
            list = list.getNext();
            assertEquals(0, list.getSize());
        } catch (DukeException error) {
            fail("Should not have thrown any exception");
        }
    }

    @Test
    public void deleteTask_invalidInput_throwError() {
        try {
            list.deleteTask(0);
            fail("Should have thrown  DukeException");
        } catch (DukeException error) {
            return;
        }
    }

    @Test
    public void getTask_validInput_getTaskSuccessfully() {
        Task task = new Task("");
        list.addTask(task);
        list = list.getNext();
        Task result = list.getTask(0);
        assertEquals(task, result);
    }

    @Test
    public void getSize_validInput_returnSizeOfTasklistSuccessfully() {
        list.addTask(new Task(""));
        list = list.getNext();
        list.addTask(new Task(""));
        list = list.getNext();
        assertEquals(2, this.list.getSize());
    }
}
