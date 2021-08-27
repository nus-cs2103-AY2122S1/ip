package duke.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import duke.StorageStub;
import duke.TaskList;
import duke.Ui;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

class AddCommandTest {

    private static final LocalDate localDate = LocalDate.parse("2020-01-01");

    // Ui returns largely constants. No need for stub;
    private final Ui ui = new Ui();

    private static TaskList getTaskListTodo() {
        ArrayList<Task> taskArrList = new ArrayList<>();
        taskArrList.add(new Todo("task description"));
        return new TaskList(taskArrList);
    }

    private static TaskList getTaskListEvent() {
        ArrayList<Task> taskArrList = new ArrayList<>();
        taskArrList.add(new Event("task description", localDate));
        return new TaskList(taskArrList);
    }

    private static TaskList getTaskListDeadline() {
        ArrayList<Task> taskArrList = new ArrayList<>();
        taskArrList.add(new Deadline("task description", localDate));
        return new TaskList(taskArrList);
    }

    @Test
    void execute_todoInput_todoAdded() {
        TaskList tasks = new TaskList();
        StorageStub storageStub = new StorageStub(tasks);
        AddCommand addCommand = new AddCommand("todo task description");
        addCommand.execute(tasks, this.ui, storageStub);

        assertTrue(getTaskListTodo().equals(tasks));
    }

    @Test
    void execute_eventInput_eventAdded() {
        TaskList tasks = new TaskList();
        StorageStub storageStub = new StorageStub(tasks);
        AddCommand addCommand = new AddCommand("event task description /at 1/1/2020");
        addCommand.execute(tasks, this.ui, storageStub);

        assertTrue(getTaskListEvent().equals(tasks));
    }

    @Test
    void execute_deadlineInput_deadlineAdded() {
        TaskList tasks = new TaskList();
        StorageStub storageStub = new StorageStub(tasks);
        AddCommand addCommand = new AddCommand("deadline task description /by 1/1/2020");
        addCommand.execute(tasks, this.ui, storageStub);

        assertTrue(getTaskListDeadline().equals(tasks));
    }

    @Test
    void execute_invalidCommand_noTaskAdded() {
        TaskList tasks = new TaskList();
        StorageStub storageStub = new StorageStub(tasks);
        AddCommand addCommand = new AddCommand("bogus");
        addCommand.execute(tasks, this.ui, storageStub);

        assertTrue(new TaskList().equals(tasks));
    }

    @Test
    void execute_missingTaskDescription_noTaskAdded() {
        TaskList tasks = new TaskList();
        StorageStub storageStub = new StorageStub(tasks);
        AddCommand addCommand = new AddCommand("event /at 1/1/2020");
        AddCommand addCommand1 = new AddCommand("todo");
        AddCommand addCommand2 = new AddCommand("todo ");
        AddCommand addCommand3 = new AddCommand("event");
        AddCommand addCommand4 = new AddCommand("event ");
        AddCommand addCommand5 = new AddCommand("event /at 1/1/2020");
        AddCommand addCommand6 = new AddCommand("deadline");
        AddCommand addCommand7 = new AddCommand("deadline ");
        AddCommand addCommand8 = new AddCommand("deadline /by 1/1/2020");

        addCommand.execute(tasks, this.ui, storageStub);
        addCommand1.execute(tasks, this.ui, storageStub);
        addCommand2.execute(tasks, this.ui, storageStub);
        addCommand3.execute(tasks, this.ui, storageStub);
        addCommand4.execute(tasks, this.ui, storageStub);
        addCommand5.execute(tasks, this.ui, storageStub);
        addCommand6.execute(tasks, this.ui, storageStub);
        addCommand7.execute(tasks, this.ui, storageStub);
        addCommand8.execute(tasks, this.ui, storageStub);

        assertTrue(new TaskList().equals(tasks));
    }

    @Test
    void execute_missingSpaces_noTaskAdded() {
        TaskList tasks = new TaskList();
        StorageStub storageStub = new StorageStub(tasks);
        // User might want "a" as description.
        AddCommand addCommand = new AddCommand("todoa");
        AddCommand addCommand1 = new AddCommand("eventa");
        AddCommand addCommand2 = new AddCommand("event abc/at 1/1/2020");
        AddCommand addCommand3 = new AddCommand("eventabc /at1/1/2020");
        AddCommand addCommand4 = new AddCommand("deadlinea");
        AddCommand addCommand5 = new AddCommand("deadline abc/by 1/1/2020");
        AddCommand addCommand6 = new AddCommand("deadlineabc /at1/1/2020");


        addCommand.execute(tasks, this.ui, storageStub);
        addCommand1.execute(tasks, this.ui, storageStub);
        addCommand2.execute(tasks, this.ui, storageStub);
        addCommand3.execute(tasks, this.ui, storageStub);
        addCommand4.execute(tasks, this.ui, storageStub);
        addCommand5.execute(tasks, this.ui, storageStub);
        addCommand6.execute(tasks, this.ui, storageStub);

        assertTrue(new TaskList().equals(tasks));
    }

    @Test
    void equals_sameAddCommand_true() {
        AddCommand addCommand = new AddCommand("list");
        AddCommand addCommand1 = new AddCommand("list");

        assertEquals(addCommand, addCommand1);
    }

    @Test
    void equals_differentAddCommand_false() {
        AddCommand addCommand = new AddCommand("list");
        AddCommand addCommand1 = new AddCommand("other");

        assertNotEquals(addCommand, addCommand1);
    }

}
