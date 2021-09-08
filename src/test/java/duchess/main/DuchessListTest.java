package duchess.main;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import duchess.task.Deadline;
import duchess.task.Event;
import duchess.task.Task;
import duchess.task.ToDo;

/**
 * This class implements a JUnit Test for the DuchessList methods.
 *
 * @author Amos Tan
 * @version CS2103T AY21/22 Semester 1
 */
public class DuchessListTest {

    /**
     * Tests the add() and getTask() methods.
     */
    @Test
    public void testAddAndGetTask() {
        DuchessList d = new DuchessList();
        Task t = new ToDo("homework");
        d.add(t);
        assertEquals(t, d.getTask(1));
    }

    /**
     * Tests the printList() method.
     */
    @Test
    public void testPrintList() {
        DuchessList dl = new DuchessList();
        dl.add(new ToDo("homework"));
        dl.add(new Deadline("assignment", LocalDateTime.MAX));
        dl.add(new Event("meeting", LocalDateTime.MIN, LocalDateTime.MAX));

        assertEquals(dl.printList(),
                "1. [T][ ] homework\n"
                        + "2. [D][ ] assignment (by: Dec 31 +999999999 23:59)\n"
                        + "3. [E][ ] meeting (at: Jan 1 +1000000000 00:00 to Dec 31 +999999999 23:59)");
    }

    /**
     * Tests the checkWithinRange() method.
     */
    @Test
    public void testCheckWithinRange() {
        DuchessList dl = new DuchessList();
        dl.add(new ToDo("foo"));
        dl.add(new ToDo("bar"));
        assertEquals(dl.checkWithinRange(0), false);
        assertEquals(dl.checkWithinRange(1), true);
        assertEquals(dl.checkWithinRange(2), true);
        assertEquals(dl.checkWithinRange(3), false);
    }

    /**
     * Tests the delete() method.
     */
    @Test
    public void testDelete() {
        DuchessList dl = new DuchessList();
        Task t = new ToDo("foo");
        dl.add(t);
        assertEquals(dl.delete(1), t);
    }
}
