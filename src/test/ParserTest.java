package duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    @Test
    public void byeTest() throws DukeException {
        String[] command = Parser.parse("bye");
        String[] expected = new String[]{"end"};
        assertEquals(expected[0], command[0]);
    }
}
