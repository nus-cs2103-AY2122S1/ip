package nyx;

import static org.junit.jupiter.api.Assertions.assertEquals;

import nyx.task.ToDo;
import org.junit.jupiter.api.Test;

public class ToDoTest {
    @Test
    public void testStringConversion_done() {
        ToDo toDo = new ToDo("return book", true);
        assertEquals("[T][X] return book", toDo.toString());
    }

    @Test
    public void testStringConversion_notDone() {
        ToDo toDo = new ToDo("return book");
        assertEquals("[T][ ] return book", toDo.toString());
    }

    @Test
    public void testDataFormatConversion_notDone() {
        ToDo toDo = new ToDo("return book");
        assertEquals("T, 0, return book\n", toDo.formatData());
    }

    @Test
    public void testDataFormatConversion_done() {
        ToDo toDo = new ToDo("return book", true);
        assertEquals("T, 1, return book\n", toDo.formatData());
    }

    @Test
    public void testSetDone() {
        ToDo toDo = new ToDo("return book");
        toDo.setDone();
        assertEquals("[T][X] return book", toDo.toString());
    }
}
