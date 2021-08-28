package duke.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.Ui;
import duke.storage.StorageStub;
import duke.tasklist.TaskListStub;

/**
 * Tests the functions in DeadlineCommand
 *
 * @author Zhen Xuan (Tutorial Group W12)
 * @version CS2103T AY21/22 S1
 */
public class DeadlineCommandTest {

    private final DeadlineCommand c1 = new DeadlineCommand("tutorial /by 2021-08-23 1643");
    private final DeadlineCommand c2 = new DeadlineCommand("assignment /by 2021-08-21 0401");
    private final StorageStub s = new StorageStub();
    private final Ui u = new Ui();
    private final TaskListStub t = new TaskListStub(s.load(), u);

    @Test
    public void execute() {
        c1.execute(t, u, s);
        c2.execute(t, u, s);
        t.printList();
        assertEquals(t.output().size(), 2);
        assertEquals("[D][ ] tutorial (by: 23 Aug 2021 4.43pm)", t.output().get(0).toString());
        assertEquals("D|0|assignment|2021-08-21 0401", s.getString(1));
    }
}
