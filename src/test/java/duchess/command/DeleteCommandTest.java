package duchess.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duchess.main.Duchess;
import duchess.main.DuchessFileHandler;
import duchess.task.ToDo;

/**
 * This class implements a JUnit Test for the Delete Command.
 *
 * @author Amos Tan
 * @version CS2103T AY21/22 Semester 1
 */
public class DeleteCommandTest {
    /**
     * Tests the logic of the DeleteCommand.
     */
    @Test
    public void testHandleLogic() {
        DuchessFileHandler.clearData();
        Duchess d = new Duchess();
        ToDo t = new ToDo("foo");
        d.getDuchessList().add(t);
        String expectedResponse1 = "Alright. I've removed this task:   \n  " + t
                + "\nNow you have 0 tasks in the list.";
        String expectedResponse2 = "Apologies, that task does not exist and cannot be deleted.";
        assertEquals(new DeleteCommand("1").handleLogic(d.getDuchessList()), expectedResponse1);
        assertEquals(new DeleteCommand("1").handleLogic(d.getDuchessList()), expectedResponse2);

    }
}
