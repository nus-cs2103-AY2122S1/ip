package src.main.java.duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TaskListTest {

    @Test
    void remove() throws DukeException {
        TaskList list = new TaskList();
        Task todo = new Todo("read book", false);
        list.add(todo);
        assertEquals("T | 0 | read book", list.remove(1));
    }
}
