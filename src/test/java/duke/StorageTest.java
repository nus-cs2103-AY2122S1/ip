package duke;

import org.junit.jupiter.api.Test;
import task.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StorageTest {

    Todo task1 = new Todo("test1", false);
    Deadline task2 = new Deadline("test2", "10pm", true);
    Event task3 = new Event("test3", "Aug 25 2021", false);

    @Test
    public void testLoadingExistingFile() throws IOException {
        Tasklist expectedList = new Tasklist(new ArrayList<>());
        expectedList.add(task1);
        expectedList.add(task2);
        expectedList.add(task3);

        String filepath = "../../text-ui-test/testTaskCase1.txt";
        Storage storage = new Storage(filepath);
        Tasklist tasklist = storage.load();

        assertTrue(tasklist.equals(expectedList));
    }
}
