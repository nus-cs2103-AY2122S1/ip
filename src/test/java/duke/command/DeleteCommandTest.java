package duke.command;

import duke.ToDoList;
import duke.Ui;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

public class DeleteCommandTest {

    @Test
    public void execute_IndexOutOfBounds_exceptionThrown() {
        String name = "Testing Bot";
        ToDoList tdl = new ToDoList(name);
        Ui ui = new Ui(name);
        DeleteCommand dc = new DeleteCommand(tdl, ui, 100);
        try {
            dc.execute();
        } catch (IndexOutOfBoundsException e) {
            fail();
        }
    }
}
