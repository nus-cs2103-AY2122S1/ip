import duke.main.TaskList;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    public void toStringTest() {
        ArrayList<Task> testList = new ArrayList<>();
        testList.add(new ToDo("homework"));
        testList.add(new Deadline("submit ", "2021-09-03"));
        testList.add(new Event("interview ", "2021-10-04"));
        String expected = "\t 1. [T][ ] homework\n" +
                "\t 2. [D][ ] submit (by Sep 3 2021)\n" +
                "\t 3. [E][ ] interview (at Oct 4 2021)\n";
        assertEquals(expected, new TaskList(testList).toString());
    }

    @Test
    public void toStringTest2() {
        assertEquals("\t Nothing has been added to the list", new TaskList(new ArrayList<Task>()).toString());
    }

    @Test
    public void getSizeTest() {
        ArrayList<Task> testList = new ArrayList<>();
        testList.add(new ToDo("homework"));
        testList.add(new Deadline("submit ", "2021-09-03"));
        assertEquals(2, new TaskList(testList).getSize());
    }
}
