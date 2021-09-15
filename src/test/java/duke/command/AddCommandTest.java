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
        AddCommand addCommandTodoMissingSpaceDescription = new AddCommand("todo");
        AddCommand addCommandTodoMissingDescription = new AddCommand("todo ");
        AddCommand addCommandTodoBlankDescription = new AddCommand("todo  ");

        AddCommand addCommandEventMissingSpaceDescription = new AddCommand("event");
        AddCommand addCommandEventMissingDescription = new AddCommand("event ");
        AddCommand addCommandEventMissingDescription1 = new AddCommand("event /at 1/1/2020");
        AddCommand addCommandEventBlankDescription = new AddCommand("event  ");
        AddCommand addCommandEventBlankDescription1 = new AddCommand("event  /at 1/1/2020");

        addCommandTodoMissingSpaceDescription.execute(tasks, this.ui, storageStub);
        addCommandTodoMissingDescription.execute(tasks, this.ui, storageStub);
        addCommandTodoBlankDescription.execute(tasks, this.ui, storageStub);

        addCommandEventMissingSpaceDescription.execute(tasks, this.ui, storageStub);
        addCommandEventMissingDescription.execute(tasks, this.ui, storageStub);
        addCommandEventMissingDescription1.execute(tasks, this.ui, storageStub);
        addCommandEventBlankDescription.execute(tasks, this.ui, storageStub);
        addCommandEventBlankDescription1.execute(tasks, this.ui, storageStub);

        assertTrue(new TaskList().equals(tasks));
    }

    @Test
    void execute_missingSpaces_noTaskAdded() {
        TaskList tasks = new TaskList();
        StorageStub storageStub = new StorageStub(tasks);

        AddCommand addCommandTodoMissingSpaceAfterCommand = new AddCommand("todoa");
        AddCommand addCommandEventMissingSpaceAfterCommand = new AddCommand("eventa");
        AddCommand addCommandEventMissingSpaceBeforeDescriptor = new AddCommand("event abc/at 1/1/2020");
        AddCommand addCommandEventMissingSpaceAfterDescriptor = new AddCommand("event abc /at1/1/2020");


        addCommandTodoMissingSpaceAfterCommand.execute(tasks, this.ui, storageStub);
        addCommandEventMissingSpaceAfterCommand.execute(tasks, this.ui, storageStub);
        addCommandEventMissingSpaceBeforeDescriptor.execute(tasks, this.ui, storageStub);
        addCommandEventMissingSpaceAfterDescriptor.execute(tasks, this.ui, storageStub);

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
