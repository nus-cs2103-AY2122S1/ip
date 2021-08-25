package duke.task;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class TodoTest {

    @Test
    void formatTask_formattedCorrectly() {
        Todo todo = new Todo("Read book");
        String[] expected = new String[] {"T", "1", "Read book"};
        assertArrayEquals(expected, todo.formatTask());
    }

    @Test
    void markAsDone_markedCorrectly() {
        Todo todo = new Todo("Read book");
        Todo doneTodo = todo.markAsDone();
        assertTrue(doneTodo.isDone());
    }

    @Test
    void toString_convertedCorrectly() {
        Todo todo = new Todo("Read book");
        String expected = "[T][ ] Read book";
        assertEquals(expected, todo.toString());
    }
}
