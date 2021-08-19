package task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class TaskTest {

    @Test
    void isDone() {
        Task todo = new Todo("Read book");
        assertFalse(todo.isDone());
    }

    @Test
    void getName() {
        Task todo = new Todo("Read book");
        assertEquals("Read book", todo.getName());
    }
}
