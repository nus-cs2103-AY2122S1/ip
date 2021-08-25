package tasks;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void saveText_completedTodo_success() {
        Todo todo = new Todo("Dummy description", true);
        assertEquals(todo.saveText(), "T | 1 | Dummy description\n");
    }

    @Test
    public void markAsDone_newTodo_success() {
        Todo todo = new Todo("Test 2", false);
        assertEquals(todo.isDone, false);
        todo.markAsDone();
        assertEquals(todo.toString(), "[T][X] Test 2");
    }
}
