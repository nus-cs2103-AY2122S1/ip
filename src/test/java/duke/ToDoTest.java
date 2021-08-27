package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ToDoTest {
    /**
     * Test the ToDo constructor and toString methods.
     */
    @Test
    public void toStringTest() {
        assertEquals("[T][ ] sample todo", new ToDo("sample todo").toString());
    }

    /**
     * Test the ToDo constructor with the isDone boolean and getFileString methods.
     */
    @Test
    public void getFileStringTest() {
        assertEquals("T | 1 | sample todo", new ToDo("sample todo", true).getFileString());
    }
}
