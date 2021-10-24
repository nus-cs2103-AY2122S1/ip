package duke;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.ParseException;

import org.junit.jupiter.api.Test;


public class StorageTest {
    @Test
    public void addTaskTest() throws DukeException, ParseException {
        Storage storage = new Storage("./data/duke.txt");
        storage.convertToTask("E | | Concert | Jan 9 2020");

        assertEquals("E | | Concert | Jan 9 2020", storage.returnFirstTask());
    }
}
