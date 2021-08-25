package task;

import duke.task.Task;
import duke.task.ToDo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void encode_todo_success() {
        Task task = new ToDo("test description", false);
        assertEquals("t\u001E0\u001Etest description", task.encode());
    }

    @Test
    public void toString_todo_success() {
        Task task = new ToDo("another test description", true);
        assertEquals("[T][X] another test description", task.toString());
    }
}
