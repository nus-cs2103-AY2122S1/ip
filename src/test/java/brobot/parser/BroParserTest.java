package brobot.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import brobot.Storage;
import brobot.exception.BroException;
import brobot.task.TaskList;

public class BroParserTest {


    @Test
    public void parse_invalidCommand_exceptionThrown() {
        try {
            BroParser p = new BroParser(new TaskList(), new Storage("./data/list1.txt"));
            p.parse("hello");
            fail();
        } catch (BroException e) {
            assertEquals("Oops, unfortunately i am not yet smart enough to understand what you are saying",
                    e.getMessage());
        }
    }

    @Test
    public void parse_invalidCommandParameterDone_exceptionThrown() {
        try {
            BroParser p = new BroParser(new TaskList(), new Storage("./data/list1.txt"));
            p.parse("done hello");
        } catch (BroException e) {
            assertEquals("Oops, you have entered an invalid parameter for this command", e.getMessage());
        }
    }
}
