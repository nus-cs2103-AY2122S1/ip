package command;

import static org.hamcrest.CoreMatchers.instanceOf;

import java.util.ArrayList;

import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import exception.MissingCommandDescriptionException;
import message.Message;
import storage.StorageFile;
import stub.storage.StorageStub;
import tasklist.TaskList;
import tasklist.TodoTask;

/**
 * Encapsulates tests for find command.
 */
public class FindCommandTest {
    /**
     * Tests if exception is thrown when command description is missing.
     */
    @Test
    public void createCommand_missingDescription_exceptionThrown() {
        String description = "";
        Assertions.assertThrows(MissingCommandDescriptionException.class, () ->
                FindCommand.createCommand(description));
    }

    /**
     * Tests if command is created when description is valid.
     */
    @Test
    public void createCommand_presentDescription_commandCreated() {
        String description = "cat";
        Assertions.assertDoesNotThrow(() -> {
            MatcherAssert.assertThat(
                    FindCommand.createCommand(description),
                    instanceOf(FindCommand.class)
            );
        });
    }

    /**
     * Tests if only tasks matching the keywords are returned.
     */
    @Test
    public void execute_keywordInList_onlyTasksWithKeywordReturned() {
        String description = "cake";
        Assertions.assertDoesNotThrow(() -> {
            StorageFile storageFile = new StorageStub().loadListFile();
            storageFile.rewriteFile(new ArrayList<>());
            TaskList taskList = new TaskList(storageFile);
            int numOfTasksContainingKeyword = 3;

            for (int i = 0; i < numOfTasksContainingKeyword; i++) {
                taskList.addTaskToList(TodoTask.createTask("eat cake"));
            }

            for (int i = 0; i < 2; i++) {
                taskList.addTaskToList(TodoTask.createTask("sleep"));
                taskList.addTaskToList(TodoTask.createTask("jump"));
            }

            FindCommand command = FindCommand.createCommand(description);
            Message outputMessage = command.execute(taskList);

            TaskList expectedList = taskList.getListContainingKeyword(description);
            String prefix = "Here are the matching tasks in your list:";
            Message expectedMessage = new Message(prefix, expectedList.toString());

            Assertions.assertEquals(outputMessage.toString(), expectedMessage.toString());
        });
    }
}
