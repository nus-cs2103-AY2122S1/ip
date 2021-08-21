package duke.command;

import duke.StorageStub;
import duke.TaskList;
import duke.Ui;
import duke.task.Todo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class MarkCommandTest {

    private static final String DESCRIPTION = "task description";

    private final Ui ui = new Ui();

    private TaskList getTaskListOneUndoneTodo() {
        TaskList tasks = new TaskList();
        tasks.add(new Todo(DESCRIPTION));

        return tasks;
    }

    private TaskList getTaskListOneDoneTodo() {
        TaskList tasks = new TaskList();
        Todo doneTodo = new Todo(DESCRIPTION);
        doneTodo.markAsDone();
        tasks.add(doneTodo);

        return tasks;
    }

    @Test
    void execute_doneInput_taskMarkedAsDone() {
        TaskList tasks = getTaskListOneUndoneTodo();
        StorageStub storageStub = new StorageStub(tasks);
        MarkCommand markCommand = new MarkCommand("done 1");

        markCommand.execute(tasks, this.ui, storageStub);

        assertTrue(getTaskListOneDoneTodo().equals(tasks));
    }

    @Test
    void execute_missingIndex_taskNotMarkedAsDone() {
        TaskList tasks = getTaskListOneUndoneTodo();
        StorageStub storageStub = new StorageStub(tasks);
        MarkCommand markCommand = new MarkCommand("done");
        MarkCommand markCommand1 = new MarkCommand("done ");

        markCommand.execute(tasks, this.ui, storageStub);
        markCommand1.execute(tasks, this.ui, storageStub);

        assertTrue(getTaskListOneUndoneTodo().equals(tasks));
    }

    @Test
    void execute_missingSpace_taskNotMarkedAsDone() {
        TaskList tasks = getTaskListOneUndoneTodo();
        StorageStub storageStub = new StorageStub(tasks);
        MarkCommand markCommand = new MarkCommand("done1");

        markCommand.execute(tasks, this.ui, storageStub);

        assertTrue(getTaskListOneUndoneTodo().equals(tasks));
    }

    @Test
    void execute_invalidIndex_taskNotMarkedAsDone() {
        TaskList tasks = getTaskListOneUndoneTodo();
        StorageStub storageStub = new StorageStub(tasks);
        MarkCommand markCommand = new MarkCommand("done 100");
        MarkCommand markCommand1 = new MarkCommand("done abc");

        markCommand.execute(tasks, this.ui, storageStub);
        markCommand1.execute(tasks, this.ui, storageStub);

        assertTrue(getTaskListOneUndoneTodo().equals(tasks));
    }
}