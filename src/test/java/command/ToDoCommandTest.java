package command;


import main.java.duke.command.ToDoCommand;
import main.java.duke.storage.StorageStub;
import main.java.duke.tasklist.TaskListStub;
import main.java.duke.Ui;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoCommandTest {

    private ToDoCommand c1 = new ToDoCommand("tutorial");
    private ToDoCommand c2 = new ToDoCommand("   assignment   ");
    private StorageStub s = new StorageStub();
    private Ui u = new Ui();
    private TaskListStub t = new TaskListStub(s.load(), u);

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
