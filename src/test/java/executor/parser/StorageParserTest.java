package executor.parser;

import model.task.Deadline;
import model.task.Event;
import model.task.Task;
import model.task.ToDo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StorageParserTest {
    private final StorageParser parser = new StorageParser();

    @Test
    public void testDeadline() {
        Task original = new Deadline("eat breakfast", "2021-01-31");
        Task afterParse = parser.parse(original.toString());
        assertEquals(original.toString(), afterParse.toString());
    }

    @Test
    public void testTodo() {
        Task original = new ToDo("eat breakfast");
        Task afterParse = parser.parse(original.toString());
        assertEquals(original.toString(), afterParse.toString());
    }

    @Test
    public void testEvent() {
        Task original = new Event("eat breakfast", "2021-01-31");
        System.out.println(original.toString());
        Task afterParse = parser.parse(original.toString());
        assertEquals(original.toString(), afterParse.toString());
    }
}
