package duchess.command;

import duchess.main.Duchess;
import duchess.main.DuchessFileHandler;
import duchess.task.ToDo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TasksCommandTest {
    @Test
    public void testHandleLogic() {
        DuchessFileHandler.clear();
        Duchess d = new Duchess();
        String expectedResponse1 = "You have no tasks before 25/8/2021 12pm";
        ToDo t = new ToDo("foo");
        d.getDuchessList().add(t);
        String expectedResponse2 = t + "\n";
        assertEquals(new TasksCommand("/before 25/8/2021 12pm").handleLogic(d), expectedResponse1);
        assertEquals(new TasksCommand("/after 25/8/2021 12pm").handleLogic(d), expectedResponse2);
        assertEquals(new TasksCommand("/after today").handleLogic(d), expectedResponse2);
    }
}