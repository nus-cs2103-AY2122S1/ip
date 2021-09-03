package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.commands.AddCommand;
import duke.commands.DeleteCommand;
import duke.commands.DoneCommand;
import duke.commands.ListCommand;
import duke.exceptions.CommandParamException;
import duke.exceptions.DukeException;
import duke.exceptions.EmptyDescriptionException;
import duke.exceptions.UnknownCommandException;
import duke.util.Parser;

public class ParserTest {

    @Test
    public void addCommandTest_todo_sameResult()
            throws CommandParamException, EmptyDescriptionException, UnknownCommandException {
        assertEquals(new AddCommand("todo", "go jogging"),
                Parser.decipher("todo go jogging"));

    }

    @Test
    public void addCommandTest_todo_emptyDescriptionExceptionThrown() {
        try {
            assertEquals(new AddCommand("todo", "qwe"), Parser.decipher("todo"));
            fail();
        } catch (DukeException e) {
            assertEquals(new EmptyDescriptionException("todo").getMessage(), e.getMessage());
        }
    }

    @Test
    public void addCommandTest_deadline_sameResult()
            throws CommandParamException, EmptyDescriptionException, UnknownCommandException {
        assertEquals(new AddCommand("deadline", "go jogging /by 2021-08-21 2359"),
                Parser.decipher("deadline go jogging /by 2021-08-21 2359"));
    }

    @Test
    public void addCommandTest_deadline_emptyDescriptionExceptionThrown() {
        try {
            assertEquals(new AddCommand("deadline", ""), Parser.decipher("deadline"));
            fail();
        } catch (DukeException e) {
            assertEquals(new EmptyDescriptionException("deadline").getMessage(), e.getMessage());
        }
    }

    @Test
    public void addCommandTest_event_sameResult()
            throws CommandParamException, EmptyDescriptionException, UnknownCommandException {
        assertEquals(new AddCommand("event", "go jogging /at 2021-08-21 2359"),
                Parser.decipher("event go jogging /at 2021-08-21 2359"));
    }

    @Test
    public void addCommandTest_event_emptyDescriptionExceptionThrown() {
        try {
            assertEquals(new AddCommand("event", ""), Parser.decipher("event"));
            fail();
        } catch (DukeException e) {
            assertEquals(new EmptyDescriptionException("event").getMessage(), e.getMessage());
        }
    }

    @Test
    public void deleteCommandTest_sameResult()
            throws CommandParamException, EmptyDescriptionException, UnknownCommandException {
        assertEquals(new DeleteCommand(0), Parser.decipher("delete 1"));
        assertEquals(new DeleteCommand(1), Parser.decipher("delete 2"));
        assertEquals(new DeleteCommand(2), Parser.decipher("delete 3"));
    }

    @Test
    public void deleteCommandTest_emptyDescriptionExceptionThrown() {
        try {
            assertEquals(new DeleteCommand(123), Parser.decipher("delete"));
            fail();
        } catch (DukeException e) {
            assertEquals(new EmptyDescriptionException("delete").getMessage(), e.getMessage());
        }
    }

    @Test
    public void doneCommandTest_sameResult()
            throws CommandParamException, EmptyDescriptionException, UnknownCommandException {
        assertEquals(new DoneCommand(0), Parser.decipher("done 1"));
        assertEquals(new DoneCommand(1), Parser.decipher("done 2"));
        assertEquals(new DoneCommand(2), Parser.decipher("done 3"));
    }

    @Test
    public void doneCommandTest_emptyDescriptionExceptionThrown() {
        try {
            assertEquals(new DoneCommand(123), Parser.decipher("done"));
            fail();
        } catch (DukeException e) {
            assertEquals(new EmptyDescriptionException("done").getMessage(), e.getMessage());
        }
    }

    @Test
    public void listCommandTest_sameResult()
            throws CommandParamException, EmptyDescriptionException, UnknownCommandException {
        assertEquals(new ListCommand(), Parser.decipher("list"));
    }


}
