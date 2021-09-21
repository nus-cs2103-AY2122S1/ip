package duke;

import duke.exceptions.DukeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTest {

    @Test
    public void testWrongCommand() throws DukeException {
        Ui ui = new Ui();
        assertEquals("-------------------------------------------\n" +
                "OOPS!!! I'm sorry, but I don't know what that means :-(\n" +
                "-------------------------------------------\n", ui.executeCommand("lol", "des"));
    }

    @Test
    public void testEmptyDescription() throws DukeException {
        Ui ui = new Ui();
        assertEquals("-------------------------------------------\n" +
                "OOPS!!! The description of a todo cannot be empty.\n" +
                "-------------------------------------------\n", ui.executeCommand("todo"," "));
    }

    @Test
    public void testUpdateTodoWithTime() throws DukeException {
    }
}
