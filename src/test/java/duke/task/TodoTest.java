package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TodoTest {
    @Test
    public void constructor_success() {
        Todo todo = new Todo("test todo");
        String expected = "[T][ ] test todo";
        assertEquals(expected, todo.toString());
    }

    @Test
    public void markAsDone_success() {
        Todo todo = new Todo("test todo");
        todo.markAsDone();
        String expected = "[T][X] test todo";
        assertEquals(expected, todo.toString());
    }
}
