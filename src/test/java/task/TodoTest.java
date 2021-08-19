package task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TodoTest {

    @Test
    void formatTask() {
        Todo todo = new Todo("Read book");
        String[] expected = new String[] {"T", "1", "Read book"};
        assertArrayEquals(expected, todo.formatTask());
    }

    @Test
    void markAsDone() {
        Todo todo = new Todo("Read book");
        Todo doneTodo = todo.markAsDone();
        Todo expected = new Todo("Read book", true);
        assertEquals(expected.toString(), doneTodo.toString());
    }

    @Test
    void testToString() {
        Todo todo = new Todo("Read book");
        String expected = "[T][ ] Read book";
        assertEquals(expected, todo.toString());
    }
}
