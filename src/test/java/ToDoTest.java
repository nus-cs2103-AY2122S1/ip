import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import iris.task.ToDo;

public class ToDoTest {

    @Test
    public void toDoGetStatus() {
        ToDo toDo = new ToDo("read book", false);
        assertEquals("[T][ ] read book", toDo.getStatus());
        toDo.markDone();
        assertEquals("[T][X] read book", toDo.getStatus());
    }

    @Test
    public void toDoToString() {
        ToDo toDo = new ToDo("read book", false);
        assertEquals("T | 0 | read book", toDo.toString());
        toDo.markDone();
        assertEquals("T | 1 | read book", toDo.toString());
    }
}
