package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    @Test
    public void createTest() {
        ToDo t = new ToDo("test");
        assertEquals("[T][ ] test", t.toString());
    }

}