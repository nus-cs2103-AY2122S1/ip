package duchess.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duchess.main.Duchess;
import duchess.main.DuchessFileHandler;
import duchess.task.ToDo;

/**
 * This class implements a JUnit Test for the Find Command.
 *
 * @author Amos Tan
 * @version CS2103T AY21/22 Semester 1
 */
public class FindCommandTest {
    /**
     * Tests the logic of the FindCommand.
     */
    @Test
    public void testHandleLogic() {
        DuchessFileHandler.clearData();
        Duchess d = new Duchess();
        ToDo t = new ToDo("assignment");
        d.getDuchessList().add(t);
        assertEquals(new FindCommand("assignment").handleLogic(d.getDuchessList()), "1. " + t + "\n");
        assertEquals(new FindCommand("hahaha").handleLogic(d.getDuchessList()),
                "There are no tasks with that keyword.");
    }
}
