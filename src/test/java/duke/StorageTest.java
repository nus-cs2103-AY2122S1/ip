package duke;

import org.junit.jupiter.api.Test;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StorageTest {
    @Test
    public void addTaskTest() throws DukeException, ParseException {
        Storage storage = new Storage("./data/duke.txt");
        storage.convertToTask("E | | Concert | Jan 9 2020");
        
        assertEquals("E | | Concert | Jan 9 2020", storage.taskArr.get(0).toString());
    }
}
