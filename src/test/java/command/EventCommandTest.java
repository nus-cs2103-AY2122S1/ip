package command;

import main.java.duke.Ui;
import main.java.duke.command.EventCommand;
import main.java.duke.storage.StorageStub;
import main.java.duke.tasklist.TaskListStub;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests the functions in EventCommand
 *
 * @author Zhen Xuan (Tutorial Group W12)
 * @version CS2103T AY21/22 S1
 */
public class EventCommandTest {

    private final EventCommand c1 = new EventCommand("tutorial /at 2021-08-23 1643");
    private final EventCommand c2 = new EventCommand("assignment /at 2021-08-21 0401");
    private final StorageStub s = new StorageStub();
    private final Ui u = new Ui();
    private final TaskListStub t = new TaskListStub(s.load(), u);

    @Test
    public void execute() {
        c1.execute(t, u, s);
        c2.execute(t, u, s);
        t.printList();
        assertEquals(t.output().size(), 2);
        assertEquals("[E][ ] tutorial (at: 23 Aug 2021 4.43pm)", t.output().get(0).toString());
        assertEquals("E|0|assignment|2021-08-21 0401", s.getString(1));
    }
}
