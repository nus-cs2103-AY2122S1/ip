package command;


import main.java.duke.Ui;
import main.java.duke.command.DoneCommand;
import main.java.duke.storage.StorageStub;
import main.java.duke.task.Deadline;
import main.java.duke.task.Event;
import main.java.duke.task.Task;
import main.java.duke.task.ToDo;
import main.java.duke.tasklist.TaskListStub;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests the functions in DoneCommand
 *
 * @author Zhen Xuan (Tutorial Group W12)
 * @version CS2103T AY21/22 S1
 */
public class DoneCommandTest {

    private final DoneCommand c1 = new DoneCommand("1");
    private final DoneCommand c2 = new DoneCommand("2");
    private final DoneCommand c3 = new DoneCommand("3");
    private final StorageStub s = new StorageStub();
    private final Ui u = new Ui();
    private TaskListStub t;

    @Test
    public void execute() {
        s.add("T", "tutorial", "");
        s.add("D", "assignment", "2021-08-23 2010");
        s.add("E", "test", "2021-08-21 1530");
        ArrayList<Task> list = new ArrayList<>();
        list.add(new ToDo(false, "tutorial"));
        list.add(new Deadline(false, "assignment", "2021-08-23 2010"));
        list.add(new Event(false, "test", "2021-08-21 1530"));
        t = new TaskListStub(list, u);
        t.printList();
        assertEquals(t.output().size(), 3);
        assertEquals("[T][ ] tutorial", t.output().get(0).toString());
        assertEquals("[D][ ] assignment (by: 23 Aug 2021 8.10pm)", t.output().get(1).toString());
        assertEquals("[E][ ] test (at: 21 Aug 2021 3.30pm)", t.output().get(2).toString());
        assertEquals("T|0|tutorial|", s.getString(0));
        assertEquals("D|0|assignment|2021-08-23 2010", s.getString(1));
        assertEquals("E|0|test|2021-08-21 1530", s.getString(2));
        c1.execute(t, u, s);
        c2.execute(t, u, s);
        c3.execute(t, u, s);
        assertEquals("[T][X] tutorial", t.output().get(0).toString());
        assertEquals("[D][X] assignment (by: 23 Aug 2021 8.10pm)", t.output().get(1).toString());
        assertEquals("[E][X] test (at: 21 Aug 2021 3.30pm)", t.output().get(2).toString());
        assertEquals("T|1|tutorial|", s.getString(0));
        assertEquals("D|1|assignment|2021-08-23 2010", s.getString(1));
        assertEquals("E|1|test|2021-08-21 1530", s.getString(2));
    }
}
