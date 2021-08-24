
import duke.PersistentStorage;
import duke.Tasklist;
import duke.UI;
import duke.commands.*;

import duke.tasks.Task;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeCommandsTest {
    @Test
    public void ByeCommandTest() {
        Command c = new ByeCommand();
        assertEquals(true, c.isExit());
    }

    @Test
    public void DeadlineCommandTest() {
        UI ui = new UI(new Scanner(System.in));
        Tasklist taskList = new Tasklist();
        PersistentStorage storage = new PersistentStorage("./test.txt");

        DeadlineCommand c = new DeadlineCommand("test description",
                LocalDateTime.of(2012,2,2,16,30));

        c.executeCommand(taskList, ui, storage);
        String expected = "1. [D][ ] test description (by: Feb 02 2012 16:30)";

        assertEquals(expected, taskList.toString());


    }
}
