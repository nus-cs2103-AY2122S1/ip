package poseidon.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

/**
 * Represents a testing class for {@code Todo}.
 *
 * @author Yeluri Ketan
 * @version CS2103T AY21/22 Sem 1 iP
 */
public class TodoTest {

    @Test
    public void todoConstructor_newTodo_correctDescAndStatus() {
        String description = "Finish all tasks";
        Todo todo = new Todo(description);
        assertEquals(description, todo.description);
        assertFalse(todo.isDone);
    }

    @Test
    public void todoConstructorWithStatus_undoneTodo_correctDoneStatus() {
        String description = "Finish all tasks";
        Todo todo = new Todo(description, false);
        assertFalse(todo.isDone);
    }

    @Test
    public void todoConstructorWithStatus_doneTodo_correctDoneStatus() {
        String description = "Finish all tasks";
        Todo todo = new Todo(description, true);
        assertTrue(todo.isDone);
    }

    @Test
    public void setDone_todoMarkedDone_correctDoneStatus() {
        String description = "Finish all tasks";
        Todo todo = new Todo(description);
        todo.setDone();
        assertTrue(todo.isDone);
    }

    @Test
    public void getDateTime_todoObject_correctDateTime() {
        String description = "Finish all tasks";
        LocalDateTime todoTime = LocalDateTime.MAX;
        Todo todo1 = new Todo(description);
        assertEquals(todoTime, todo1.getDateTime());

        Todo todo2 = new Todo(description);
        assertEquals(todoTime, todo2.getDateTime());

        Todo todo3 = new Todo(description);
        assertEquals(todoTime, todo3.getDateTime());
    }

    @Test
    public void toString_todoObject_stringRep() {
        String description = "Finish all tasks";
        Todo todo = new Todo(description);
        String expectedToString = "[T][ ] Finish all tasks";
        assertEquals(expectedToString, todo.toString());
    }

    @Test
    public void toStorage_todoObject_storageRep() {
        String description = "Finish all tasks";
        Todo todo = new Todo(description);
        String expectedToStorageString = "T%false%Finish all tasks\n";
        assertEquals(expectedToStorageString, todo.toStorage());
    }
}
