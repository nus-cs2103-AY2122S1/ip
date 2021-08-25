package duchess.command;

import duchess.main.Duchess;
import duchess.main.DuchessFileHandler;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineCommandTest {
    @Test
    public void testHandleLogic() {
        DuchessFileHandler.clear();
        Duchess d = new Duchess();
        assertEquals(new DeadlineCommand("deadline assignment /by 25/8/2021 4pm").handleLogic(d), true);
        assertEquals(new DeadlineCommand("deadline assignment /by 25/12/2021 12pm").handleLogic(d), true);

    }
}
