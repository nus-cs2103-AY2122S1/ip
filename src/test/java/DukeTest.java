import duke.*;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTest {

    Duke duke = new Duke("C:\\Users\\Rickie\\Documents\\University\\Year 2\\Semester 1\\CS2103T\\ip\\Data\\taskList.txt");

    @Test
    public void parserTest1() {
        assertEquals("bye", Parser.parse("bye"));
        assertEquals("list", Parser.parse("list"));
        assertEquals("todo", Parser.parse("todo borrow book"));
    }

    @Test
    public void storageTest() {
        assertEquals(4, new Storage("C:\\Users\\Rickie\\Documents\\University\\Year 2\\Semester 1\\CS2103T\\ip\\Data\\taskList.txt").load().size());
    }

    @Test
    public void uiWelcomeTest() {
        String separator = "    ____________________________________________________________";
        String message = separator + "\n" + "     Hello! I'm Duke" + "\n" + "     What can I do for you?"  + "\n" + separator;
        assertEquals(message, Ui.WelcomeMessage());
    }

}