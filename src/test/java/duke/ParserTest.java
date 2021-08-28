package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

public class ParserTest {
    @Test
    public void testParse1() throws DukeException {
        String input = "todo eat sleep eat sleep";
        String[] output = {"todo", "eat sleep eat sleep"};
        Parser parser = new Parser(input);
        assertEquals(Arrays.toString(output), Arrays.toString(parser.parse()));
    }

    @Test
    public void testParse2() throws DukeException {
        String input = "deadline return book /by 2021-03-21";
        String[] output = {"deadline", "return book", "2021-03-21"};
        Parser parser = new Parser(input);
        assertEquals(Arrays.toString(output), Arrays.toString(parser.parse()));
    }

    @Test
    public void testParse3() throws DukeException {
        String input = "event project meeting /at 4pm";
        String[] output = {"event", "project meeting", "4pm"};
        Parser parser = new Parser(input);
        assertEquals(Arrays.toString(output), Arrays.toString(parser.parse()));
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
