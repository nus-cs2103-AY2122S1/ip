package kayu.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TodoTest {

    private Todo todo;

    @BeforeEach
    public void setUp() {
        String desc = "mock tests";
        todo = new Todo(desc);
    }

    @Test
    public void testEncodingConversion() {
        assertEquals("T # 0 # mock tests", todo.toEncodedString());
    }

    @Test
    public void testStringConversion() {
        assertEquals("[T][ ] mock tests", todo.toString());
    }
}
