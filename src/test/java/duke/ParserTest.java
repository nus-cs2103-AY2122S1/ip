package duke;


import duke.commands.AddTaskCommand;
import duke.commands.ByeCommand;
import duke.commands.DeleteCommand;
import duke.commands.DoneCommand;
import duke.commands.ListCommand;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Todo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {
    @Test
    public void testParseAddTaskDeadline() throws DukeException {
        Deadline deadline = new Deadline("test description 123", "20-01-2021 02:13");
        assertEquals(new AddTaskCommand(deadline),
                Parser.parse("deadline test description 123 /by 20-01-2021 02:13"));
    }

    @Test
    public void testParseAddTaskDeadline_noDescription_exceptionThrown() {
        try {
            Parser.parse("deadline");
            fail();
        } catch (DukeException e) {
            assertEquals("Task details cannot be empty", e.getMessage());
        }
    }
    
    @Test
    public void testParseAddTaskDeadline_noBy_exceptionThrown() {
        try {
            Parser.parse("deadline abc");
            fail();
        } catch (DukeException e) {
            assertEquals("Deadline descriptions must contain /by [dd-mm-yyyy hh:mm]", e.getMessage());
        }
    }
    
    @Test
    public void testParseAddTaskEvent() throws DukeException {
        Event event = new Event("test description 123", "20-01-2021 02:13");
        assertEquals(new AddTaskCommand(event),
                Parser.parse("event test description 123 /at 20-01-2021 02:13"));
    }

    @Test
    public void testParseAddTaskEvent_noDescription_exceptionThrown() {
        try {
            Parser.parse("event");
            fail();
        } catch (DukeException e) {
            assertEquals("Task details cannot be empty", e.getMessage());
        }
    }

    @Test
    public void testParseAddTaskEvent_noAt_exceptionThrown() {
        try {
            Parser.parse("event abc");
            fail();
        } catch (DukeException e) {
            assertEquals("Event descriptions must contain /at [dd-mm-yyyy hh:mm]", e.getMessage());
        }
    }
    
    @Test
    public void testParseAddTaskTodo() throws DukeException {
        Todo todo = new Todo("test description 123");
        assertEquals(new AddTaskCommand(todo), Parser.parse("todo test description 123"));
    }
    
    @Test
    public void testParseAddTaskTodo_noDescription_exceptionThrown() {
        try {
            Parser.parse("todo");
            fail();
        } catch (DukeException e) {
            assertEquals("Task details cannot be empty", e.getMessage());
        }
    }

    @Test
    public void testParseBye() throws DukeException {
        assertEquals(new ByeCommand(), Parser.parse("bye"));
    }

    @Test
    public void testParseList() throws DukeException {
        assertEquals(new ListCommand(), Parser.parse("list"));
    }
    
    @Test
    public void testParseDelete() throws DukeException {
        assertEquals(new DeleteCommand(0), Parser.parse("delete 1"));
    }

    @Test
    public void testParseDelete_negativeIndex_exceptionThrown() {
        try {
            Parser.parse("delete 0");
            fail();
        } catch (DukeException e) {
            assertEquals("Invalid task number. Task number should be positive.", e.getMessage());
        }
    }

    @Test
    public void testParseDelete_nonNumericTaskIndex_exceptionThrown() {
        try {
            Parser.parse("delete abc");
            fail();
        } catch (DukeException e) {
            assertEquals("Invalid task number. Sample input with correct format: " 
                            + "[command] [taskNo] eg. 'done 2'", e.getMessage());
        }
    }
    
    @Test
    public void testParseDone() throws DukeException {
        assertEquals(new DoneCommand(0), Parser.parse("done 1"));
    }

    @Test
    public void testParseDone_negativeIndex_exceptionThrown() {
        try {
            Parser.parse("done 0");
            fail();
        } catch (DukeException e) {
            assertEquals("Invalid task number. Task number should be positive.", e.getMessage());
        }
    }

    @Test
    public void testParseDone_nonNumericTaskIndex_exceptionThrown() {
        try {
            Parser.parse("done abc");
            fail();
        } catch (DukeException e) {
            assertEquals("Invalid task number. Sample input with correct format: "
                    + "[command] [taskNo] eg. 'done 2'", e.getMessage());
        }
    }

}
