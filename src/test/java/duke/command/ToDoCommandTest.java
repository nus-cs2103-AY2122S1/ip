package duke.command;


import duke.Ui;
import duke.storage.StorageStub;
import duke.tasklist.TaskListStub;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
    private final Ui u = new Ui();
    private final TaskListStub t = new TaskListStub(s.load(), u);

    @Test
    public void execute() {
        c1.execute(t, u, s);
        c2.execute(t, u, s);
        t.printList();
        assertEquals(t.output().size(), 2);
        assertEquals("[T][ ] tutorial", t.output().get(0).toString());
        assertEquals("T|0|assignment|", s.getString(1));
    }
}
