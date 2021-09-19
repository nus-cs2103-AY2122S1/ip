package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TodoTest {
    @Test
    public void testTodoToString() {
        Todo todo = new Todo("test1");
        assertEquals("[T][ ] test1", todo.toString());
    }
}
