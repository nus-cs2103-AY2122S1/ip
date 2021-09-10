package command;

import exception.InvalidTaskNumberException;
import exception.MissingCommandDescriptionException;
import exception.NonExistentTaskNumberException;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import storage.StorageFile;
import stub.storage.StorageStub;
import tasklist.Task;
import tasklist.TaskList;
import tasklist.TodoTask;

import static org.hamcrest.CoreMatchers.instanceOf;

public class DeleteCommandTest {
    @Test
    public void createCommand_missingDescription_exceptionThrown() {
        String description = "";
        Assertions.assertThrows(
                MissingCommandDescriptionException.class,
                () -> DeleteCommand.createCommand(description)
        );
    }

    @Test
    public void createCommand_nonNumberDescription_exceptionThrown() {
        String description = "asd";
        Assertions.assertThrows(
                InvalidTaskNumberException.class,
                () -> DeleteCommand.createCommand(description)
        );
    }

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

    @Test
    public void execute_taskNumberZero_exceptionThrown() {
        String description = "0";
        Assertions.assertThrows(
                NonExistentTaskNumberException.class,
                () -> {
                    StorageFile storageFile = new StorageStub().loadListFile();
                    TaskList taskList = new TaskList(storageFile);
                    DeleteCommand command = DeleteCommand.createCommand(description);
                    command.execute(taskList);
                }
        );
    }

    @Test
    public void execute_negativeTaskNumber_exceptionThrown() {
        String description = "-2";
        Assertions.assertThrows(
                NonExistentTaskNumberException.class,
                () -> {
                    StorageFile storageFile = new StorageStub().loadListFile();
                    TaskList taskList = new TaskList(storageFile);
                    DeleteCommand command = DeleteCommand.createCommand(description);
                    command.execute(taskList);
                }
        );
    }

    @Test
    public void execute_tooHighTaskNumber_exceptionThrown() {
        Assertions.assertThrows(
                NonExistentTaskNumberException.class,
                () -> {
                    StorageFile storageFile = new StorageStub().loadListFile();
                    TaskList taskList = new TaskList(storageFile);
                    int numOfTasks = taskList.getNumberOfTasks();
                    String description = String.valueOf(numOfTasks + 2);

                    DeleteCommand command = DeleteCommand.createCommand(description);
                    command.execute(taskList);
                }
        );
    }

    @Test
    public void execute_validTaskNumber_taskRemovedFromList() {
        Assertions.assertDoesNotThrow(
                () -> {
                    StorageFile storageFile = new StorageStub().loadListFile();
                    TaskList taskList = new TaskList(storageFile);
                    Task task = TodoTask.createTask("run");
                    taskList.addTaskToList(task);

                    int initialNumOfTasks = taskList.getNumberOfTasks();
                    String description = String.valueOf(initialNumOfTasks);

                    DeleteCommand command = DeleteCommand.createCommand(description);
                    command.execute(taskList);

                    Assertions.assertEquals(initialNumOfTasks - 1, taskList.getNumberOfTasks());
                }
        );
    }
}
