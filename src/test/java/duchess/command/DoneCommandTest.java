package duchess.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duchess.main.Duchess;
import duchess.main.DuchessFileHandler;
import duchess.task.ToDo;

public class DoneCommandTest {
    @Test
    public void testHandleLogic() {
        DuchessFileHandler.clearData();
        Duchess d = new Duchess();
        ToDo t = new ToDo("foo");
        d.getDuchessList().add(t);
        String expectedResponse1 = "Apologies, that task does not exist and cannot be marked as done.";
        String expectedResponse2 = "Oops... This task is already done.";
        assertEquals(new DoneCommand("1").handleLogic(d), "Brilliant! I've marked this task as done:   \n  " + t);
        assertEquals(new DoneCommand("5").handleLogic(d), expectedResponse1);
        assertEquals(new DoneCommand("1").handleLogic(d), expectedResponse2);

    }
}
