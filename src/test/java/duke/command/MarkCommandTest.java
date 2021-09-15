package duke.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import duke.StorageStub;
import duke.TaskList;
import duke.Ui;
import duke.task.Todo;

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
        MarkCommand markCommandMissingSpaceIndex = new MarkCommand("done");
        MarkCommand markCommandMissingIndex = new MarkCommand("done ");

        markCommandMissingSpaceIndex.execute(tasks, this.ui, storageStub);
        markCommandMissingIndex.execute(tasks, this.ui, storageStub);

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
        MarkCommand markCommandInvalidLargeIndex = new MarkCommand("done 100");
        MarkCommand markCommandInvalidNegativeIndex = new MarkCommand("done -1");
        MarkCommand markCommandInvalidZeroIndex = new MarkCommand("done 0");
        MarkCommand markCommandInvalidFormatIndex = new MarkCommand("done abc");

        markCommandInvalidLargeIndex.execute(tasks, this.ui, storageStub);
        markCommandInvalidNegativeIndex.execute(tasks, this.ui, storageStub);
        markCommandInvalidZeroIndex.execute(tasks, this.ui, storageStub);
        markCommandInvalidFormatIndex.execute(tasks, this.ui, storageStub);

        assertTrue(getTaskListOneUndoneTodo().equals(tasks));
    }

    @Test
    void equals_sameMarkCommand_true() {
        MarkCommand markCommand = new MarkCommand("list");
        MarkCommand markCommand1 = new MarkCommand("list");

        assertEquals(markCommand, markCommand1);
    }

    @Test
    void equals_differentMarkCommand_false() {
        MarkCommand markCommand = new MarkCommand("list");
        MarkCommand markCommand1 = new MarkCommand("other");

        assertNotEquals(markCommand, markCommand1);
    }
}
