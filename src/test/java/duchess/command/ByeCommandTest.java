package duchess.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duchess.main.Duchess;
import duchess.main.DuchessFileHandler;

/**
 * This class implements a JUnit Test for the Bye Command.
 *
 * @author Amos Tan
 * @version CS2103T AY21/22 Semester 1
 */
public class ByeCommandTest {
    /**
     * Tests the logic of the ByeCommand.
     */
    @Test
    public void testHandleLogic() {
        DuchessFileHandler.clearData();
        Duchess d = new Duchess();
        assertEquals(new ByeCommand().handleLogic(d.getDuchessList()), "I bid thee farewell.");
    }
}
