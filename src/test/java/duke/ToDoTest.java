package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class ToDoTest {
    @Test
    public void toStringTest() {
        assertEquals("[T][ ] sample todo", new ToDo("sample todo").toString());
    }

    @Test
    public void getFileStringTest() {
        assertEquals("T | 0 | sample todo", new ToDo("sample todo").getFileString());
    }
}