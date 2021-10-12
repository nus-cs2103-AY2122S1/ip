import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import duke.util.PersistentStorage;
import duke.util.Response;
import duke.util.Tasklist;
import duke.commands.ByeCommand;
import duke.commands.Command;
import duke.commands.DeadlineCommand;

public class DukeCommandsTest {
    @Test
    public void byeCommandTest() {
        Command c = new ByeCommand();
        assertEquals(true, c.isExit());
    }

    @Test
    public void deadlineCommandTest() {
        Response response = new Response();
        Tasklist taskList = new Tasklist();
        PersistentStorage storage = new PersistentStorage("./test.txt");

        DeadlineCommand c = new DeadlineCommand("test description",
                LocalDateTime.of(2012, 2, 2, 16, 30));

        c.executeCommand(taskList, response, storage);
        String expected = "1. [D][ ] test description (by: Feb 02 2012 16:30)";

        assertEquals(expected, taskList.toString());


    }
}
