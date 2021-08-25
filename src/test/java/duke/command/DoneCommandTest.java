package duke.command;

import duke.ToDoList;
import duke.Ui;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

public class DoneCommandTest {

    @Test
    public void execute_indexOutOfBounds_exceptionThrown() {
        String name = "Testing Bot";
        Ui ui = new Ui(name);
        ToDoList tdl = new ToDoList(name);
        DoneCommand dc = new DoneCommand(tdl, ui, 100);
        try {
            dc.execute();
        } catch (IndexOutOfBoundsException e) {
            fail();
        }
    }
}
