package duke.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDosTest {
    ToDos todo = new ToDos("buy milk");

    @Test
    public void toStringTest() {
        assertEquals("[T] [ ] buy milk", todo.toString());
    }

    @Test
    public void toDataFileStringTest() {
        assertEquals("T|0|buy milk", todo.toDataFileString());
    }
}
