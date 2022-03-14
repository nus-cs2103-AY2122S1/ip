package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void todoInstanceCreation_createGeneralInstance_success() {
        Todo todo = new Todo("test sentence", false);
        assertEquals(todo.toString(), "[T][ ] test sentence");
    }
}
