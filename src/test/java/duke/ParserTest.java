package duke;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import duke.commands.DeadlineCommand;
import duke.commands.EditCommand;
import duke.commands.EventCommand;
import duke.commands.FindCommand;
import duke.commands.ListCommand;
import duke.commands.SortCommand;
import duke.commands.TodoCommand;

public class ParserTest {
    @Test
    public void testParseList() throws DukeException {
        String input = "list";
        Parser parser = new Parser(input);
        assertTrue(parser.parse() instanceof ListCommand);
    }

    @Test
    public void testParseTodo() throws DukeException {
        String input = "todo eat sleep eat sleep";
        Parser parser = new Parser(input);
        assertTrue(parser.parse() instanceof TodoCommand);
    }

    @Test
    public void testParseDeadline() throws DukeException {
        String input = "deadline return book /by 2021-03-21";
        Parser parser = new Parser(input);
        assertTrue(parser.parse() instanceof DeadlineCommand);
    }

    @Test
    public void testParseEvent() throws DukeException {
        String input = "event project meeting /at 4pm";
        Parser parser = new Parser(input);
        assertTrue(parser.parse() instanceof EventCommand);
    }

    @Test
    public void testParseDone() throws DukeException {
        String input = "done 3";
        Parser parser = new Parser(input);
        assertTrue(parser.parse() instanceof EditCommand);
    }

    @Test
    public void testParseDelete() throws DukeException {
        String input = "delete 1";
        Parser parser = new Parser(input);
        assertTrue(parser.parse() instanceof EditCommand);
    }

    @Test
    public void testParseSort() throws DukeException {
        String input = "sort";
        Parser parser = new Parser(input);
        assertTrue(parser.parse() instanceof SortCommand);
    }

    @Test
    public void testParseFind() throws DukeException {
        String input = "find sleep";
        Parser parser = new Parser(input);
        assertTrue(parser.parse() instanceof FindCommand);
    }

    @Test
    public void testInvalidInput1() throws DukeException {
        String input = "blahblahblah";
        Parser parser = new Parser(input);
        assertThrows(DukeException.class, () -> parser.parse());
    }

    @Test
    public void testInvalidInput2() {
        String input = "     ";
        assertThrows(DukeException.class, () -> new Parser(input));
    }

    @Test
    public void testInvalidInput3() throws DukeException {
        String input = "listsdfdlsk";
        Parser parser = new Parser(input);
        assertThrows(DukeException.class, () -> parser.parse());
    }
}
