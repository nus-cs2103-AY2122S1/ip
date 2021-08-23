package command;


import main.java.duke.command.DeadlineCommand;
import main.java.duke.storage.StorageStub;
import main.java.duke.tasklist.TaskListStub;
import main.java.duke.Ui;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineCommandTest {

    private DeadlineCommand c1 = new DeadlineCommand("tutorial /by 2021-08-23 1643");
    private DeadlineCommand c2 = new DeadlineCommand("assignment /by 2021-08-21 0401");
    private StorageStub s = new StorageStub();
    private Ui u = new Ui();
    private TaskListStub t = new TaskListStub(s.load(), u);

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
