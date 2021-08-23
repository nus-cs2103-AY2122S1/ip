import duke.task.Todo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {

    @Test
    public void todoTest() {
        Todo test = new Todo("test string");
        assertEquals(test.toString(), "[T][ ] test string");
    }

    @Test
    public void todoMarkDoneTest() {
        Todo test = new Todo("test string");
        test.markDone();
        assertEquals(test.toString(), "[T][X] test string");
    }
}
