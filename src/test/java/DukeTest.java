import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.Duke;
import duke.Parser;
import duke.Storage;
import duke.Ui;

public class DukeTest {

    private Duke duke = new Duke("Data\\taskList.txt");

    @Test
    public void parserTest1() {
        assertEquals("bye", Parser.parse("bye"));
        assertEquals("list", Parser.parse("list"));
        assertEquals("todo", Parser.parse("todo borrow book"));
    }

    @Test
    public void storageTest() {
        assertEquals(3, new Storage("Data\\taskList.txt").load().size());
    }

    @Test
    public void uiWelcomeTest() {
        String separator = "    ____________________________________________________________";
        String message = separator + "\n" + "     Hello! I'm Duke" + "\n"
                + "     What can I do for you?" + "\n" + separator;
        assertEquals(message, Ui.welcomeMessage());
    }

}
