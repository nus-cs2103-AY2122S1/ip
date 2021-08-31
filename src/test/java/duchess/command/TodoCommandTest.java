package duchess.command;

import duchess.main.Duchess;
import duchess.main.DuchessFileHandler;
import duchess.task.ToDo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoCommandTest {
    @Test
    public void testHandleLogic() {
        DuchessFileHandler.clear();
        Duchess d = new Duchess();
        ToDo t = new ToDo("homework");
        String expectedResponse = "Understood. I've added this task:\n    " + t
                + "\nYou now have 1 task in the list.";
        assertEquals(new TodoCommand("homework").handleLogic(d), expectedResponse);
    }
}
