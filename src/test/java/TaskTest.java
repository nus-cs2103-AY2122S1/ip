import duke.DeadlineTask;
import duke.EventTask;
import duke.TaskList;
import duke.ToDoTask;
import jdk.jfr.Event;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Checks Task class and its subclasses, and checks Tasklist to see if they function
 */

public class TaskTest {

    /**
     * Tests if the tasks can be initialized
     */

    @Test
    public void taskTest1() {
        EventTask task = new EventTask("play ball", "2021-08-25");
        assertEquals("[E][ ] play ball(at: 2021-08-25)", task.toString());
    }

    @Test
    public void taskTest2() {
        ToDoTask task = new ToDoTask("do homework");
        task.markAsDone();
        assertEquals("[T][X] do homework", task.toString());
    }

    @Test
    public void taskTest3() {
        DeadlineTask task = new DeadlineTask("submit assignment ", "2021-08-27 2pm");
        task.markAsDone();
        assertEquals("[D][X] submit assignment (by: 2021-08-27 2pm)", task.toString());
    }

    /**
     * Tests if methods called in the tasklist can function
     */

    @Test
    public void taskListTest1() {
        TaskList tasks = new TaskList();
        assertEquals("No tasks", tasks.toString());
    }

    @Test
    public void taskListTest2() {
        TaskList tasks = new TaskList();
        tasks.addToDoTask("todo eat lunch");
        assertEquals("[T][ ] eat lunch ", tasks.toString());
    }

    @Test
    public void taskListTest3() {
        TaskList tasks = new TaskList();
        tasks.addToDoTask("todo buy dinner");
        tasks.addToDoTask("todo eat dinner");
        tasks.addToDoTask("todo go to sleep");
        tasks.doTask(1);
        assertEquals("[T][X] buy dinner [T][ ] eat dinner [T][ ] go to sleep ", tasks.toString());
    }

    @Test
    public void taskListTest4() {
        TaskList tasks = new TaskList();
        tasks.addEventTask("event swimming /2021-08-15", 15);
        tasks.addDeadlineTask("deadline homework /2021-09-10", 18);
        assertEquals("[E][ ] swimming (at: 2021-08-15) [D][ ] homework (by: 2021-09-10) ", tasks.toString());
    }

    @Test
    public void taskListTest5() {
        TaskList tasks = new TaskList();
        tasks.addToDoTask("todo buy dinner");
        tasks.addToDoTask("todo eat dinner");
        tasks.deleteTask(1);
        tasks.deleteTask(1);
        assertEquals("No tasks", tasks.toString());
    }
}
