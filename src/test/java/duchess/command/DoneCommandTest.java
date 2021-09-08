package duchess.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duchess.main.Duchess;
import duchess.main.DuchessFileHandler;
import duchess.task.ToDo;

/**
 * This class implements a JUnit Test for the Done Command.
 *
 * @author Amos Tan
 * @version CS2103T AY21/22 Semester 1
 */
public class DoneCommandTest {
    /**
     * Tests the logic of the DoneCommand.
     */
    @Test
    public void testHandleLogic() {
        DuchessFileHandler.clearData();
        Duchess d = new Duchess();
        ToDo t = new ToDo("foo");
        d.getDuchessList().add(t);
        String expectedResponse1 = "Apologies, that task does not exist and cannot be marked as done.";
        String expectedResponse2 = "Oops... This task is already done.";
        assertEquals(new DoneCommand("1").handleLogic(d.getDuchessList()),
                "Brilliant! I've marked this task as done:   \n  " + t);
        assertEquals(new DoneCommand("5").handleLogic(d.getDuchessList()), expectedResponse1);
        assertEquals(new DoneCommand("1").handleLogic(d.getDuchessList()), expectedResponse2);

    }
}
