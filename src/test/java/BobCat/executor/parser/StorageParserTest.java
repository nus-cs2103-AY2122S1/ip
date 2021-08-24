package BobCat.executor.parser;

import BobCat.model.task.Deadline;
import BobCat.model.task.Event;
import BobCat.model.task.Task;
import BobCat.model.task.ToDo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StorageParserTest {
    private final StorageParser parser = new StorageParser();

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
