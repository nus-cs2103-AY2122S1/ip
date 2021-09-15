package duke.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import duke.StorageStub;
import duke.TaskList;
import duke.Ui;
import duke.task.Todo;

class DeleteCommandTest {

    private static final String DESCRIPTION = "task description";

    private final Ui ui = new Ui();

    private TaskList getTaskListOneTodo() {
        TaskList tasks = new TaskList();
        tasks.add(new Todo(DESCRIPTION));

        return tasks;
    }

    @Test
    void execute_deleteInput_taskDeleted() {
        TaskList taskList = new TaskList();
        taskList.add(new Todo(DESCRIPTION));
        StorageStub storageStub = new StorageStub(taskList);
        DeleteCommand deleteCommand = new DeleteCommand("delete 1");

        deleteCommand.execute(taskList, this.ui, storageStub);

        assertTrue(new TaskList().equals(taskList));
    }

    @Test
    void execute_missingSpace_taskNotDeleted() {
        TaskList taskList = new TaskList();
        taskList.add(new Todo(DESCRIPTION));
        StorageStub storageStub = new StorageStub(taskList);
        DeleteCommand deleteCommand = new DeleteCommand("delete1");

        deleteCommand.execute(taskList, this.ui, storageStub);

        assertTrue(getTaskListOneTodo().equals(taskList));
    }

    @Test
    void execute_missingIndex_taskNotDeleted() {
        TaskList taskList = new TaskList();
        taskList.add(new Todo(DESCRIPTION));
        StorageStub storageStub = new StorageStub(taskList);
        DeleteCommand deleteCommandMissingSpaceIndex = new DeleteCommand("delete");
        DeleteCommand deleteCommandMissingIndex = new DeleteCommand("delete ");

        deleteCommandMissingSpaceIndex.execute(taskList, this.ui, storageStub);
        deleteCommandMissingIndex.execute(taskList, this.ui, storageStub);

        assertTrue(getTaskListOneTodo().equals(taskList));
    }

    @Test
    void execute_invalidIndex_taskNotDeleted() {
        TaskList taskList = new TaskList();
        taskList.add(new Todo(DESCRIPTION));
        StorageStub storageStub = new StorageStub(taskList);
        DeleteCommand deleteCommandInvalidLargeIndex = new DeleteCommand("delete 100");
        DeleteCommand deleteCommandInvalidNegativeIndex = new DeleteCommand("delete -1");
        DeleteCommand deleteCommandInvalidZeroIndex = new DeleteCommand("delete 0");
        DeleteCommand deleteCommandInvalidFormatIndex = new DeleteCommand("delete abc");

        deleteCommandInvalidLargeIndex.execute(taskList, this.ui, storageStub);
        deleteCommandInvalidNegativeIndex.execute(taskList, this.ui, storageStub);
        deleteCommandInvalidZeroIndex.execute(taskList, this.ui, storageStub);
        deleteCommandInvalidFormatIndex.execute(taskList, this.ui, storageStub);

        assertTrue(getTaskListOneTodo().equals(taskList));
    }

    @Test
    void equals_sameDeleteCommand_true() {
        DeleteCommand deleteCommand = new DeleteCommand("list");
        DeleteCommand deleteCommand1 = new DeleteCommand("list");

        assertEquals(deleteCommand, deleteCommand1);
    }

    @Test
    void equals_differentDeleteCommand_false() {
        DeleteCommand deleteCommand = new DeleteCommand("list");
        DeleteCommand deleteCommand1 = new DeleteCommand("other");

        assertNotEquals(deleteCommand, deleteCommand1);
    }
}
