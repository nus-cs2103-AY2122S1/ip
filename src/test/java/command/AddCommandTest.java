package command;

import static org.hamcrest.CoreMatchers.instanceOf;

import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import exception.MissingCommandDescriptionException;
import storage.StorageFile;
import stub.storage.StorageStub;
import tasklist.TaskList;
import type.CommandTypeEnum;

/**
 * Encapsulates tests for add command.
 */
public class AddCommandTest {
    /**
     * Tests if exception is thrown when command description is missing.
     */
    @Test
    public void createCommand_missingDescription_exceptionThrown() {
        String description = "";
        Assertions.assertThrows(MissingCommandDescriptionException.class, () ->
                AddCommand.createCommand(description, CommandTypeEnum.TODO));
    }

    /**
     * Tests if command is created when description is present.
     */
    @Test
    public void createCommand_presentDescription_commandCreated() {
        String description = "run";
        Assertions.assertDoesNotThrow(() -> {
            MatcherAssert.assertThat(
                    AddCommand.createCommand(description, CommandTypeEnum.TODO),
                    instanceOf(AddCommand.class)
            );
        });
    }

    /**
     * Tests if task is successfully added to list.
     */
    @Test
    public void execute_validTask_taskAddedToList() {
        String description = "run";

        Assertions.assertDoesNotThrow(() -> {
            StorageFile storageFile = new StorageStub().loadListFile();
            TaskList taskList = new TaskList(storageFile);
            int initialNumOfTasks = taskList.getNumberOfTasks();

            AddCommand command = AddCommand.createCommand(description, CommandTypeEnum.TODO);
            command.execute(taskList);

            Assertions.assertEquals(initialNumOfTasks + 1, taskList.getNumberOfTasks());
        });
    }
}
