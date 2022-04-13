package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.data.task.ToDo;

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
