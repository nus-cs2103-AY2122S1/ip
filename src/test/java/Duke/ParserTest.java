package Duke;

import duke.Parser;
import duke.TaskList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests the Parser class.
 */
class ParserTest {
    /**
     * Tests if event task is added via parser and checks if event task is successfully encoded.
     */
    @Test
    void eventTest() {
        TaskList tasks = new TaskList();
        Parser parser = new Parser(tasks);
        parser.commands("event dance night /at 11/12/2019");
        assertEquals("E | 0 | dance night | 11/12/2019", tasks.encodeTasks().get(0));
    }
}