package command;

import static org.hamcrest.CoreMatchers.instanceOf;

import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import exception.InvalidTaskNumberException;
import exception.MissingCommandDescriptionException;
import exception.NonExistentTaskNumberException;
import message.Message;
import storage.StorageFile;
import stub.storage.StorageStub;
import tasklist.Task;
import tasklist.TaskList;
import tasklist.TodoTask;


/**
 * Encapsulates tests for done command.
 */
public class DoneCommandTest {
    /**
     * Tests if exception is thrown when command description is missing.
     */
    @Test
    public void createCommand_missingDescription_exceptionThrown() {
        String description = "";
        Assertions.assertThrows(MissingCommandDescriptionException.class, () ->
                DoneCommand.createCommand(description));
    }

    /**
     * Tests if exception is thrown when description is not a number.
     */
    @Test
    public void createCommand_nonNumberDescription_exceptionThrown() {
        String description = "asd";
        Assertions.assertThrows(InvalidTaskNumberException.class, () ->
                DoneCommand.createCommand(description));
    }

    /**
     * Tests if command is created when description is valid.
     */
    @Test
    public void createCommand_numberDescription_commandCreated() {
        String description = "1";
        Assertions.assertDoesNotThrow(() -> {
            MatcherAssert.assertThat(
                    DoneCommand.createCommand(description),
                    instanceOf(DoneCommand.class)
            );
        });
    }

    /**
     * Tests if exception is thrown when attempting to mark task number 0 as done.
     */
    @Test
    public void execute_taskNumberZero_exceptionThrown() {
        String description = "0";
        Assertions.assertThrows(NonExistentTaskNumberException.class, () -> {
            StorageFile storageFile = new StorageStub().loadListFile();
            TaskList taskList = new TaskList(storageFile);
            DoneCommand command = DoneCommand.createCommand(description);
            command.execute(taskList);
        });
    }

    /**
     * Tests if exception is thrown when attempting to mark a negative task number as done.
     */
    @Test
    public void execute_negativeTaskNumber_exceptionThrown() {
        String description = "-2";
        Assertions.assertThrows(NonExistentTaskNumberException.class, () -> {
            StorageFile storageFile = new StorageStub().loadListFile();
            TaskList taskList = new TaskList(storageFile);
            DoneCommand command = DoneCommand.createCommand(description);
            command.execute(taskList);
        });
    }

    /**
     * Tests if exception is thrown when attempting to mark a task number that is too large as done.
     */
    @Test
    public void execute_tooHighTaskNumber_exceptionThrown() {
        Assertions.assertThrows(NonExistentTaskNumberException.class, () -> {
            StorageFile storageFile = new StorageStub().loadListFile();
            TaskList taskList = new TaskList(storageFile);
            int numOfTasks = taskList.getNumberOfTasks();
            String description = String.valueOf(numOfTasks + 2);

            DoneCommand command = DoneCommand.createCommand(description);
            command.execute(taskList);
        });
    }

    /**
     * Tests if task is successfully marked as done when valid.
     */
    @Test
    public void execute_validTaskNumber_taskMarkedDone() {
        Assertions.assertDoesNotThrow(() -> {
            StorageFile storageFile = new StorageStub().loadListFile();
            TaskList taskList = new TaskList(storageFile);
            Task task = TodoTask.createTask("run");
            taskList.addTaskToList(task);

            int initialNumOfTasks = taskList.getNumberOfTasks();
            String description = String.valueOf(initialNumOfTasks);

            DoneCommand command = DoneCommand.createCommand(description);
            Message outputMessage = command.execute(taskList);

            String expectedPrefix = "Nice! I've marked this task as done:";
            Message expectedOutputMessage = new Message(expectedPrefix, task.toString());

            Assertions.assertEquals(expectedOutputMessage.toString(), outputMessage.toString());
        });
    }
}
