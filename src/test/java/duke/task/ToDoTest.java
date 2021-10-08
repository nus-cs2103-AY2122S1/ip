package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ToDoTest {
    @Test
    public void toDataString() {
        ToDo todo = new ToDo("brush teeth", false);
        assertEquals("T | 0 | brush teeth", todo.toDataString());
        todo.markAsDone();
        assertEquals("T | 1 | brush teeth", todo.toDataString());
    }
}
