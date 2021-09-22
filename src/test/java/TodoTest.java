import org.junit.jupiter.api.Test;
import duke.tasks.Todo;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void createTodoTest() {
        Todo todo = new Todo("say hi");
        assertEquals("[T][ ] say hi", todo.toString());
    }

    @Test
    public void completeTodoTest() {
        Todo todo = new Todo("say hi");
        todo.markAsDone();
        assertEquals("[T][X] say hi", todo.toString());
    }
}
