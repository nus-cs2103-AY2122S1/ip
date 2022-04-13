package duke.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import duke.storage.StorageStub;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;
import duke.tasklist.TaskListStub;

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
    private TaskListStub t;

    @Test
    public void execute() {

        //Initialise Storage and TaskList
        s.add("T", "tutorial", "", 2);
        s.add("D", "assignment", "2021-08-23 2010", 3);
        s.add("E", "test", "2021-08-21 1530", 1);
        ArrayList<Task> list = new ArrayList<>();
        list.add(new ToDo(false, "tutorial", 2));
        list.add(new Deadline(false, "assignment", "2021-08-23 2010", 3));
        list.add(new Event(false, "test", "2021-08-21 1530", 1));
        t = new TaskListStub(list);
        t.printList();

        //Check size of TaskList
        assertEquals(t.output().size(), 3);

        //Check TaskList and Storage
        assertEquals("[T][ ][!! ] tutorial", t.output().get(0).toString());
        assertEquals("[D][ ][!!!] assignment (by: 23 Aug 2021 8.10pm)", t.output().get(1).toString());
        assertEquals("[E][ ][!  ] test (at: 21 Aug 2021 3.30pm)", t.output().get(2).toString());
        assertEquals("T|0|tutorial||2", s.getString(0));
        assertEquals("D|0|assignment|2021-08-23 2010|3", s.getString(1));
        assertEquals("E|0|test|2021-08-21 1530|1", s.getString(2));

        //Deletes first duke.task and check
        c1.execute(t, s);
        assertEquals(t.output().size(), 2);
        assertEquals("[D][ ][!!!] assignment (by: 23 Aug 2021 8.10pm)", t.output().get(0).toString());
        assertEquals("[E][ ][!  ] test (at: 21 Aug 2021 3.30pm)", t.output().get(1).toString());
        assertEquals("D|0|assignment|2021-08-23 2010|3", s.getString(0));
        assertEquals("E|0|test|2021-08-21 1530|1", s.getString(1));

        //Deletes second duke.task and check
        c2.execute(t, s);
        assertEquals(t.output().size(), 1);
        assertEquals("[E][ ][!  ] test (at: 21 Aug 2021 3.30pm)", t.output().get(0).toString());
        assertEquals("E|0|test|2021-08-21 1530|1", s.getString(0));

        //Deletes third duke.task and check
        c3.execute(t, s);
        assertEquals(t.output().size(), 0);
    }
}
