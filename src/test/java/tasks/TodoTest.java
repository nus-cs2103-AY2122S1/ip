package tasks;

import duke.tasks.Todo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void toString_notDone_correctStringReturned() {
        String description = "exercise";
        Todo todo = new Todo(description);
        assertEquals("[T][ ] exercise", todo.toString());
    }

    @Test
    public void toString_done_correctStringReturned() {
        String description = "relax";
        Todo todo = new Todo(description);
        todo.markAsDone();
        assertEquals("[T][X] relax", todo.toString());
    }
}
