package duke;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;



public class ParserTest {

    @Test
    public void parse_normalInput_parsedCorrectly() {
        Parser p = new Parser();
        String[] expected = new String[]{"deadline", "tutorial", "2021-08-25", "false"};
        assertArrayEquals(expected, p.parse("deadline tutorial /at 2021-08-25"));
    }
}
