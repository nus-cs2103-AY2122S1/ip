package duke;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class DukeTest {
    @Test
    public void testDisplayList() throws DukeException {
        ArrayList<Task> tasklist = new ArrayList<>(100);
        tasklist.add(new Todo("Kill me"));
        tasklist.add(new Event("Revive me", LocalDate.of(2022, 12,12)));
        TaskList t = new TaskList(tasklist);
        assertEquals("T | 0 | Kill me\nE | 0 | Revive me (at: Dec 12 2022)", t.saveAsString());
    }

    @Test
    public void testSaveAsString() throws DukeException {
        ArrayList<Task> tasklist = new ArrayList<>(100);
        TaskList t = new TaskList(tasklist);
        tasklist.add(new Event("Revive me", LocalDate.of(2022, 12,12)));
        assertEquals("1. [E] [] Revive me (at: Dec 12 2022)", t.displayList());
    }

}
