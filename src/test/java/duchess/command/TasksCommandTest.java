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
        assertEquals(new TasksCommand("tasks /before 25/8/2021 12pm").handleLogic(d), true);
        assertEquals(new TasksCommand("tasks /after 25/8/2021 12pm").handleLogic(d), true);
        assertEquals(new TasksCommand("tasks /after today").handleLogic(d), true);
    }
}