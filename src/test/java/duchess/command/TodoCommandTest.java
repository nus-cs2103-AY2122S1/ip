package duchess.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duchess.main.Duchess;
import duchess.main.DuchessFileHandler;
import duchess.task.ToDo;

/**
 * This class implements a JUnit Test for the Todo Command.
 *
 * @author Amos Tan
 * @version CS2103T AY21/22 Semester 1
 */
public class TodoCommandTest {
    /**
     * Tests the logic of the TodoCommand.
     */
    @Test
    public void testHandleLogic() {
        DuchessFileHandler.clearData();
        Duchess d = new Duchess();
        ToDo t = new ToDo("homework");
        String expectedResponse = "Understood. I've added this task:\n    " + t
                + "\nYou now have 1 task in the list.";
        assertEquals(new TodoCommand("homework").handleLogic(d.getDuchessList()), expectedResponse);
    }
}
