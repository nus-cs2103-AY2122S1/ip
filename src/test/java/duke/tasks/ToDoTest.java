package duke.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ToDoTest {
    @Test
    public void testStringConversion() {
        Task toDo = new ToDo("return book");
        assertEquals("[T][ ] return book", toDo.toString());
    }

    @Test
    public void testFormatConversion() {
        Task toDo = new ToDo("return book");
        assertEquals("T, 0, return book", toDo.format());
    }

    @Test
    public void testMarkAsDone() {
        Task toDo = new ToDo("return book");
        assertEquals(" ", toDo.getStatusIcon());
        toDo.markAsDone();
        assertEquals("X", toDo.getStatusIcon());
    }
}
