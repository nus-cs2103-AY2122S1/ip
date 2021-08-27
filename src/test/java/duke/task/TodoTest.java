package duke.task;

import duke.commands.Task;
import duke.commands.Todo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void statusIconTest() {
        Task todo = new Todo("do homework", true);
        assertEquals("[X]", todo.getStatusIcon());
    }

    @Test
    public void toStringTest() {
        Task todo = new Todo("do homework", true);
        assertEquals("[T][X] do homework", todo.toString());
    }

    @Test
    public void saveFormatTest() {
       Task todo = new Todo("do homework", true);
       assertEquals("T | 1 | do homework", todo.printFormat());
    }
}
