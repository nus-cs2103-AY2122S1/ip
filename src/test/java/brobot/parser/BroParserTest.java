package brobot.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import brobot.exception.BroException;
import brobot.storage.Storage;
import brobot.task.TaskList;

public class BroParserTest {


    @Test
    public void parse_invalidCommand_exceptionThrown() {
        try {
            BroParser p = new BroParser(new TaskList(), new Storage("./data/list1.txt"));
            p.parse("hello");
            fail();
        } catch (BroException e) {
            assertEquals("Eh bro, I don't understand what you are saying lah",
                    e.getMessage());
        }
    }

    @Test
    public void parse_invalidCommandParameterDone_exceptionThrown() {
        try {
            BroParser p = new BroParser(new TaskList(), new Storage("./data/list1.txt"));
            p.parse("done hello");
        } catch (BroException e) {
            assertEquals("Bro i think you put in the wrong parameters for this command", e.getMessage());
        }
    }
}
