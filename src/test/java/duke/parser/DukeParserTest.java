package duke.parser;

import duke.task.TaskList;
import duke.Storage;
import duke.exception.DukeException;
import duke.exception.InvalidCommandException;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class DukeParserTest {


    @Test
    public void parse_invalidCommand_exceptionThrown() {
        try {
            DukeParser p = new DukeParser(new TaskList(), new Storage("./data/list1.txt"));
            p.parse("hello");
            fail();
        } catch (DukeException e) {
            assertEquals("Oops, unfortunately i am not yet smart enough to understand what you are saying", e.getMessage());
        }
    }

    @Test
    public void parse_invalidCommandParameterDone_exceptionThrown() {
        try {
            DukeParser p = new DukeParser(new TaskList(), new Storage("./data/list1.txt"));
            p.parse("done hello");
        } catch (DukeException e) {
            assertEquals("Oops, you have entered an invalid parameter for this command", e.getMessage());

        }
    }

}
