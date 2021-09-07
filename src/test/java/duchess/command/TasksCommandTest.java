package duchess.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duchess.main.Duchess;
import duchess.main.DuchessFileHandler;
import duchess.task.ToDo;

/**
 * This class implements a JUnit Test for the Tasks Command.
 *
 * @author Amos Tan
 * @version CS2103T AY21/22 Semester 1
 */
public class TasksCommandTest {
    /**
     * Tests the logic of the TasksCommand.
     */
    @Test
    public void testHandleLogic() {
        DuchessFileHandler.clearData();
        Duchess d = new Duchess();
        String expectedResponse1 = "You have no tasks before 25/8/2021 12pm";
        ToDo t = new ToDo("foo");
        d.getDuchessList().add(t);
        String expectedResponse2 = t + "\n";
        assertEquals(new TasksCommand("/before 25/8/2021 12pm").handleLogic(d.getDuchessList()),
                expectedResponse1);
        assertEquals(new TasksCommand("/after 25/8/2021 12pm").handleLogic(d.getDuchessList()),
                expectedResponse2);
        assertEquals(new TasksCommand("/after today").handleLogic(d.getDuchessList()), expectedResponse2);
    }
}
