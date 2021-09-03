package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.ToDo;


public class TaskListTest {
    @Test
    public void testStringifyForList() {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new ToDo("wah"));
        tasks.add(new Event("event 1", LocalDate.of(2021, 8, 23)));
        TaskList t = new TaskList(tasks);
        assertEquals("1. [T][ ] wah\n"
                           + "2. [E][ ] event 1 (at: Aug 23 2021)", t.stringifyTasksForList());
    }

    @Test
    public void testStringifyForSave() {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new ToDo("wah"));
        tasks.add(new Event("event 1", LocalDate.of(2021, 8, 23)));
        TaskList t = new TaskList(tasks);
        assertEquals("1. | T | 0 | wah\n"
                           + "2. | E | 0 | event 1 | 2021-08-23", t.stringifyTasksForSave());
    }
}
