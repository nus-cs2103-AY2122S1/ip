import duke.Duke;
import duke.Event;
import duke.ToDo;
import duke.Deadlines;
import duke.TaskList;
import duke.Parser;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTest {
    @Test
    public void taskIconTest() {
        Event e = new Event("Dinner with mom", "at 7:30pm");
        Deadlines d = new Deadlines("Finish CS2103 iP", "by 2021-08-26");
        ToDo t = new ToDo("Read The Mythical Man Month");

        assertEquals("E", e.getTaskIcon());
        assertEquals("D", d.getTaskIcon());
        assertEquals("T", t.getTaskIcon());
    }

    @Test
    public void statusIconTest() {
        Event e = new Event("Dinner with mom", "at 7:30pm");
        assertEquals("0", e.getStatusIcon());
        e.markAsDone();
        assertEquals("1", e.getStatusIcon());
    }

    @Test
    public void generateTasksTest() {
        Event e = new Event("Dinner with mom", "at 7:30pm");
        Deadlines d = new Deadlines("Finish CS2103 iP", "by 2021-08-26");
        ToDo t = new ToDo("Read The Mythical Man Month");
        TaskList tasks = new TaskList(new ArrayList<>());
        tasks.addTask(e);
        tasks.addTask(d);
        tasks.addTask(t);
        Parser p = new Parser(tasks, "doesn't matter", 1);
        String expected = e.toString() + "\n" + d.toString() + "\n" + t.toString() + "\n";
        assertEquals(expected, p.generateTasks());
    }
}
