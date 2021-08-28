package duke.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.storage.StorageStub;
import duke.tasklist.TaskListStub;

/**
 * Tests the functions in ToDoCommand
 *
 * @author Zhen Xuan (Tutorial Group W12)
 * @version CS2103T AY21/22 S1
 */
public class ToDoCommandTest {

    private final ToDoCommand c1 = new ToDoCommand("tutorial");
    private final ToDoCommand c2 = new ToDoCommand("   assignment   ");
    private final StorageStub s = new StorageStub();
    private final TaskListStub t = new TaskListStub(s.load());

    @Test
    public void execute() {
        c1.execute(t, s);
        c2.execute(t, s);
        t.printList();
        assertEquals(t.output().size(), 2);
        assertEquals("[T][ ] tutorial", t.output().get(0).toString());
        assertEquals("T|0|assignment|", s.getString(1));
    }
}
