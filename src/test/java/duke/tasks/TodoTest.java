package duke.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import duke.DukeException;



public class TodoTest {
    @Test
    public void createTodo() throws DukeException {
        assertEquals("[T][ ] readings", new Todo("readings").toString());
    }
}
