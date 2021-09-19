package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DukeTest {
    @Test
    public void storageTest() {
        Storage store = new Storage("./Data/Test.txt");
        assertEquals(0, store.load().size());

        TaskList taskList = new TaskList();
        taskList.add(new Event("hi hi", "2019-12-12"));
        store.writeAll(taskList);
        assertEquals(1, store.load().size());

        store.writeAll(new TaskList());
        assertEquals(0, store.load().size());
    }

    @Test
    public void todoTest() {
        Todo todo = new Todo("yellow");
        todo.markAsDone();
        assertEquals("[T][X] yellow", todo.toString());
    }

    @Test
    public void eventTest() {
        Event event = new Event("hi hi", "2019-12-12");
        assertEquals("[E][ ] hi hi (at: Dec 12 2019 )", event.toString());
    }

    @Test
    public void deadlineTest() {
        Deadline deadline = new Deadline("hi hi", "2019-12-12");
        deadline.markAsDone();
        assertEquals("[D][X] hi hi (by: Dec 12 2019 )", deadline.toString());
    }
}
