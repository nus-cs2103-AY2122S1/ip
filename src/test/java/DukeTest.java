import static org.junit.jupiter.api.Assertions.assertEquals;

import duke.Duke;
import org.junit.jupiter.api.Test;

import duke.parser.Parser;
import duke.storage.Storage;
import duke.ui.Ui;

public class DukeTest {

    Ui ui = new Ui();
    Storage storage = new Storage("Test\\taskList.txt");
    Duke duke = new Duke("Test\\taskList.txt");

    @Test
    public void parserTest() {
        assertEquals("bye", Parser.parse("bye"));
        assertEquals("list", Parser.parse("list"));
        assertEquals("todo", Parser.parse("todo borrow book"));
    }

    @Test
    public void dukeExceptionTest() {
        assertEquals("(ㆆ_ㆆ) OOPS!!! You haven't specified the task you have completed.",
                this.duke.getResponse("done"));
        assertEquals("(O_O) OOPS!!! You haven't specified the task you want to delete.",
                this.duke.getResponse("delete"));
    }

    @Test
    public void storageTest() {
        assertEquals(0, storage.load().getNumTasks());
    }

}
