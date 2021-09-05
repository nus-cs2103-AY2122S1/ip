import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import duke.Todo;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {

    private Todo testTodo;

    public TodoTest() {
        this.testTodo = new Todo("Work out");
    }

    @Test
    @DisplayName("Adding a new Todo should work")
    public void testTodoToString() {
        assertEquals("[T][ ] Work out", testTodo.toString());
    }
}
