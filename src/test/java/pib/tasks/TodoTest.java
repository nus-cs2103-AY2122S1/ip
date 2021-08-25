package pib.tasks;

import org.junit.jupiter.api.Test;
import pib.pibexception.PibException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


public class TodoTest {
    @Test
    public void createTodo_nonBlankDescription_success() throws PibException {
        Todo todo = Todo.createTodo("Study");
        assertEquals("Study", todo.getDescription());
        assertEquals(0, todo.getIsDone());
    }

    @Test
    public void createTodo_blankDescription_exceptionThrown() {
        try {
            Todo todo = Todo.createTodo(" ");
            fail();
        } catch (PibException e) {
            assertEquals("empty-task-description", e.getMessage());
        }
    }

    @Test
    public void createTodo_nonBlankDescriptionWithIsDone_success() throws PibException {
        Todo todo = Todo.createTodo("Study", 1);
        assertEquals("Study", todo.getDescription());
        assertEquals(1, todo.getIsDone());
    }

    @Test
    public void toString_todoObjectNoIsDone_success() throws PibException {
        Todo todo = Todo.createTodo("Study");
        assertEquals("[T][ ] Study", todo.toString());
    }

    @Test
    public void toString_todoObjectWithIsDone_success() {
        Todo todo = Todo.createTodo("Study", 1);
        assertEquals("[T][X] Study", todo.toString());
    }
}
