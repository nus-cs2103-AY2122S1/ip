package duke;

import duke.command.*;
import duke.exception.DukeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {
    @Test
    public void byeParsing() {
        try {
            Command c = Parser.parse("bye");
            assertTrue(c instanceof End);
        } catch (DukeException e) {
            fail();
        }        
    }

    @Test
    public void listParsing() {
        try {
            Command c = Parser.parse("list");
            assertTrue(c instanceof List);
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void doneParsing() {
        try {
            Command c = Parser.parse("done");
            assertTrue(c instanceof Action);
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void deleteParsing() {
        try {
            Command c = Parser.parse("delete");
            assertTrue(c instanceof Action);
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void todoParsing_emptyDescription() {
        try {
            Command c = Parser.parse("todo");
            fail();
        } catch (DukeException e) {
            assertEquals("A task requires a description following it!", e.getMessage());
        }
    }

    @Test
    public void todoParsing() {
        try {
            Command c = Parser.parse("todo wah");
            assertTrue(c instanceof Add);
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void eventParsing_missingDate() {
        try {
            Command c = Parser.parse("event wah");
            fail();
            assertTrue(c instanceof Add);
        } catch (DukeException e) {
            assertEquals("Invalid format! Try `event ${item} /at ${time}`", e.getMessage());
        }
    }

    @Test
    public void eventParsing_withDateTime() {
        try {
            Command c = Parser.parse("event wah /at 2021-08-24 20:01");
            assertTrue(c instanceof Add);
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void deadlineParsing_missingDate() {
        try {
            Command c = Parser.parse("deadline wah");
            fail();
            assertTrue(c instanceof Add);
        } catch (DukeException e) {
            assertEquals("Invalid format! Try `deadline ${item} /by ${time}`", e.getMessage());
        }
    }

    @Test
    public void deadlineParsing_withDateTime() {
        try {
            Command c = Parser.parse("deadline wah /by 2021-08-24 20:01");
            assertTrue(c instanceof Add);
        } catch (DukeException e) {
            fail();
        }
    }
}
