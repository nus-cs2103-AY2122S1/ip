package duchess.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duchess.main.Duchess;
import duchess.main.DuchessFileHandler;

public class EventCommandTest {
    @Test
    public void testHandleLogic() {
        DuchessFileHandler.clearData();
        Duchess d = new Duchess();
        String expectedResponse1 = "Understood. I've added this task:\n    "
                + "[E][ ] exam (at: Dec 25 2021 12:00 to Dec 25 2021 16:00)"
                + "\nYou now have 1 task in the list.";
        String expectedResponse2 = "Understood. I've added this task:\n    "
                + "[E][ ] exam (at: Jan 1 2022 08:00 to Jan 1 2022 16:00)"
                + "\nYou now have 2 tasks in the list.";
        assertEquals(new EventCommand("exam /at 25/12/2021 12pm-4pm").handleLogic(d), expectedResponse1);
        assertEquals(new EventCommand("exam /at 1/01/2022 8am-4pm").handleLogic(d), expectedResponse2);

    }
}
