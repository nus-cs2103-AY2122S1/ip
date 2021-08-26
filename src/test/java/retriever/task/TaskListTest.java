package retriever.task;

import org.junit.jupiter.api.Test;

import retriever.Storage;
import retriever.exception.IllegalDateFormatException;
import retriever.exception.IllegalDeadlineFormatException;
import retriever.exception.RetrieverException;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class TaskListTest {
    @Test
    public void taskListLength_checkingLengthOfEmptyList_success() {
        assertEquals(0, new TaskList(new StorageStub()).taskListLength());
    }

    @Test
    public void addDeadlineTask_addingTaskUsingCorrectFormat_success() {
        boolean hasPassed = false;
        String task = "deadline CS2103T iP /by 26/08/2021";

        try {
            TaskList taskList = new TaskList(new StorageStub());
            taskList.addDeadlineTask(task);
            hasPassed = true;
        } catch (RetrieverException e) {
            fail();
        }

        assertTrue(hasPassed);
    }

    @Test
    public void addEventTask_addingTaskUsingCorrectFormat_success() {
        boolean hasPassed = false;
        String task = "event Apple WWDC 2021 /at 10/06/2021";

        try {
            TaskList taskList = new TaskList(new StorageStub());
            taskList.addEventTask(task);
            hasPassed = true;
        } catch (RetrieverException e) {
            fail();
        }

        assertTrue(hasPassed);
    }

    @Test
    public void addTodoTask_addingTaskUsingCorrectFormat_success() {
        boolean hasPassed = false;
        String task = "todo laundry";

        try {
            TaskList taskList = new TaskList(new StorageStub());
            taskList.addTodoTask(task);
            hasPassed = true;
        } catch (RetrieverException e) {
            fail();
        }

        assertTrue(hasPassed);
    }

    @Test
    public void taskListLength_afterAddingSomeItems_success() throws RetrieverException {
        TaskList taskList = new TaskList(new StorageStub());
        taskList.addTodoTask("todo sleep");
        taskList.addDeadlineTask("deadline CS2103T iP /by 26/08/2021");
        assertEquals(2, taskList.taskListLength());
    }

    @Test
    public void addDeadlineTask_addingTaskUsingIncorrectDateFormat_exceptionThrown() {
        boolean hasPassed = false;
        String task = "deadline CS2103T iP /by 26-08-2021";

        try {
            TaskList taskList = new TaskList(new StorageStub());
            taskList.addDeadlineTask(task);
            fail();
        } catch (IllegalDateFormatException | IllegalDeadlineFormatException e) {
            hasPassed = true;
            assertTrue(hasPassed);
        }
    }

    @Test
    public void addEventTask_addingTaskUsingIncorrectDateFormat_exceptionThrown() {
        boolean hasPassed = false;
        String task = "event Apple WWDC 2021 /at 10th June 2021";

        try {
            TaskList taskList = new TaskList(new StorageStub());
            taskList.addEventTask(task);
            fail();
        } catch (RetrieverException e) {
            hasPassed = true;
            assertTrue(hasPassed);
        }
    }
}

class StorageStub extends Storage {
    public StorageStub() {
        super("file_name_stub");
    }

    @Override
    public ArrayList<Task> readTasks() {
        return new ArrayList<Task>();
    }

    @Override
    public void writeTask(Task.TaskType task, String taskDescription, String taskDeadline) {
        // Assume we are writing a task to the file
    }

    @Override
    public void deleteTask(int taskNumber) {
        // Assume we are deleting task from file
    }

    @Override
    public void updateTaskStatusToDone(int taskNumber) {
        // Assume we are updating task status in file
    }

    @Override
    public void createNewFile() {
        // Assume we are creating a new file
    }
}
