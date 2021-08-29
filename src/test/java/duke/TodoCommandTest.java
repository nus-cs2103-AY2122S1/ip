package duke;

import duke.command.DeadlineCommand;
import duke.command.EventCommand;
import duke.command.TodoCommand;
import org.junit.jupiter.api.Test;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.command.Command;
import duke.task.Todo;

import static org.junit.jupiter.api.Assertions.*;

public class TodoCommandTest {
    Storage store;
    {
        try {
            store = Storage.createStorage("test/db.txt");
        } catch (DukeException e) {
            e.printStackTrace();
        }
    }
    TaskList tl = new TaskList();

    @Test
    void testAddTodo() {
        Command c = new TodoCommand(this.tl, "a simple todo task");
        try {
            c.execute();
        } catch (duke.DukeException e) {
            fail();
        }
        Task expected = new Todo("a simple todo task", false);
        try {
            assertEquals(expected.getLabel(),tl.get(1).getLabel()); //same label
            assertEquals(expected.getTaskType(), tl.get(1).getTaskType()); //same type
        } catch(DukeException e) {
            fail(e.getMessage());
        }
        assertEquals(1, tl.size()); //exactly one task added
    }

    @Test
    void testAddDeadline() {
        Command c = new DeadlineCommand(this.tl, "a simple deadline task /by 2020-02-20 00:00:00");
        try {
            c.execute();
            Task expected = new Deadline("a simple deadline task", "2020-02-20 00:00:00", false);
            assertEquals(expected.getLabel(), tl.get(1).getLabel());
            assertEquals(expected.getTaskType(), tl.get(1).getTaskType());
        } catch(DukeException e) {
            fail(e.getMessage());
        }
        assertEquals(1, tl.size());
    }

    @Test
    void testAddEvent() {
        Command c = new EventCommand(this.tl, "a simple event task /at 2020-02-20 00:00:00");
        try {
            c.execute();
            Task expected = new Event("a simple event task", "2020-02-20 00:00:00", false);
            assertEquals(expected.getLabel(), tl.get(1).getLabel());
            assertEquals(expected.getTaskType(), tl.get(1).getTaskType());
        } catch(DukeException e) {
            fail(e.getMessage());
        }
        assertEquals(1, tl.size());
    }

    public class UiStub extends duke.UserInterface {

        public void notifySuccessfulAdd(duke.TaskList tl) {
            Task t = null;
            try {
                t = tl.get(0);
            } catch (DukeException e) {
                fail();
            }
            System.out.println("You have successfully added " + t);
        }
    }
}


