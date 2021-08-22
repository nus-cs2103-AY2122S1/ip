package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    @Test
    public void toDoTest() {
        ToDo event = new ToDo("Sleep");
        assertEquals(event.toString(),"[T][ ] Sleep");
    }
}