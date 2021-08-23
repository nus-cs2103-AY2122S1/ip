package Duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {
    @Test
    void eventTest() {
        TaskList tasks = new TaskList();
        Parser parser = new Parser(tasks);
        parser.commands("event dance night /at 11/12/2019");
        assertEquals("E | 0 | dance night | 11/12/2019", tasks.encodeTasks().get(0));
    }
}