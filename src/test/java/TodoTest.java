import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import duke.DukeException;
import duke.Todo;

public class TodoTest {
    @Test
    public void toString_normal() throws Exception {
        Todo todo = new Todo("task");
        Assertions.assertEquals("[T][ ] task", todo.toString());
    }
    @Test
    public void constructor_emptyDescription_dukeException() {
        Throwable exception = Assertions.assertThrows(DukeException.class, () -> new Todo(""));
        Assertions.assertEquals("The description of a todo cannot be empty.", exception.getMessage());
    }
}
