package duke.parser;

import duke.data.DukeException;
import duke.parser.Parser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {
    private Parser parser = new Parser();

    @Test
    public void testParseDeadline() throws DukeException {
        String input = "deadline fightmaster /by 2030-10-12 9:30";
        String[] splitInput = this.parser.splitType(input);
        String[] furtherSplit = this.parser.parseDeadline(splitInput);
        String[] expected = {"fightmaster ", " 2030-10-12 9:30"};
        assertArrayEquals(expected, furtherSplit);
    }
}
