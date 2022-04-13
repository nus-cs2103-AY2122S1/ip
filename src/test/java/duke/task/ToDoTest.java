package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ToDoTest {
    @Test
    public void createTest() {
        ToDo t = new ToDo("test");
        assertEquals("[T][ ] test", t.toString());
    }
}
