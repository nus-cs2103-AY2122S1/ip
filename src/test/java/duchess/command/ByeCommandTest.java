package duchess.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duchess.main.Duchess;
import duchess.main.DuchessFileHandler;


public class ByeCommandTest {
    @Test
    public void testHandleLogic() {
        DuchessFileHandler.clearData();
        Duchess d = new Duchess();
        assertEquals(new ByeCommand().handleLogic(d), "I bid thee farewell.");
    }
}
