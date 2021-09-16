
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.command.DoneCommand;
import duke.task.ToDo;


public class DoneCommandTest {

    @Test
    public void execute_outOfBounds() {
        TaskList list = new TaskList();
        DoneCommand dc = new DoneCommand("5");
        for (int i = 0; i < 4; i++) {
            list.addTask(new ToDo("do hw"));
        }
        assertThrows(DukeException.class, () -> dc.execute(list, new Ui(), new Storage()));
    }

    @Test
    public void execute_parseToInt() {
        DoneCommand dc = new DoneCommand("e");
        TaskList list = new TaskList();
        list.addTask(new ToDo("sleep"));
        assertThrows(DukeException.class, () -> dc.execute(list, new Ui(), new Storage()));
    }
}
