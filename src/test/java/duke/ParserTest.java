package duke;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import duke.commands.DeadlineCommand;
import duke.commands.EventCommand;
import duke.commands.TodoCommand;

public class ParserTest {
    @Test
    public void testParse1() throws DukeException {
        String input = "todo eat sleep eat sleep";
        Parser parser = new Parser(input);
        assertTrue(parser.parse() instanceof TodoCommand);
    }

    @Test
    public void testParse2() throws DukeException {
        String input = "deadline return book /by 2021-03-21";
        Parser parser = new Parser(input);
        assertTrue(parser.parse() instanceof DeadlineCommand);
    }

    @Test
    public void testParse3() throws DukeException {
        String input = "event project meeting /at 4pm";
        Parser parser = new Parser(input);
        assertTrue(parser.parse() instanceof EventCommand);
    }

    @Test
    public void testInvalidInput1() {
        String input = "done";
        Parser parser = new Parser(input);
        assertThrows(DukeException.class, () -> parser.parse());
    }

    @Test
    public void testInvalidInput2() {
        String input = "done b";
        Parser parser = new Parser(input);
        assertThrows(DukeException.class, () -> parser.parse());
    }

    @Test
    public void testInvalidInput3() {
        String input = "blahblahblah";
        Parser parser = new Parser(input);
        assertThrows(DukeException.class, () -> parser.parse());
    }
}
