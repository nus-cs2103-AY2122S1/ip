package duchess.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duchess.main.Duchess;
import duchess.main.DuchessFileHandler;
import duchess.task.ToDo;

public class FindCommandTest {
    @Test
    public void testHandleLogic() {
        DuchessFileHandler.clearData();
        Duchess d = new Duchess();
        ToDo t = new ToDo("assignment");
        d.getDuchessList().add(t);
        assertEquals(new FindCommand("assignment").handleLogic(d), "1. " + t + "\n");
        assertEquals(new FindCommand("hahaha").handleLogic(d), "There are no tasks with that keyword.");
    }
}
