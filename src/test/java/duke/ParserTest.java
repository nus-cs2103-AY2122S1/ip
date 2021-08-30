package duke;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void parseCommandTest() throws DukeException {
        Parser parser = new Parser();
        assertEquals("bye", parser.parseCommand("bYE"));
    }

    @Test
    public void parseTodo() throws DukeException {
        Parser parser = new Parser();
        String[] strparse = new String[] {"todo", "kill", "me"};
        assertEquals("kill me", parser.parseTodo(strparse).getTaskStr());
    }


}
