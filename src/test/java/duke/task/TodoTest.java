package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void testStringConversion() {
        Todo dummyTodo = new Todo("Dummy todo");
        assertEquals("[T][ ] Dummy todo", dummyTodo.toString());
        dummyTodo.setCompleted();
        assertEquals("[T][X] Dummy todo", dummyTodo.toString());
    }

    @Test
    public void testStorageFormatConversion() {
        Todo dummyTodo = new Todo("Dummy todo");
        assertEquals("T/0/Dummy todo", dummyTodo.toStorageFormat());
        dummyTodo.setCompleted();
        assertEquals("T/1/Dummy todo", dummyTodo.toStorageFormat());
    }
}
