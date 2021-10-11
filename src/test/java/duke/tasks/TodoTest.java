package duke.tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void toString_mockTodo_success() {
        Task todo = new Todo("buy coffee");
        assertEquals("[T][ ] buy coffee", todo.toString());
    }
}