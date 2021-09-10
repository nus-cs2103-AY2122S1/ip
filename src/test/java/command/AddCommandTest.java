package command;

import exception.MissingCommandDescriptionException;
import static org.hamcrest.CoreMatchers.instanceOf;

import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import storage.StorageFile;
import stub.storage.StorageStub;
import tasklist.TaskList;
import type.CommandTypeEnum;

public class AddCommandTest {
    @Test
    public void createCommand_missingDescription_exceptionThrown() {
        String description = "";
        Assertions.assertThrows(
                MissingCommandDescriptionException.class,
                () -> AddCommand.createCommand(description, CommandTypeEnum.TODO)
        );
    }

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
