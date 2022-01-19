package duchess.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duchess.main.Duchess;
import duchess.main.DuchessFileHandler;

/**
 * This class implements a JUnit Test for the Deadline Command.
 *
 * @author Amos Tan
 * @version CS2103T AY21/22 Semester 1
 */
public class DeadlineCommandTest {
    /**
     * Tests the logic of the DeadlineCommand.
     */
    @Test
    public void testHandleLogic() {
        DuchessFileHandler.clearData();
        Duchess d = new Duchess();
        String expectedResponse1 = "Understood. I've added this task:\n    "
                + "[D][ ] assignment (by: Aug 25 2021 16:00)"
                + "\nYou now have 1 task in the list.";
        String expectedResponse2 = "Understood. I've added this task:\n    "
                + "[D][ ] assignment (by: Dec 25 2021 12:00)"
                + "\nYou now have 2 tasks in the list.";
        assertEquals(new DeadlineCommand("assignment /by 25/8/2021 4pm").handleLogic(d.getDuchessList()),
                expectedResponse1);
        assertEquals(new DeadlineCommand("assignment /by 25/12/2021 12pm").handleLogic(d.getDuchessList()),
                expectedResponse2);

    }
}
