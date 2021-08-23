package command;

import main.java.duke.Ui;
import main.java.duke.command.DeleteCommand;
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
 * Tests the functions in DeleteCommand
 *
 * @author Zhen Xuan (Tutorial Group W12)
 * @version CS2103T AY21/22 S1
 */
public class DeleteCommandTest {

    private final DeleteCommand c1 = new DeleteCommand("1");
    private final DeleteCommand c2 = new DeleteCommand("1");
    private final DeleteCommand c3 = new DeleteCommand("1");
    private final StorageStub s = new StorageStub();
    private final Ui u = new Ui();
    private TaskListStub t;

    @Test
    public void execute() {

        //Initialise Storage and TaskList
        s.add("T", "tutorial", "");
        s.add("D", "assignment", "2021-08-23 2010");
        s.add("E", "test", "2021-08-21 1530");
        ArrayList<Task> list = new ArrayList<>();
        list.add(new ToDo(false, "tutorial"));
        list.add(new Deadline(false, "assignment", "2021-08-23 2010"));
        list.add(new Event(false, "test", "2021-08-21 1530"));
        t = new TaskListStub(list, u);
        t.printList();

        //Check size of TaskList
        assertEquals(t.output().size(), 3);

        //Check TaskList and Storage
        assertEquals("[T][ ] tutorial", t.output().get(0).toString());
        assertEquals("[D][ ] assignment (by: 23 Aug 2021 8.10pm)", t.output().get(1).toString());
        assertEquals("[E][ ] test (at: 21 Aug 2021 3.30pm)", t.output().get(2).toString());
        assertEquals("T|0|tutorial|", s.getString(0));
        assertEquals("D|0|assignment|2021-08-23 2010", s.getString(1));
        assertEquals("E|0|test|2021-08-21 1530", s.getString(2));

        //Deletes first task and check
        c1.execute(t, u, s);
        assertEquals(t.output().size(), 2);
        assertEquals("[D][ ] assignment (by: 23 Aug 2021 8.10pm)", t.output().get(0).toString());
        assertEquals("[E][ ] test (at: 21 Aug 2021 3.30pm)", t.output().get(1).toString());
        assertEquals("D|0|assignment|2021-08-23 2010", s.getString(0));
        assertEquals("E|0|test|2021-08-21 1530", s.getString(1));

        //Deletes second task and check
        c2.execute(t, u, s);
        assertEquals(t.output().size(), 1);
        assertEquals("[E][ ] test (at: 21 Aug 2021 3.30pm)", t.output().get(0).toString());
        assertEquals("E|0|test|2021-08-21 1530", s.getString(0));

        //Deletes third task and check
        c3.execute(t, u, s);
        assertEquals(t.output().size(), 0);
    }
}
