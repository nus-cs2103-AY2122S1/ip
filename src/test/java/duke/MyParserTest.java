package duke;

import duke.command.Duke;
import duke.command.DukeException;
import duke.command.MyParser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class MyParserTest {
    MyParser parser = new MyParser();
    Duke d = new Duke("test.file");

    @Test
    void delete_noDescription_exceptionThrown() {
        try {
            parser.parse("delete", " ", d);
            fail();
        } catch (DukeException e) {
            assertEquals("OOPS!!! The description of delete cannot be empty", e.getMessage());
        }
    }

    @Test
    void delete_nonInteger_exceptionThrown() {
        try {
            parser.parse("delete", "x", d);
            fail();
        } catch (DukeException e) {
            assertEquals("OOPS!!! The description of delete must be an integer", e.getMessage());
        }
    }

    @Test
    void done_noDescription_exceptionThrown() {
        try {
            parser.parse("done", " ", d);
            fail();
        } catch (DukeException e) {
            assertEquals("OOPS!!! The description of done cannot be empty", e.getMessage());
        }
    }

    @Test
    void done_nonInteger_exceptionThrown() {
        try {
            parser.parse("done", "x", d);
            fail();
        } catch (DukeException e) {
            assertEquals("OOPS!!! The description of done must be an integer", e.getMessage());
        }
    }

    @Test
    void todo_noDescription_exceptionThrown() {
        try {
            parser.parse("todo", " ", d);
            fail();
        } catch (DukeException e) {
            assertEquals("OOPS!!! The description of a todo cannot be empty", e.getMessage());
        }
    }

    @Test
    void deadline_noDescription_exceptionThrown() {
        try {
            parser.parse("deadline", " ", d);
            fail();
        } catch (DukeException e) {
            assertEquals("OOPS!!! The description of a deadline cannot be empty", e.getMessage());
        }
    }

    @Test
    void deadline_noDueBy_exceptionThrown() {
        try {
            parser.parse("deadline", "assignment", d);
            fail();
        } catch (DukeException e) {
            assertEquals("OOPS!!! No deadline has been set. Input with '/by dd/mm/yyyy 0000'", e.getMessage());
        }
    }

    @Test
    void deadline_dueByFormat_exceptionThrown() {
        try {
            parser.parse("deadline", "assignment /by today", d);
            fail();
        } catch (DukeException e) {
            assertEquals("OOPS!!! The date is not formatted as dd/mm/yyyy 0000", e.getMessage());
        }
    }

    @Test
    void event_noDescription_exceptionThrown() {
        try {
            parser.parse("event", " ", d);
            fail();
        } catch (DukeException e) {
            assertEquals("OOPS!!! The description of an event cannot be empty", e.getMessage());
        }
    }

    @Test
    void event_noDueBy_exceptionThrown() {
        try {
            parser.parse("event", "assignment", d);
            fail();
        } catch (DukeException e) {
            assertEquals("OOPS!!! No event time has been set. " +
                    "Input with '/at dd/mm/yyyy 0000'", e.getMessage());
        }
    }

    @Test
    void event_dueByFormat_exceptionThrown() {
        try {
            parser.parse("event", "assignment /at today", d);
            fail();
        } catch (DukeException e) {
            assertEquals("OOPS!!! The date is not formatted as dd/mm/yyyy 0000", e.getMessage());
        }
    }

    @Test
    void unknownCommand_exceptionThrown() {
        try {
            parser.parse("hi", "", d);
            fail();
        } catch (DukeException e) {
            assertEquals("OOPS!!! I'm Sorry, but I don't know what that means", e.getMessage());
        }
    }

}
