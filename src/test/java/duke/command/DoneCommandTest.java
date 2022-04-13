package duke.command;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.util.ToDoList;


public class DoneCommandTest {

    @Test
    public void execute_indexOutOfBounds_exceptionThrown() {
        String name = "Testing Bot";
        ToDoList tdl = new ToDoList(name);
        DoneCommand dc = new DoneCommand(tdl, 100);
        try {
            dc.execute();
        } catch (IndexOutOfBoundsException e) {
            fail();
        }
    }
}
