package duchess.command;

import duchess.main.Duchess;
import duchess.main.DuchessFileHandler;
import duchess.task.ToDo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventCommandTest {
    @Test
    public void testHandleLogic() {
        DuchessFileHandler.clear();
        Duchess d = new Duchess();
        assertEquals(new EventCommand("event exam /at 25/12/2021 12pm-4pm").handleLogic(d), true);
        assertEquals(new EventCommand("event exam /at 1/01/2022 8am-4pm").handleLogic(d), true);

    }
}