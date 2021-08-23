package duke.util;

import duke.command.*;

import duke.exception.DukeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParserTest {
    @Test
    public void testParseCommandString_exitCommand_returnExitCommand(){
        Parser parser = new Parser();
        Command finalCommand = new InvalidCommand();
        try {

            finalCommand = parser.parseCommandString("bye");

        } catch(DukeException e) {

        }
        assertTrue(finalCommand instanceof ExitCommand);
    }

    @Test
    public void testParseCommandString_invalidCommand_returnInvalidCommand(){
        Parser parser = new Parser();
        Command finalCommand = new InvalidCommand();
        try {

            finalCommand = parser.parseCommandString("diwjiewjiqjeq");

        } catch(DukeException e) {

        }
        assertTrue(finalCommand instanceof InvalidCommand);
    }

    @Test
    public void testParseCommandString_todoTask_returnAddCommand(){
        Parser parser = new Parser();
        Command finalCommand = new InvalidCommand();
        try {

            finalCommand = parser.parseCommandString("todo test123");

        } catch(DukeException e) {

        }
        assertTrue(finalCommand instanceof AddCommand);
    }

    @Test
    public void testParseCommandString_deadlineTask_returnAddCommand(){
        Parser parser = new Parser();
        Command finalCommand = new InvalidCommand();
        try {

            finalCommand = parser.parseCommandString("deadline test123 /by 2020-Aug-20 21:00");

        } catch(DukeException e) {

        }
        assertTrue(finalCommand instanceof AddCommand);
    }

    @Test
    public void testParseCommandString_eventTask_returnAddCommand(){
        Parser parser = new Parser();
        Command finalCommand = new InvalidCommand();
        try {

            finalCommand = parser.parseCommandString("event test123 /by 2020-Aug-20 21:00 to 21-Sep-2021 17:00");

        } catch(DukeException e) {

        }
        assertTrue(finalCommand instanceof AddCommand);
    }

    @Test
    public void testParseCommandString_invalidTask_returnInvalidCommand(){
        Parser parser = new Parser();
        Command finalCommand = new InvalidCommand();
        try {

            finalCommand = parser.parseCommandString("todo    ");

        } catch(DukeException e) {

        }
        assertTrue(finalCommand instanceof InvalidCommand);
    }

    @Test
    public void testParseCommandString_markTask_returnMarkCommand(){
        Parser parser = new Parser();
        Command finalCommand = new InvalidCommand();
        try {

            finalCommand = parser.parseCommandString("done 2");

        } catch(DukeException e) {

        }
        assertTrue(finalCommand instanceof MarkCommand);
    }

    @Test
    public void testParseCommandString_deleteTask_returnDeleteCommand(){
        Parser parser = new Parser();
        Command finalCommand = new InvalidCommand();
        try {

            finalCommand = parser.parseCommandString("delete 2");

        } catch(DukeException e) {

        }
        assertTrue(finalCommand instanceof DeleteCommand);
    }

    @Test
    public void testParseCommandString_invalidInteger_returnInvalidCommand(){
        Parser parser = new Parser();
        Command finalCommand = new InvalidCommand();
        try {

            finalCommand = parser.parseCommandString("done dfadjowe");

        } catch(DukeException e) {

        }
        assertTrue(finalCommand instanceof InvalidCommand);
    }
}
