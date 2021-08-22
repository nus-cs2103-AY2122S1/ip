import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TodoTest {

    @Test
    void testToString() {
        Todo testTodo = new Todo("eat food");
        assertEquals("[T][ ]" + testTodo.getTitle(), testTodo.toString());
    }
}