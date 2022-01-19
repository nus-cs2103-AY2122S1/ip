package duchess.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duchess.main.Duchess;
import duchess.main.DuchessFileHandler;

/**
 * This class implements a JUnit Test for the List Command.
 *
 * @author Amos Tan
 * @version CS2103T AY21/22 Semester 1
 */
public class ListCommandTest {
    /**
     * Tests the logic of the ListCommand.
     */
    @Test
    public void testHandleLogic() {
        DuchessFileHandler.clearData();
        Duchess d = new Duchess();
        assertEquals(new ListCommand().handleLogic(d.getDuchessList()), d.getDuchessList().printList());
    }
}
