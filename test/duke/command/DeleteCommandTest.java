package duke.command;

import duke.StorageStub;
import duke.TaskList;
import duke.Ui;
import duke.task.Todo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

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
        DeleteCommand deleteCommand = new DeleteCommand("delete");
        DeleteCommand deleteCommand1 = new DeleteCommand("delete ");
        DeleteCommand deleteCommand2 = new DeleteCommand("delete 100");
        DeleteCommand deleteCommand3 = new DeleteCommand("delete abc");

        deleteCommand.execute(taskList, this.ui, storageStub);
        deleteCommand1.execute(taskList, this.ui, storageStub);
        deleteCommand2.execute(taskList, this.ui, storageStub);
        deleteCommand3.execute(taskList, this.ui, storageStub);

        assertTrue(getTaskListOneTodo().equals(taskList));
    }

    @Test
    void execute_invalidIndex_taskNotDeleted() {
        TaskList taskList = new TaskList();
        taskList.add(new Todo(DESCRIPTION));
        StorageStub storageStub = new StorageStub(taskList);
        DeleteCommand deleteCommand = new DeleteCommand("delete 100");
        DeleteCommand deleteCommand1 = new DeleteCommand("delete abc");

        deleteCommand.execute(taskList, this.ui, storageStub);
        deleteCommand1.execute(taskList, this.ui, storageStub);

        assertTrue(getTaskListOneTodo().equals(taskList));
    }
}