package duke;

import duke.data.task.ToDo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    @Test
    public void testConvertToDoToData() {
        String commandDesc = "NEW TODO";
        ToDo task = new ToDo(commandDesc);
        String expected = "T| |NEW TODO";
        assertEquals(expected, task.toData());
    }

    @Test
    public void testConvertToDoToString() {
        String commandDesc = "NEW TODO";
        ToDo task = new ToDo(commandDesc);
        String expected = "[T][ ] NEW TODO";
        assertEquals(expected, task.toString());
    }
}
