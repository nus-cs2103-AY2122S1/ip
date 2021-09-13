package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ToDoTest {
    @Test
    public void toStringTest() {
        assertEquals("[T] [ ] run around", new ToDo("run around").toString());
    }
}
