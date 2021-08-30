package duke;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    public void getTaskDescrTest() throws DukeException {
        ArrayList<Task> tasklist = new ArrayList<Task>(100);
        tasklist.add(new Todo("Kill me"));
        tasklist.add(new Event("Revive me", LocalDate.of(2022, 12,12)));
        TaskList t = new TaskList(tasklist);
        assertEquals("[T] [ ] Kill me", t.getTaskDescr(1));
    }

    @Test
    public void saveAsStringTest() throws DukeException {
        ArrayList<Task> tasklist = new ArrayList<Task>(100);
        tasklist.add(new Todo("Kill me"));
        tasklist.add(new Event("Revive me", LocalDate.of(2022, 12,12)));
        TaskList t = new TaskList(tasklist);
        assertEquals("T | 0 | Kill me\nE | 0 | Revive me | 2022-12-12", t.saveAsString());
    }
}
