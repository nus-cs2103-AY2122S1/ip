package duke.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.storage.StorageStub;
import duke.tasklist.TaskListStub;

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
    private final TaskListStub t = new TaskListStub(s.load());

    @Test
    public void execute() {
        c1.execute(t, s);
        c2.execute(t, s);
        t.printList();
        assertEquals(t.output().size(), 2);
        assertEquals("[E][ ] tutorial (at: 23 Aug 2021 4.43pm)", t.output().get(0).toString());
        assertEquals("E|0|assignment|2021-08-21 0401", s.getString(1));
    }
}
