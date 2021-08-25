package duke;

import duke.task.TaskList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {
    @Test
    public void parse_invalidCommandWord_exceptionThrown() {
        try {
            assertEquals(0, Parser.parse("show", new TaskList()));
            fail();
        } catch (DukeException e) {
            assertEquals("∑(O_O;) Oh no!! Sorry, I don't know what that means.", e.toString());
        }
    }
}