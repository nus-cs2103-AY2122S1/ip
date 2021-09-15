package duke;

import duke.exceptions.DukeException;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.util.ArrayList;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    public void getTaskDescrTest() throws DukeException {
        ArrayList<Task> tasklist = new ArrayList<Task>(100);
        tasklist.add(new Todo("Kill me"));
        tasklist.add(new Event("Revive me", LocalDate.of(2022, 12,12)));
        TaskList tasks = new TaskList(tasklist);
        assertEquals("[T] [ ] Kill me", tasks.getTaskDescr(1));
    }

    @Test
    public void saveAsStringTest() throws DukeException {
        ArrayList<Task> tasklist = new ArrayList<Task>(100);
        tasklist.add(new Todo("Kill me"));
        tasklist.add(new Event("Revive me", LocalDate.of(2022, 12,12)));
        tasklist.add(new Event("Revive me", LocalDate.of(2022, 12,12),
                LocalTime.parse("23:23")));
        TaskList tasks = new TaskList(tasklist);
        assertEquals("T | 0 | Kill me\nE | 0 | Revive me | 2022-12-12\n" +
                "E | 0 | Revive me | 2022-12-12 | 23:23", tasks.saveAsString());
    }

    @Test
    public void saveAsStringDateTest() throws DukeException {
        ArrayList<Task> tasklist = new ArrayList<Task>(100);
        tasklist.add(new Todo("Kill me"));
        tasklist.add(new Event("Revive me", LocalDate.of(2022, 12,12)));
        tasklist.add(new Event("Revive me", LocalDate.of(2022, 12,12),
                LocalTime.parse("23:23")));
        TaskList tasks = new TaskList(tasklist);
        assertEquals("T | 0 | Kill me\nE | 0 | Revive me | 2022-12-12\n" +
                "E | 0 | Revive me | 2022-12-12 | 23:23", tasks.saveAsString());
    }

}
