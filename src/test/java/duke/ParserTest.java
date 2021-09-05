package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests the Parser class.
 */
public class ParserTest {
    /**
     * Tests if event task is added via parser and checks if event task is successfully encoded.
     */
    @Test
    public void eventTest() {
        TaskList tasks = new TaskList();
        Parser parser = new Parser(tasks);
        parser.getResponse("event dance night /at 11/12/2019");
        assertEquals("E | 0 | dance night | 11/12/2019", tasks.encodeTasks().get(0));
    }
}
