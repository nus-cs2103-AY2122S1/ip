package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ToDoTest {

    @Test
    public void testStringConversion() {
        assertEquals("[T][ ] example", new ToDo("example").toString());
        assertEquals("[T][ ] example2", new ToDo("example2").toString());
    }
}
