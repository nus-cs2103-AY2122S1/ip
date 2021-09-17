package bobcat.executor.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import bobcat.model.task.Deadline;
import bobcat.model.task.Event;
import bobcat.model.task.Task;
import bobcat.model.task.ToDo;


public class StorageParserTest {
    private final bobcat.executor.parser.StorageParser parser = new StorageParser();

    @Test
    public void deadlineStorageParser() {
        Task original = new Deadline("eat breakfast", "2021-01-31");
        Task afterParse = parser.parse(original.toString());
        assertEquals(original.toString(), afterParse.toString());
    }

    @Test
    public void todoStorageParser() {
        Task original = new ToDo("eat breakfast");
        Task afterParse = parser.parse(original.toString());
        assertEquals(original.toString(), afterParse.toString());
    }

    @Test
    public void eventStorageParser() {
        Task original = new Event("eat breakfast", "2021-01-31");
        Task afterParse = parser.parse(original.toString());
        assertEquals(original.toString(), afterParse.toString());
    }
}
