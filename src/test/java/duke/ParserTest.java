package duke;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void parserTestForStop() throws IOException, DeleteException, DukeException, FindException {
        Parser p = new Parser("bye", new Ui(), new Storage("./task_list.txt"), new TaskList());
        p.parseCommand();
        assertEquals(false, p.getRun());
    }

    @Test
    public void parserTestForRun() throws DeleteException, DukeException, IOException, FindException {
        Parser p = new Parser("list", new Ui(), new Storage("./task_list.txt"), new TaskList());
        p.parseCommand();
        assertEquals(true, p.getRun());
    }

}
