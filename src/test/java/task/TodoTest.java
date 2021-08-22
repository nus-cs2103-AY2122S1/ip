package task;

import duke.task.Todo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void toDataFormat() {
        Todo todo = new Todo("Finish iP");
        assertEquals("T | 0 | Finish iP", todo.toDataFormat());
    }

    @Test
    public void stringRepresentation() {
        Todo todo = new Todo("Finish iP");
        assertEquals("[T][ ] Finish iP", todo.toString());
    }
}
