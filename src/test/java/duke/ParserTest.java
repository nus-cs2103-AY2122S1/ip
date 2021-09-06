package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ParserTest {
    @Test
    public void dummyTest() {
        assertEquals(2, 2);
    }

    @Test
    public void byeTest() throws DukeException {
        TaskList taskList = new TaskList();
        Storage storage = new Storage("./data/duke.txt");
        Parser parser = new Parser(taskList, storage);
        assertEquals("bye", parser.parseCommand("bye"));
    }
}
