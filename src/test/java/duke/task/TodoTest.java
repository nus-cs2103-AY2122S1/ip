package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class TodoTest {

    @Test
    void testToString() {
        Todo todo = new Todo("Read a book");
        String actual = todo.toString();

        assertEquals("[T][ ] Read a book", actual);
    }

    @Test
    void toSavedFormat() {
        Todo todo = new Todo("Read a book");
        String actual = todo.toSavedFormat();

        assertEquals("Read a book", actual);
    }
}
