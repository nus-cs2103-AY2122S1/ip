import duke.task.ToDo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    @Test
    public void test_markTodoAsDone() {
        ToDo toDo = new ToDo("test", false);
        toDo.markAsDone();
        assertEquals("X", toDo.getStatusIcon());
    }
}
