package lifeline.parser;

import static lifeline.util.ErrorString.ERROR_INVALID_COMMAND;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import lifeline.command.Command;
import lifeline.exception.LifelineException;

public class ParserTest {

    @Test
    public void parse_listAsCommand_success() throws LifelineException {
        Command c = Parser.parse("list");
        assertEquals(Command.valueOf("LIST"), c);
    }

    @Test
    public void parse_byeAsCommand_success() throws LifelineException {
        Command c = Parser.parse("bye");
        assertEquals(Command.valueOf("BYE"), c);
    }

    @Test
    public void parse_doneAsCommand_success() throws LifelineException {
        Command c = Parser.parse("done");
        assertEquals(Command.valueOf("DONE"), c);
    }

    @Test
    public void parse_deleteAsCommand_success() throws LifelineException {
        Command c = Parser.parse("delete");
        assertEquals(Command.valueOf("DELETE"), c);
    }

    @Test
    public void parse_deadlineAsCommand_success() throws LifelineException {
        Command c = Parser.parse("deadline");
        assertEquals(Command.valueOf("DEADLINE"), c);
    }

    @Test
    public void parse_eventAsCommand_success() throws LifelineException {
        Command c = Parser.parse("event");
        assertEquals(Command.valueOf("EVENT"), c);
    }

    @Test
    public void parse_todoAsCommand_success() throws LifelineException {
        Command c = Parser.parse("todo");
        assertEquals(Command.valueOf("TODO"), c);
    }

    @Test
    public void parse_invalidCommand_exceptionThrown() {
        try {
            Command c = Parser.parse("blah");
            fail();
        } catch (LifelineException e) {
            assertEquals(ERROR_INVALID_COMMAND, e.getMessage());
        }
    }
}
