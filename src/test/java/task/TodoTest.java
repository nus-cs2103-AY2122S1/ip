package task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.task.Todo;

public class TodoTest {
    @Test
    public void toDataFormat() {
        Todo todo = new Todo("Finish iP");
        assertEquals("T | 0 | Finish iP | ", todo.toDataFormat());
    }

    @Test
    public void stringRepresentation() {
        Todo todo = new Todo("Finish iP");
        assertEquals("[T][ ] Finish iP", todo.toString());
    }
}
