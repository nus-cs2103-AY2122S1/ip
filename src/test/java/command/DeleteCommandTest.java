package command;

import static org.hamcrest.CoreMatchers.instanceOf;

import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import exception.InvalidTaskNumberException;
import exception.MissingCommandDescriptionException;
import exception.NonExistentTaskNumberException;
import storage.StorageFile;
import stub.storage.StorageStub;
import tasklist.Task;
import tasklist.TaskList;
import tasklist.TodoTask;

/**
 * Encapsulates tests for delete command.
 */
public class DeleteCommandTest {
    /**
     * Tests if exception is thrown when command description is missing.
     */
    @Test
    public void createCommand_missingDescription_exceptionThrown() {
        String description = "";
        Assertions.assertThrows(MissingCommandDescriptionException.class, () ->
                DeleteCommand.createCommand(description));
    }

    /**
     * Tests if exception is thrown when description is not a number.
     */
    @Test
    public void createCommand_nonNumberDescription_exceptionThrown() {
        String description = "asd";
        Assertions.assertThrows(InvalidTaskNumberException.class, () ->
                DeleteCommand.createCommand(description));
    }

    /**
     * Tests if command is created when description is valid.
     */
    @Test
    public void createCommand_numberDescription_commandCreated() {
        String description = "1";
        Assertions.assertDoesNotThrow(() -> {
            MatcherAssert.assertThat(
                    DeleteCommand.createCommand(description),
                    instanceOf(DeleteCommand.class)
            );
        });
    }

    /**
     * Tests if exception is thrown when attempting to delete task number 0.
     */
    @Test
    public void execute_taskNumberZero_exceptionThrown() {
        String description = "0";
        Assertions.assertThrows(NonExistentTaskNumberException.class, () -> {
            StorageFile storageFile = new StorageStub().loadListFile();
            TaskList taskList = new TaskList(storageFile);
            DeleteCommand command = DeleteCommand.createCommand(description);
            command.execute(taskList);
        });
    }

    /**
     * Tests if exception is thrown when attempting to delete a negative task number.
     */
    @Test
    public void execute_negativeTaskNumber_exceptionThrown() {
        String description = "-2";
        Assertions.assertThrows(NonExistentTaskNumberException.class, () -> {
            StorageFile storageFile = new StorageStub().loadListFile();
            TaskList taskList = new TaskList(storageFile);
            DeleteCommand command = DeleteCommand.createCommand(description);
            command.execute(taskList);
        });
    }

    /**
     * Tests if exception is thrown when attempting to delete a task number that is too large.
     */
    @Test
    public void execute_tooHighTaskNumber_exceptionThrown() {
        Assertions.assertThrows(NonExistentTaskNumberException.class, () -> {
            StorageFile storageFile = new StorageStub().loadListFile();
            TaskList taskList = new TaskList(storageFile);
            int numOfTasks = taskList.getNumberOfTasks();
            String description = String.valueOf(numOfTasks + 2);

            DeleteCommand command = DeleteCommand.createCommand(description);
            command.execute(taskList);
        });
    }

    /**
     * Tests if task is successfully removed from list when valid.
     */
    @Test
    public void execute_validTaskNumber_taskRemovedFromList() {
        Assertions.assertDoesNotThrow(() -> {
            StorageFile storageFile = new StorageStub().loadListFile();
            TaskList taskList = new TaskList(storageFile);
            Task task = TodoTask.createTask("run");
            taskList.addTaskToList(task);

            int initialNumOfTasks = taskList.getNumberOfTasks();
            String description = String.valueOf(initialNumOfTasks);

            DeleteCommand command = DeleteCommand.createCommand(description);
            command.execute(taskList);

            Assertions.assertEquals(initialNumOfTasks - 1, taskList.getNumberOfTasks());
        });
    }
}
