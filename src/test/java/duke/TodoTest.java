package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TodoTest {

    private Todo todo;

    @Test
    public void dummyTest() {
        assertEquals(2, 2);
    }

    @Test
    public void createTodo() {
        todo = new Todo("Description");
    }

    @Test
    public void toStringTest() {
        todo = new Todo("Description");
        assertEquals("[T][ ] Description", todo.toString());
    }
}
