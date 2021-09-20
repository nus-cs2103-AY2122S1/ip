package duke;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.errors.DukeException;
import duke.errors.InvalidUserInputException;
import duke.stubs.TaskListStub;


class ParserTest {

    @Test
    void testParseExit() {
        Parser p = new Parser(new TaskListStub());
        try {
            duke.command.Command actual = p.parse("bye");
            assertTrue(actual instanceof duke.command.ExitCommand);
        } catch (DukeException dukeException) {
            fail("Duke Exception thrown when parsing Exit command");
        }
    }

    @Test
    void testParseList() {
        Parser p = new Parser(new TaskListStub());
        try {
            duke.command.Command actual = p.parse("list");
            assertTrue(actual instanceof duke.command.ListCommand);
        } catch (DukeException dukeException) {
            fail("Duke Exception thrown when parsing List command");
        }
    }

    @Test
    void testParseDone() {
        Parser p = new Parser(new TaskListStub());
        try {
            duke.command.Command actual = p.parse("done 2");
            assertTrue(actual instanceof duke.command.DoneCommand);
        } catch (DukeException dukeException) {
            fail("Duke Exception thrown when parsing Done command");
        }
    }

    @Test
    void testParseDelete() {
        Parser p = new Parser(new TaskListStub());
        try {
            duke.command.Command actual = p.parse("delete 3");
            assertTrue(actual instanceof duke.command.DeleteCommand);
        } catch (DukeException dukeException) {
            fail("Duke Exception thrown when parsing Delete command");
        }
    }

    @Test
    void testParseTodo() {
        Parser p = new Parser(new TaskListStub());
        try {
            duke.command.Command actual = p.parse("todo meet a friend");
            assertTrue(actual instanceof duke.command.AddCommand);
        } catch (DukeException dukeException) {
            fail("Duke Exception thrown when parsing Add(todo) command");
        }
    }
    @Test
    void testParseDeadline() {
        Parser p = new Parser(new TaskListStub());
        try {
            duke.command.Command actual = p.parse("deadline project submission /by 2020-11-11");
            assertTrue(actual instanceof duke.command.AddCommand);
        } catch (DukeException dukeException) {
            fail("Duke Exception thrown when parsing Add(Deadline) command");
        }
    }
    @Test
    void testParseEvent() {
        Parser p = new Parser(new TaskListStub());
        try {
            duke.command.Command actual = p.parse("event project submission /at 2020-11-11");
            assertTrue(actual instanceof duke.command.AddCommand);
        } catch (DukeException dukeException) {
            fail("Duke Exception thrown when parsing Add(Event) command");
        }
    }
    @Test
    void testParseCheckDate() {
        Parser p = new Parser(new TaskListStub());
        try {
            duke.command.Command actual = p.parse("check 2019-10-15");
            assertTrue(actual instanceof duke.command.CheckDateCommand);
        } catch (DukeException dukeException) {
            fail("Duke Exception thrown when parsing checkDate command");
        }
    }

    @Test
    void testParseInvalid() {
        Parser p = new Parser(new TaskListStub());
        try {
            duke.command.Command actual = p.parse("bleh");
        } catch (DukeException dukeException) {
            assertTrue(dukeException instanceof InvalidUserInputException,
                    "Duke Exception thrown when parsing Invalid Command");
        }
    }
}
