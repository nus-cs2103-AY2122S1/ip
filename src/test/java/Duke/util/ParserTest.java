package Duke.util;

import Duke.command.*;

import Duke.exception.DukeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParserTest {
    @Test
    public void testParserReturnExitCommand(){
        Parser parser = new Parser();
        Command finalCommand = new InvalidCommand();
        try {

            finalCommand = parser.parseCommandString("bye");

        } catch(DukeException e) {

        }
        assertTrue(finalCommand instanceof ExitCommand);
    }

    @Test
    public void testParserReturnInvalidCommand(){
        Parser parser = new Parser();
        Command finalCommand = new InvalidCommand();
        try {

            finalCommand = parser.parseCommandString("diwjiewjiqjeq");

        } catch(DukeException e) {

        }
        assertTrue(finalCommand instanceof InvalidCommand);
    }

    @Test
    public void testParserReturnAddCommandForTodo(){
        Parser parser = new Parser();
        Command finalCommand = new InvalidCommand();
        try {

            finalCommand = parser.parseCommandString("todo test123");

        } catch(DukeException e) {

        }
        assertTrue(finalCommand instanceof AddCommand);
    }

    @Test
    public void testParserReturnAddCommandForDeadline(){
        Parser parser = new Parser();
        Command finalCommand = new InvalidCommand();
        try {

            finalCommand = parser.parseCommandString("deadline test123 /by 2020-Aug-20 21:00");

        } catch(DukeException e) {

        }
        assertTrue(finalCommand instanceof AddCommand);
    }

    @Test
    public void testParserReturnAddCommandForEvent(){
        Parser parser = new Parser();
        Command finalCommand = new InvalidCommand();
        try {

            finalCommand = parser.parseCommandString("event test123 /by 2020-Aug-20 21:00 to 21-Sep-2021 17:00");

        } catch(DukeException e) {

        }
        assertTrue(finalCommand instanceof AddCommand);
    }

    @Test
    public void testParserDescriptionParser(){
        Parser parser = new Parser();
        Command finalCommand = new InvalidCommand();
        try {

            finalCommand = parser.parseCommandString("todo    ");

        } catch(DukeException e) {

        }
        assertTrue(finalCommand instanceof InvalidCommand);
    }

    @Test
    public void testParserReturnMarkCommand(){
        Parser parser = new Parser();
        Command finalCommand = new InvalidCommand();
        try {

            finalCommand = parser.parseCommandString("done 2");

        } catch(DukeException e) {

        }
        assertTrue(finalCommand instanceof MarkCommand);
    }

    @Test
    public void testParserReturnDeleteCommand(){
        Parser parser = new Parser();
        Command finalCommand = new InvalidCommand();
        try {

            finalCommand = parser.parseCommandString("delete 2");

        } catch(DukeException e) {

        }
        assertTrue(finalCommand instanceof DeleteCommand);
    }

    @Test
    public void testParserIntegerParser(){
        Parser parser = new Parser();
        Command finalCommand = new InvalidCommand();
        try {

            finalCommand = parser.parseCommandString("done dfadjowe");

        } catch(DukeException e) {

        }
        assertTrue(finalCommand instanceof InvalidCommand);
    }
}
