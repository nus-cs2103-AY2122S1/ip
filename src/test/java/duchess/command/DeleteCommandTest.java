package duchess.command;

import duchess.main.Duchess;
import duchess.main.DuchessFileHandler;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeleteCommandTest {
    @Test
    public void testHandleLogic() {
        DuchessFileHandler.clear();
        Duchess d = new Duchess();
        assertEquals(new DeleteCommand("delete 1").handleLogic(d), true);
    }
}