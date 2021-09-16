package duke.util;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.InvalidCommand;
import duke.command.MarkCommand;
import duke.exception.DukeException;

public class ParserTest {
    @Test
    public void testParseCommandString_exitCommand_returnExitCommand() {
        Parser parser = new Parser();
        Command finalCommand;
        Store storage = new Store("data/duke.txt");
        Tasklist tempList = new Tasklist();
        try {

            finalCommand = parser.parseCommandString("bye", tempList, storage);

        } catch (DukeException e) {

            finalCommand = new InvalidCommand();

        }
        assertTrue(finalCommand instanceof ExitCommand);
    }

    @Test
    public void testParseCommandString_invalidCommand_returnInvalidCommand() {
        Parser parser = new Parser();
        Command finalCommand = new InvalidCommand();
        Store storage = new Store("data/duke.txt");
        Tasklist tempList = new Tasklist();
        try {

            finalCommand = parser.parseCommandString("diwjiewjiqjeq", tempList, storage);

        } catch (DukeException e) {

            finalCommand = new InvalidCommand();

        }
        assertTrue(finalCommand instanceof InvalidCommand);
    }

    @Test
    public void testParseCommandString_todoTask_returnAddCommand() {
        Parser parser = new Parser();
        Command finalCommand = new InvalidCommand();
        Store storage = new Store("data/duke.txt");
        Tasklist tempList = new Tasklist();
        try {

            finalCommand = parser.parseCommandString("todo test123", tempList, storage);

        } catch (DukeException e) {

            finalCommand = new InvalidCommand();

        }
        assertTrue(finalCommand instanceof AddCommand);
    }

    @Test
    public void testParseCommandString_deadlineTask_returnAddCommand() {
        Parser parser = new Parser();
        Command finalCommand = new InvalidCommand();
        Store storage = new Store("data/duke.txt");
        Tasklist tempList = new Tasklist();
        try {

            finalCommand = parser.parseCommandString("deadline test123 /by 2020-Aug-20 21:00", tempList,
                storage);

        } catch (DukeException e) {

            finalCommand = new InvalidCommand();

        }
        assertTrue(finalCommand instanceof AddCommand);
    }

    @Test
    public void testParseCommandString_eventTask_returnAddCommand() {
        Parser parser = new Parser();
        Command finalCommand = new InvalidCommand();
        Store storage = new Store("data/duke.txt");
        Tasklist tempList = new Tasklist();
        try {

            finalCommand = parser.parseCommandString(
            "event test123 /by 2020-Aug-20 21:00 to 21-Sep-2021 17:00", tempList, storage);

        } catch (DukeException e) {

            finalCommand = new InvalidCommand();

        }
        assertTrue(finalCommand instanceof AddCommand);
    }

    @Test
    public void testParseCommandString_invalidTask_returnInvalidCommand() {
        Parser parser = new Parser();
        Command finalCommand = new InvalidCommand();
        Store storage = new Store("data/duke.txt");
        Tasklist tempList = new Tasklist();
        try {

            finalCommand = parser.parseCommandString("todo    ", tempList, storage);

        } catch (DukeException e) {

            finalCommand = new InvalidCommand();

        }
        assertTrue(finalCommand instanceof InvalidCommand);
    }

    @Test
    public void testParseCommandString_markTask_returnMarkCommand() {
        Parser parser = new Parser();
        Command finalCommand = new InvalidCommand();
        Store storage = new Store("data/duke.txt");
        Tasklist tempList = new Tasklist();
        try {

            finalCommand = parser.parseCommandString("done 2", tempList, storage);

        } catch (DukeException e) {

            finalCommand = new InvalidCommand();

        }
        assertTrue(finalCommand instanceof MarkCommand);
    }

    @Test
    public void testParseCommandString_deleteTask_returnDeleteCommand() {
        Parser parser = new Parser();
        Command finalCommand = new InvalidCommand();
        Store storage = new Store("data/duke.txt");
        Tasklist tempList = new Tasklist();
        try {

            finalCommand = parser.parseCommandString("delete 2", tempList, storage);

        } catch (DukeException e) {

            finalCommand = new InvalidCommand();

        }
        assertTrue(finalCommand instanceof DeleteCommand);
    }

    @Test
    public void testParseCommandString_invalidInteger_returnInvalidCommand() {
        Parser parser = new Parser();
        Command finalCommand = new InvalidCommand();
        Store storage = new Store("data/duke.txt");
        Tasklist tempList = new Tasklist();
        try {

            finalCommand = parser.parseCommandString("done dfadjowe", tempList, storage);

        } catch (DukeException e) {

            finalCommand = new InvalidCommand();

        }
        assertTrue(finalCommand instanceof InvalidCommand);
    }
}
