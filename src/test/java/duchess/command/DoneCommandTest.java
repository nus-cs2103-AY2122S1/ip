package duchess.command;

import duchess.main.Duchess;
import duchess.main.DuchessFileHandler;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DoneCommandTest {
    @Test
    public void testHandleLogic() {
        DuchessFileHandler.clear();
        Duchess d = new Duchess();
        assertEquals(new DoneCommand("done 1").handleLogic(d), true);
    }
}