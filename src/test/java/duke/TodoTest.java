package duke;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;



class TodoTest {

    @Test
    void testToString() {
        Todo testTodo = new Todo("eat food");
        assertEquals("[T][ ]" + testTodo.getTitle(), testTodo.toString());
    }
}
