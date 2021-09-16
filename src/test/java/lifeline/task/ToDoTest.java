package lifeline.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ToDoTest {
    @Test
    public void testNewToDoIsDoneIsFalse() {
        ToDo todo = new ToDo("read book");
        assertFalse(todo.isDone());
    }

    @Test
    public void testToDoGetName() {
        ToDo todo = new ToDo("read book");
        assertEquals("read book", todo.getName());
    }

    @Test
    public void testToDoToString() {
        ToDo todo = new ToDo("read book");
        assertEquals("[T][ ] read book", todo.toString());
    }

    @Test
    public void testToDoToggleIsDone() {
        ToDo todo = new ToDo("read book");
        assertFalse(todo.isDone());
        todo.setDone(true);
        assertTrue(todo.isDone());
    }
}
