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
        String expectedResponse1 = "Understood. I've added this task:\n    " + "[D][ ] assignment (by: Aug 25 2021 16:00)"
                + "\nYou now have 1 task in the list.";
        String expectedResponse2 = "Understood. I've added this task:\n    " + "[D][ ] assignment (by: Dec 25 2021 12:00)"
                + "\nYou now have 2 tasks in the list.";
        assertEquals(new DeadlineCommand("assignment /by 25/8/2021 4pm").handleLogic(d), expectedResponse1);
        assertEquals(new DeadlineCommand("assignment /by 25/12/2021 12pm").handleLogic(d), expectedResponse2);

    }
}
