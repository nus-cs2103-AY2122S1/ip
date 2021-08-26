package duke;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParserTest {

    private Ui ui = new Ui();
    private TaskList taskList = new TaskList();
    private Parser parser = new Parser(ui, taskList);

    @Test
    public void parseTest() {
        assertThrows(DukeException.class, ()
                -> parser.parse("hello"));
    }

    @Test
    public void checkDeleteTest() {
        taskList.add(new Todo("read book"));
        assertThrows(DukeException.class, ()
                -> parser.parse("delete 2"));
    }
}
