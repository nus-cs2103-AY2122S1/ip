package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class ParserTest {
    @Test
    public void dummyTest() {
        assertEquals(2,2);
    }

    @Test
    public void byeTest() throws DukeException {
        TaskList taskList = new TaskList();
        Storage storage = new Storage("./data/duke.txt");
        Parser parser = new Parser(taskList, storage);
        assertFalse(parser.parseCommand("bye"));
    }

    @Test
    public void listTest() throws DukeException {
        TaskList taskList = new TaskList();
        Storage storage = new Storage("./data/duke.txt");
        Parser parser = new Parser(taskList, storage);
        assertTrue(parser.parseCommand("list"));
    }

}
