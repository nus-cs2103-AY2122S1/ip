package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.commands.AddTaskCommand;
import duke.commands.ByeCommand;
import duke.commands.DeleteCommand;
import duke.commands.DoneCommand;
import duke.commands.ListCommand;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Todo;

public class ParserTest {
    @Test
    public void parseAddTaskDeadline() throws DukeException {
        Deadline deadline = new Deadline("test description 123", "20-01-2021 02:13");
        assertEquals(new AddTaskCommand(deadline),
                Parser.parse("deadline test description 123 /by 20-01-2021 02:13"));
    }

    @Test
    public void parseAddTaskDeadline_noDescription_exceptionThrown() {
        try {
            Parser.parse("deadline");
            fail();
        } catch (DukeException e) {
            assertEquals("Deadline details cannot be empty.", e.getMessage());
        }
    }

    @Test
    public void parseAddTaskDeadline_noBy_exceptionThrown() {
        try {
            Parser.parse("deadline abc");
            fail();
        } catch (DukeException e) {
            assertEquals("Deadline descriptions must contain /by [dd-mm-yyyy hh:mm]", e.getMessage());
        }
    }

    @Test
    public void parseAddTaskEvent() throws DukeException {
        Event event = new Event("test description 123", "20-01-2021 02:13");
        assertEquals(new AddTaskCommand(event),
                Parser.parse("event test description 123 /at 20-01-2021 02:13"));
    }

    @Test
    public void parseAddTaskEvent_noDescription_exceptionThrown() {
        try {
            Parser.parse("event");
            fail();
        } catch (DukeException e) {
            assertEquals("Event details cannot be empty.", e.getMessage());
        }
    }

    @Test
    public void parseAddTaskEvent_noAt_exceptionThrown() {
        try {
            Parser.parse("event abc");
            fail();
        } catch (DukeException e) {
            assertEquals("Event descriptions must contain /at [dd-mm-yyyy hh:mm]", e.getMessage());
        }
    }

    @Test
    public void parseAddTaskTodo() throws DukeException {
        Todo todo = new Todo("test description 123");
        assertEquals(new AddTaskCommand(todo), Parser.parse("todo test description 123"));
    }

    @Test
    public void parseAddTaskTodo_noDescription_exceptionThrown() {
        try {
            Parser.parse("todo");
            fail();
        } catch (DukeException e) {
            assertEquals("Todo details cannot be empty.", e.getMessage());
        }
    }

    @Test
    public void parseBye() throws DukeException {
        assertEquals(new ByeCommand(), Parser.parse("bye"));
    }

    @Test
    public void parseList() throws DukeException {
        assertEquals(new ListCommand(), Parser.parse("list"));
    }

    @Test
    public void parseDelete() throws DukeException {
        assertEquals(new DeleteCommand(0), Parser.parse("delete 1"));
    }

    @Test
    public void parseDelete_negativeIndex_exceptionThrown() {
        try {
            Parser.parse("delete 0");
            fail();
        } catch (DukeException e) {
            assertEquals("Invalid task number. Task number should be positive.", e.getMessage());
        }
    }

    @Test
    public void parseDelete_nonNumericTaskIndex_exceptionThrown() {
        try {
            Parser.parse("delete abc");
            fail();
        } catch (DukeException e) {
            assertEquals("Invalid task number. Sample input with correct format: "
                    + "[command] [taskNo] eg. 'done 2'", e.getMessage());
        }
    }

    @Test
    public void parseDone() throws DukeException {
        assertEquals(new DoneCommand(0), Parser.parse("done 1"));
    }

    @Test
    public void parseDone_negativeIndex_exceptionThrown() {
        try {
            Parser.parse("done 0");
            fail();
        } catch (DukeException e) {
            assertEquals("Invalid task number. Task number should be positive.", e.getMessage());
        }
    }

    @Test
    public void parseDone_nonNumericTaskIndex_exceptionThrown() {
        try {
            Parser.parse("done abc");
            fail();
        } catch (DukeException e) {
            assertEquals("Invalid task number. Sample input with correct format: "
                    + "[command] [taskNo] eg. 'done 2'", e.getMessage());
        }
    }
}
